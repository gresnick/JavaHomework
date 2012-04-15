package za.co.resnick.javahomework.readers;

import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import za.co.resnick.javahomework.exceptions.ApplicationException;
import za.co.resnick.javahomework.readers.ArgumentReader;

/**
 * This class provides a suite of JUnit test cases that exercise the
 * {@link ArgumentReader} class. 
 * <p>
 * Note that, due to time constraints, the {@link ArgumentReader#readArguments()}
 * method is only tested in the integration tests package. 
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class Test_ArgumentReader 
extends TestCase
{
	private static final String EXPRESSION = 
			"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
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
	public void test_constructNullReader()
	throws Exception
	{
		try
		{
			new ArgumentReader(null, EXPRESSION);
			
			fail(
				"Constructing an ArgumentReader with a null Reader completed " +
				"successfully. This should not have happened!");
		}
		catch (NullPointerException e)
		{
			//
			// Expecting this test case to throw this exception as there is no
			// way for us to prevent passing null through to the super class.
			//
		}
		catch (Exception e)
		{
			fail(
				"Constructing an ArgumentReader with a null Reader threw the " +
				"exception " + e.getMessage() + ". However, " +
				"a NullPointerException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_constructNullExpression()
	throws Exception
	{
		try
		{
			new ArgumentReader(new InputStreamReader(System.in), null);
			
			fail(
				"Constructing an ArgumentReader with a null expression " +
				"completed successfully. This should not have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as it will 
			// disallow null input.
			//
		}
		catch (Exception e)
		{
			fail(
				"Constructing an ArgumentReader with a null expression threw " +
				"the exception " + e.getMessage() + ". However, " +
				"an ApplicationException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_constructEmptyExpression()
	throws Exception
	{
		try
		{
			new ArgumentReader(new InputStreamReader(System.in), "");
			
			fail(
				"Constructing an ArgumentReader with an empty expression " +
				"completed successfully. This should not have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as it will 
			// disallow empty input.
			//
		}
		catch (Exception e)
		{
			fail(
				"Constructing an ArgumentReader with an empty expression threw " +
				"the exception " + e.getMessage() + ". However, " +
				"an ApplicationException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_readLine()
	throws Exception
	{
		ArgumentReader reader;
		
		try
		{
			reader = 
				new ArgumentReader(new InputStreamReader(System.in), EXPRESSION);
			
			reader.readLine();
			
			fail(
				"Reading a line of input from an ArgumentReader completed " +
				"successfully. This should not have happened!");
		}
		catch (UnsupportedOperationException e)
		{
			//
			// Expecting this test case to throw this exception as this method
			// is unsupported
			//
		}
		catch (Exception e)
		{
			fail(
				"Reading a line of input from an ArgumentReader threw the " +
				"exception " + e.getMessage() + ". However, an " +
				"UnsupportedOperationException was expected. This should not " +
				"have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_read()
	throws Exception
	{
		ArgumentReader reader;
		
		try
		{
			reader = 
				new ArgumentReader(new InputStreamReader(System.in), EXPRESSION);
			
			reader.read();
			
			fail(
				"Reading input from an ArgumentReader completed " +
				"successfully. This should not have happened!");
		}
		catch (UnsupportedOperationException e)
		{
			//
			// Expecting this test case to throw this exception as this method
			// is unsupported
			//
		}
		catch (Exception e)
		{
			fail(
				"Reading input from an ArgumentReader threw the exception " + 
				e.getMessage() + ". However, an UnsupportedOperationException " +
				"was expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_readWithParams()
	throws Exception
	{
		ArgumentReader reader;
		
		try
		{
			reader = 
				new ArgumentReader(new InputStreamReader(System.in), EXPRESSION);
			
			reader.read(new char []{}, 0, 10);
			
			fail(
				"Reading input from an ArgumentReader completed " +
				"successfully. This should not have happened!");
		}
		catch (UnsupportedOperationException e)
		{
			//
			// Expecting this test case to throw this exception as this method
			// is unsupported
			//
		}
		catch (Exception e)
		{
			fail(
				"Reading input from an ArgumentReader threw the exception " + 
				e.getMessage() + ". However, an UnsupportedOperationException " +
				"was expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Allows running this test suite from the command line
	 */
	public static void main(String... args)
	{
		JUnitCore.main(Test_ArgumentReader.class.getName());
	}
}