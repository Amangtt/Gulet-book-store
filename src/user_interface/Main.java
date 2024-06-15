package user_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String [] args)
	{	
		mainHandler();
	}
	public static void mainHandler()
	{
		MainFrame mainFrame = new MainFrame();
		
		Login login = new Login(mainFrame);
		login.loginPage();
		mainFrame.mainPage();

	}
	
	public static Connection getConnection() {
		Connection connection = null;
		String url= "jdbc:sqlserver://127.0.0.1:1433;instanceName=MSSQLSERVER2019;user=aman;password=aman123708;databaseName=BookStore1";
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "The program couldn't retrive the database","Error message",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
				
		try {
			connection= DriverManager.getConnection(url);
			System.out.println("Connected");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "The program couldn't retrive the database","Error message",JOptionPane.WARNING_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
