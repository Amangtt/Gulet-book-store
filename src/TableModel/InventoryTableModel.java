package TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import classes.Inventory;

public class InventoryTableModel extends AbstractTableModel{
	
	private static final int BOOK_ID_COL = 0;
	private static final int PURCHASED_COL = 1;
	private static final int SOLED_COL = 2;

	
	private String [] columnNames = {"Book ID", "Purchased", "Soled"};
	private List<Inventory> inventory;
	
	public InventoryTableModel(List <Inventory> i)
	{
		inventory = i;
	}

	public int getRowCount()
	{
		return inventory.size();		
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
		Inventory temp = inventory.get(row);
		
		switch(col)
		{
		case BOOK_ID_COL:
			return temp.getBookId();
		case PURCHASED_COL:
			return temp.getPurchased();
		case SOLED_COL:
			return temp.getSold();
		default:
			return temp.getBookId();
		}
	}
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}
