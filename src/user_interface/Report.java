package user_interface;

import classes.*;
import DAO.*;
import TableModel.InventoryTableModel;
import TableModel.SaleTableModel;
import TableModel.TotalSaleTabelModel;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public class Report extends JFrame implements ActionListener{
	private JPanel topNav = new JPanel();
	private JPanel bottomNav = new JPanel();
	private JPanel westNav = new JPanel();
	private JPanel eastNav = new JPanel();
	private JPanel centerNav = new JPanel();
	
	private JPanel searchPan = new JPanel();
	private JLabel searchLabel = new JLabel("Enter Invoice : ");
	private JTextField searchTxt = new JTextField("");
	
	private JLabel appName = new JLabel("DownTown Book Store");
	private JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	private JLabel about = new JLabel("<html><b><br><br>"
									  +"---------------------------------<br>"
									  + "&#160; &#160; &#160;About <br>"
									  +"---------------------------------"
									  +"<br>This software is made <br>for the main purpose of "
									  + "<br> automating a <br>book store</b>");
	private JButton [] buttons  = new JButton[11];
	private String [] btnName = {"Manage User", "Manage Book", "Manage Supplier", "Back to Authentication", "Exit"," " , "Inventory",
								 "Total sale", "Total sale of Day","Total sale by invoice", "Manage Report"};
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	private SalesDAO saleDAO = new SalesDAO();
	
	public Report()
	{//setting parameters for the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1010, 675);
		setLocation(375, 75);
		setTitle("Admininstarators Main Page");
		setLayout(new BorderLayout(1,1));
		setVisible(true);
	//Assigning buttons to button names
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
			if(i < 5)
				buttons[i].setBackground(Color.LIGHT_GRAY);
		}
		buttons[10].setBackground(Color.LIGHT_GRAY);
		buttons[10].setBackground(new Color(240,240,240));
		buttons[5].setBackground(Color.GRAY);
		buttons[5].setEnabled(false);
		searchTxt.setPreferredSize(new Dimension(200, 24));
		scrollPane.setPreferredSize(new Dimension(400, 490));
		table.setPreferredScrollableViewportSize(new Dimension(400, 490));
		table.setFillsViewportHeight(true);
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
		con.weighty  = 1;
		westNav.add(buttons[8], con);

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
		constraints.weighty = 0.001;
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
		centerNav.add(buttons[10], constraints);
		constraints.gridx = 4;
		constraints.gridy = 0;
		centerNav.add(buttons[3], constraints);
		constraints.gridx = 5;
		constraints.gridy = 0;
		centerNav.add(buttons[4], constraints);
		constraints.gridx = 6;
		constraints.gridy = 0;
	//adding search panel
		searchPan.setBackground(new Color(240,240,240));
		searchPan.add(searchLabel);
		searchPan.add(searchTxt);
		searchPan.add(buttons[9]);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 5;
		constraints.weighty = 0.01;
		centerNav.add(searchPan, constraints);
	//add table
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 6;
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
	@Override
	public void actionPerformed(ActionEvent a)
	{	
		if(a.getSource() == buttons[0])
		{	dispose();
			MainFrame newUser = new MainFrame();
			newUser.setVisible(true);
			newUser.mainPage();
		}
		else if(a.getSource() == buttons[1])
		{	dispose();
			Book newBook = new Book();
			newBook.mainPage();
		}
		else if(a.getSource() == buttons[2])
		{	dispose();
			Supplier newSupplier = new Supplier();
			newSupplier.mainPage();
		}
		else if(a.getSource() == buttons[3])
		{	dispose();
			Main.mainHandler();
		}
		else if(a.getSource() == buttons[4])
		{	dispose();
			setVisible(false);
		}
		else if(a.getSource()== buttons[6])//inventory
		{
			List<Inventory> inventory = null;
			inventory = saleDAO.getInventory();
			if(inventory.size()==0)
				JOptionPane.showMessageDialog(null, "There is no purchase or sales record yet");
			else
			{
				InventoryTableModel model = new InventoryTableModel(inventory);
				table.setModel(model);
			}
		}
		else if(a.getSource() == buttons[7])//get all sale
		{
			List<Sale> allSale = null;
			allSale = saleDAO.getAllSale();
			if(allSale.size()==0)
				JOptionPane.showMessageDialog(null, "There is no sales record");
			else
			{
				SaleTableModel model = new SaleTableModel(allSale);
				table.setModel(model);
			}
		}
		else if(a.getSource() == buttons[8])
		{
			List<TotalSale> totalSale = null;
			totalSale = saleDAO.totalSale();
			if(totalSale.size() == 0)
				JOptionPane.showMessageDialog(null, "There were no sales to Day");
			else
			{
				TotalSaleTabelModel model = new TotalSaleTabelModel(totalSale);
				table.setModel(model);
			}
		}
		else if (a.getSource()== buttons[9])//search by invoice
		{
			List<Sale> allSale = null;
			allSale = saleDAO.getAllSale();
			int invoice;
			try {
				invoice = Integer.parseInt(searchTxt.getText());
			}
			catch(Exception e)
			{
				invoice = 1;
			}
			allSale = saleDAO.searchSaleByInvoice(invoice);
			if(allSale.size() == 0)
				JOptionPane.showMessageDialog(null, "No eligible data was found");
			else
			{
				SaleTableModel model = new SaleTableModel(allSale);
				table.setModel(model);
			}
		}
		else if(a.getSource() == buttons[10])
		{	dispose();
			Report newReport = new Report();
			newReport.mainPage();
		}
	}
}
