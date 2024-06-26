package user_interface;

import classes.*;
import DAO.*;
import TableModel.PurchaseTableModel;
import TableModel.SaleTableModel;

import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Purchase extends JFrame implements ActionListener {
	
	private JPanel topNav = new JPanel();
	private JPanel bottomNav = new JPanel();
	private JPanel westNav = new JPanel();
	private JPanel eastNav = new JPanel();
	private JPanel centerNav = new JPanel();
	private JLabel appName = new JLabel("DownTown Book Store");
	private JLabel copy = new JLabel("� ALL COPY RIGHT RESERVED 2013/2021");
	private JLabel about = new JLabel("<html><b><br><br>"
			  +"---------------------------------<br>"
			  + "&#160; &#160; &#160;About <br>"
			  +"---------------------------------"
			  +"<br>This software is made <br>for the main purpose of "
			  + "<br> automating a <br>book store</b>");
	private JPanel searchPan = new JPanel();
	private JLabel searchLabel = new JLabel("Enter sub-string : ");
	private JTextField searchTxt = new JTextField("");
	
	private JButton [] buttons  = new JButton[12];
	private String [] btnName = {"Manage Sale", "Manage Purchase", "Manage Account", "Back to Authentication", "Exit"," " , "Add Purchase",
								 "Update Purchase", "Delete Purchase", "View All Purchases", "Search By Date","Search By Invoice"};
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	private Employee currentUser;
	private PurchaseDAO purchaseDAO = new PurchaseDAO();
	private PurchaseClass purchase;
	
	public Purchase(Employee currentUser)
	{//setting parameters for the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 675);
		setLocation(375, 75);
		setTitle("Cashier Main Page");
		setVisible(true);
		setLayout(new BorderLayout(1,1));
	
	//Assigning buttons to button names
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
			if(i < 5)
				buttons[i].setBackground(Color.LIGHT_GRAY);
		}
		buttons[1].setBackground(new Color(240,240,240));
		buttons[5].setBackground(Color.GRAY);
		buttons[5].setEnabled(false);
		searchTxt.setPreferredSize(new Dimension(200, 24));
		scrollPane.setPreferredSize(new Dimension(200, 490));
		table.setPreferredScrollableViewportSize(new Dimension(400, 490));
		table.setFillsViewportHeight(true);
		
		this.currentUser = currentUser;
	}
	public void mainPage()
	{
	//top navigator
		appName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		appName.setForeground(Color.WHITE);
		topNav.add(appName);
		topNav.setBackground(new Color(0xff8c00));

	//west navigator
		westNav.setBackground(Color.GRAY);
		westNav.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 0;
		con.weightx = 0;
		con.weighty  = 0;
		//con.ipady = 0;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.anchor = GridBagConstraints.FIRST_LINE_START;
		westNav.add(buttons[5], con);
		con.gridx = 0;
		con.gridy = 1;
		con.weighty  = 0;
		con.anchor= GridBagConstraints.BASELINE;
		westNav.add(buttons[6], con);
		con.gridx = 0;
		con.gridy = 2;
		con.weighty  = 0;
		westNav.add(buttons[7], con);
		con.gridx = 0;
		con.gridy = 3;
		con.weighty  = 0;
		westNav.add(buttons[8], con);
		con.gridx = 0;
		con.gridy = 4;
		con.weighty  = 1;
		westNav.add(buttons[9], con);
	
	//east navigator
			eastNav.setBackground(Color.GRAY);
			about.setForeground(Color.WHITE);
			about.setFont(new Font("Times New Roman", Font.BOLD, 12));
			eastNav.add(about);
		
	//bottom navigator
		copy.setForeground(Color.WHITE);
		bottomNav.add(copy);
		bottomNav.setBackground(Color.BLACK);
		FlowLayout Layout = new FlowLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		bottomNav.setLayout(Layout);
		
	//center navigator
		centerNav.setBackground(new Color(240,240,240));
		centerNav.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.ipadx = 2;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 0.01;
		constraints.anchor = GridBagConstraints. NORTHWEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		centerNav.add(buttons[0], constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		centerNav.add(buttons[1], constraints);
		constraints.gridx = 2;
		constraints.gridy = 0;
		centerNav.add(buttons[2], constraints);
		constraints.gridx = 3;
		constraints.gridy = 0;
		centerNav.add(buttons[3], constraints);
		constraints.gridx = 4;
		constraints.gridy = 0;
		centerNav.add(buttons[4], constraints);
		constraints.gridx = 5;
		constraints.gridy = 0;
	//adding search panel
		searchPan.setBackground(new Color(240,240,240));
		searchPan.add(searchLabel);
		searchPan.add(searchTxt);
		searchPan.add(buttons[10]);
		searchPan.add(buttons[11]);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 5;
		constraints.weighty = 0.01;
		centerNav.add(searchPan, constraints);
	//add table
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 5;
		constraints.weighty = 0.1;
		centerNav.add(scrollPane, constraints);
	//adding to event handler
		for(int i = 0; i < btnName.length; i++)
			buttons[i].addActionListener(this);
	//adding into frame
		add(bottomNav, BorderLayout.SOUTH);
		add(topNav, BorderLayout.NORTH);
		add(westNav, BorderLayout.WEST);
		add(eastNav, BorderLayout.EAST);
		add(centerNav, BorderLayout.CENTER);
	}
	public void populate()//method that populate the update GUI
	{
		
	}
	@Override
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == buttons[0])//sale
		{	dispose();
			CashierMain newCashier = new CashierMain(currentUser);
			newCashier.setVisible(true);
			newCashier.mainPage();
		}
		else if(a.getSource() == buttons[1])//purchase
		{	dispose();
			Purchase newPurchase = new Purchase(currentUser);
			newPurchase.mainPage();
		}
		else if(a.getSource() == buttons[2])//supplier
		{	
			AddEmployee newEmployee = new AddEmployee("Update", currentUser, true);
			newEmployee.mainPage();
		}
		else if(a.getSource() == buttons[3])//back to authentication
		{	dispose();
			Main.mainHandler();
		}
		else if(a.getSource() == buttons[4])//exit
		{	dispose();
			setVisible(false);
		}
		else if(a.getSource() == buttons[6])//Add purchase
		{	AddPurchase newPurchase = new AddPurchase("Add", purchase, false);
			newPurchase.mainPage();
		}
		else if(a.getSource() == buttons[7])//update purchase
		{	
			int row = table.getSelectedRow();
			if(row < 0)
				JOptionPane.showMessageDialog(null, "You must select a sale to Update a sale");
			else
			{	purchase = (PurchaseClass) table.getValueAt(row, PurchaseTableModel.OBJECT_COL);
				AddPurchase newSale = new AddPurchase("Update", purchase, true);
				newSale.mainPage();
			}
		}
		else if(a.getSource() == buttons[8])//delete purchase
		{	
			int row = table.getSelectedRow();
			if(row < 0)
				JOptionPane.showMessageDialog(null, "You must select a sale to delete a Purchase");
			else
			{
				purchase = (PurchaseClass) table.getValueAt(row, PurchaseTableModel.OBJECT_COL);
				purchaseDAO.deletePurchase(purchase.getOrderId());
				JOptionPane.showMessageDialog(null, "Sale Deleted successsfully");
			}
		}
		else if(a.getSource() == buttons[9])//view all purchase
		{	
			List<PurchaseClass> allPurchase = null;
			allPurchase = purchaseDAO.getAllPuchase();
			if(allPurchase.size() == 0)
				JOptionPane.showMessageDialog(null, "There is no purchase record yet");
			else
			{
				PurchaseTableModel model = new PurchaseTableModel(allPurchase);
				table.setModel(model);
			}
		}
		else if(a.getSource() == buttons[10])//search purchase by date
		{	
			List<PurchaseClass> allPurchase = null;
			int day;
			try {
				day = Integer.parseInt(searchTxt.getText());
			}
			catch(Exception e)
			{
				day = 1;
			}
			allPurchase = purchaseDAO.searchByInvoice(day);
			if(allPurchase.size() == 0)
				JOptionPane.showMessageDialog(null, "No eligible data was found");
			else
			{
				PurchaseTableModel model = new PurchaseTableModel(allPurchase);
				table.setModel(model);
			}
		}
		else if(a.getSource() == buttons[11])//search purchase by invoice
		{	
			List<PurchaseClass> allPurchase = null;
			int invoice;
			try {
				invoice = Integer.parseInt(searchTxt.getText());
			}
			catch(Exception e)
			{
				invoice = 1;
			}
			allPurchase = purchaseDAO.searchByInvoice(invoice);
			if(allPurchase.size() == 0)
				JOptionPane.showMessageDialog(null, "No eligible data was found");
			else
			{
				PurchaseTableModel model = new PurchaseTableModel(allPurchase);
				table.setModel(model);
			}
		}
	}
}
