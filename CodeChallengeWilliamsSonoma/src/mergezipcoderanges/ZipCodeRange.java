/**
 * Class Name: 	ZipCodeRange
 * Description:	Data structure for Zip Code Ranges.
 * 				begin:	Zip Code start range (inclusive).
 * 				end:	Zip Code end range (inclusive).
 */

package mergezipcoderanges;

/**
 * @author arunp date: 2017/Aug-16
 */

public class ZipCodeRange {
	private String begin;
	private String end;

	ZipCodeRange() {
		this.begin = "0";
		this.end = "0";
	}

	ZipCodeRange(String begin, String end) {
		this.begin = begin;
		this.end = end;
	}

	public String getBegin() {
		return this.begin;
	}

	public String getEnd() {
		return this.end;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}