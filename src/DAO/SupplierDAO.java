package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.*;
import user_interface.Main;

public class SupplierDAO {
	
	Connection myCon = null;
	
	public SupplierDAO()
	{
		myCon = Main.getConnection();
	}
	public SupplierClass convertToSupplier(ResultSet result)
	{
		SupplierClass temp = null;
		
		try 
		{
			String name = result.getString("companyName");
			String tin = result.getString("tinNumber");
			int id = result.getInt("id");
			String address = result.getString("address");
			String tel = result.getString("tel");
			String tel2 = result.getString("tel2");
			String email = result.getString("email");
			
			temp = new SupplierClass(id, name, tin, address, tel, tel2, email);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	public List<SupplierClass> getAllSupplier()
	{
		List<SupplierClass> suppliers = new ArrayList<>();
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call GetAllSupplier()}");
			
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{	
				SupplierClass temp = convertToSupplier(result);
				suppliers.add(temp);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return suppliers;
	}
	public List<SupplierClass> searchSupplier(String text)
	{
		List<SupplierClass> suppliers = new ArrayList<>();
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call SearchSupplier(?)}");
			myStmt.setString(1, text);
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{	
				SupplierClass temp = convertToSupplier(result);
				suppliers.add(temp);
			}
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return suppliers;
	}
	public void insertSupplier(SupplierClass temp)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call InsertIntoSupplier(?, ?, ?, ?, ?, ?)}");
			
			myStmt.setString(1, temp.getName());
			myStmt.setString(2, temp.getTin());
			myStmt.setString(3,  temp.getAddress());
			myStmt.setString(4, temp.getEmail());
			myStmt.setString(5, temp.getTel());
			myStmt.setString(6, temp.getTel2());
			
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void updateSupplier(SupplierClass temp)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call UpdateSupplier(?, ?, ?, ?, ?, ?, ?)}");//change procedure
			
			myStmt.setInt(1, temp.getId());
			myStmt.setString(2, temp.getName());
			myStmt.setString(3, temp.getTin());
			myStmt.setString(4,  temp.getAddress());
			myStmt.setString(5, temp.getEmail());
			myStmt.setString(6, temp.getTel());
			myStmt.setString(7, temp.getTel2());
			
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void deleteSupplier(int id)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call DeleteSupplier(?)}");//change procedure
			
			myStmt.setInt(1 , id);
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
