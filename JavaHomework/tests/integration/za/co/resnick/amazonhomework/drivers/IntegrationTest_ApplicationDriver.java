package za.co.resnick.amazonhomework.drivers;

import java.io.File;

/**
 * This class exercises the {@link ApplicationDriver} class, and, by proxy, 
 * the entire application.
 * <p>
 * Note that for this class to execute correctly, it must be run from the /bin
 * directory, e.g.
 * <ul>
 * 		<li>
 * 		.../bin $> java za.co.resnick.amazonhomework.drivers.IntegrationTest_ApplicationDriver
 * 		</li>
 * </ul>
 * 
 * @author Gary Resnick (gary@resnick.co.za)
 */
public class IntegrationTest_ApplicationDriver 
{
	private static int TEST_FAILURE = -1;
	private static int TEST_SUCCESS =  0;
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Assumes execution from the /bin directory
	 */
	public static String SAMPLE_INPUT = 
		joinPath(
			new String[]{
				"..",
				"resources",
				"sample_input",
				"test.txt"});
	
	/*------------------------------------------------------------------------*/

	/**
	 * Allows running this test suite from the command line
	 */
	public static void main(String... args) 
	{
		try
		{
			new ApplicationDriver(SAMPLE_INPUT).execute();

			System.exit(TEST_SUCCESS);
		}
		catch (Exception e)
		{
			System.exit(TEST_FAILURE);
		}
	}
	
	/*------------------------------------------------------------------------*/
	
	/**
	 * Utility method to join file paths
	 * 
	 * @param paths
	 * 		file paths to join
	 * @return
	 * 		joined paths
	 */
	private static String joinPath(String... paths)
	{
		StringBuilder builder = new StringBuilder();
		
		for (String path : paths)
			builder.append(path + File.separator);
		
		return builder.toString().substring(0, builder.length() -1);
	}
}
