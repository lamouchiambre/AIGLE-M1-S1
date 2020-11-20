
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
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

public class exo6_GroupeBy_Join {
	private static final String INPUT_PATH = "input-join/";
	private static final String OUTPUT_PATH = "output/join-";
	private static final Logger LOG = Logger.getLogger(exo6_GroupeBy_Join.class.getName());

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
	
//  -- Indices du Moodle --
	
//	Modifiez le programme de l'exercice 5 afin de calculer 
//  le montant total des achats faits par chaque client.
//  Le programme doit restituer des couples (CUSTOMERS.name, SUM(totalprice))
	
	public static class Map extends Mapper<LongWritable, Text, Text, Text> {
		private final static String emptyWords[] = { "" };
		
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString(); // pour chaque ligne appel a map
			String[] words = line.split("\\|"); // tableau de mots
			
			if (Arrays.equals(words, emptyWords))
				return;
			
			if (words.length == 9) {
				// ORDERS
				String custkey = words[1];
				String totalprice = words[3];
				LOG.info(custkey + " " + totalprice);
				context.write(new Text(custkey), new Text("O" + totalprice));
			} else {
				// CUSTOMERS
				String custkey = words[0];
				String name = words[1];
				LOG.info(custkey + " " + name);
				context.write(new Text(custkey), new Text("C" + name));
			}
		}
	}

	public static class Reduce extends Reducer<Text, Text, Text, Text> {

		@Override
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {

			ArrayList<String> values_copy = new ArrayList<String>();
			double sum = 0.0;
			String result = "";
			String name = "";

			
			for (Text val : values) {
				values_copy.add(val.toString());
			}
			
			for (String a : values_copy) {
				
				for (String b : values_copy) {
					
					if (a.charAt(0) == 'C' && b.charAt(0) == 'O' ) {
						a = a.substring(1);
						b = b.substring(1);
//						LOG.info(b);
						name = a;
//						LOG.info(b.substring(1));
//						double tmp = Double.parseDouble(b);
						sum += Double.parseDouble(b);
//						LOG.info(String.valueOf(tmp));
//						LOG.info(String.valueOf(sum));
						
						// Name + TotalPrice
						result = a.substring(1) + " " + Double.parseDouble(b.substring(1));
				        context.write(key, new Text(result));		
					}
					
				}	
							 
			}	
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

		job.setOutputValueClass(Text.class); 

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH + Instant.now().getEpochSecond()));

		job.waitForCompletion(true);
	}
}