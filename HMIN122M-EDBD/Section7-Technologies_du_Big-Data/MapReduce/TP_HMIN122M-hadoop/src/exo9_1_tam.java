
import java.io.IOException;
import java.time.Instant;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class exo9_1_tam {
	private static final String INPUT_PATH = "input-tam/";
	private static final String OUTPUT_PATH = "output/exo9-";
	private static final Logger LOG = Logger.getLogger(exo9_1_tam.class.getName());
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
	
	// Donner un aper�u des trams et bus de la station OCCITANIE. 
	// Plus pr�cis�ment, donner le nombre de (bus ou trams) pour chaque heure et ligne. 
	// Exemple : <Ligne 1, 17h, 30> (lire : � 17h, passent 30 tram de la ligne 1) 
	
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private final static String emptyWords[] = { "" };
		
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			compt++;
			if (compt != 1) {
				String line = value.toString(); // pour chaque ligne appel a map
				String[] words = line.split(";"); // tableau de mots
			
				String time = words[7].substring(0, 2); // l'heure
				String ligne = words[4]; // num�ro de la ligne
				
				if (Arrays.equals(words, emptyWords))
					return;
				
				//LOG.info(words[3].getClass().getName());
				if (words[3].equals("OCCITANIE")) {
//					LOG.info(words[3]);
					context.write(new Text(ligne + " " + time), one);	
				}
			}
		}
	}

	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			
			int sum = 0;
				
			for (IntWritable val : values) {
//				LOG.info("test");
				sum += val.get();
			}
				
			context.write(new Text("ligne " + key), new IntWritable(sum));
		}
	}

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		conf.set("fs.file.impl", "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");
		
		Job job = new Job(conf, "Join");

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setOutputValueClass(IntWritable.class); 

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH + Instant.now().getEpochSecond()));

		job.waitForCompletion(true);
	}
}