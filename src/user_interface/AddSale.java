package user_interface;

import classes.*;
import DAO.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddSale extends JFrame implements ActionListener{
	
	private static final String [] btnName = {"Add", "Finish", "Exit"};
	private String type;
	
	private static final JPanel topNav = new JPanel();
	private static final JPanel bottomNav = new JPanel();
	private static final JLabel appName = new JLabel("DownTown Books Store");
	private static final JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	
	private JLabel empId = new JLabel("Employee Id : ");
	private JLabel customer = new JLabel("Customer : ");
	private JLabel bookId = new JLabel("Book Id : ");
	private JLabel quantity = new JLabel("Quantity : ");
	private JLabel discount = new JLabel("Discount : ");
	
	private JTextField empIdTxt = new JTextField();
	private JTextField customerTxt = new JTextField();
	private JTextField bookIdTxt = new JTextField();
	private JTextField quantityTxt = new JTextField();
	private JTextField discountTxt = new JTextField();
	
	private JTextArea bill = new JTextArea( " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n"+
											"\tTIN 00000250901\n"+
											"                   DownTown Book Store PLC\n"+
											"                A.A SUB CITY 4 KILO WEREDA\n"+
											"\tTEL 0901945054\n"+
											" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n"+
											"  Item Id                Quantity                price\n"+
											" -------------------------------------------------------------------");
	private JScrollPane billScroll = new JScrollPane(bill, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton buttons[] = new JButton[3];
	
	private int firstSale = 0;
	private boolean updateMode = false;
	private Sale newSale;
	private SalesDAO saleDAO = new SalesDAO();
	private double total = 0;
	
	public AddSale(String type, Sale newSale, boolean updateMode)
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(740, 500);
		setVisible(true);
		setLocation(450, 150);
		setTitle(type + " Sale");
		setResizable(false);
		setLayout(null);
		
		bill.setEditable(false);//setting the text area to make it not editable
		
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
		}
		this.type = type;
		this.newSale = newSale;
		this.updateMode = updateMode;
	}
	public void mainPage()
	{
	//top navigator
		appName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		appName.setForeground(Color.WHITE);
		topNav.add(appName);
		topNav.setBackground(new Color(0xff8c00));
		topNav.setBounds(0,0,740,50);
		add(topNav);
		
	//bottom navigator
		copy.setForeground(Color.WHITE);
		bottomNav.add(copy);
		bottomNav.setBackground(Color.BLACK);
		bottomNav.setBounds(0,442,740,30);
		FlowLayout Layout = new FlowLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		bottomNav.setLayout(Layout);
		add(bottomNav);
	//center navigator
		
		billScroll.setBounds(400, 80, 300, 320);
		
		empId.setBounds(70, 80, 120, 25);
		customer.setBounds(70, 120, 120, 25);
		bookId.setBounds(70, 160, 120, 25);
		quantity.setBounds(70, 200, 120, 25);
		discount.setBounds(70, 240, 120, 25);
		
		empIdTxt.setBounds(180, 80, 200, 25);
		customerTxt.setBounds(180, 120, 200, 25);
		bookIdTxt.setBounds(180, 160, 200, 25);
		quantityTxt.setBounds(180, 200, 200, 25);
		discountTxt.setBounds(180, 240, 200, 25);
		
		buttons[0].setBounds(90, 300, 100, 25);
		buttons[1].setBounds(190, 300, 100, 25);
		buttons[2].setBounds(290, 300, 80, 25);
		
		if(updateMode)
		{
			populate();
			buttons[0].setText("Update");
			buttons[1].setEnabled(false);
			empIdTxt.setEditable(false);
			customerTxt.setEditable(false);
		}
		
		add(empId);
		add(customer);
		add(bookId);
		add(quantity);
		add(discount);
		add(empIdTxt);
		add(customerTxt);
		add(bookIdTxt);
		add(quantityTxt);
		add(discountTxt);
		add(billScroll);
		
	//adding to action listener
		for(int i = 0; i < btnName.length; i++)
		{	add(buttons[i]);
			buttons[i].addActionListener(this);
		}
	}
	public void populate()
	{
		empIdTxt.setText(String.valueOf(newSale.getEmployeeId()));
		customerTxt.setText(newSale.getCustomer());
		bookIdTxt.setText(String.valueOf(newSale.getIsbn()));
		quantityTxt.setText(String.valueOf(newSale.getQuanity()));
		discountTxt.setText(String.valueOf(newSale.getDiscount()));
	}
	public int checkTextField()
	{	int result = 1;
		String emp = empIdTxt.getText() ;
		String sup = customerTxt.getText();
		String book = bookIdTxt.getText();
		String disc = discount.getText();
		String quan = quantityTxt.getText();
		if(emp.trim().length() == 0 || sup.trim().length() == 0 || book.trim().length() == 0 || quan.trim().length() == 0|| disc.trim().length() == 0)
		{
			empIdTxt.setText(" ");
			customerTxt.setText(" ");
			bookIdTxt.setText(" ");
			quantityTxt.setText(" ");
			discountTxt.setText("");
			result = 0;
		}
		return result;
	}
	public Sale getSale()
	{
		Sale temp = null;
		String customer = customerTxt.getText();
		int employeeId ;
		int bookId;
		int quantity;
		double discount = 0;
		String isbn = "\0";
		int invoice = 0;
		int orderId = 0;
		int id = 0;
		if(updateMode)
		{
			invoice = newSale.getInvoice();
			orderId = newSale.getOrderId();
			id = newSale.getId();
		}
		try
		{	employeeId = Integer.parseInt(empIdTxt.getText());
			bookId = Integer.parseInt(bookIdTxt.getText());
			quantity = Integer.parseInt(quantityTxt.getText());
			discount = Double.parseDouble(discountTxt.getText());
		}
		catch(Exception e)
		{
			bookId = 0;
			quantity = 0;
			discount = 0;
			employeeId = 0;
		}
		temp = new Sale(id, invoice, employeeId, bookId, quantity, customer, discount, orderId);
		return temp;
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == buttons[0])
		{	
			if(checkTextField() == 1)
			{
				empIdTxt.setEditable(false);
				customerTxt.setEditable(false);
				Sale temp = getSale();
				if(updateMode)
				{
					int result = saleDAO.updateSale(temp);
					if(result == 1)
					{	
						JOptionPane.showMessageDialog(null, "Update complted successfully");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Update Failed");
						dispose();
					}
				}
				else
				{
					int result = saleDAO.insertSale(temp, firstSale);
					if(result == 1)
					{
						JOptionPane.showMessageDialog(null, "Insert complted successfully");
						double price = saleDAO.getPrice(temp.getIsbn());
						bill.setText(bill.getText()+ "\n   "+temp.getIsbn()+"\t"+ temp.getQuanity() + "\t" + price); 
						total += price * temp.getQuanity();
					}
					else
						JOptionPane.showMessageDialog(null, "insert Failed");
				}
				firstSale = 1;
			}
			else
				JOptionPane.showMessageDialog(null, "Form is not complete");
		}
		else if (a.getSource() == buttons[1])
		{
			buttons[0].setEnabled(false);
			bill.setText(bill.getText()+"\n    Total price      : "+ total); 
		}
		else if(a.getSource() == buttons[2])
		{
			dispose();
		}
	}
}
