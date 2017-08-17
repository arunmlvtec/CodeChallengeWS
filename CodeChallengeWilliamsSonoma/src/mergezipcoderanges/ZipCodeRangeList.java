/**
 * Class Name: 	ZipCodeRangeList
 * Description:	First Sort the input Zip Code Range and then merge the ranges.
 * 
 */
package mergezipcoderanges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author arunp date: 2017-August-16
 */
public class ZipCodeRangeList {
	private ArrayList<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();

	public void addRange(ZipCodeRange range) {
		this.rangeList.add(range);
	}

	public ArrayList<ZipCodeRange> getRangeList() {
		return this.rangeList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortRange(ArrayList<ZipCodeRange> rangeList) {
		Collections.sort(rangeList, new Comparator() {

			public int compare(Object range1, Object range2) {
				String r1 = ((ZipCodeRange) range1).getBegin();
				String r2 = ((ZipCodeRange) range2).getBegin();
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

	public void printRangeList(ArrayList<ZipCodeRange> rangeList) {
		System.out.println("RangeList Size is: " + rangeList.size());
		for (ZipCodeRange range : rangeList) {
			System.out.println(range.getBegin() + "\t" + range.getEnd());
		}
	}

	public ArrayList<ZipCodeRange> mergeRange() {
		int rangeListLength = rangeList.size();
		ArrayList<ZipCodeRange> mergedRangeList = new ArrayList<ZipCodeRange>();
		if (rangeListLength > 0) {
			sortRange(rangeList);

			ZipCodeRange tmp = rangeList.get(0);

			for (int i = 0; i < rangeListLength; i++) {
				if (((Integer.parseInt(tmp.getEnd()) + 1) == Integer.parseInt(rangeList.get(i).getBegin())
						|| (Integer.parseInt(tmp.getEnd()) >= Integer.parseInt(rangeList.get(i).getBegin())))) {
					tmp.setEnd(rangeList.get(i).getEnd());
				} else {
					mergedRangeList.add(tmp);
					tmp = rangeList.get(i);
				}
			}
			mergedRangeList.add(tmp);
		}
		return mergedRangeList;
	}
}