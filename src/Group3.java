package src;


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
    private static void sumPrimeFactors(int n, HashMap<Integer, Integer> temp) {
        int sum = 0;
        int originalN = n;
        // Loop up to the square root of 'n'
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            sum += n;
        }
        temp.put(originalN, sum);
    }

    // Method to sort the array based on the sum of prime factors
    private static void sort(String[] toSort) {
        HashMap<Integer, Integer> temp = new HashMap<>();
        // Pre-compute the sum of prime factors for all numbers
        for (String s : toSort) {
            int num = Integer.parseInt(s);
            sumPrimeFactors(num, temp);
        }
        // Sort using the pre-computed values
        Arrays.sort(toSort, (a, b) -> {
            int numA = Integer.parseInt(a);
            int numB = Integer.parseInt(b);
            int compare = temp.get(numA) - temp.get(numB);
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
