package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.*;
import user_interface.Main;

public class BookDAO {
	
	private Connection myCon = null;
	
	public BookDAO()
	{
		myCon = Main.getConnection();
	}
	public BookClass convertToBook(ResultSet result)
	{
		BookClass temp = null;
		try
		{
			int id = result.getInt("id");
			String title = result.getString("title");
			String author = result.getString("author");
			String category = result.getString("category");
			String publisher = result.getString("publisher");
			String isbn = result.getString("isbn");
			double price = result.getDouble("price");
			int edition = result.getInt("edition");
			int copies = result.getInt("copies");
			
			temp = new BookClass( title, author, category, price, publisher, id, isbn, edition, copies);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return temp;
	}
	public List<BookClass> searchBook(String text)
	{
		List<BookClass> books = new ArrayList<>();
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call SearchBook(?)}");
			
			myStmt.setString(1, text);
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			while(result.next())
			{	
				BookClass temp = convertToBook(result);
				books.add(temp);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return books;
	}

	public List<BookClass> getAllBook()
	{
		List<BookClass> books = new ArrayList<>();
		CallableStatement myStmt = null;
		
		try
		{
			myStmt = myCon.prepareCall("{call GetAllBook()}");
			myStmt.execute();
			ResultSet result = myStmt.getResultSet();
			
			while(myStmt.getResultSet().next())
			{	
				BookClass temp = convertToBook(result);
				books.add(temp);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return books;
	}

	public void update_book (BookClass book)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call UpdateBook(?, ?, ?, ?, ?, ?, ?)}");

			myStmt.setInt(1, book.getId());
			myStmt.setString(2,  book.getTitle());
			myStmt.setString(3, book.getAuthor());
			myStmt.setString(4, book.getCategory());
			myStmt.setString(5, book.getPublisher());
			myStmt.setInt(6,  book.getEdition());
			myStmt.setString(7, book.getIsbn());
			
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void insert_book (BookClass book)
	{
		CallableStatement myStmt = null;
		try
		{
			myStmt = myCon.prepareCall("{call InsertIntoBook(?, ?, ?, ?, ?, ?)}");
			
			myStmt.setString(1,  book.getTitle());
			myStmt.setString(2, book.getAuthor());
			myStmt.setString(3, book.getCategory());
			myStmt.setString(4, book.getPublisher());
			myStmt.setInt(5,  book.getEdition());
			myStmt.setString(6, book.getIsbn());
			
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void delete_book (int id)
	{
		CallableStatement myStmt = null;
		try
		{	
			myStmt = myCon.prepareCall("{call DeleteBook(?)}");
			myStmt.setInt(1,  id);
			
			myStmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
