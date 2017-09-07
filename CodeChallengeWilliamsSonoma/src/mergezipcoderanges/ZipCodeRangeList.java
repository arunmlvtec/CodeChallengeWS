/**
 * Class Name: 	ZipCodeRangeList
 * Description:	First Sort the input Zip Code Range and then merge the ranges.
 * 
 */
package mergezipcoderanges;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author arunp date: 2017-August-16
 */
public class ZipCodeRangeList {

	public void addRange(ZipCodeRange range, ArrayList<ZipCodeRange> rangeList) {
		rangeList.add(range);
	}

	public void sortRange(ArrayList<ZipCodeRange> rangeList) {
		Collections.sort(rangeList, new Comparator<ZipCodeRange>() {

			public int compare(ZipCodeRange range1, ZipCodeRange range2) {
				String r1 = range1.getBegin();
				String r2 = range2.getBegin();
				int comp = r1.compareTo(r2);

				if (comp != 0) {
					return comp;
				} else {
					String x1 = ((ZipCodeRange) range1).getEnd();
					String x2 = ((ZipCodeRange) range2).getEnd();
					return x1.compareTo(x2);
				}
			}
		});
	}

	public ArrayList<ZipCodeRange> mergeRange(ArrayList<ZipCodeRange> rangeList) {
		int rangeListSize = rangeList.size();
		ArrayList<ZipCodeRange> mergedRangeList = new ArrayList<ZipCodeRange>();
		if (rangeListSize > 0) {
			sortRange(rangeList);

			ZipCodeRange tmp = rangeList.get(0);

			for (int i = 0; i < rangeListSize; i++) {
				if ((Integer.parseInt(tmp.getEnd()) + 1 == Integer.parseInt(rangeList.get(i).getBegin())
						|| Integer.parseInt(tmp.getEnd()) >= Integer.parseInt(rangeList.get(i).getBegin()))) {

/**					 Additional condition to ignore duplicate zip code pairs
					 and zip codes pairs such as [94100,94199] and
					 [94136,94136]
*/
					if (Integer.parseInt(tmp.getEnd()) < Integer.parseInt(rangeList.get(i).getEnd())
							&& Integer.parseInt(tmp.getBegin()) != Integer.parseInt(rangeList.get(i).getBegin())) {
						tmp.setEnd(rangeList.get(i).getEnd());
					}

				} else {
					mergedRangeList.add(tmp);
					tmp = rangeList.get(i);
				}
			}
			mergedRangeList.add(tmp);
		}
		return mergedRangeList;
	}

	public ArrayList<ZipCodeRange> readInput(String inputFile) {
		ArrayList<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		BufferedReader br = null;

		String currentLine;
		try {
			br = new BufferedReader(new FileReader(inputFile));

			try {
				while ((currentLine = br.readLine()) != null) {
					String[] zipCodes = currentLine.split(",");
					if (validateInput(Integer.parseInt(zipCodes[0])) && validateInput(Integer.parseInt(zipCodes[1]))) {
						ZipCodeRange range = new ZipCodeRange();

						if (Integer.parseInt(zipCodes[0]) <= Integer.parseInt(zipCodes[1]))
							range = new ZipCodeRange(zipCodes[0], zipCodes[1]);
						else
							range = new ZipCodeRange(zipCodes[1], zipCodes[0]);

						addRange(range, rangeList);
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

	public boolean validateInput(int zipCode) {
		if (zipCode <= 99999)
			return true;
		return false;
	}

	public void writeOutput(ArrayList<ZipCodeRange> mergedRangeList, String outputFile) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mergedRangeList.size() > 0) {
			for (ZipCodeRange range : mergedRangeList) {
				try {
					writer.write(range.toString() + "\n");
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

	public ArrayList<ZipCodeRange> executeMe(String inputFile, String outputFile) {
		ArrayList<ZipCodeRange> mergedRangeList = new ArrayList<ZipCodeRange>();

		// Reading input Zip Code Range from the file.
		ArrayList<ZipCodeRange> rangeList = readInput(inputFile);

		// Sort and Merge the input as per the given requirement.
		mergedRangeList = mergeRange(rangeList);

		// Write output in the file.
		writeOutput(mergedRangeList, outputFile);

		System.out.println("Execution Completed!");

		return mergedRangeList;
	}
}