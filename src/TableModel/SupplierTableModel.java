package TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import classes.SupplierClass;

public class SupplierTableModel extends AbstractTableModel{
	
	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int TIN_NUMBER_COL = 2;
	private static final int ADDRESS_COL = 3;
	private static final int TEL_1_COL = 4;
	private static final int TEL_2_COL = 5;
	private static final int EMAIL_COL = 6;
	public static final int OBJECT_COL = -1;

	
	private String [] columnNames = {"Id", "Company Name", "Tin NUmber", "Address", "Telephone 1", "Telephone 2", "Email"};
	private List<SupplierClass> suppliers;
	
	public SupplierTableModel(List <SupplierClass> s)
	{
		suppliers = s;
	}

	public int getRowCount()
	{
		return suppliers.size();		
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
		SupplierClass temp = suppliers.get(row);
		
		switch(col)
		{
		case ID_COL:
			return temp.getId();
		case NAME_COL:
			return temp.getName();
		case TIN_NUMBER_COL:
			return temp.getTin();
		case ADDRESS_COL:
			return temp.getAddress();
		case TEL_1_COL:
			return temp.getTel();
		case TEL_2_COL:
			return temp.getTel2();
		case EMAIL_COL:
			return temp.getEmail();
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
