package nativ;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;

import com.google.common.collect.Lists;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// FIXME
class Main {
}

class MyMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

    private final Text key = new Text();
    private final Text val = new Text();

    private static final String date = "Mar/2004:";

    public void map(LongWritable lineCount, Text line, Context context) throws IOException, InterruptedException {
        var lineStr = line.toString();
        var index = lineStr.indexOf(date) + date.length();

        try {
            var hour = Integer.parseInt(lineStr.substring(index, index + 2));
            var writable = new IntWritable(hour);
            context.write(writable, line);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class MyReducer extends Reducer<IntWritable, Text, Text, IntWritable> {
    private static final String date = "Mar/2004:";

    private static String reduced(String input) {
        var index = input.indexOf(date);
        return input.substring(index + date.length());
    }

    public void reduce(IntWritable key, Iterable<Text> values, Context context) {
        var mapped = Lists.newArrayList(values).stream().map(Text::toString).reduce(
            new HashMap<String, ArrayList<String>>(), (map,value) ->  {
                var re = MyReducer::reduced(value);
                map.
            }
        );

    }
}

public class App {

    // main throws Exception because I'm a lazy person
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        // job.setCombinerClass(IntSumReducer.class);
        // job.setReducerClass(IntSumReducer.class);
        // job.setOutputKeyClass(Text.class);
        // job.setOutputValueClass(IntWritable.class);
        // FileInputFormat.addInputPath(job, new Path(args[0]));
        // FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}