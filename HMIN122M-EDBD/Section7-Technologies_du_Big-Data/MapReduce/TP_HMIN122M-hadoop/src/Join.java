
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.DoubleWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Join {
	private static final String INPUT_PATH = "input-join/";
	private static final String OUTPUT_PATH = "output/join-";
	private static final Logger LOG = Logger.getLogger(Join.class.getName());

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
	
//	La jointure doit être réalisée sur l'attribut custkey. 
//	Voici le schéma des relations dont les lignes sont extraites :

//	ORDERS(orderkey,custkey,orderstatuts,totalprice,orderdate,orderpriority,clerk,ship-priority,comment) // 9 colonnes
//	CUSTOMERS(custkey,name,address,nationkey,phone,acctbal,mktsegment,comment) // 8 colonnes

//	Le programme doit restituer des couples (CUSTOMERS.name,ORDERS.comment)

//	Pour réaliser la jointure il faut à l'avance recopier dans un tableau temporaire 
//	les valeurs de l'itérateur values dans la méthode REDUCE, 
//	puis effectuer le parcours avec deux 'for' imbriqués sur ce tableau temporaire
	
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
				String comment = words[8];
//				LOG.info("O = " + custkey + " " + comment);
				context.write(new Text(custkey), new Text("O" + comment));
			} else {
				// CUSTOMERS
				String custkey = words[0];
				String name = words[1];
//				LOG.info("C = " + custkey + " " + name);
				context.write(new Text(custkey), new Text("C" + name));
			}
		}
	}

	public static class Reduce extends Reducer<Text, Text, Text, Text> {

		@Override
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			//ArayList <String> lst=new ArrayList <String>();  
			ArrayList<String> values_copy = new ArrayList<String>();
			for (Text val : values) {
				values_copy.add(val.toString());
			}
			//LOG.info(values_copy.toString());
			
//			Iterable<Text> tab = values;
//			LOG.info(.toString());
			
			for (String a : values_copy) {
				LOG.info("a = " + a.charAt(0));
				for (String b : values_copy) {
					LOG.info("b = " + b.charAt(0));
					if (a.charAt(0) == 'O' && b.charAt(0) == 'C' ) {
						
						String result = a.toString() + " " + b.toString();
						//LOG.info(result);
						LOG.info("boucle if");
						context.write(key, new Text(result));
					}
					//System.out.println( value );
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