package za.co.resnick.amazonhomework.drivers;

import static za.co.resnick.amazonhomework.helpers.IntegerHelper.tokenise;
import static za.co.resnick.amazonhomework.helpers.ReflectionHelper.reflectIntVarArgMethod;
import static za.co.resnick.amazonhomework.helpers.StreamHelper.setArgumentReader;
import static za.co.resnick.amazonhomework.helpers.StreamHelper.setOutputStreamWriter;
import static za.co.resnick.amazonhomework.helpers.PrintHelper.println;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Deque;

import za.co.resnick.amazonhomework.exceptions.ApplicationException;
import za.co.resnick.amazonhomework.helpers.IntegerHelper;
import za.co.resnick.amazonhomework.helpers.PrintHelper;
import za.co.resnick.amazonhomework.helpers.ReflectionHelper;
import za.co.resnick.amazonhomework.helpers.StreamHelper;
import za.co.resnick.amazonhomework.libraries.MathLibrary;
import za.co.resnick.amazonhomework.readers.ArgumentReader;

/**
 * This class is the application workhorse; it leverages the various
 * application helpers, libraries and readers to drive the application. 
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 * 
 * @see IntegerHelper#tokenise(String)
 * @see PrintHelper#println(PrintWriter, String)
 * @see PrintHelper#println(PrintWriter, int)
 * @see ReflectionHelper#reflectIntVarArgMethod(Class, String)
 * @see StreamHelper#setArgumentReader(String, String)
 * @see StreamHelper#setOutputStreamWriter(String)
 */
public class ApplicationDriver 
{
	/**
	 * The regular expression that expresses the pattern against which input
	 * will be matched. For the purpose of this assignment this is statically 
	 * assigned.
	 */
	private static final String INPUT_EXPRESSION = 
		"([a-zA-Z0-9]{3});([0-9]+(,[0-9]+)*)";
	
	/**
	 * If set to <code>true</code>, the mapping of method names is insensitive
	 * of case. Conversely, if set to <code>false</code>, method names will be
	 * sensitive of case.  
	 */
	private static final boolean CASE_INSENSITIVE = true;
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Contains the {@link PrintWriter} used to write the resulting output.
	 */
	private PrintWriter 	 out;
	
	/**
	 * Contains the {@link ArgumentReader} used to read and process the input.
	 */
	private ArgumentReader   in;
	
