package user_interface;

import classes.*;
import DAO.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddEmployee extends JFrame implements ActionListener{
	
	private static final String []usertype = {"Admin", "User"};
	private static final String [] btnName = {"Submit", "Clear", "Exit"};
	private String type;
	
	private static final JPanel topNav = new JPanel();
	private static final JPanel bottomNav = new JPanel();
	private static final JLabel appName = new JLabel("DownTown Book Store");
	private static final JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	
	private static final JLabel fName = new JLabel("First Name : ");
	private static final JLabel lName = new JLabel("Last Name : ");
	private static final JLabel gender = new JLabel("Gender : ");
	private static final JLabel typeOfUser = new JLabel("User Type : ");
	private static final JLabel salary = new JLabel("Salary : ");
	private static final JLabel userName = new JLabel("User Name : ");
	private static final JLabel password = new JLabel("Password : ");
	private static final JLabel confirmPass = new JLabel("Confirm Password ");
	private static final JLabel telephone = new JLabel("Telephone no. 1 : ");
	private static final JLabel telephone2 = new JLabel("Telephone no. 2 : ");
	private static final JLabel manager = new JLabel("Manager Id : ");
	
	private JTextField fnameText = new JTextField();
	private JTextField lnameText = new JTextField();
	private JTextField salaryText = new JTextField();
	private JTextField managerText = new JTextField();
	private JTextField telphoneText = new JTextField();
	private JTextField telphoneText2 = new JTextField();
	private JTextField userNameText = new JTextField();
	private JPasswordField passwordText = new JPasswordField();
	private JPasswordField confirmText = new JPasswordField();
	
	private JButton buttons[] = new JButton[3];
	
	private JRadioButton male = new JRadioButton("Male", true);
	private JRadioButton female = new JRadioButton("Female", false);
	private ButtonGroup genderRadio = new ButtonGroup();
	
	private JComboBox userType = new JComboBox(usertype);
	private boolean upadteMode;
	private Employee temp = null;
	private EmployeeDAO employee = new EmployeeDAO();
	
	public AddEmployee(String type, Employee employee, boolean upadteMode) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(440, 615);
		setVisible(true);
		setLocation(620, 100);
		setTitle(type + " Employee");
		setResizable(false);
		setLayout(null);
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
		}
		genderRadio.add(male);
		genderRadio.add(female);
		this.type = type;
		this.temp = employee;
		this.upadteMode = upadteMode;
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
	//center navigator
		fName.setBounds(70,80,120,25);
		lName.setBounds(70, 120, 120, 25);
		gender.setBounds(70, 160, 120, 25);
		typeOfUser.setBounds(70, 200, 100, 25);
		telephone.setBounds(70, 240, 120, 25);
		telephone2.setBounds(70, 280, 120, 25);
		manager.setBounds(70, 320, 120, 25);
		salary.setBounds(70, 360, 120, 25);
		userName.setBounds(70, 400, 120, 25);
		password.setBounds(70, 440, 120, 25);
		confirmPass.setBounds(70, 480, 120, 25);
		
		fnameText.setBounds(180,80, 200, 25);
		lnameText.setBounds(180,120, 200, 25);
		male.setBounds(180, 160, 100, 25);
		female.setBounds(280, 160, 100, 25);
		userType.setBounds(180, 200, 200, 25);
		telphoneText.setBounds(180, 240, 200, 25);
		telphoneText2.setBounds(180, 280, 200, 25);
		managerText.setBounds(180, 320, 200, 25);
		salaryText.setBounds(180, 360, 200, 25);
		userNameText.setBounds(180, 400, 200, 25);
		passwordText.setBounds(180, 440, 200, 25);
		confirmText.setBounds(180, 480, 200, 25);
		buttons[0].setBounds(90, 520, 100, 25);
		buttons[1].setBounds(190, 520, 100, 25);
		buttons[2].setBounds(290, 520, 80, 25);

		if(upadteMode)
			populate(temp);

			
		add(fName);
		add(lName);
		add(gender);
		add(typeOfUser);
		add(telephone);
		add(telephone2);
		add(salary);
		add(manager);
		add(userName);
		add(password);
		add(confirmPass);
		add(fnameText);
		add(lnameText);
		add(male);
		add(female);
		add(userType);
		add(telphoneText);
		add(telphoneText2);
		add(salaryText);
		add(managerText);
		add(userNameText);
		add(passwordText);
		add(confirmText);
	//bottom navigator
		
		copy.setForeground(Color.WHITE);
		bottomNav.add(copy);
		bottomNav.setBackground(Color.BLACK);
		bottomNav.setBounds(0,557,440,30);
		FlowLayout Layout = new FlowLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		bottomNav.setLayout(Layout);
		add(bottomNav);
	//adding to action listener
		for(int i = 0; i < btnName.length; i++)
		{	add(buttons[i]);
			buttons[i].addActionListener(this);
		}
	}
	public int checkTextField()
	{
		int result = 1;
		String f = fnameText.getText();
		String l = lnameText.getText();
		String s = salaryText.getText();
		String m = managerText.getText();
		String t = telphoneText.getText();
		String t2 = telphoneText2.getText();
		String u = userNameText.getText();
		String p = passwordText.getText();
		String c = confirmText.getText();
		if(f.trim().length() == 0 || l.trim().length() == 0 || s.trim().length() == 0 || m.trim().length() == 0 || t.trim().length() == 0 || t2.trim().length() == 0 || u.trim().length() == 0 || p.trim().length() == 0 || c.trim().length() == 0 )
		{	
			fnameText.setText("");
			lnameText.setText("");
			salaryText.setText("");
			managerText.setText("");
			telphoneText.setText("");
			telphoneText2.setText("");
			userNameText.setText("");
			passwordText.setText("");
			confirmText.setText("");
			result = 0;
		}
		else if(c.compareTo(p) != 0)
		{	
			fnameText.setText("");
			lnameText.setText("");
			salaryText.setText("");
			managerText.setText("");
			telphoneText.setText("");
			telphoneText2.setText("");
			userNameText.setText("");
			passwordText.setText("");
			confirmText.setText("");
			result = -1;
		}

		return result;
	}
	public void populate(Employee e)
	{	
		fnameText.setText(e.getFname());
		lnameText.setText(e.getLname());
		salaryText.setText(String.valueOf(e.getSalary()));
		managerText.setText(String.valueOf(e.getManagerId()));
		telphoneText.setText(e.getTel1());
		telphoneText2.setText(e.getTel2());
		userNameText.setText(e.getUserName());
		passwordText.setText(e.getPassword());
		confirmText.setText(e.getPassword());
		if(e.getGender().equals("M"))
			male.setSelected(true);
		else
			female.setSelected(true);
		if(e.getTypeofUser().equals("Admin"))
			userType.setSelectedItem("Admin");
		else
			userType.setSelectedItem("User");
	}
	public Employee getEmployee()
	{	
		String fName = fnameText.getText();
		String lName = lnameText.getText();
		double salary = 0;
		int managerId = 1;
		int id = 0;
		if(upadteMode)
			id = temp.getId();
		String tel1 = telphoneText.getText();
		String tel2 = telphoneText2.getText();
		String userName = userNameText.getText();
		String password = passwordText.getText();
		String gender;
		try
		{
			salary = Double.parseDouble(salaryText.getText());
			managerId = Integer.parseInt(managerText.getText());
		}
		catch(Exception e)
		{
			System.out.println("\tNo numbers enterned for the items\nFor 1. Manager Id\nFor 2. salary");
		}
		if(male.isSelected())	gender = "M";
		else	gender = "F";
		String type = String.valueOf(userType.getSelectedItem());
	
		Employee newEmployee = new Employee(fName, lName, tel1, tel2, type, userName, password, salary, gender, id, managerId);
		
		return newEmployee;
	}
	@Override
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == buttons[0])
		{	
			switch(checkTextField())
			{
			case 1:
				temp = getEmployee();
				if(upadteMode)
					employee.upadte_Employee(temp);
				else
					employee.insert_Employee(temp);	
				JOptionPane.showMessageDialog(null, type + " Completed Successfully");
				dispose();
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "Form is not complete");
				break;
			case -1:
				JOptionPane.showMessageDialog(null, "The passwords doesnot match");
				break;
			}
		}
		else if (a.getSource() == buttons[1])
		{
			fnameText.setText("");
			lnameText.setText("");
			salaryText.setText("");
			managerText.setText("");
			telphoneText.setText("");
			telphoneText2.setText("");
			userNameText.setText("");
			passwordText.setText("");
			confirmText.setText("");
		}
		else if(a.getSource() == buttons[2])
			dispose();
	}
}
