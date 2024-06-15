package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import classes.Sale;

public class SaleTableModel extends AbstractTableModel{
	
	private static final int ID_COL = 0;
	private static final int INVOICE_COL = 1;
	private static final int EMPLOYEE_ID_COL = 2;
	private static final int CUSTOMER_COL = 3;
	private static final int BOOK_ID_COL = 4;
	private static final int QUANTITY_COL = 5;
	private static final int DISCOUNT_COL = 6;
	private static final int ORDER_ID_COL = 7;
	public static final int OBJECT_COL = -1;

	
	private String [] columnNames = {"Order Id", "Invoice", "Employee Id", "Customer Name", "Book Id", "Quantity", "Discount", "Order Detail Id"};
	private List<Sale> sales;
	
	public SaleTableModel(List <Sale> s)
	{
		sales = s;
	}

	public int getRowCount()
	{
		return sales.size();		
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
		Sale temp = sales.get(row);
		
		switch(col)
		{
		case ID_COL:
			return temp.getId();
		case INVOICE_COL:
			return temp.getInvoice();
		case EMPLOYEE_ID_COL:
			return temp.getEmployeeId();
		case CUSTOMER_COL:
			return temp.getCustomer();
		case BOOK_ID_COL:
			return temp.getIsbn();
		case QUANTITY_COL:
			return temp.getQuanity();
		case DISCOUNT_COL:
			return temp.getDiscount();
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
