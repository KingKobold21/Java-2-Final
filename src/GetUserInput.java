import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Chad A Hammons
 * @version 0.1
 * @since 4/29/2025
 */



/**
 * Name: GetUserInput
 * Abstract: Calls the other two classes
 */
public class GetUserInput {

	/**
	 * Name: Main (GetUserInput)
	 * @param args
	 */
	public static void main(String[] args) {

		getUserInput();
	}
	
	
	
	/**
	 * Name:getUserInput
	 * Abstract:takes input from a user and calls the other classes
	 */
	public static void getUserInput()
	{
		
		//Variable to hole user input
		int intUserInput = 0;
		do
		{
			
		System.out.print("Please choose to (1) View Customers, or (2) Modify the customer XML file.");
		intUserInput = ReadIntegerFromUser();
		
		}while(intUserInput != 1 && intUserInput != 2);
		
		if(intUserInput == 1)
		{
			ViewCustomers.main(null);;
		}
		else
		{
			ModifyCustomers.main(null);
		}
	}
	
	
	
	/**
	 * Name:ReadIntegerFromUser
	 * Abstract: Takes in an integer from the user.
	 * @return
	 */
	public static int ReadIntegerFromUser( )
	{			  

		int intValue = 0;

		try
		{
			String strBuffer = "";	

			// Input stream
			BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

			// Read a line from the user
			strBuffer = burInput.readLine( );
			
			// Convert from string to integer
			intValue = Integer.parseInt( strBuffer );
		}
		catch( Exception excError )
		{
			System.out.println( excError.toString( ) );
		}
		
		// Return integer value
		return intValue;
	}

}
