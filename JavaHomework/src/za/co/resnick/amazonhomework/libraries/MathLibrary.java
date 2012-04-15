package za.co.resnick.amazonhomework.libraries;

import java.util.Arrays;

import za.co.resnick.amazonhomework.exceptions.ApplicationException;

/**
 * This class encapsulates the mathematical functions utilised by this
 * application.
 * <p>
 * Note that at any point in time only a single instance of this class can 
 * exist in the system.   
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public final class MathLibrary 
{
	/**
	 * Singleton instance of this class 
	 */
	private static MathLibrary library;
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Default constructor
	 * <p>
	 * This constructor exists to defeat instantiation. 
	 */
	private MathLibrary()
	{		
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Provide external access to the singleton instance of this class. This 
	 * method is thread safe. 
	 * 
 	 * @return
 	 * 		an instance of this class
	 */
	public static synchronized MathLibrary getInstance()
	{
		//
		// Initialise an instance of this class
		//
		if (library == null)
			library = new MathLibrary();
		
		//
		// Return the instance
		//
		return library;
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Returns the minimum value in the {@link Integer} [] provided.
	 *  
	 * @param list
	 * 		an array of integers
	 * 
	 * @return
	 * 		the minimum value in the <code>list</code> provided
	 */
	public static int min(int... list)
	{
		//
		// Initialise the value to the maximum integer value possible, any
		// value in the array should override this. 
		//
		int min = Integer.MAX_VALUE; 
		
		//
		// Iterate through the array, compare each value to the current 
		// minimum, if its smaller, set it as the minimum. 
		// 
		for (int i : list)
			if (i < min)
				min = i;
		
		//
		// Return the minimum
		//
		return min;
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Returns the maximum value in the {@link Integer} [] provided.
	 *  
	 * @param list
	 * 		an array of integers
	 * 
	 * @return
	 * 		the maximum value in the <code>list</code> provided
	 */
	public static int max(int... list)
	{
		//
		// Initialise the value to the minimum integer value possible, any
		// value in the array should override this. 
		//
		int max = Integer.MIN_VALUE;

		//
		// Iterate through the array, compare each value to the current 
		// maximum, if its larger, set it as the maximum. 
		// 
		for (int i : list)
			if (i > max)
				max = i;
	
		//
		// Return the maximum
		//
		return max;
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Returns the sum of the values in the {@link Integer} [] provided.
	 *  
	 * @param list
	 * 		an array of integers
	 * 
	 * @return
	 * 		the summed value
	 * 
	 * @throws ApplicationException
	 * 		if an over or underflow is encountered during calculation
	 */
	public static int sum(int... list)
	throws ApplicationException
	{
		//
		// Initialise to 0 (zero)
		//
		long sum = 0;
		
		//
		// Iterate through the array, adding each value
		//
		for (int i : list)
			sum += i;
		
		if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE)
			throw new ApplicationException(
				"Unhandled integer over/underflow detected.");
		//
		// Return the summation 
		//
		return (int)sum;
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Returns the average of the values in the {@link Integer} [] provided.
	 *  
	 * @param list
	 * 		an array of integers
	 * 
	 * @return
	 * 		the average value
	 * 
	 * @throws ApplicationException
	 * 		if an over or underflow is encountered during calculation
	 */
	public static int avg(int... list)
	throws ApplicationException
	{
		//
		// Sum the list and divide by the number of elements to return
		// the average
		//
		return (sum(list) / list.length);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Returns the value at the ninetieth percentile position in the 
	 * {@link Integer} [] provided. 
	 * <p>
	 * Note that rounding is achieved through the use of 
	 * {@link Math#floor(double)}.
	 *  
	 * @param list
	 * 		an array of integers
	 * 
	 * @return
	 * 		the value at the ninetieth percentile position in the 
	 * 		<code>list</code> provided
	 */
	public static int p90(int... list)
	{
		Arrays.sort(list);
		
		return list[(int) Math.floor((list.length -1) * 0.9)];
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Override this object's clone() method to prevent more than one instance
	 * from existing concurrently in the system.
	 * 
	 * @throws CloneNotSupportedException
	 * 		Always throws this exception as cloning this object is not 
	 * 		supported.
	 */
	@Override
	public Object clone() 
	throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
