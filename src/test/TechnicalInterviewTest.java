package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import source.TechnicalInterview;

class TechnicalInterviewTest {

	@Test
	void testEmptyString() {
		assertEquals(0, TechnicalInterview.add(""));
	}
	 
	@Test
	void testNullString() {
		assertEquals(0, TechnicalInterview.add(null));
	}
	 
	@Test
	void testRegularString() {
		assertEquals(6, TechnicalInterview.add("1,2,3"));
	}
	
	@Test
	void testRegularStringWithNewLines() {
		assertEquals(6, TechnicalInterview.add("\n1,2,\n3"));
	}
	
	@Test
	void testCustomDelimiter() {
		assertEquals(6, TechnicalInterview.add("//;\n1;2;3"));
	}

	@Test
	void testCustomDelimiter2() {
		assertEquals(9, TechnicalInterview.add("//$\n1$3$5"));
	}
	
	@Test
	void testNegativeValues() {
		//The exception is thrown and can be seen in the console
		assertEquals(-3, TechnicalInterview.add("-1,3,-5"));
	}
	
	@Test
	void testLargerThanThousand() {
		assertEquals(2, TechnicalInterview.add("2,1001"));
	}
	
	@Test
	void testDelimiterWithArbitraryLength() {
		assertEquals(6, TechnicalInterview.add("//***\n1***2***3"));
	}
	
	@Test
	void testMultipleDelimiters() {
		assertEquals(12, TechnicalInterview.add("//$,@\n3$4@5"));
	}
	
	@Test
	void testMultipleDelimitersWithArbitraryLength() {
		assertEquals(12, TechnicalInterview.add("//$$$,@\n3$$$4@5"));
	}

}
