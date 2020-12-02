
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
//import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.WritableComparable;
//import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/* Trier les clients (identifiant+nom) par profit généré
 * 
 * Cette question demande de 1) enchaîner deux jobs map-reduce jobA et jobB dans la méthode main et 
 * 2) définir les fonctions mapJobA, reduceJobA, mapJobB, reduceJobB. L'entrée du deuxième job correspond à la sortie du premier job, qui est enregistrée dans un repertoire de sortie. 
 * Il faudra donc faire attention à bien indiquer où le deuxième job peut récupérer les données. 
 * 
 * Job A : Faire la somme des profits généra par chaque client
 * Job B : Trier par le profit généré
 */

// Main class
public class Exo10_2_Tri {
	private static final String INPUT_PATH = "input-groupBy/";
	private static final String OUTPUT_PATH = "output-10-2-Tri-JobA/";
//	private static final String INPUT_PATH_B = "output-10-2-Tri-JobA/";
	private static final String OUTPUT_PATH_B = "output/10-2-Tri-JobB/";
	private static final Logger LOG = Logger.getLogger(Exo10_2_Tri.class.getName());
	private static int compt = 0;

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

	// Mapper A
	public static class Map extends Mapper<LongWritable, Text, Text, Text> {
		private final static String emptyWords[] = { "" };

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString(); //pour chaque ligne appel a map
			String[] words = line.split(",");  // tableau de mots
			compt++;
			
			if (Arrays.equals(words, emptyWords))
				return;

			if (compt != 1) {
				String id = words[5];
				String name = words[6];
				String profit = words[20];
				context.write(new Text(id + ";" + name), new Text(";" + profit));
			}
		}
	}

	// Reducer A
	public static class Reduce extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			double sum = 0;	
			for (Text val : values) {
				val = new Text(val.toString().substring(1));
				sum += Double.parseDouble(val.toString());
			}
			context.write(key, new Text(";" + String.valueOf(sum)));			
		}
	}
	
	// Mapper B
	public static class MapB extends Mapper<LongWritable, Text, Text, Text> {
		private final static String emptyWords[] = { "" };

		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString(); //pour chaque ligne appel a map
			String[] words = line.split(";");  // tableau de mots
			compt++;
			
			if (Arrays.equals(words, emptyWords))
				return;

			if (compt != 1) {
				String id = words[0];
				String name = words[1];
				String profit = words[2];
				context.write(new Text(profit), new Text(id + " " + name));
			}
		}
	}
	
	// Reducer B
	public static class ReduceB extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			for (Text val : values) {
				context.write(key, val);	
			}
		}
	}

	// Main
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.file.impl", "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");

		// Job A
		Job job = new Job(conf, "A");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setOutputValueClass(Text.class); 
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		Path output_jobA = new Path(OUTPUT_PATH + Instant.now().getEpochSecond());
		FileOutputFormat.setOutputPath(job, output_jobA);

		job.waitForCompletion(true);
		
		// Job B
//		Job jobB = new Job(conf, "B");
//		
//		jobB.setOutputKeyClass(Text.class);
//		jobB.setOutputValueClass(Text.class);
//
//		jobB.setMapperClass(Map.class);
//		jobB.setReducerClass(Reduce.class);
//
//		jobB.setOutputValueClass(Text.class); 
//		
//		jobB.setInputFormatClass(TextInputFormat.class);
//		jobB.setOutputFormatClass(TextOutputFormat.class);
//
//		FileInputFormat.addInputPath(jobB, new Path(OUTPUT_PATH_B));
//		FileOutputFormat.setOutputPath(jobB, new Path(OUTPUT_PATH_B + Instant.now().getEpochSecond()));
//
//		jobB.waitForCompletion(true);
	}
}