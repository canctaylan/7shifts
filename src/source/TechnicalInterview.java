package source;

import java.util.ArrayList;
import java.util.List;

public class TechnicalInterview {

	

	public static void main(String[] args) {
		System.out.println(add("-1,3,-5"));
	}

	/**
	 * This method gets a list of numbers with default or custom delimiters,
	 * adds the numbers and returns the sum value.
	 * @param numbers The list of numbers
	 * @return The sum of the list of numbers
	 */
	public static int add(String numbers) {
		int sum = 0;
		String delimiter = ",";
		boolean hasNegatives = false;
		List<String> negatives = new ArrayList<String>();
		if (numbers == null || numbers.length() == 0) {
			//If the input is empty, return 0
			return sum;
		} else {
			if (numbers.startsWith("//")) { // If the input has custom delimiters the delimiters are found and  a regex String is created that will be inserted to the split method later on.
				delimiter = numbers.substring(2, numbers.indexOf("\n")); // String of delimiters
				numbers = numbers.substring(numbers.indexOf("\n") + 1); // String of list of numbers
				delimiter = returnRegex(delimiter); 
				
			}
			String[] numberString = numbers.split(delimiter);

			for (String s : numberString) {
				try {
					int number = Integer.parseInt(s.replace("\n", "")); // The number is converted to integer, at the same time new line is handled
					if (number < 0) { // If there are negative values the boolean is set to true and value is added to the list.
						hasNegatives = true;
						negatives.add("" + number);
					}
					if(number<1000) // If the number is above 1000 it is ignored
						sum += number;
				} catch (NumberFormatException e) {
					System.out.println("Non-numberical character entered!");
				}
			}
		}

		if (hasNegatives) { //If there are negatives then an exception is thrown and the list of negative numbers are displayed
			try {
				haveNegativeNumbers(negatives);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sum;
	}

	/**
	 * This method receives the list of negative values and throws an Exception
	 * @param negatives The list of negative values
	 * @throws Exception
	 */
	private static void haveNegativeNumbers(List<String> negatives) throws Exception {
		String listOfNegatives = String.join(",", negatives);

		throw new Exception("Negatives not allowed!\nThe negative value(s): " + listOfNegatives);

	}

	/**
	 * This method receives a string of custom delimiters and adds "\\" in case of characters that needs to be escaped.
	 * @param delimiters A string of custom delimiters
	 * @return A string value that is ready to be given to the split method.
	 */
	private static String returnRegex(String delimiters) {
		String[] delimiterArray = delimiters.split(",");
		for(int i=0;i<delimiterArray.length;i++)
		{
			if(delimiterArray[i].length()==1)
				delimiterArray[i] = "\\"+delimiterArray[i];
			else
			{
				char[] charArray = delimiterArray[i].toCharArray();
				String newDelimiter = "";
				for (char delim : charArray)
				{
					newDelimiter += "\\" + delim;
				}
				delimiterArray[i] = newDelimiter;
			}
		}
		return String.join("|", delimiterArray);
	}
}
