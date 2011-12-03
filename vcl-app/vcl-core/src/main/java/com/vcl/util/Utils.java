package com.vcl.util;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Utility methods for the AAIS project.
 *
 */
public class Utils {

	/**
	 * Constructor is defined as private because all contained methods are defined as static.
	 */
	private Utils() { }

	/**
	* Evaluates whether <code>value</code> is null or zero-length.
	* 
	* @param value The string to be validated.
	* @return <code>true</code> if <code>value</code> <b>is</b> null or zero-length, otherwise <code>false</code>.
	*/
	public static boolean isEmpty(String value) {

		if (value == null) {
			return true;
		}

		return value.isEmpty();

	}

	/**
	* Evaluates whether <code>value</code> is null or is less than or equal to zero.
	* 
	* @param value The string to be validated.
	* @return <code>true</code> if <code>value</code> <b>is</b> null or is less than or equal to zero, 
	* otherwise <code>false</code>.
	*/
	public static boolean isEmpty(Number value) {

		if (value == null) {
			return true;
		}

		return value.intValue() <= 0;

	}
	
	/**
	* Evaluates whether <code>value</code> is null or is equal to zero.
	* 
	* @param value 
	* @return <code>true</code> if <code>value</code> <b>is</b> null or is equal to zero, 
	* otherwise <code>false</code>.
	*/
	public static boolean isNullOrZero(BigDecimal value) {

		if (value == null) {
			return true;
		}

		return value.compareTo(BigDecimal.ZERO) == 0;
	}
	
	/**
	 * 
	 * @return true if value is not null and greater than {@link BigDecimal#ZERO}.
	 */
	public static boolean greaterThanZero(BigDecimal value) {
		return (value != null && value.compareTo(BigDecimal.ZERO) > 0);
	}

	/**
	 * Evaluates whether <code>values</code> is null or any of the contained elements is less than or equal to zero.
	 * 
	 * @param values An array of <code>Number</code>s to be validated.
	 * @return <code>true</code> if any of the elements in the array is null or is less than or equal to zero, 
	 * otherwise <code>false</code>.
	 */
	public static boolean isEmpty(Number[] values) {

		if (values == null || values.length == 0) {
			return true;
		}

		for (int i = 0; i < values.length; i++) {

			if (values[i] == null || values[i].intValue() <= 0) {
				return true;
			}

		}

		return false;

	}

	/**
	 * Convenience method to determine if a collection is null or empty.
	 * 
	 * @param coll The collection class (Set, List, etc) to be tested.
	 * @return <code>true</code> if <code>coll</code> is null or empty.
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Collection coll) {

		if (coll == null || coll.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	 * Convenience method to determine if a Map is null or empty.
	 * 
	 * @param map The map class (HashMap etc) to be tested.
	 * @return <code>true</code> if <code>map</code> is null or empty.
	 */
	public static boolean isEmpty(Map<?, ?> map) {

		if (map == null || map.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	* Returns true if <code>element</code> is contained in <code>values</code>.
	*	This method is not case sensitive.
	*
	*  @param element The string to validate.
	*  @param values An array of valid String values.
	*  @return <code>true</code> If <code>element</code> is found in array, otherwise <code>false</code>.
	*/
	public static boolean isEnumeratedType(String element, String[] values) {

		if (isEmpty(element)) {
			return false;
		}

		if (values == null || values.length == 0) {
			return false;
		}

		for (int i = 0; i < values.length; i++) {

			if (values[i].equalsIgnoreCase(element)) {
				return true;
			}

		}

		return false;

	}

	/**
	* Returns true if <code>element</code> is contained in <code>values</code>.
	*	This method is not case sensitive.
	*
	*  @param element The char to validate.
	*  @param values An array of valid char values.
	*  @return <code>true</code> If <code>element</code> is found in array, otherwise <code>false</code>.
	*/
	public static boolean isEnumeratedType(char element, char[] values) {

		if (values == null || values.length == 0) {
			return false;
		}

		for (int i = 0; i < values.length; i++) {

			if (values[i] == element) {
				return true;
			}

		}

		return false;

	}

	/**
	* Returns true if <code>element</code> is contained in <code>values</code>. The <code>int</code> value
	* of <code>element</code> and <code>values</code> is used for evaluation.
	*
	*  @param element The numeric value to validate.
	*  @param values An array of valid integer values.
	*  @return <code>true</code> If <code>element</code> is found in array, otherwise <code>false</code>.
	*/
	public static boolean isEnumeratedType(Number element, Number[] values) {

		if (isEmpty(element)) {
			return false;
		}

		if (values == null || values.length == 0) {
			return false;
		}

		for (int i = 0; i < values.length; i++) {

			if (values[i].intValue() == element.intValue()) {
				return true;
			}

		}

		return false;

	}

	/**
	 * Reverse the order of the elements in an array.
	 * 
	 * @param values The array to be 'reversed'.
	 * @return A new array containing the data in <code>values</code>, in reverse order.
	 */
	public static Object[] reverseArrayOrder(Object[] values) {

		if (values == null) {
			return values;
		}

		Object[] newArray = new Object[values.length];

		for (int i = values.length - 1, j = 0; i >= 0; i--, j++) {
			newArray[j] = values[i];
		}

		return newArray;

	}

	/**
	 * Null is easier to check than a value is not specified, but some clients such as flex cannot send a
	 * proper null number so they send a zero instead. If zero is considered invalid then this routine is
	 * helpful.
	 *  
	 * @return returns null if number is zero else returns the value of number (which could be null). 
	 * This routine is helpful where flex sends a zero value and zero is considered invalid and so 
	 * we reset the value back to null so that we only have to check for null. 
	 **/
	public static BigDecimal zeroValueToNull(BigDecimal number) {
		BigDecimal value = number; 
		if (number != null) {
			if (number.compareTo(BigDecimal.ZERO) == 0) {
				value = null;
			}
		}
		return value;
	}
	
	/**
	 * Convert an object array into a CSV. 
	 */
	public static String objectsToCsv(Object[] values) {
		StringBuffer sb = new StringBuffer();
		for (Object o : values) {
			sb.append(o + ",");
		}
		String csv = sb.toString();
		if (csv.length() == 0) {
			return "";
		}
		return csv.substring(0, csv.length() - 1);
	}
	
	/**
	 * @return An array of trimmed strings
	 * @see {@link String#trim()}
	 */
	public static String[] trim(String[] strings) {
		
		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].trim();
		}
		
		return strings;
		
	}
}