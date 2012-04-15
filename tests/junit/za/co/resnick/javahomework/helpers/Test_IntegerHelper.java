package za.co.resnick.javahomework.helpers;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import za.co.resnick.javahomework.exceptions.ApplicationException;
import za.co.resnick.javahomework.helpers.IntegerHelper;

/**
 * This class provides a suite of JUnit test cases that exercise the
 * {@link IntegerHelper} class. 
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class Test_IntegerHelper 
extends TestCase
{
	private static final String[] VALID_STRING_ARRAY = 
		{"99","56","9","-2323","0"};
	
	private static final String[] INVALID_STRING_ARRAY = 
		{"342","-22342424242424444","1","77","abc"};
	
	private static final String VALID_STRING = 
		"909,93,4,89,0,0,0,0,6";
	
	private static final String VALID_STRING_NONDEFAULT_DELIMITER = 
		"-892::2324::2::221::0::-8";
	
	private static final String NONDEFAULT_DELIMITER = "::";
	
	private static final String INVALID_STRING_DELIMITERS = 
		"99,104>909,11,-98922,234,0,1";
	
	private static final String INVALID_STRING_CONTENT = 
		"1,-3405,abc,!45,9826,0,92";
	
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
	public void test_tokeniseValidStringArray()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise(VALID_STRING_ARRAY);
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a string array containing only integers threw " +
				"the exception " + e.getMessage() + ". This should not have " +
				"happened!");
		}
		
		//
		// Expecting this test case to tokenise the input string successfully
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseInvalidStringArray()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise(INVALID_STRING_ARRAY);
			
			fail(
				"Tokenising a string array containing invalid data (i.e " +
				"non-numeric characters or out of the integer value range [" + 
				Integer.MIN_VALUE + ", " + Integer.MAX_VALUE + "] was " +
				"tokenised successfully. This should not have happened!");
		}
		catch (NumberFormatException e)
		{
			//
			// Expecting this test case to throw this exception as tokenise()
			// will be unable to parse the input into integers.
			//
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a string array with invalid data threw the " +
				"exception " + e.getMessage() + ". However, a " +
				"NumberFormatException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseNullStringArray()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise((String[])null);
			
			fail(
				"Tokenising a null string array was successful. This should " +
				"not have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as tokenise()
			// will disallow null input
			//
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a null string array threw the exception " + 
				e.getMessage() + ". However, an ApplicationException was " +
				"expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseEmptyString()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise("");
			
			fail(
				"Tokenising an empty string was tokenised successfully. This " +
				"should not have happened!");
		}
		catch (NumberFormatException e)
		{
			//
			// Expecting this test case to throw this exception as tokenise()
			// will be unable to parse the input into integers.
			//
		}
		catch (Exception e)
		{
			fail(
				"Tokenising an empty string threw the exception " + 
				e.getMessage() + ". However, a NumberFormatException was " +
				"expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseValidString()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise(VALID_STRING);
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a valid string threw the exception " + 
				e.getMessage() + ". This should not have happened!");
		}
		
		//
		// Expecting this test case to tokenise the input string successfully
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseValidStringNonDefaultDelimiter()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise(
				VALID_STRING_NONDEFAULT_DELIMITER,
				NONDEFAULT_DELIMITER);
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a valid string with a non-default delimiter threw " +
				"the exception " + e.getMessage() + ". This should not have " +
				"happened!");
		}
		
		//
		// Expecting this test case to tokenise the input string successfully
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseInvalidStringDelimiter()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise(INVALID_STRING_DELIMITERS);
			
			fail(
				"Tokenising a string that contained an invalid delimiter was " +
				"successful. This should not have happened!");
		}
		catch (NumberFormatException e)
		{
			//
			// Expecting this test case to throw this exception as tokenise()
			// will be unable to parse the input into integers
			//
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a string that contained an invalid delimiter " +
				"threw the exception " + e.getMessage() + ". However, an " +
				"NumberFormatException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_tokeniseInvalidStringContent()
	throws Exception
	{
		try
		{
			IntegerHelper.tokenise(INVALID_STRING_CONTENT);
			
			fail(
				"Tokenising a string that contained non-numeric input was " +
				"successful. This should not have happened!");
		}
		catch (NumberFormatException e)
		{
			//
			// Expecting this test case to throw this exception as tokenise()
			// will be unable to parse the input into integers
			//
		}
		catch (Exception e)
		{
			fail(
				"Tokenising a string that contained non-numeric input " +
				"threw the exception " + e.getMessage() + ". However, an " +
				"NumberFormatException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Allows running this test suite from the command line
	 */
	public static void main(String... args)
	{
		JUnitCore.main(Test_IntegerHelper.class.getName());
	}
}