package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import classes.*;
import user_interface.Main;

public class PurchaseDAO {
	
	Connection myCon = null;
	public PurchaseDAO()
	{
		myCon = Main.getConnection();
	}
	public PurchaseClass convertToPurchase(ResultSet result)
	{
		PurchaseClass temp = null;
		try
		{
			int id = result.getInt("orderId");
			int bookId = result.getInt("bookId");
			int supplierId = result.getInt("supplierId");
			int invoice = result.getInt("invoice");
			int quantity = result.getInt("quantity");
			int orderId = result.getInt("id");
			int employeeId = result.getInt("employeeId");
			
			temp = new PurchaseClass( id, invoice, employeeId, bookId, quantity, supplierId, orderId, 0);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return temp;
	}
	public List<PurchaseClass> getAllPuchase()
	{
		List<PurchaseClass> allPurchase = new ArrayList<>();
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call GetAllPurchase()}");
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				PurchaseClass temp = convertToPurchase(result);
				allPurchase.add(temp);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return allPurchase;
	}
	public List<PurchaseClass> searchByDate(int day)
	{
		List<PurchaseClass> allPurchase = new ArrayList<>();
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call SearchPurchaseByDay(?)}");
			myStmt.setInt(1, day);
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				PurchaseClass temp = convertToPurchase(result);
				allPurchase.add(temp);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return allPurchase;
	}
	public List<PurchaseClass> searchByInvoice(int invoice)
	{
		List<PurchaseClass> allPurchase = new ArrayList<>();
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call SearchPurchaseByInvocie (?)}");
			myStmt.setInt(1, invoice);
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				PurchaseClass temp = convertToPurchase(result);
				allPurchase.add(temp);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return allPurchase;
	}
	public void deletePurchase(int id)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call DeletePurchase(?)}");
			myStmt.setInt(1, id);
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public int updatePurchase(PurchaseClass temp)
	{
		int result = 0;
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call UpdatePurchase(?, ?, ?, ?, ?)}");
			myStmt.setInt(1, temp.getOrderId());
			myStmt.setInt(2, temp.getIsbn());
			myStmt.setInt(3, temp.getQuanity());
			myStmt.registerOutParameter(4, Types.INTEGER);
			myStmt.execute();
			result = myStmt.getInt(4);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public int insertIntoPurchase(PurchaseClass temp, int firstTime)
	{
		int result = 0;
		try
		{
			CallableStatement myStmt = myCon.prepareCall("{Call InsertIntoPurchase(?, ?, ?, ?, ?, ?, ?)}");
			
			myStmt.setInt(1, temp.getEmployeeId());
			myStmt.setInt(2, temp.getSupplier());
			myStmt.setInt(3, temp.getIsbn());
			myStmt.setInt(4,temp.getQuanity());
			myStmt.setDouble(5, temp.getPrice());
			myStmt.setInt(6, firstTime);
			myStmt.registerOutParameter(7, Types.INTEGER);
			
			myStmt.execute();
			
			result = myStmt.getInt(7);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
