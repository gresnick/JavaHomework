package za.co.resnick.javahomework.libraries;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import za.co.resnick.javahomework.exceptions.ApplicationException;
import za.co.resnick.javahomework.libraries.MathLibrary;

/**
 * This class provides a suite of JUnit test cases that exercise the
 * {@link MathLibrary} class.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class Test_MathLibrary
extends TestCase
{
	private static final int EXPECTED_MIN = -35945764;
	private static final int EXPECTED_MAX =  8348383;
	private static final int EXPECTED_SUM = -26947559;
	private static final int EXPECTED_AVG = -2994173;
	private static final int EXPECTED_P90 =  345343;
	
	private static final int [] VALID_INPUT = 
		{-13535, 0, 34355, EXPECTED_MAX, 66, 274245, 345343, EXPECTED_MIN, 9348};
	
	private static final int [] VALID_OVERFLOW_INPUT =
		{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
	
	/*------------------------------------------------------------------------*/
	
	@Before
	public void setUp()
	throws Exception
	{
		//
		// Not used
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@After
	public void tearDown()
	throws Exception
	{
		//
		// Not used
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_getInstance()
	throws Exception
	{
		//
		// Note that this test does not test the thread safety of this method.
		// Here we only want to ensure that calls to getInstance always return
		// references to only one instance of MathLibrary
		//
		MathLibrary library = MathLibrary.getInstance();
		
		assertNotNull(library);
		
		if (library != MathLibrary.getInstance())
			fail(
				"More than one instance of MathLibrary exists. This should not " +
				"have happened");
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_clone()
	throws Exception
	{
		MathLibrary library = MathLibrary.getInstance();
		
		try
		{
			library.clone();
			
			fail(
				"Cloning of MathLibrary completed successfully. This should " +
				"not have happened!");
		}
		catch (CloneNotSupportedException e)
		{
			//
			// Expecting this test case to throw this exception as the clone
			// would allow multiple instances of MathLibrary to coexist at
			// any point in time and is thus not supported.
			//
		}
		catch (Exception e)
		{
			fail(
				"Cloning of MathLibrary threw the exception " + e.getMessage() + 
				". However, a CloneNotSupportedException was expeceted. This " +
				"should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_min()
	throws Exception
	{
		int observed_min = MathLibrary.min(VALID_INPUT);
		
		if (observed_min != EXPECTED_MIN)
			fail(
				"The minimum value diverged from expectation. Observed " +
				"minimum was " + observed_min + " but the expected minimum " +
				"was " + EXPECTED_MIN);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_minOverflow()
	throws Exception
	{
		int observed_min = MathLibrary.min(VALID_OVERFLOW_INPUT);
		
		if (observed_min != Integer.MIN_VALUE)
			fail(
				"The minimum value diverged from expectation. Observed " +
				"minimum was " + observed_min + " but the expected minimum " +
				"was " + Integer.MIN_VALUE);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_max()
	throws Exception
	{
		int observed_max = MathLibrary.max(VALID_INPUT);
		
		if (observed_max != EXPECTED_MAX)
			fail(
				"The maximum value diverged from expectation. Observed " +
				"maximum was " + observed_max + " but the expected maximum " +
				"was " + EXPECTED_MAX);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_maxOverflow()
	throws Exception
	{
		int observed_max = MathLibrary.max(VALID_OVERFLOW_INPUT);
		
		if (observed_max != Integer.MAX_VALUE)
			fail(
				"The maximum value diverged from expectation. Observed " +
				"maximum was " + observed_max + " but the expected maximum " +
				"was " + Integer.MAX_VALUE);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_sum()
	throws Exception
	{
		int observed_sum = MathLibrary.sum(VALID_INPUT);
		
		if (observed_sum != EXPECTED_SUM)
			fail(
				"The sum diverged from expectation. Observed sum was " + 
				observed_sum + " but the expected sum was " + EXPECTED_SUM);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_sumOverflow()
	throws Exception
	{
		try
		{
			MathLibrary.sum(VALID_OVERFLOW_INPUT);
			
			fail(
				"Calculating the sum of input that will cause an integer " +
				"overflow has completed successfully. This should not have " +
				"happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as an overflow
			// has occurred whilst calculating the sum of the input. 
			//
		}
		catch (Exception e)
		{
			fail(
				"Calculating the sum of input that will cause an integer " +
				"overflow threw the exception " + e.getMessage() + ". However, " +
				"an ApplicationException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_avg()
	throws Exception
	{
		int observed_avg = MathLibrary.avg(VALID_INPUT);
		
		if (observed_avg != EXPECTED_AVG)
			fail(
				"The average diverged from expectation. Observed average was " + 
				observed_avg + " but the expected average was " + EXPECTED_AVG);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_avgOverflow()
	throws Exception
	{
		try
		{
			MathLibrary.avg(VALID_OVERFLOW_INPUT);
			
			fail(
				"Calculating the average of input that will cause an integer " +
				"overflow has completed successfully. This should not have " +
				"happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as an overflow
			// has occurred whilst calculating the average of the input. 
			//
		}
		catch (Exception e)
		{
			fail(
				"Calculating the average of input that will cause an integer " +
				"overflow threw the exception " + e.getMessage() + ". However, " +
				"an ApplicationException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_p90()
	throws Exception
	{
		int observed_p90 = MathLibrary.p90(VALID_INPUT);
		
		if (observed_p90 != EXPECTED_P90)
			fail(
				"The value at the ninetieth percentile position (p90) " +
				"diverged from expectation. Observed p90 was " + observed_p90 + 
				" but the expected p90 was " + EXPECTED_P90);
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_p90Overflow()
	throws Exception
	{
		int observed_p90 = MathLibrary.p90(VALID_OVERFLOW_INPUT);
		
		if (observed_p90 != Integer.MAX_VALUE)
			fail(
				"The value at the ninetieth percentile position (p90) " +
				"diverged from expectation. Observed p90 was " + observed_p90 + 
				" but the expected p90 was " + Integer.MAX_VALUE);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Allows running this test suite from the command line
	 */
	public static void main(String... args)
	{
		JUnitCore.main(Test_MathLibrary.class.getName());
	}
}