package za.co.resnick.javahomework.helpers;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import za.co.resnick.javahomework.exceptions.ApplicationException;
import za.co.resnick.javahomework.helpers.ReflectionHelper;

/**
 * This class provides a suite of JUnit test cases that exercise the
 * {@link ReflectionHelper} class.
 *  
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class Test_ReflectionHelper 
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
	public void test_reflectMethod()
	throws Exception
	{
		try
		{
			ReflectionHelper.reflectMethod(
				TestClass.class, 
				"publicMethod", 
				new Class[]{
					String.class,
					int.class});
		}
		catch (Exception e)
		{
			fail(
				"Reflecting public method with correct method name and " +
				"arguments (or signature) threw the exception " + 
				e.getMessage() + ". This should not have happened!");
		}
		
		//
		// Expecting this test case to reflect the method successfully
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_reflectMethodIncorrectArguments()
	throws Exception
	{
		try 
		{
			ReflectionHelper.reflectMethod(
					TestClass.class, 
					"publicMethod", 
					new Class[]{
						String.class,
						boolean.class});
			
			fail(
				"Reflecting public method with correct method name but " +
				"invalid arguments completed successfully. This should not " +
				"have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as it will
			// not be able to locate and thus reflect the method with
			// signature publicMethod(String,boolean).
			//
		}
		catch (Exception e)
		{
			fail(
				"Reflecting public method with correct method name but " +
				"invalid arguments threw the exception " + e.getMessage() + 
				". However, an ApplicationException was expected. This should " +
				"not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_reflectMethodNotVisible()
	throws Exception
	{
		try 
		{
			ReflectionHelper.reflectMethod(
					TestClass.class, 
					"privateMethod", 
					String.class);
			
			fail(
				"Reflecting private method with correct method name and " +
				"arguments completed successfully. This should not " +
				"have happened!");
		}
		catch (ApplicationException e)
		{
			//
			// Expecting this test case to throw this exception as it will
			// not be able to locate and thus reflect the method with
			// signature privateMethod(String). We are only inspecting public
			// methods of TestClass.
			//
		}
		catch (Exception e)
		{
			fail(
				"Reflecting private method with correct method name and " +
				"arguments threw the exception " + e.getMessage() + 
				". However, an ApplicationException was expected. This should " +
				"not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_reflectMethodIntVarArgs()
	throws Exception
	{
		try
		{
			ReflectionHelper.reflectIntVarArgMethod(
				TestClass.class, 
				"publicVarIntArgMethod");
		}
		catch (Exception e)
		{
			fail(
				"Reflecting public method with variable length integer " +
				"arguments threw the exception " + 	e.getMessage() + 
				". This should not have happened!");
		}
		
		//
		// Expecting this test case to reflect the method successfully
		//
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_reflectMethodNullClass()
	throws Exception
	{
		try 
		{
			ReflectionHelper.reflectMethod(
					null, 
					"publicMethod", 
					new Class[]{
						String.class,
						int.class});
			
			fail(
				"Reflecting public method with correct method name and " +
				"arguments on an unspecified class completed successfully. " +
				"This should not have happened!");
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
				"Reflecting public method with correct method name and " +
				"arguments on an unspecified class threw the exception " + 
				e.getMessage() + ". However, an ApplicationException was " +
				"expected. This should not have happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@Test
	public void test_reflectMethodNullMethod()
	throws Exception
	{
		try 
		{
			ReflectionHelper.reflectMethod(
					TestClass.class, 
					null, 
					new Class[]{
						String.class,
						int.class});
			
			fail(
				"Reflecting public method with unspecified method name " +
				"completed successfully. This should not have happened!");
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
				"Reflecting public method with unspecified method name threw " +
				"the exception " + e.getMessage() + ". However, an " +
				"ApplicationException was expected. This should not have " +
				"happened!");
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	@SuppressWarnings("unused")
	private class TestClass
	{
		public int publicMethod(String str, int num)
		{
			return 1337;
		}
		
		private int privateMethod(String str)
		{
			return 0;
		}
		
		public String publicVarIntArgMethod(int... args)
		{
			return "Local is lekker";
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Allows running this test suite from the command line
	 */
	public static void main(String... args)
	{
		JUnitCore.main(Test_ReflectionHelper.class.getName());
	}
}