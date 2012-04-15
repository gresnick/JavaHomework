package za.co.resnick.amazonhomework;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;

import za.co.resnick.amazonhomework.drivers.ApplicationDriver;

/**
 * This class is the entry point for the application and is thus responsible
 * for launching the application.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class Launcher 
{
	/**
	 * Allows this application to be started from the command line. 
	 * 
	 * @param args
	 * 		The command line arguments to be passed to the application instance.
	 */
	public static void main(String[] args) 
	throws Exception
	{
		//
		// Application instance
		//
		ApplicationDriver appDriver;
		
		try
		{
			//
			// Instantiate the application, passing command line arguments
			//
			appDriver = new ApplicationDriver(args);
			
			//
			// Execute the application - or invoke the application to begin
			// processing. Note that, if exceptions are encountered during
			// application processing, we explicitly request the application
			// reports these exceptions to the standard error stream.
			//
			if (!appDriver.execute());
				appDriver.report();
		}
		catch (InvalidParameterException e)
		{
			//
			// Display command line usage
			//
			help();
		}
		catch (FileNotFoundException e)
		{
			//
			// Erroneous input
			//
			help(true);
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Displays the command line syntax of this application.
	 */
	private static void help()
	{
		help(false);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Displays the command line syntax of this application.
	 * 
	 * @param erroneousInput
	 * 		if <code>true</code>, notifies the caller that the input was
	 * 		invalid. Conversely, if <code>false</code> only displays command
	 *      usage syntax.
	 */
	private static void help(boolean erroneousInput)
	{
		if (erroneousInput)
			System.out.println(
				"Erroneous input:\nPlease ensure the argument(s) match the " +
				"syntax dictated below. Furthermore, if arguments refer to " +
				"any system resources, ensure the resources exist and this " +
				"application has sufficient permissions to access them.\n");
		
		System.out.println(
			"Syntax:\nza.co.resnick.amazonhomework.Launcher <FILE>");
	}
}
