package edu.fiu.cis.acrl.virtuallabs.server.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a template file from disk and performs substitutions on keys based on a set of properties
 *
 */
public class TemplateReader {


	private static String readFile(File f) throws IOException {

		StringBuilder output = new StringBuilder();

		BufferedReader input = new BufferedReader(new FileReader(f));
		String line = null;

		while ((line = input.readLine()) != null) {

			output.append(line);
			output.append(System.getProperty("line.separator"));

		}

		input.close();

		return output.toString();

	}

	/**
	 * Process a template file, make the appropriate substitutions based on the context and return a String with the result
	 */
	public static String processTemplate(
			File templateFile, 
			Properties context) 
	throws IOException {

		// read file
		String contents = readFile(templateFile);

		String tagString = "\\{\\s*([^\\}]+)\\s*\\}";

		Pattern tagPattern = Pattern.compile(tagString);
		Matcher tagMatcher = tagPattern.matcher(contents);

		StringBuffer output = new StringBuffer();

		while (tagMatcher.find()) {

			String key = tagMatcher.group(1);
			tagMatcher.appendReplacement(output, context.getProperty(key,""));

		}

		tagMatcher.appendTail(output);

		return output.toString();

	}


	public static String scanProcessor() {

		/*
	StringBuffer output = new StringBuffer();
	Scanner scan = new Scanner(templateFile);
	scan.useDelimiter("");


	while (scan.hasNext()) {



	}
		 */
		return null;

	}

	/**** FOR TESTING ****/
	public static void main(String [] args) throws Exception {

		Properties p = new Properties();
		p.setProperty("foo", "David");

		File f = new File("/tmp/template1");

		long start = System.currentTimeMillis();
		System.out.println(processTemplate(f,p));
		System.out.println("\n\nTime: " + (System.currentTimeMillis() - start));


	}


}