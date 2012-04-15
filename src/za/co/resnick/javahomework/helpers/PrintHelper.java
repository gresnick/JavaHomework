package za.co.resnick.javahomework.helpers;

import java.io.PrintWriter;

import za.co.resnick.javahomework.exceptions.ApplicationException;

/**
 * This class provides output writing utility methods required by this
 * application.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class PrintHelper 
{
	/**
	 * Prints <code>x</code> to the output stream <code>writer</code>.
	 * 
	 * @param writer
	 * 		A writer
	 * @param x
	 * 		An <code>Integer</code> to write
	 */
	public static void println(PrintWriter writer, int x)
	throws ApplicationException
	{
		//
		// Output is application critical, we are unable to continue if 
		// no output stream is specified.
		//
		if (writer == null)
			throw new ApplicationException(
				"Unable to write output as no output stream was provided");
		
		//
		// Write the output and subsequently flush the streams buffer.
		//
		writer.println(x);	
		writer.flush();
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Prints <code>x</code> to the output stream <code>writer</code>.
	 * 
	 * @param writer
	 * 		A writer
	 * @param x
	 * 		An <code>String</code> to write
	 */
	public static void println(PrintWriter writer, String x)
	throws ApplicationException
	{
		//
		// Output is application critical, we are unable to continue if 
		// no output stream is specified.
		//
		if (writer == null)
			throw new ApplicationException(
				"Unable to write output as no output stream was provided");
		
		//
		// Write the output and subsequently flush the streams buffer.
		//
		writer.println(x);	
		writer.flush();
	}
}
