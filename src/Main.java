//import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.io.*;
//import java.util.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

	/*public static void runner() {
		String s = null;
		ArrayList<String> keys;
		ArrayList<String> outputs;

		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			Process p = Runtime.getRuntime().exec("ls -al");

			BufferedReader stdInput = new BufferedReader(new
					InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new
					InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}

			//System.exit(0);
		}
		catch (IOException e) {
			System.out.println("Exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}
	
		//compare(keys, outputs);
	}*/


	/**
	* Compares corresponding indexes of the key and output arrays
	* Calls the .pl script to compare key vs. output of OCR
	* TODO: Redirect input in .pl file so key/output come from arrays, not files
	* TODO: Redirect output scores to matrix
	* @param key string, output of OCR string
	* @return score from this alignment
	**/
	public static int compare(String key, String output) {
		String[] cmd = {"perl", "NWA.pl", key, output};
		StringBuilder perlout = new StringBuilder();
		//Runtime runtime = Runtime.getRuntime();
		ProcessBuilder pb = new ProcessBuilder(cmd);
		try {
			Process process = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				perlout.append(line);
				perlout.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Running command: " + cmd[1]);
		String score = perlout.toString();
		score = score.trim();
		int s = Integer.valueOf(score);
		//System.out.println(s);
		//System.out.println(score);
		return s;
	}

	/**
	* Given a folder, parse each text file into a string
	* Store string in ArrayList
	* @param path of folder containing text files
	* @return array of strings, each containing the contents of one text file
	**/
	public static ArrayList<String> getAllKeys(String path) throws FileNotFoundException {
		// for all files in ImageKeys
		// scan in all text to String
		// add to arrayList and return
		String s = "";
		String filepath;
		ArrayList<String> allKeys = new ArrayList<>();
		Path dir = Paths.get(path);
		int files = 0;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			//For each file
			for (Path file: stream) {
				//System.out.println("Reading from " + file.getFileName());
				//Scanner in = new Scanner(new File("ImageKeys/Demolm01.txt"));
				filepath = path + file.getFileName();
				Scanner in = new Scanner(new File(filepath));
				//Pattern grab = Pattern.compile("[a-z]");
				while (in.hasNext()) {
					s += in.next();
					//System.out.println(s);
				}
				allKeys.add(s);
				s = "";
				files++;
			}
		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}
		return allKeys;
		//Scanner in = new Scanner(new file ());

	}
	
	/**
	* @param array of scores
	* @return average of scores
	**/
	public static int getAverage(int[] array) {
		int sum = 0;
		int i = array.length;
		for (int n = 0; n < i; n++) {
			sum += array[n];
		}
		return sum/i;
	}
	
	/**
	* Use given directories of keys and outputs, parse text files into strings
	* Store strings in ArrayLists
	* Run compare() on each index to determine accuracy of OCR based on expected output
	* @param directory containing keys, directory containing outputs
	* (TODO): @return score array
	*/
	public static void analyzeOCR(String keydir, String outputdir) {
		try {
			//Array of keys and outputs
			ArrayList<String> keys;
			ArrayList<String> outputs;
			
			//Get key values from given directory
			keys = getAllKeys(keydir);
			//Get outputs from given directory
			outputs = getAllKeys(outputdir);
			
			//Create score array from # of keys (matches # of outputs)
			int[] scores = new int[keys.size()];
			
			//Uncomment to see what's in the arrays
			/*System.out.println("List of Keys:");
			System.out.println(keys);
			System.out.println("List of Outputs:");
			System.out.println(outputs);*/
			
			//For each key/output matching, get the alignment score and store in array
			//Indexes will match
			for (int i = 0; i < keys.size(); i++) {
				scores[i] = compare(keys.get(i), outputs.get(i));
				System.out.println("Score of alignment " + i + ": " + scores[i]);
			}
			System.out.println("Average score is " + getAverage(scores));
		} catch (FileNotFoundException e) {
			System.err.println("FAIL: " + e.getMessage() );
		}
	}

	/**
	* For each OCR:
	* (TODO): Run OCR on image files, generate a folder of outputs
	* (TODO): Pass the name of this folder to outputdir
	* (Done): Pass outputdir to analyze method.
	* (Done): analyzeOCR will call:
	* 	(Done): getAllKeys() to parse data 
	*	(Done): compare() to generate scores
	* (TODO): Keep scores in separate data structures, allow user to print info
	*/
	public static void main(String args[]) {
		//This is where the keys are stored, does not change during session
		String keydir = "ImageKeys/";
		String outputdir = "";
		//For each OCR 
			//generate a folder of outputs
			outputdir = "TesseractOutput/";
			analyzeOCR(keydir, outputdir);
	}
}