
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.lang.*;


// =========================================================================
// COMPARATEURS
// =========================================================================

/**
 * Comparateur qui inverse la méthode de comparaison d'un sous-type T de WritableComparable (ie. une clé).
 */
@SuppressWarnings("rawtypes")
class InverseComparator<T extends WritableComparable> extends WritableComparator {
	private static final Logger LOG = Logger.getLogger(TriAvecComparaison.class.getName());

	public InverseComparator(Class<T> parameterClass) {
		super(parameterClass, true);
	}

	/**
	 * Cette fonction définit l'ordre de comparaison entire 2 objets de type T.
	 * Dans notre cas nous voulons simplement inverser la valeur de retour de la méthode T.compareTo.
	 * 
	 * @return 0 si a = b <br>
	 *         x > 0 si a > b <br>
	 *         x < 0 sinon
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
//		LOG.info("a: " + a.toString());
//		LOG.info("b: " + b.toString());
		// On inverse le retour de la méthode de comparaison du type
		String[] ac = a.toString().split("/");
		//date = val.toString().split("/");
		int a_day = Integer.parseInt(ac[2]);
		int a_month = Integer.parseInt(ac[1]);
		int a_year = Integer.parseInt(ac[0]);
		
		String[] bc = b.toString().split("/");
		int b_day = Integer.parseInt(bc[2]);
		int b_month = Integer.parseInt(bc[1]);
		int b_year = Integer.parseInt(bc[0]);
		
		if (a_year != b_year) { // a_year > b_year
			if (a_year - b_year < 0) {
				LOG.info(a.toString() + " < " + b.toString());
			} else if (a_year - b_year > 0) {
				LOG.info(a.toString() + " > " + b.toString());
			} else {
				LOG.info(a.toString() + " == " + b.toString());
			}
			return a_year - b_year;
		} else if (a_month != b_month) {
			if (a_month - b_month < 0) {
				LOG.info(a.toString() + " < " + b.toString());
			} else if (a_month - b_month > 0) {
				LOG.info(a.toString() + " > " + b.toString());
			} else {
				LOG.info(a.toString() + " == " + b.toString());
			}
			return a_month - b_month;
		} else {
			if (a_day - b_day < 0) {
				LOG.info(a.toString() + " < " + b.toString());
			} else if (a_day - b_day > 0) {
				LOG.info(a.toString() + " > " + b.toString());
			} else {
				LOG.info(a.toString() + " == " + b.toString());
			}
			return a_day - b_day;
		}
	}
//	
//	@SuppressWarnings("unchecked")
////	@Override
//	public int compareInv(WritableComparable a, WritableComparable b) {
//		
////		LOG.info("a: " + a.toString());
////		LOG.info("b: " + b.toString());
//		// On inverse le retour de la méthode de comparaison du type
//		String[] ac = a.toString().split("/");
//		//date = val.toString().split("/");
//		int a_day = Integer.parseInt(ac[2]);
//		int a_month = Integer.parseInt(ac[1]);
//		int a_year = Integer.parseInt(ac[0]);
//		
//		String[] bc = b.toString().split("/");
//		int b_day = Integer.parseInt(bc[2]);
//		int b_month = Integer.parseInt(bc[1]);
//		int b_year = Integer.parseInt(bc[0]);
//		
//		if (a_year != b_year) { // a_year > b_year
//			if (a_year - b_year < 0) {
//				LOG.info(a.toString() + " < " + b.toString());
//			} else if (a_year - b_year > 0) {
//				LOG.info(a.toString() + " > " + b.toString());
//			} else {
//				LOG.info(a.toString() + " == " + b.toString());
//			}
//			return b_year - a_year;
//		} else if (a_month != b_month) {
//			if (a_month - b_month < 0) {
//				LOG.info(a.toString() + " < " + b.toString());
//			} else if (a_month - b_month > 0) {
//				LOG.info(a.toString() + " > " + b.toString());
//			} else {
//				LOG.info(a.toString() + " == " + b.toString());
//			}
//			return b_month - a_month;
//		} else {
//			if (a_day - b_day < 0) {
//				LOG.info(a.toString() + " < " + b.toString());
//			} else if (a_day - b_day > 0) {
//				LOG.info(a.toString() + " > " + b.toString());
//			} else {
//				LOG.info(a.toString() + " == " + b.toString());
//			}
//			return b_day - a_day;
//		}
//	}
}

/**
 * Inverseur de la comparaison du type Text.
 */
class TextInverseComparator extends InverseComparator<Text> {

	public TextInverseComparator() {
		super(Text.class);
	}
	
}


// =========================================================================
// CLASSE MAIN
// =========================================================================

public class TriAvecComparaison {
	private static final String INPUT_PATH = "input-groupBy/";
	private static final String OUTPUT_PATH = "output/9-TriAvecComparaison-";
	private static final Logger LOG = Logger.getLogger(TriAvecComparaison.class.getName());
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


	// =========================================================================
	// MAPPER
	// =========================================================================

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
				String[] date = words[3].split("/"); // MM/JJ/ANNEE
				String day = date[1];
				String month = date[0];
				String year = date[2];
				context.write(new Text(year+"/"+month+"/"+day), new Text());
//				LOG.info("y:" + year + "/" + "m:" + month + "/" + "d:" + day);
			}
		}
	}

	// =========================================================================
	// REDUCER
	// =========================================================================

	public static class Reduce extends Reducer<Text, IntWritable, Text, Text> {

		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			// 1 10 11 12 2 3 4 5 6 7 8 9
//			String[] date;
//			Text dateT = new Text();
//			for (Text val : values) {
//				dateT = val;
				//date = val.toString().split("/");
				//String day = date[1];
				//String month = date[0];
				//String year = date[2];
				
//			}
			context.write(key, new Text());
			
			
		}
	}

	// =========================================================================
	// MAIN
	// =========================================================================

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.file.impl", "com.conga.services.hadoop.patch.HADOOP_7682.WinLocalFileSystem");

		Job job = new Job(conf, "9-Sort");

		/*
		 * Affectation de la classe du comparateur au job.
		 * Celui-ci sera appelé durant la phase de shuffle.
		 */
//		job.setSortComparatorClass(TextInverseComparator.class);
		job.setSortComparatorClass(TextInverseComparator.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(Map.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH + Instant.now().getEpochSecond()));

		job.waitForCompletion(true);
	}
}