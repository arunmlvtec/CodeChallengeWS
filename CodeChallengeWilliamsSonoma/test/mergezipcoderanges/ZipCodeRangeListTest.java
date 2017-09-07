/**
 * Test Class: 	ZipCodeRangeListTest
 * Description:	First Sort the input Zip Code Range and then merge the ranges.
 * 
 */
package mergezipcoderanges;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author arunp
 *
 */
public class ZipCodeRangeListTest {
	ZipCodeRangeList exec;
	String expected = "resources/expected/output1.csv";
	String input = "resources/input/input1.csv";
	String actual = "resources/actual/actual1.csv";
	ArrayList<ZipCodeRange> mergedExpecteds, mergedActuals;	
	
	@Before
	public void before(){
		exec = new ZipCodeRangeList();
		mergedExpecteds = new ArrayList<ZipCodeRange>();
		mergedExpecteds = exec.readInput(expected);
	}
	
	@After
	public void after() {
		exec = null;
		expected = null;
	}
	
	@Test
	public void testCase1() {
		mergedActuals = new ArrayList<ZipCodeRange>();
		mergedActuals = exec.executeMe(input, actual);
		for (int i = 0; i < mergedActuals.size(); i++) {
			Assert.assertEquals(mergedExpecteds.get(i).toString(), mergedActuals.get(i).toString());
		}
	}
}
