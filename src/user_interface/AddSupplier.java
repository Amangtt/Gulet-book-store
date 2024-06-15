package user_interface;

import classes.*;
import DAO.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddSupplier extends JFrame implements ActionListener{
	
	private static final String [] btnName = {"Submit", "Finish", "Exit"};
	private String type;
	
	private static final JPanel topNav = new JPanel();
	private static final JPanel bottomNav = new JPanel();
	private static final JLabel appName = new JLabel("DownTown Book Store");
	private static final JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	
	private static final JLabel name = new JLabel("Company Name : ");
	private static final JLabel tinNumber = new JLabel("Tin Number : ");
	private static final JLabel address = new JLabel("Address : ");
	private static final JLabel email = new JLabel("Email : ");
	private static final JLabel tel1 = new JLabel("Telephone 1 : ");
	private static final JLabel tel2 = new JLabel("Telepjhone 2 : ");
	
	private JTextField nameTxt = new JTextField();
	private JTextField tinNumberTxt = new JTextField();
	private JTextField addressTxt = new JTextField();
	private JTextField emailTxt = new JTextField();
	private JTextField telTxt1 = new JTextField();
	private JTextField telTxt2 = new JTextField();
	
	private JButton buttons[] = new JButton[3];
	
	private boolean updateMode = false;
	private SupplierClass supplier = null;
	private SupplierDAO supplierDAO = new SupplierDAO();
	
	public AddSupplier(String type, SupplierClass supplier, boolean updateMode)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize (440, 500);
		setVisible(true);
		setLocation(650, 150);
		setTitle(type + " Supplier");
		setResizable(false);
		setLayout(null);
		
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
		}
		this.type = type;
		this.updateMode = updateMode;
		this.supplier = supplier;
	}
	public void mainPage()
	{
	//top navigator
		appName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		appName.setForeground(Color.WHITE);
		topNav.add(appName);
		topNav.setBackground(new Color(0xff8c00));
		topNav.setBounds(0,0,440,50);
		add(topNav);
		
	//bottom navigator
		copy.setForeground(Color.WHITE);
		bottomNav.add(copy);
		bottomNav.setBackground(Color.BLACK);
		bottomNav.setBounds(0,442,440,30);
		FlowLayout Layout = new FlowLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		bottomNav.setLayout(Layout);
		add(bottomNav);
		
	//center navigator
		name.setBounds(70, 80, 120, 25);
		tinNumber.setBounds(70, 120, 120, 25);
		address.setBounds(70,160, 120, 25);
		email.setBounds(70, 200, 120, 25);
		tel1.setBounds(70, 240, 120, 25);
		tel2.setBounds(70, 280, 120, 25);
		
		nameTxt.setBounds(180, 80, 200, 25);
		tinNumberTxt.setBounds(180, 120, 200, 25);
		addressTxt.setBounds(180, 160, 200, 25);
		emailTxt.setBounds(180, 200, 200, 25);
		telTxt1.setBounds(180, 240, 200, 25);
		telTxt2.setBounds(180, 280, 200, 25);
		
		buttons[0].setBounds(90, 320, 100, 25);
		buttons[1].setBounds(190, 320, 100, 25);
		buttons[2].setBounds(290, 320, 80, 25);
		
		if(updateMode)
			populate(supplier);
		
		add(name);
		add(tinNumber);
		add(address);
		add(email);
		add(tel1);
		add(tel2);
		add(nameTxt);
		add(tinNumberTxt);
		add(addressTxt);
		add(emailTxt);
		add(telTxt1);
		add(telTxt2);
	
	//adding to action listener
		for(int i = 0; i < btnName.length; i++)
		{	add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
	}
	public void populate(SupplierClass s)
	{
		nameTxt.setText(s.getName());
		tinNumberTxt.setText(s.getTin());
		addressTxt.setText(s.getAddress());
		telTxt1.setText(s.getTel());
		telTxt2.setText(s.getTel2());
		emailTxt.setText(s.getEmail());
	}
	public SupplierClass getSupplier() 
	{	
		SupplierClass newSupplier = null;
		
		int id = 0;
		String name = nameTxt.getText();
		String tin = tinNumberTxt.getText();
		String address = addressTxt.getText();
		String tel = telTxt1.getText();
		String tel2 = telTxt2.getText();
		String email = emailTxt.getText();
		if(updateMode)
			id = supplier.getId();
		newSupplier = new SupplierClass(id, name, tin, address, tel, tel2, email);
		
		return newSupplier;
	}
	public int checkTextField()
	{
		int result = 1;
		String n = nameTxt.getText();
		String t = tinNumberTxt.getText();
		String e = emailTxt.getText();
		String t1 = telTxt1.getText();
		String t2 = telTxt2.getText();
		if(n.trim().length() == 0 || t.trim().length() == 0 || e.trim().length() == 0 || t1.trim().length() == 0 || t2.trim().length() == 0 )
		{	
			nameTxt.getText();
			tinNumberTxt.setText(" ");
			emailTxt.setText(" ");
			telTxt1.setText(" ");
			telTxt2.setText(" ");
			result = 0;
		}
		return result;
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == buttons[0])
		{
			if(checkTextField() == 1)
			{	
				SupplierClass temp = getSupplier();
				if(updateMode)
					supplierDAO.updateSupplier(temp);
				else
					supplierDAO.insertSupplier(temp); 
				JOptionPane.showMessageDialog(null, type + " Completed Successfully");
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Form is not complete");
		}
		else if(a.getSource() == buttons[1])
		{	nameTxt.getText();
			tinNumberTxt.setText(" ");
			emailTxt.setText(" ");
			telTxt1.setText(" ");
			telTxt2.setText(" ");
		}
		else if(a.getSource() == buttons[2])
		{
			dispose();
		}
	}
}
