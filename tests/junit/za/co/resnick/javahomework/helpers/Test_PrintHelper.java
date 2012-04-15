package za.co.resnick.javahomework.helpers;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import za.co.resnick.javahomework.exceptions.ApplicationException;
import za.co.resnick.javahomework.helpers.PrintHelper;

/**
 * This class provides a suite of JUnit test cases that exercise the
 * {@link PrintHelper} class. 
 * <p>
 * Note that, due to time constraints, only negative testing has been completed.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class Test_PrintHelper 
extends TestCase
{
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
	public void test_printlnIntegerNullWriter()
	throws Exception
	{
		try
		{
			PrintHelper.println(null, 7);
			
			fail(
				"Printing an integer with no output stream provided completed " +
				"successfully. This should not have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as it will 
			// disallow null input
			//
		}
		catch (Exception e)
		{
			fail(
				"Printing with no output stream provided threw the exception " + 
				e.getMessage() + ". However, an ApplicationException was " +
				"expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_printlnStringNullWriter()
	throws Exception
	{
		try
		{
			PrintHelper.println(null, "Hello World!");
			
			fail(
				"Printing a string with no output stream provided completed " +
				"successfully. This should not have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as it will 
			// disallow null input
			//
		}
		catch (Exception e)
		{
			fail(
				"Printing with no output stream provided threw the exception " + 
				e.getMessage() + ". However, an ApplicationException was " +
				"expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Allows running this test suite from the command line
	 */
	public static void main(String... args)
	{
		JUnitCore.main(Test_PrintHelper.class.getName());
	}
}