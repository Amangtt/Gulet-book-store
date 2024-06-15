package user_interface;

import classes.*;
import DAO.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddPurchase extends JFrame implements ActionListener{
	
	private static final String [] btnName = {"Add", "Finish", "Exit"};
	private String type;
	
	private static final JPanel topNav = new JPanel();
	private static final JPanel bottomNav = new JPanel();
	private static final JLabel appName = new JLabel("DownTown Book Store");
	private static final JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	
	private JLabel empId = new JLabel("Employee Id : ");
	private JLabel supplier = new JLabel("Supplier Id: ");
	private JLabel bookId = new JLabel("Book Id : ");
	private JLabel quantity = new JLabel("Quantity : ");
	private JLabel price =  new JLabel("Price : ");
	
	private JTextField empIdTxt = new JTextField();
	private JTextField supplierTxt = new JTextField();
	private JTextField bookIdTxt = new JTextField();
	private JTextField quantityTxt = new JTextField();
	private JTextField priceTxt = new JTextField();
	
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
	
	private boolean updateMode = false;
	private PurchaseClass purchase;
	private PurchaseDAO purchaseDAO = new PurchaseDAO();
	private int firstPurchase = 0;
	private double total = 0;
	
	public AddPurchase(String type, PurchaseClass purchase, boolean updateMode)
	{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(740, 500);
		setVisible(true);
		setLocation(450, 150);
		setTitle(type + " Purchase");
		setResizable(false);
		setLayout(null);
		
		bill.setEditable(false);//setting the text area to make it not editable
		
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
		}
		this.type = type;
		this.purchase = purchase;
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
		
		if(updateMode)
		{
			populate();
			buttons[0].setText("Update");
			buttons[1].setEnabled(false);
			empIdTxt.setEditable(false);
			supplierTxt.setEditable(false);
		}
		
		empId.setBounds(70, 80, 120, 25);
		supplier.setBounds(70, 120, 120, 25);
		bookId.setBounds(70, 160, 120, 25);
		quantity.setBounds(70, 200, 120, 25);
		price.setBounds(70, 240, 120, 25);
		
		empIdTxt.setBounds(180, 80, 200, 25);
		supplierTxt.setBounds(180, 120, 200, 25);
		bookIdTxt.setBounds(180, 160, 200, 25);
		quantityTxt.setBounds(180, 200, 200, 25);
		priceTxt.setBounds(180, 240, 200, 25);
		
		buttons[0].setBounds(90, 300, 100, 25);
		buttons[1].setBounds(190, 300, 100, 25);
		buttons[2].setBounds(290, 300, 80, 25);
		
		if(type.compareTo("Update")==0)
			populate();
		
		add(empId);
		add(supplier);
		add(bookId);
		add(quantity);
		add(price);
		add(empIdTxt);
		add(supplierTxt);
		add(bookIdTxt);
		add(quantityTxt);
		add(priceTxt);
		add(billScroll);
	//adding to action listener
		for(int i = 0; i < btnName.length; i++)
		{	add(buttons[i]);
			buttons[i].addActionListener(this);
		}
	}
	
	public int checkTextField()
	{	int result = 1;
		String emp = empIdTxt.getText() ;
		String sup = supplierTxt.getText();
		String book = bookIdTxt.getText();
		String quan = quantityTxt.getText();
		String pric = priceTxt.getText();
		if(emp.trim().length() == 0 || sup.trim().length() == 0 || book.trim().length() == 0 || quan.trim().length() == 0|| pric.trim().length() == 0)
		{
			empIdTxt.setText("");
			supplierTxt.setText("");
			bookIdTxt.setText("");
			quantityTxt.setText("");
			priceTxt.setText("");
			result = 0;
		}
		return result;
	}
	public void populate()
	{
		empIdTxt.setText(String.valueOf(purchase.getEmployeeId()));
		supplierTxt.setText(String.valueOf(purchase.getSupplier()));
		bookIdTxt.setText(String.valueOf(purchase.getIsbn()));
		quantityTxt.setText(String.valueOf(purchase.getQuanity()));
		priceTxt.setText(String.valueOf(purchase.getPrice()));
	}
	public PurchaseClass getPurchase()
	{
		PurchaseClass temp = null;
		int id = 0;
		int bookId = 0;
		int supplierId = 0;
		int invoice = 0;
		int quantity = 0;
		int orderId = 0;
		int employeeId = 0;
		double price = 0;
		if(updateMode)
		{
			id = purchase.getId();
			supplierId = purchase.getSupplier();
			invoice = purchase.getInvoice();
			orderId = purchase.getOrderId();
			employeeId = purchase.getEmployeeId();
		}
		try
		{
			bookId = Integer.parseInt(bookIdTxt.getText());
			quantity = Integer.parseInt(quantityTxt.getText());
			price = Double.parseDouble(priceTxt.getText());
			
			temp = new PurchaseClass(id, invoice, employeeId, bookId, quantity, supplierId, orderId, price);
		}
		catch(Exception e)
		{
			id = 0;
			price = 0;
			bookId = 0;
			supplierId = 0;
			invoice = 0;
			quantity = 0;
			orderId = 0;
			employeeId = 0;
		}
		return temp;
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == buttons[0])
		{	
			if(checkTextField() == 1)
			{
				empIdTxt.setEditable(false);
				supplierTxt.setEditable(false);
				PurchaseClass temp = getPurchase();
				if(updateMode)
				{
					int result = purchaseDAO.updatePurchase(temp);
					if(result == 1)
						JOptionPane.showMessageDialog(null, "Update complted successfully");
					else
						JOptionPane.showMessageDialog(null, "Update Failed");
				}
				else
				{
					int result = purchaseDAO.insertIntoPurchase(temp, firstPurchase);
					if(result == 1)
					{
						JOptionPane.showMessageDialog(null, "Insert complted successfully");
						bill.setText(bill.getText()+ "\n   "+temp.getIsbn()+"        "+ temp.getQuanity() + "       " + temp.getPrice()); 
						total += temp.getPrice() * temp.getQuanity();
					}
					else
						JOptionPane.showMessageDialog(null, "insert Failed");
				}
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
