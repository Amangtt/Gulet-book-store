package user_interface;

import classes.*;
import DAO.*;
import TableModel.BookTableModel;
import TableModel.SupplierTableModel;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Supplier extends JFrame implements ActionListener {
	private JPanel topNav = new JPanel();
	private JPanel bottomNav = new JPanel();
	private JPanel westNav = new JPanel();
	private JPanel eastNav = new JPanel();
	private JPanel centerNav = new JPanel();
	private JLabel appName = new JLabel("DownTown Book Store");
	private JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	private JLabel about = new JLabel("<html><b><br><br>"
			  +"---------------------------------<br>"
			  + "&#160; &#160; &#160;About <br>"
			  +"---------------------------------"
			  +"<br>This software is made <br>for the main purpose of "
			  + "<br> automating a <br>book store</b>");
	
	private JPanel searchPan = new JPanel();
	private JLabel searchLabel = new JLabel("Enter first name : ");
	private JTextField searchTxt = new JTextField("");
	
	private JButton [] buttons  = new JButton[12];
	private String [] btnName = {"Manage User", "Manage Book", "Manage Supplier", "Back to Authentication", "Exit"," " , "Add Supplier",
								 "Update Supplier", "Delete Supplier", "View All Supplier", "Search", "Manage Report"};
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	private SupplierClass suppliers;
	private SupplierDAO supplierDAO = new SupplierDAO();
	
	public Supplier()
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
		buttons[11].setBackground(Color.LIGHT_GRAY);
		buttons[2].setBackground(new Color(240,240,240));
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
		con.weighty  = 0;
		westNav.add(buttons[8], con);
		con.gridx = 0;
		con.gridy = 4;
		con.weighty  = 0.1;
		westNav.add(buttons[9], con);
		con.gridx = 0;
		con.gridy = 5;
		con.weighty  = 1;


	
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
		centerNav.add(buttons[11], constraints);
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
		searchPan.add(buttons[10]);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 6;
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
	public void populate()//method that populate the update GUI
	{
		
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
		else if(a.getSource() == buttons[11])
		{	dispose();
			Report newReport = new Report();
			newReport.mainPage();
		}
		else if(a.getSource() == buttons[6])//add supplier
		{	
			SupplierClass temp = null;
			AddSupplier newSupplier = new AddSupplier("Add", temp, false);
			newSupplier.mainPage();
			
		}
		else if(a.getSource() == buttons[7])//update supplier
		{	
			int row = table.getSelectedRow();
			if(row < 0)
				JOptionPane.showMessageDialog(null, "You must select a supplier to update");
			else
			{	
				SupplierClass selectedSupplier = (SupplierClass) table.getValueAt(row, SupplierTableModel.OBJECT_COL);
				AddSupplier newSupplier = new AddSupplier("Update", selectedSupplier, true);
				newSupplier.mainPage();
				List<SupplierClass> suppliers = new ArrayList<>();
				suppliers = supplierDAO.getAllSupplier();
				if(suppliers.size() == 0)
					JOptionPane.showMessageDialog(null,	"No eligible data was found");
				else
				{
					SupplierTableModel model = new SupplierTableModel(suppliers);
					table.setModel(model);
				}
			}
		}
		else if(a.getSource() == buttons[8])//delete supplier
		{	
			int row = table.getSelectedRow();
			if(row < 0)
				JOptionPane.showMessageDialog(null, "You must select a supplier to delete");
			else
			{	
				SupplierClass selectedSupplier = (SupplierClass) table.getValueAt(row, SupplierTableModel.OBJECT_COL);
				supplierDAO.deleteSupplier(selectedSupplier.getId());
				JOptionPane.showMessageDialog(null, "Delete successful");
				List<SupplierClass> suppliers = new ArrayList<>();
				suppliers = supplierDAO.getAllSupplier();
				if(suppliers.size() == 0)
					JOptionPane.showMessageDialog(null,	"No eligible data was found");
				else
				{
					SupplierTableModel model = new SupplierTableModel(suppliers);
					table.setModel(model);
				}
			}
		}
		else if(a.getSource() == buttons[9])//view employee
		{	
			List<SupplierClass> suppliers = new ArrayList<>();
			suppliers = supplierDAO.getAllSupplier();
			if(suppliers.size() == 0)
				JOptionPane.showMessageDialog(null,	"No eligible data was found");
			else
			{
				SupplierTableModel model = new SupplierTableModel(suppliers);
				table.setModel(model);
			}
			
		}
		else if(a.getSource() == buttons[10])//search employee name
		{	
			List<SupplierClass> suppliers = new ArrayList<>();
			
			String text = searchTxt.getText();
			if(text.trim().length() == 0)
				suppliers = supplierDAO.getAllSupplier();
			else
				suppliers = supplierDAO.searchSupplier(text);
			if(suppliers.size() == 0)
				JOptionPane.showMessageDialog(null,	"No eligible data was found");
			else
			{
				SupplierTableModel model = new SupplierTableModel(suppliers);
				table.setModel(model);
			}
		}		
	}
}
