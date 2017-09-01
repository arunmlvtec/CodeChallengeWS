/**
 * 
 */
package mergezipcoderanges;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author arunp date: 2017-August-16
 *
 */
public class ExecuteMe {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input file path: ");
		String inputFile = sc.nextLine();
		System.out.println("Enter the output file path: ");
		String outputFile = sc.nextLine();
		ExecuteMe exec = new ExecuteMe();

		ArrayList<ZipCodeRange> mergedRangeList = new ArrayList<ZipCodeRange>();

		// Reading input Zip Code Range from the file.
		ZipCodeRangeList rangeList = exec.readInput(inputFile);

		// Sort and Merge the input as per the given requirement
		mergedRangeList = rangeList.mergeRange();

		// Write output in the file
		exec.writeOutput(mergedRangeList, outputFile);

		System.out.println("Execution Completed!");
		sc.close();
	}

	public boolean validateInput(int zipCode) {
		if (zipCode <= 99999)
			return true;
		return false;
	}

	public ZipCodeRangeList readInput(String inputFile) {
		ZipCodeRangeList rangeList = new ZipCodeRangeList();
		BufferedReader br = null;

		String currentLine;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			// D:\EclipseWorkspace\CodeChallengeWilliamsSonoma\resources\input1.csv

			try {
				while ((currentLine = br.readLine()) != null) {
					String[] zipCodes = currentLine.split(",");
					if (validateInput(Integer.parseInt(zipCodes[0])) && validateInput(Integer.parseInt(zipCodes[1]))) {
						ZipCodeRange range = new ZipCodeRange();
						if (Integer.parseInt(zipCodes[0]) <= Integer.parseInt(zipCodes[1])) {
							range = new ZipCodeRange(zipCodes[0], zipCodes[1]);
						} else {
							range = new ZipCodeRange(zipCodes[1], zipCodes[0]);
						}
						rangeList.addRange(range);
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return rangeList;
	}

	public void writeOutput(ArrayList<ZipCodeRange> mergedRangeList, String outputFile) {
		// System.out.println("in write output method.");
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mergedRangeList.size() > 0) {
			for (ZipCodeRange range : mergedRangeList) {
				// System.out.println(range.getBegin() + "," + range.getEnd());
				try {
					writer.write(range.getBegin() + "," + range.getEnd() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}