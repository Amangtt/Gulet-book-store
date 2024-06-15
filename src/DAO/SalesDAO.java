package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import classes.*;
import user_interface.Main;

public class SalesDAO 
{	
	Connection myCon = null;
	public SalesDAO()
	{
		myCon = Main.getConnection();
	}
	public TotalSale convertToTotalSale(ResultSet result)
	{
		TotalSale temp = null;
		try
		{
			double beforeVat = result.getDouble("Before VAT");
		    double vat = result.getDouble("VAT");
			double total = result.getDouble("Grand Total");
			temp = new TotalSale(beforeVat, vat, total);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return temp;
	}
	public Inventory convertToInventory(ResultSet result)
	{
		Inventory temp = null;
		
		try 
		{
			int bookId = result.getInt("bookId");
			double purchased = result.getDouble("Purchased");
			double soled = result.getDouble("Soled");
			temp = new Inventory(bookId, purchased, soled);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return temp;
	}
	public Sale convertToSale(ResultSet result) 
	{
		Sale temp = null;
		try
		{
			int id = result.getInt("orderId");
			int invoice = result.getInt("Invoice");
			int employeeId = result.getInt("employeeId");
			int bookId = result.getInt("bookId");
			int quantity = result.getInt("quantity");
			String customer = result.getString("Customer");
			double discount = result.getDouble("discount");
			int orderId = result.getInt("id");
			
			temp = new Sale(id, invoice, employeeId, bookId, quantity, customer, discount, orderId);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	public List<Sale> getAllSale()
	{
		List<Sale> allSale = new ArrayList<>();
		CallableStatement myStmt = null;
		try 
		{
			myStmt = myCon.prepareCall("{Call GetAllSale()}");
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				Sale temp = convertToSale(result);
				allSale.add(temp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return allSale;
	}
	public List<Sale> searchSaleByInvoice(int invoice)
	{
		List<Sale> allSale = new ArrayList<>();
		CallableStatement myStmt = null;
		try 
		{
			myStmt = myCon.prepareCall("{Call SearchByInvoice (?)}");
			myStmt.setInt(1, invoice);
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				Sale temp = convertToSale(result);
				allSale.add(temp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return allSale;
	}
	public List<Sale> searchSaleByDate(int day)
	{
		List<Sale> allSale = new ArrayList<>();
		CallableStatement myStmt = null;
		try 
		{
			myStmt = myCon.prepareCall("{Call SearchByDay(?)}");
			myStmt.setInt(1, day);
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				Sale temp = convertToSale(result);
				allSale.add(temp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allSale;
	}
	public void deleteSale(int id)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call DeleteSale(?)}");
			myStmt.setInt(1, id);
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public int insertSale(Sale s, int firstTime)
	{
		int result = 0;
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call InsertIntoSale(?, ?, ?, ?, ?, ?, ?)}");
			
			myStmt.setInt(1, s.getEmployeeId());
			myStmt.setString(2, s.getCustomer());
			myStmt.setInt(3, s.getIsbn());
			myStmt.setInt(4, s.getQuanity());
			myStmt.setDouble(5, s.getDiscount());
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
	public int updateSale(Sale s)
	{
		int result = 0;
		
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{Call UpdateSale(?, ?, ?, ?, ?)}");
			myStmt.setInt(1, s.getOrderId());
			myStmt.setInt(2, s.getIsbn());
			myStmt.setInt(3, s.getQuanity());
			myStmt.setDouble(4, s.getDiscount());
			myStmt.registerOutParameter(5, Types.INTEGER);
			myStmt.execute();
			result = myStmt.getInt(5);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public double getPrice(int id) 
	{
		double result = 0;
		java.sql.Statement myStmt = null;
		String query = "SELECT price FROM Book WHERE id = "+ String.valueOf(id);
		try
		{
			myStmt = myCon.createStatement();
			myStmt.executeQuery(query);
			ResultSet res = myStmt.getResultSet();
			result = res.getDouble("price");
			JOptionPane.showInternalMessageDialog(null, result);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public List<Inventory> getInventory()
	{
		List<Inventory> inventory = new ArrayList<>();
		CallableStatement myStmt = null;
		try 
		{
			myStmt = myCon.prepareCall("{Call Inventory()}");
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				Inventory temp = convertToInventory(result);
				inventory.add(temp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return inventory;
	}
	public List <TotalSale> totalSale()
	{
		List<TotalSale> totalSale = new ArrayList<>();
		CallableStatement myStmt = null;
		try 
		{
			myStmt = myCon.prepareCall("{Call GetTotalSaleOfDay()}");
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{
				TotalSale temp = convertToTotalSale(result);
				totalSale.add(temp);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return totalSale;
	}
}
