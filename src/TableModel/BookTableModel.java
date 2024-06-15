package TableModel;


import java.util.*;
import javax.swing.table.AbstractTableModel;
import classes.BookClass;

public class BookTableModel extends AbstractTableModel{
	
	private static final int ID_COL = 0;
	private static final int TITLE_COL = 1;
	private static final int AUTHOR_COL = 2;
	private static final int CATEGORY_COL = 3;
	private static final int PRICE_COL = 4;
	private static final int PUBLISHER_COL = 5;
	private static final int EDITION_COL = 6;
	private static final int ISBN_COL = 7;
	private static final int COPIES_COL = 8;
	public static final int OBJECT_COL = -1;

	
	private String [] columnNames = {"Id", "Title", "Author", "Category", "Price", "Publisher", "Edition", "ISBN", "Copies"};
	private List<BookClass> books;
	
	public BookTableModel(List <BookClass> b)
	{
		books = b;
	}

	public int getRowCount()
	{
		return books.size();		
	}
	public int getColumnCount()
	{
		return columnNames.length;
	}
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	public Object getValueAt(int row, int col)
	{
		BookClass temp = books.get(row);
		
		switch(col)
		{
		case ID_COL:
			return temp.getId();
		case TITLE_COL:
			return temp.getTitle();
		case AUTHOR_COL:
			return temp.getAuthor();
		case CATEGORY_COL:
			return temp.getCategory();
		case PRICE_COL:
			return temp.getPrice();
		case PUBLISHER_COL:
			return temp.getPublisher();
		case EDITION_COL:
			return temp.getEdition();
		case ISBN_COL:
			return temp.getIsbn();
		case COPIES_COL:
			return temp.getCopy();
		case OBJECT_COL:
			return temp;
		default:
			return temp.getId();
		}
	}
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}
