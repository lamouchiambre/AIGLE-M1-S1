
import java.io.IOException;
import java.time.Instant;
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

public class GroupBy {
	private static final String INPUT_PATH = "input-groupBy/";
	private static final String OUTPUT_PATH = "output/groupBy-";
	private static final Logger LOG = Logger.getLogger(GroupBy.class.getName());
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

	public static class Map extends Mapper<LongWritable, Text, Text, DoubleWritable> {
		private final static String emptyWords[] = { "" };
		
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString(); //pour chaque ligne appel a map
			String[] words = line.split(",");  // tableau de mots
			
			compt++;
			
			if (Arrays.equals(words, emptyWords))
				return;

			if (compt != 1) {
				/* group by pour customerID */
				//String customerID = words[5];
				//DoubleWritable profit = new DoubleWritable(Double.parseDouble(words[20]));
				//context.write(new Text(customerID), profit);
				
				// 1) GROUP BY pour date et state (VALIDE)
				
//				String state = words[10];
//				String date = words[2];
//				DoubleWritable profit = new DoubleWritable(Double.parseDouble(words[20]));
//				
//				context.write(new Text(date + " " + state), profit);
				
				// 2) GROUP BY pour Date et Category (VALIDE) 
				
//				String date = words[2];
//				String category = words[14];
//				DoubleWritable profit = new DoubleWritable(Double.parseDouble(words[20]));
//				
//				context.write(new Text(date + " " + category), profit);
			
				// 3) Le nombre de produits différents (distincts) achetés. (VALIDE)
				
//				String IDcommand = words[2];
//				String IDproduit = words[13];
//				DoubleWritable one = new DoubleWritable(1);
//				context.write(new Text(IDcommand + " " + IDproduit), one);
				
				// 4) Le nombre total d'exemplaires. (VALIDE)
				
//				String IDcommand = words[2];
//				String IDproduit = words[13];
//				DoubleWritable quantite = new DoubleWritable(Double.parseDouble(words[18]));
//				context.write(new Text(IDcommand + " " +IDproduit), quantite);
			}
		}
	}

	public static class Reduce extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

		@Override
		public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
				throws IOException, InterruptedException {
			double sum = 0;

			for (DoubleWritable val : values) {
				sum += val.get();
//				if (key.equals(new Text("CG-12520"))) {
//					LOG.info("dedans");
//					LOG.info(val.toString());
//					LOG.info("");
//				}
			}
			
			context.write(key, new DoubleWritable(sum));
		}
	}

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		conf.set("fs.file.impl", "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");
		
		Job job = new Job(conf, "GroupBy");

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setOutputValueClass(DoubleWritable.class); 

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH + Instant.now().getEpochSecond()));

		job.waitForCompletion(true);
	}
}