package TableModel;


import java.util.*;
import javax.swing.table.AbstractTableModel;
import classes.*;

public class EmployeeTableModel extends AbstractTableModel{
	
	private static final int ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int SALARY_COL = 3;
	private static final int GENDER_COL = 4;
	private static final int TEL_1_COL = 5;
	private static final int TEL_2_COL = 6;
	private static final int MANAGER_ID_COL = 7;
	private static final int TYPE_OF_USER = 8;
	private static final int USER_NAME_COL = 9;
	private static final int PASSWORD_COL = 10;
	public static final int OBJECT_COL = -1;
	
	private String [] columnNames = {"Id", "First Name", "Last Name", "Salary", "Gender", "Telephone 1", "Telphone 2",
									 "Manager Id", "Type of User", "User Name", "Password"};
	private List<Employee> employees;

	public EmployeeTableModel(List<Employee> employee) 
	{
		employees = (List<Employee>) employee;
	}

	public int getRowCount()
	{
		return employees.size();		
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
		Employee temp = employees.get(row);
		
		switch(col)
		{
		case ID_COL:
			return temp.getId();
		case FIRST_NAME_COL:
			return temp.getFname();
		case LAST_NAME_COL:
			return temp.getLname();
		case SALARY_COL:
			return temp.getSalary();
		case GENDER_COL:
			return temp.getGender();
		case TEL_1_COL:
			return temp.getTel1();
		case TEL_2_COL:
			return temp.getTel2();
		case MANAGER_ID_COL:
			return temp.getManagerId();
		case TYPE_OF_USER:
			return temp.getTypeofUser();
		case USER_NAME_COL:
			return temp.getUserName();
		case PASSWORD_COL:
			return temp.getPassword();
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