	/**
	 * Contains an {@link ArrayDeque} of {@link Throwable} exception objects
	 * encountered during processing. 
	 * <p>
	 * Note that onus is deferred to the calling
	 * class to call the {@link ApplicationDriver#report()} or 
	 * {@link ApplicationDriver#report(OutputStream)} methods to inspect these
	 * exceptions. 
	 */
	private Deque<Throwable> q = new ArrayDeque<Throwable>();
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Constructs and initialises a new <code>ApplicationDriver</code> object.
	 * 
	 * @param args
	 * 		The application arguments
	 * 
	 * @throws Exception
	 * 		if an error is encountered during initialisation
	 * 
	 * @see ApplicationDriver#initialise(String...)
	 */
	public ApplicationDriver(String... args)
	throws Exception
	{
		initialise(args);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Responsible for the execution of the application; reading and 
	 * processing input and writing output. 
	 * <p>
	 * This method does the bulk of the application's heavy lifting. 
	 * <p>
	 * Further, it also contains a critical assumption (based on the assignment 
	 * specifications) that all method arguments in the input provided are
	 * <code>Integer</code>s. This violates the explicit decoupling of the
	 * <code>INPUT_EXPRESSION</code>, {@link ReflectionHelper} and this method.
	 * <p>
	 * Fortunately the rigid specification allows us to, at least for the scope
	 * of this assignment, rely on the aforementioned assumption.
	 *   
	 * @return
	 * 		<code>true</code> if processing completely successfully, 
	 * 		<code>false</code> otherwise 
	 * 
	 * @throws IOException
	 * 		if an error occurs whilst reading from the source of input
	 */
	public boolean execute() 
	throws IOException
	{
		String [] arguments;
		
		String methodName, methodArguments;
		
		//
		// Iterate while the input stream buffer is non-empty and the
		// underlying character stream is ready. 
		//
		while (in.ready())
		{
			try
			{	
				//
				// Extract the per-line arguments. If the regular expression
				// INPUT_EXPRESSION is used, the resulting arguments follow the
				// format:
				// 		arguments[]
				//			[0]: method name
				//			[1]: method arguments, integers comma delimited
				//
				arguments = in.readArguments();

				//
				// Assign the arguments for clarity
				// 
				methodName 		= arguments[0];
				methodArguments = arguments[1];
				
				//
				// Locate the appropriate method in MathLibrary using 
				// reflection. The ReflectionHelper class exposes a 
				// utility method for methods with int... arguments (varargs).
				//
				Method method =
					reflectIntVarArgMethod(
						MathLibrary.class,
						(CASE_INSENSITIVE ? methodName.toLowerCase() : methodName));
				
				//
				// Tokenise the method arguments into (principle assumption) 
				// an integer array and invoke the method with these arguments.
				//
				int result = 
					(Integer) method.invoke(
								MathLibrary.getInstance(),
								tokenise(methodArguments));
				
				//
				// Write the method result into the output stream.
				//
				println(out, result);
	
			}
			catch (Exception e)
			{
				//
				// Record any exceptions that may occur whilst 
				// processing a single entry but allow processing to 
				// continue.
				//
				q.add(e);
			}
		}
		
		//
		// Close the input and output streams
		//
		close();

		//
		// Return the exit state
		//
		return checkError();
	}
	
	/*------------------------------------------------------------------------*/

	/**
	 * Inspects the application for any exceptions encountered during execution.
	 * 
	 * @return
	 * 		<code>true</code> if processing completely successfully, 
	 * 		<code>false</code> otherwise
	 */
	public boolean checkError()
	{
		//
		// The presence of any exceptions in the queue indicates that the
		// application did not execute successfully. The onus is on the calling
		// class to ensure that, if required, errors are logged to the error
		// output stream via the report() method.
		//
		return q.isEmpty();
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Reports on all exceptions encountered by the application during 
	 * processing.
	 * 
	 * @param stream
	 * 		The {@link OutputStream} in which to report the errors
	 */
	public void report(OutputStream stream)
	throws ApplicationException
	{
		//
		// Create and wrap the OutputStream with an 'easy-to-use' Writer.
		//
		PrintWriter err = new PrintWriter(stream);
		
		//
		// Report each exception in the order in which they were encountered
		// (insertion order).
		//
		for (Throwable t : q)
			println(err, t.getClass().getSimpleName() + ": " + t.getMessage());
		
		//
		// Close the output stream
		//
		err.close();
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Reports on all exceptions encountered by the application during 
	 * processing through {@link System#err}.
	 * 
	 * @see ApplicationDriver#report(OutputStream)
	 * @see System#err
	 */
	public void report()
	throws ApplicationException
	{
		//
		// Defer to report(OutputStream), defaulting the OutputStream to 
		// System.err.
		//
		report(System.err);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Initialises input and output streams once validation of the application
	 * arguments has been completed.
	 * 
	 * @param args
	 * 		The command line arguments passed to this application instance.
	 * 
	 * @throws ApplicationException
	 * 		if the regular expression provided to {@link ArgumentReader} 
	 * 		is invalid
	 * @throws InvalidParameterException
	 * 		if the command line arguments are missing or enumerate to an
	 * 		unexpected number of arguments
	 * @throws FileNotFoundException
	 * 		if the source of input does not exist or the user executing this
	 * 		method has insufficient privileges to access this source. 
	 */
	private void initialise(String... args)
	throws ApplicationException, InvalidParameterException, FileNotFoundException
	{
		//
		// Ensure the validity of the application arguments. In our case we
		// explicitly (and statically) reject cases where there is not exactly
		// one argument - the filename (or file path).
		//
		if (args == null || args.length != 1)	
			throw new InvalidParameterException("Expected only one argument"); 

		//
		// Assign the argument for clarity
		//
		String filename = args[0];
		
		//
		// Initialise the input and output streams. Note that because the
		// behaviour under error conditions was not discussed (read: specified)
		// errors are *optionally* shown via System.err. 
		//
		in = setArgumentReader(filename, INPUT_EXPRESSION);		
		out = setOutputStreamWriter(filename + ".out");
	}
		
	/*------------------------------------------------------------------------*/
	
	/**
	 * Closes both {@link InputStream} and {@link OutputStream} streams.
	 *  
	 * @throws IOException
	 * 		if an error occurs whilst closing the streams
	 */
	private void close()
	throws IOException
	{
		//
		// Close the input stream
		// 
		in.close();
		
		//
		// Close the output stream
		//
		out.close();
	}
}
