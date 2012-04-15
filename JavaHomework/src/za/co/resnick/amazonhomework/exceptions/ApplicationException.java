package za.co.resnick.amazonhomework.exceptions;

/**
 * This class provides a base wrapper for resident application exceptions.
 *  
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class ApplicationException 
extends Exception 
{
	/**
	 * Unique object identifier
	 */
	private static final long serialVersionUID = -5717272705208581716L;

	/*------------------------------------------------------------------------*/
	
	/**
	 * Constructs and initialises a new <code>ApplicationException</code>
	 * instance with the <code>message</code> supplied. 
	 * 
	 * @param message
	 * 		the detailed exception message
	 */
	public ApplicationException(String message) 
	{
		super(message);
	}
	
	/*------------------------------------------------------------------------*/

	/**
	 * Constructs and initialises a new <code>ApplicationException</code>
	 * instance with the <code>cause</code> supplied. 
	 * 
	 * @param cause
	 * 		the cause
	 */
	public ApplicationException(Throwable cause) 
	{
		super(cause);
	}
	
	/*------------------------------------------------------------------------*/

	/**
	 * Constructs and initialises a new <code>ApplicationException</code>
	 * instance with the <code>message</code> and <code>cause</code> supplied. 
	 * 
	 * @param message
	 * 		the detailed exception message
	 * @param cause
	 * 		the cause
	 */
	public ApplicationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
