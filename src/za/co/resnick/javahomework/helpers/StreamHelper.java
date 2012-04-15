package za.co.resnick.javahomework.helpers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import za.co.resnick.javahomework.exceptions.ApplicationException;
import za.co.resnick.javahomework.readers.ArgumentReader;

/**
 * This class provides input and output stream utility methods required by this 
 * application.
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class StreamHelper 
{
	/**
	 * Constructs a {@link PrintWriter} object from the {@link OutputStream}
	 * provided.
	 * 
	 * @param stream
	 * 		the output stream
	 * 
	 * @return
	 * 		a writer
	 */
	public static PrintWriter setOutputStreamWriter(OutputStream stream)
	{
		return new PrintWriter(new OutputStreamWriter(stream));
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Constructs a {@link PrintWriter} object for files from the 
	 * <code>filename</code> provided.
	 * 
	 * @param filename
	 * 		the name of the file
	 * 
	 * @return
	 * 		a writer
	 * 
	 * @throws FileNotFoundException
	 * 		if the source of input does not exist or the user executing this
	 * 		method has insufficient privileges to access this source. 
	 */
	public static PrintWriter setOutputStreamWriter(String filename)
	throws FileNotFoundException
	{
		return setOutputStreamWriter(new FileOutputStream(filename));
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Constructs an {@link ArgumentReader} object for files from the
	 * <code>filename</code> and regular <code>expression</code>
	 * provided.
	 * 
	 * @param filename
	 * 		the name of the file
	 * @param expression
	 * 		The expression to be compiled		
	 * 
	 * @return
	 * 		an <code>ArgumentReader</code>
	 * 
	 * @throws ApplicationException
	 * 		if the regular expression is invalid
	 * @throws FileNotFoundException
	 * 		if the source of input does not exist or the user executing this
	 * 		method has insufficient privileges to access this source. 
	 */
	public static ArgumentReader setArgumentReader(
		String filename,
		String expression) 
	throws ApplicationException, FileNotFoundException
	{
		return new ArgumentReader(new FileReader(filename), expression);
	}
}

