import java.sql.*; //HAVE TO IMPORT. IT PROVIDES THE API FOR ACCESSING AND PROCESSING INFO FROM A DATABASE
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner; //USED TO GET USER IMPUT FROM CONSOLE



public class driver
{

    public void queryFunction(Connection conn, Statement stmt, String q, int num) throws SQLException
    {
        System.out.println(" ");
        System.out.println("I will now print out the time it took to do repeated query (in milliseconds)");
        System.out.println(" ");
        String query1 = q;

        for(int x=1; x<num+1; x++)
            {
                Instant start = Instant.now();
                stmt.executeQuery(query1);
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                System.out.println("Time taken for query execution (without index on  salaries) "+x + ": "+ timeElapsed.toMillis() +" milliseconds");        
            }    
    }

    public void queryForTask4(Connection conn, Statement stmt, String q) throws SQLException
    {
        System.out.println(" ");
        System.out.println("I will now print out the results for task 4's query");
        System.out.println(" ");
        String query1 = q;

            
    }


public static void main (String[] args)
    {

        Connection conn = null;//USED TO HOLD A CONNECTION. TRY CONNECT, ELSE CATCH EXCEPTION
        try 
        {
            String userName ="root"; //CHANGE TO YOUR USERNAME HERE IF IT NOT CALLED ROOT ON YOUR LOCALHOST SERVER ON YOUR MYSQL WORKBENCH
            String password ="Godwillneverletmedown";  //CHANGE TO YOUR PASSWORD THAT YOU USE FOR YOUR MYSQL LOCALHOST SERVER                                         
            String url = "jdbc:mysql://127.0.0.1:3306/u19304308_business"; //EVERYTHING HERE SHOULD STAY THE SAME UNLESS YOUR PORT NUMBER IS NOT 3306
            Class.forName("com.mysql.jdbc.Driver"); //CALLING A CLASS FROM A JAVA LIBRARY THAT WE NEED
            conn = DriverManager.getConnection(url, userName, password);//, password); //GET CONNECTED TO THE DATABASE
            System.out.println(" ");
            System.out.println("Database connection established");
            System.out.println(" ");
            Statement stmt=conn.createStatement();
            System.out.println("Welcome to the Business database.");
            Scanner in= new Scanner(System.in); //USED TO GET USER IMPUT
            System.out.println(" ");
            System.out.println("Type 1 to execute the query. ");
            System.out.println(" ");
            Integer number=in.nextInt(); 
            
            driver object=new driver();

            // "SELECT count(distinct(emp_no)) from salaries where salary>50000 and salary<70000 and timestampdiff(YEAR, from_date,to_date) < 1"

            switch(number)
            {
                case 1: //1. THIS FUNCTION QUERYS THE DATABASE AND ADDS A STUDENT TO THE DATABASE
                try{ 
                        System.out.println(" ");
                        System.out.println("Enter your query");
                        in.nextLine();
                        String query=in.nextLine(); 
                        System.out.println(" ");
                        System.out.print("Enter the number of times it must be executed");
                        System.out.println(" ");
                        Integer num=in.nextInt();
                        object.queryFunction(conn, stmt, query, num );
                        in.close();
                        break;
                } 
                catch(Exception e)
                {
                        System.out.println(" ");
                        System.out.println(e);
                        break;
                }
            }
        }

        catch (Exception e)
            {
                    System.out.println(e);
                System.err.println("Cannot connect to database server eish..");
            }

        finally
        {
            if (conn != null)
            {    try
                    {   
                        System.out.println();
                        System.out.println("Database connection will now close.  Run the program again to do another query.");
                        conn.close ();
                        System.out.println(" ");
                        System.out.println("Database connection has been terminated.");
                        System.out.println(" ");
                        System.out.println("Goodbye.");
                        System.out.println(" ");
                    }
                catch(Exception  e)
                    {
                            System.out.println("Unsuccessful closing the database connection");
                        
                    }
            }
        }  
    }
}
    

