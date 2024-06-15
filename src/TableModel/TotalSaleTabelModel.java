package TableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import classes.TotalSale;;

public class TotalSaleTabelModel extends AbstractTableModel{
	private static final int BEFORE_VAT_COL = 0;
	private static final int VAT_COL = 1;
	private static final int TOTAL_COL = 2;

	
	private String [] columnNames = {"Before Vat", "Vat", "Total sale"};
	private List<TotalSale> total;
	
	public TotalSaleTabelModel(List <TotalSale> t)
	{
		total = t;
	}

	public int getRowCount()
	{
		return total.size();		
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
		TotalSale temp = total.get(row);
		
		switch(col)
		{
		case BEFORE_VAT_COL:
			return temp.getBeforeVat();
		case VAT_COL:
			return temp.getVat();
		case TOTAL_COL:
			return temp.getTotal();
		default:
			return temp.getTotal();
		}
	}
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}
