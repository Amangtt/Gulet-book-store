package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import classes.PurchaseClass;

public class PurchaseTableModel extends AbstractTableModel{
	
	private static final int ID_COL = 0;
	private static final int INVOICE_COL = 1;
	private static final int EMPLOYEE_ID_COL = 2;
	private static final int SUPPLIER_COL = 3;
	private static final int BOOK_ID_COL = 4;
	private static final int QUANTITY_COL = 5;
	private static final int ORDER_ID_COL = 6;
	public static final int OBJECT_COL = -1;

	
	private String [] columnNames = {"Id", "Invoice", "Employee Id", "Supplier Name", "Book Id", "Quantity", "Order Id"};
	private List<PurchaseClass> purchases;
	
	public PurchaseTableModel(List <PurchaseClass> p)
	{
		purchases = p;
	}

	public int getRowCount()
	{
		return purchases.size();		
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
		PurchaseClass temp = purchases.get(row);
		
		switch(col)
		{
		case ID_COL:
			return temp.getId();
		case INVOICE_COL:
			return temp.getInvoice();
		case EMPLOYEE_ID_COL:
			return temp.getEmployeeId();
		case SUPPLIER_COL:
			return temp.getSupplier();
		case BOOK_ID_COL:
			return temp.getIsbn();
		case QUANTITY_COL:
			return temp.getQuanity();
		case ORDER_ID_COL:
			return temp.getOrderId();
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
