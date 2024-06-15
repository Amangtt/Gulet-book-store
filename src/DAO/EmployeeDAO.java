package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.*;
import user_interface.Main;

public class EmployeeDAO {
	
	private Connection myCon = null;
	public EmployeeDAO() 
	{
		myCon = Main.getConnection();
	}
	public Employee ConvertToEmployee(ResultSet result)
	{
		Employee temp = null;
		try {
			String fName = result.getString("fName");
			String lName =  result.getString("lName");
			String tel1 = result.getString("tel");
			String tel2 = result.getString("tel2");
			String typeOfUser = result.getString("typeOfUser");
			String userName = result.getString("userName");
			String password = result.getString("password");
			double salary = result.getFloat("salary");
			String gender = result.getString("gender");
			int id = result.getInt("id");
			int managerId = result.getInt("managerId");
			
			temp = new Employee(fName, lName, tel1, tel2, typeOfUser, userName, password, salary, gender, id, managerId);
		}
		catch(Exception e)
		{	
			e.printStackTrace();
		}
		return temp;
	}

	public List<Employee> getSearch(String text)//search Employee
	{	
		List<Employee> temp = new ArrayList<>();
		CallableStatement myStmt = null;
		try {
			myStmt = myCon.prepareCall("{call Search_Employee(?)}");
			
			myStmt.setString(1, text);
			myStmt.execute();
			
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				Employee tempEmployee = ConvertToEmployee(result);
				temp.add(tempEmployee);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	public List<Employee> getAllEmployee()//get all Employee
	{

		List<Employee> temp = new ArrayList<>();
		CallableStatement myStmt = null;
		try {
			myStmt = myCon.prepareCall("{call GetAll_Employee()}");
			
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				Employee tempEmployee = ConvertToEmployee(result);
				temp.add(tempEmployee);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	public ResultSet authenticate(String userName, String password)//get authentication
	{	
		CallableStatement myStmt = null;
		ResultSet result = null;
		
		try {
			myStmt = myCon.prepareCall("{call Authenticate(?, ?)}");
			
			myStmt.setString(1, userName);
			myStmt.setString(2, password);
			myStmt.execute();
			
			result = myStmt.getResultSet();
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public void delete_Employee(int id)
	{	
		CallableStatement myStmt = null;
		try {
			myStmt = myCon.prepareCall("{call Delete_Employee(?)}");
			
			myStmt.setInt(1, id);
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void upadte_Employee(Employee temp)
	{
		CallableStatement myStmt = null;
		try {
			myStmt = myCon.prepareCall("{call Update_Employee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			myStmt.setString(1, temp.getFname());
			myStmt.setString(2, temp.getLname());
			myStmt.setString(3, temp.getGender());
			myStmt.setDouble(4, temp.getSalary());
			myStmt.setInt(5, temp.getManagerId());
			myStmt.setString(6, temp.getTypeofUser());
			myStmt.setString(7, temp.getUserName());
			myStmt.setString(8, temp.getPassword());
			myStmt.setString(9, temp.getTel1());
			myStmt.setString(10, temp.getTel2());
			myStmt.setInt(11, temp.getId());

			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void insert_Employee(Employee temp)
	{
		CallableStatement myStmt = null;
		try {	
			myStmt = myCon.prepareCall("{call Inser_Employee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			
			myStmt.setString(1, temp.getFname());
			myStmt.setString(2, temp.getLname());
			myStmt.setString(3, temp.getGender());
			myStmt.setDouble(4, temp.getSalary());
			myStmt.setInt(5, temp.getManagerId());
			myStmt.setString(6, temp.getTypeofUser());
			myStmt.setString(7, temp.getUserName());
			myStmt.setString(8, temp.getPassword());
			myStmt.setString(9, temp.getTel1());
			myStmt.setString(10, temp.getTel2());

			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
