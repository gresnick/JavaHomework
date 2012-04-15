package za.co.resnick.javahomework.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import za.co.resnick.javahomework.exceptions.ApplicationException;

/**
 * This class utilises regular expression pattern matching to perform
 * argument validation whilst reading from a character-input stream.
 *  
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class ArgumentReader 
extends BufferedReader
{
	/**
	 * The regular expression pattern that will be used to validate input 
	 * arguments.
	 */
	private Pattern expression;
	
	/*------------------------------------------------------------------------*/

	/**
	 * Constructs and initialises a new <code>ArgumentReader</code> object with
	 * a <code>Reader</code> through which the input will be read, the
	 * regular expression against which arguments will be validated and the 
	 * bitmask <code>Integer</code> flags that augment the patterns matching 
	 * behaviour. 
	 * 
	 * @param in
	 * 		A reader
	 * @param expression
	 * 		The expression to be compiled
	 * @param flags
	 *		Match flags, a bit mask that may include 
	 *		{@link Pattern#CASE_INSENSITIVE}, {@link Pattern#MULTILINE}, 
	 *		{@link Pattern#DOTALL}, {@link Pattern#UNICODE_CASE}, 
	 *		{@link Pattern#CANON_EQ}, {@link Pattern#UNIX_LINES}, 
	 *		{@link Pattern#LITERAL} and {@link Pattern#COMMENTS}. 
	 *
	 * @throws ApplicationException
	 * 		if the expression is invalid
	 * 
	 * @see Pattern#compile(String, int)
	 */
	public ArgumentReader(Reader in, String expression, int flags) 
	throws ApplicationException
	{
		super(in);
		
		setExpression(expression, flags);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Constructs and initialises a new <code>ArgumentReader</code> object with
	 * a <code>Reader</code> through which the input will be read, the
	 * regular expression against which arguments will be
	 * validated.
	 * 
	 * @param in
	 * 		A reader
	 * @param expression
	 * 		The expression to be compiled
	 * 
	 * @throws ApplicationException
	 * 		if the expression is invalid
	 */
	public ArgumentReader(Reader in, String expression)
	throws ApplicationException
	{
		super(in);
		
		setExpression(expression);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Private constructor, prevents instantiation of this class 
	 * without providing a regular expression.
	 * 
	 * @param in
	 * 		A reader
	 * @param sz
	 * 		Input-buffer size
	 */
	private ArgumentReader(Reader in, int sz)
	{
		super(in, sz);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Private constructor, prevents instantiation of this class 
	 * without providing a regular expression.
	 * 
	 * @param in
	 * 		A reader
	 */
	private ArgumentReader(Reader in)
	{
		super(in);
	}

	/*------------------------------------------------------------------------*/
	
	/**
	 * Reads arguments from a line of text. A line is considered to be 
	 * terminated by any one of a line feed ('\n'), a carriage return ('\r'), 
	 * or a carriage return followed immediately by a linefeed. 
	 * 
	 * @return
	 * 		a <code>String[]</code> of arguments
	 * 
	 * @throws UnexpectedArgumentException
	 * 		if the input does not match the argument regular expression
	 * @throws IOException
	 * 		if an I/O error occurs
	 */
	public String [] readArguments()
	throws ApplicationException, IOException
	{
		String argumentLine = super.readLine();

		if (argumentLine == null)
			return null;

		//
		// Apply regular expression 
		//
		Matcher matcher = expression.matcher(argumentLine);
	
		if (matcher.matches())
		{
			int count = matcher.groupCount();
			
			String [] arguments = new String[count -1];
			
			//
			// The zero-th match comprises the entire expression, ignore it
			//
			for (int i = 1; i < count; i++)
				arguments[i -1] = matcher.group(i);
			
			return arguments;
		}
		
		//
		// Unexpected argument - did not match expression
		//
		throw new ApplicationException(
			"The input '" + argumentLine + "' does not match expression '" + 
			expression.pattern() + "'.");
	}

	/*------------------------------------------------------------------------*/
	
	@Override
	public String readLine()
	{
		throw new UnsupportedOperationException();
	}
	
	/*------------------------------------------------------------------------*/
	
	@Override
	public int read(char cbuf[], int off, int len)
	{
		throw new UnsupportedOperationException();
	}
	
	/*------------------------------------------------------------------------*/
	
	@Override
	public int read()
	{
		throw new UnsupportedOperationException();
	}
	
	/*------------------------------------------------------------------------*/

	/**
	 * Sets the regular expression against which input arguments will 
	 * be matched.
	 * 
	 * @param expression
	 * 		The expression to be compiled
	 * @param flags
	 *		Match flags, a bit mask that may include 
	 *		{@link Pattern#CASE_INSENSITIVE}, {@link Pattern#MULTILINE}, 
	 *		{@link Pattern#DOTALL}, {@link Pattern#UNICODE_CASE}, 
	 *		{@link Pattern#CANON_EQ}, {@link Pattern#UNIX_LINES}, 
	 *		{@link Pattern#LITERAL} and {@link Pattern#COMMENTS}. 
	 *
	 * @throws ApplicationException
	 * 		if the expression is invalid
	 */
	public void setExpression(String expression, int flags)
	throws ApplicationException
	{
		validateExpression(expression);
		
		this.expression = Pattern.compile(expression, flags);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Sets the regular expression against which input arguments will 
	 * be matched.
	 * 
	 * @param expression
	 * 		The expression to be compiled
	 * 
	 * @throws ApplicationException
	 * 		if the expression is invalid
	 */
	public void setExpression(String expression)
	throws ApplicationException
	{
		validateExpression(expression);
		
		this.expression = Pattern.compile(expression);
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Guarantees a valid, non-null regular expression
	 * 
	 * @param expression
	 * 		The expression to be validated
	 * 
	 * @throws ApplicationException
	 * 		if the expression is invalid
	 */
	private void validateExpression(String expression)
	throws ApplicationException
	{
		if (expression == null || expression.equals(""))
			throw new ApplicationException(
				"Invalid parameter 'null', expecting non-null regular expression");
	}
}

