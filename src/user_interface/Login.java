package user_interface;

import classes.*;
import DAO.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	
	//gui variables
	private static final String [] typeName = {"Admin", "User"};
	
	private JPanel topNav = new JPanel();
	private JPanel bottomNav = new JPanel();
	private JButton clearBtn = new JButton("Clear");
	private JButton loginBtn = new JButton("Login");
	private JLabel appName = new JLabel("Down Town Book Store");
	private JLabel type = new JLabel("Login As : ");
	private JLabel userName = new JLabel("User Name : ");
	private JLabel password = new JLabel("Password  : ");
	private JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	private JTextField userNameText = new JTextField("");
	private JPasswordField passwordText = new JPasswordField("");
	private JComboBox userType = new JComboBox(typeName);
	public static final String admin  = "Admin";
	MainFrame mainFrame;
	CashierMain cashier;
	private EmployeeDAO user = new EmployeeDAO();
	
	Login(MainFrame mainFrame)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 420);
		setVisible(true);
		setLocation(550, 220);
		setTitle("Authenticatio Form");
		setResizable(false);
		setLayout(null);
		this.mainFrame = mainFrame;
		this.cashier = cashier;
		
		
	}
	public void loginPage()
	{	
	//top navigator

		appName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		appName.setForeground(Color.WHITE);
		topNav.add(appName);
		topNav.setBackground(new Color(0xff8c00));
		topNav.setBounds(0,0,420,50);
		add(topNav);
	
	//bottom navigator
		copy.setForeground(Color.WHITE);
		bottomNav.add(copy);
		bottomNav.setBackground(Color.BLACK);
		bottomNav.setBounds(0,361,420,30);
		FlowLayout Layout = new FlowLayout();
		Layout.setAlignment(FlowLayout.LEFT);
		bottomNav.setLayout(Layout);
		add(bottomNav);
		
	//central navigator
		type.setBounds(70, 140, 100, 25);
		add(type);
		userType.setBounds(150, 140, 200, 25);
		add(userType);
		userName.setBounds(70,180,100,25);
		add(userName);
		userNameText.setBounds(150,180, 200, 25);
		add(userNameText);
		password.setBounds(70, 220, 100, 25);
		add(password);
		passwordText.setBounds(150, 220, 200, 25);
		add(passwordText);
		loginBtn.setBounds(150, 255, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);
		add(loginBtn);
		clearBtn.setBounds(250, 255, 100, 25);
		clearBtn.setFocusable(false);
		clearBtn.addActionListener(this);
		add(clearBtn);

	}
	public boolean CheckTextField()
	{
		String u = userNameText.getText();
		String p = passwordText.getText();
		if(u.trim().length() == 0 || p.trim().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "insuffiecnt data");
			userNameText.setText("");
			passwordText.setText("");
			return true;
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent a)
	{	ResultSet result;
		String typeOfUser = "Admin";
		if(a.getSource() == clearBtn)
		{	userNameText.setText("");
			passwordText.setText("");
		}
		else if(a.getSource() == loginBtn)
		{	if(!CheckTextField())
			{	
				result = user.authenticate(userNameText.getText(), String.valueOf(passwordText.getText()));
				try 
				{
					if(result.next())
					{	
						Employee currentUser = user.ConvertToEmployee(result);
						if(userType.getSelectedItem().equals(admin) && result.getString("typeOfUser").equals(admin) )
						{	JOptionPane.showMessageDialog(null, "Login Successful");
							dispose();
							mainFrame.setVisible(true);
						}
					 	else if(userType.getSelectedItem().equals("User") && result.getString("typeOfUser").equals(admin) || userType.getSelectedItem().equals("User") && result.getString("typeOfUser").equals("User"))
					 	{	JOptionPane.showMessageDialog(null, "Login Successful");
					 		dispose();
					 		CashierMain cashier = new CashierMain(currentUser);
					 		cashier.mainPage();
					 		cashier.setVisible(true);
					 	}
					 	else
					 		JOptionPane.showMessageDialog(null, "You don't have permission to access this page!!!");
					}
					else
					{	JOptionPane.showMessageDialog(null, "Login Failed");
						userNameText.setText("");
						passwordText.setText("");
					}
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//	mainFrame.setVisible(true);
		}
	}
}
