package za.co.resnick.amazonhomework.helpers;

import za.co.resnick.amazonhomework.exceptions.ApplicationException;

/**
 * This class provides {@link Integer} utility methods required by this
 * application.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class IntegerHelper 
{
	/**
	 * The default delimiter string used when a delimiter is not explicitly
	 * provided. 
	 */
	protected static final String DEFAULT_DELIMITER = ",";
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Tokenises the input into an array of <code>Integer</code>s.
	 * 
	 * @param str_arr
	 * 		an array of <code>String</code>s. Must not be <code>null</code>.
	 * 
	 * @return
	 * 		the array of <code>Integer</code>s
	 * 
	 * @throws ApplicationException
	 * 		if a <code>null</code> parameter is passed into this method
	 * @throws NumberFormatException
	 * 		if an element is unable to be parsed into an <code>Integer</code>
	 */
	public static int [] tokenise(String... str_arr)
	throws ApplicationException, NumberFormatException
	{
		if (str_arr == null)
			throw new ApplicationException(
				"Invalid parameter 'null', expecting non-null String [].'");
		
		int length = str_arr.length;
		
		int [] int_arr = new int [length];

		for (int i = 0; i < length; i++)
			int_arr[i] = Integer.parseInt(str_arr[i]);	
		
		return int_arr;
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Tokenises the input into an array of <code>Integer</code>s.
	 * 
	 * @param string
	 * 		a <code>String</code> to be tokenised. Must not be <code>null</code>.
	 * @param delimiter
	 * 		the delimiting <code>String</code> upon which bounds tokenisation 
	 * 		will occur	
	 * 
	 * @return
	 * 		the array of <code>Integer</code>s
	 * 
	 * @throws ApplicationException
	 * 		if a <code>null</code> parameter is passed into this method
	 * @throws NumberFormatException
	 * 		if an element is unable to be parsed into an <code>Integer</code>
	 */
	public static int [] tokenise(String string, String delimiter)
	throws ApplicationException
	{
		return tokenise(string.split(delimiter));
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Tokenises the input into an array of <code>Integer</code>s.
	 * <p>
	 * Delimiter defaults to {@link IntegerHelper#DEFAULT_DELIMITER}.
	 * 
	 * @param string
	 * 		a <code>String</code> to be tokenised. Must not be <code>null</code>.
	 * 
	 * @return
	 * 		the array of <code>Integer</code>s
	 * 
	 * @throws ApplicationException
	 * 		if a <code>null</code> parameter is passed into this method
	 * @throws NumberFormatException
	 * 		if an element is unable to be parsed into an <code>Integer</code>
	 */
	public static int [] tokenise(String string)
	throws ApplicationException
	{
		return tokenise(string, IntegerHelper.DEFAULT_DELIMITER);
	}
}
