import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class Group3 {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		if (args.length < 2) {
			System.out.println(
					"Please run with two command line arguments: input and output file names");
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];

		String[] data = readData(inputFileName);
		
		String[] toSort = data.clone();
		
		sort(toSort);

		toSort = data.clone();
		
		Thread.sleep(10);
		
		long start = System.currentTimeMillis();
		sort(toSort);
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);

		writeOutResult(toSort, outFileName);

	}

	private static String[] readData(String inputFileName) throws FileNotFoundException {
		ArrayList<String> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inputFileName));
		while (in.hasNext()) {
			input.add(in.next());
		}
		in.close();
		return input.toArray(new String[0]);
	}

	// Method to find the sum of prime factors of a number 'n'
	private static int sumPrimeFactors(int n, HashMap<Integer, Integer> temp) {
		// Check if the result is already computed
		if (temp.containsKey(n)) {
			return temp.get(n);
		}
		int sum = 0;
		int originalN = n;
		// Loop up to the square root of 'n'
		for (int i = 2; i <= Math.sqrt(n); i++) {
			// Check if 'i' is a factor
			if (n % i == 0) {
				sum += i;
				// Eliminate all occurrences of 'i'
				while (n % i == 0) {
					n /= i;
				}
			}
		}
		// If 'n' is greater than 1, it is a prime number.
		if (n > 1) {
			sum += n;
		}
		// Store the computed result for future use
		temp.put(originalN, sum);
		return sum;
	}

	// Method to sort the array based on the sum of prime factors
	private static void sort(String[] toSort) {
		// Temporary HashMap to store pre-computed sums of prime factors
		HashMap<Integer, Integer> temp = new HashMap<>();
		int[] parsedNumbers = new int[toSort.length];
		for (int i = 0; i < toSort.length; i++) {
			parsedNumbers[i] = Integer.parseInt(toSort[i]);
		}
		// Sort using custom comparator
		Arrays.sort(toSort, (a, b) -> {
			int numA = Integer.parseInt(a);
			int numB = Integer.parseInt(b);
			int compare = sumPrimeFactors(numA, temp) - sumPrimeFactors(numB, temp);
			if (compare == 0) {
				return numB - numA;
			}
			return compare;
		});
	}
	private static void writeOutResult(String[] sorted, String outputFilename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(outputFilename);
		for (String s : sorted) {
			out.println(s);
		}
		out.close();
	}
}
