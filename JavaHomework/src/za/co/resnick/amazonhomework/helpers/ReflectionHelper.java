package za.co.resnick.amazonhomework.helpers;

import java.lang.reflect.Method;

import za.co.resnick.amazonhomework.exceptions.ApplicationException;

/**
 * This class provides {@link Class} reflection utility methods required by
 * this application.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class ReflectionHelper 
{
	/**
	 * Reflects a public method.  
	 * 
	 * @param clazz
	 * 		the class
	 * @param methodName
	 * 		the name of the method, must not be <code>null</code>
	 * @param parameterTypes
	 * 		the list of parameters
	 * 
	 * @return
	 * 		the reflected method
	 * 
	 * @throws ApplicationException
	 * 		if an error occurs whilst reflecting the method
	 */
	public static Method reflectMethod(
		Class<?> 	clazz, 
		String		methodName, 
		Class<?>... parameterTypes)
	throws ApplicationException
	{
		validateClassAndMethod(clazz, methodName);
		
		try
		{
			return clazz.getMethod(methodName, parameterTypes);
		}
		catch (Exception e)
		{
			throw new ApplicationException(e);
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Reflects a public <code>Integer...</code> (variable arguments) method.  
	 * 
	 * @param clazz
	 * 		the class
	 * @param methodName
	 * 		the name of the method, must not be <code>null</code>

	 * @return
	 * 		the reflected method
	 * 
	 * @throws ApplicationException
	 * 		if an error occurs whilst reflecting the method
	 */
	public static Method reflectIntVarArgMethod(
		Class<?> 	clazz,
		String 		methodName)
	throws ApplicationException
	{
		return reflectMethod(clazz, methodName, int[].class);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Guarantees a valid, non-null method name. 
	 * 
	 * @param methodName
	 * 		the name of the method
	 * 
	 * @throws ApplicationException
	 * 		if the method name is invalid
	 */
	private static void validateClassAndMethod(
		Class<?> 	clazz, 
		String 		methodName)
	throws ApplicationException
	{
		if (clazz == null || methodName == null)
			throw new ApplicationException(
				"Invalid parameter 'null', expecting non-null method name");
	}
}
