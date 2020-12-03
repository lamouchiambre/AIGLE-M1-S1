
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.DoubleWritable;
//import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

// Job A
class Map12_2A extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private final static String emptyWords[] = { "" };
	private static int compt = 0;
		
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		compt++;
		if (compt != 1) {
			String line = value.toString(); // pour chaque ligne appel a map
			String[] words = line.split(";"); // tableau de mots
				
			String station = words[3]; // nom de la station
				
			if (Arrays.equals(words, emptyWords))
				return;
			
			int ligne = Integer.parseInt(words[4]);
			
			if (ligne >= 5) { //ligne > 4 pour les bus
				context.write(new Text(station), one);
			}
		}
	}
}

class Reduce12_2A extends Reducer<Text, IntWritable, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)	throws IOException, InterruptedException {
			
		int sum = 0;
				
		for (IntWritable val : values) {
			sum += val.get();
		}
				
		context.write(new Text(key + ","), new Text(String.valueOf(sum)));
	}
}
// Fin Job A
	
// Job B
class Map12_2B extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static String emptyWords[] = { "" };
	private static int compt = 0;

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		compt++;
		String line = value.toString();
		String[] words = line.split(",");

		if (Arrays.equals(words, emptyWords))
			return;
			
		if (compt != 1) {
			context.write(new Text(words[0]), new IntWritable(Integer.parseInt(words[1].substring(1))));
		}
	}
}

class Reduce12_2B extends Reducer<Text, IntWritable, Text, IntWritable> {
	private static final Logger LOG = Logger.getLogger(Exo12_2_TAM.class.getName());
	/**
	 * Map avec tri suivant l'ordre naturel de la clé (la clé représentant la fréquence d'un ou plusieurs mots).
	 * Utilisé pour conserver les k mots les plus fréquents.
	 * 
	 * Il associe une fréquence à une liste de mots.
	 */
	private TreeMap<Integer, List<Text>> sortedWords = new TreeMap<>();
	private int nbsortedWords = 0;
	private int k;

	/**
	 * Méthode appelée avant le début de la phase reduce.
	 */
	@Override
	public void setup(Context context) {
		// On charge k
		k = context.getConfiguration().getInt("k", 1);
	}

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;

		for (IntWritable val : values) {
			//LOG.info(val.toString());
			sum += val.get();
		}

		// On copie car l'objet key reste le même entre chaque appel du reducer
		Text keyCopy = new Text(key);

		// Fréquence déjà présente
		if (sortedWords.containsKey(sum)) // si la fréquence est déjà dans treeMap on ajoute les mots
			sortedWords.get(sum).add(keyCopy);
		else { // sinon on ajoute la fréquence et les mots
			List<Text> words = new ArrayList<>();

			words.add(keyCopy);
			sortedWords.put(sum, words); 
		}

		// Nombre de mots enregistrés atteint : on supprime le mot le moins fréquent (le premier dans sortedWords)
		if (nbsortedWords == k) {
			int firstKey = sortedWords.firstKey();
			List<Text> words = sortedWords.get(firstKey);
			words.remove(words.size() - 1);
			LOG.info("entrer "+ k);

			if (words.isEmpty())
				sortedWords.remove(firstKey);
		} else
			nbsortedWords++;
	}

	/**
	 * Méthode appelée à la fin de l'étape de reduce.
	 * 
	 * Ici on envoie les mots dans la sortie, triés par ordre descendant.
	 */
	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		Integer[] nbofs = sortedWords.keySet().toArray(new Integer[0]); // les fréquences

		// Parcours en sens inverse pour obtenir un ordre descendant
		int i = nbofs.length;

		while (i-- != 0) {
			int nbof = nbofs[i];
			
			for (Text words : sortedWords.get(nbof)) {
				//LOG.info("ecrit");
				context.write(words, new IntWritable(nbof));
			}
		}
	}
}
// Fin Job B
	
public class Exo12_2_TAM {
	private static final String INPUT_PATH_A = "input-tam/";
	private static final String OUTPUT_PATH_A = "output/exo12-2A-";
	private static final String OUTPUT_PATH_B = "output/exo12-2B-";
	private static final Logger LOG = Logger.getLogger(exo9_1_tam.class.getName());

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n%6$s");

		try {
			FileHandler fh = new FileHandler("out.log");
			fh.setFormatter(new SimpleFormatter());
			LOG.addHandler(fh);
		} catch (SecurityException | IOException e) {
			System.exit(1);
		}
	}
		
	public static void main(String[] args) throws Exception {
		// Borne 'k' du topk
		int k = 10;

		try {
			// Passage du k en argument ?
			if (args.length > 0) {
				k = Integer.parseInt(args[0]);

				// On contraint k à valoir au moins 1
				if (k <= 0) {
					LOG.warning("k must be at least 1, " + k + " given");
					k = 1;
				}
			}
		} catch (NumberFormatException e) {
			LOG.severe("Error for the k argument: " + e.getMessage());
			System.exit(1);
		}
		
		Configuration conf = new Configuration();
		conf.set("fs.file.impl", "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");
		
		conf.setInt("k", k);
		
		// Job A
		Job jobA = new Job(conf, "JobA");

		jobA.setOutputKeyClass(Text.class);
		jobA.setOutputValueClass(IntWritable.class);

		jobA.setMapperClass(Map12_2A.class);
		jobA.setReducerClass(Reduce12_2A.class);

		jobA.setInputFormatClass(TextInputFormat.class);
		jobA.setOutputFormatClass(TextOutputFormat.class);

		long instant = Instant.now().getEpochSecond();
		FileInputFormat.addInputPath(jobA, new Path(INPUT_PATH_A));
		FileOutputFormat.setOutputPath(jobA, new Path(OUTPUT_PATH_A + instant));

		jobA.waitForCompletion(true);
		
		// Job B
		Job jobB = new Job(conf, "JobB");

		jobB.setOutputKeyClass(Text.class);
		jobB.setOutputValueClass(IntWritable.class);

		jobB.setMapperClass(Map12_2B.class);
		jobB.setReducerClass(Reduce12_2B.class);

		jobB.setInputFormatClass(TextInputFormat.class);
		jobB.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(jobB, new Path(OUTPUT_PATH_A + instant));
		FileOutputFormat.setOutputPath(jobB, new Path(OUTPUT_PATH_B + instant));

		jobB.waitForCompletion(true);
	}
}