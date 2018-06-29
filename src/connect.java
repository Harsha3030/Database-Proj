import java.sql.*; 
import javax.swing.*;

/* @author Harsha Vardhan Reddy Nallagatla  SIU 853965332*/


public class connect {

	Connection conn=null;

	public static Connection dbconnector(){

		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			//step2 create  the connection object  
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","oracle");  
			return conn;
		}
		catch(Exception e)
		{ 
			JOptionPane.showMessageDialog(null,e);
			return null;
		}  
	}
}