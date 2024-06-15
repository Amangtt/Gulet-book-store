package user_interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import classes.*;
import DAO.*;


public class AddBook extends JFrame implements ActionListener{
	
	private static final String [] btnName = {"Submit", "Clear", "Exit"};
	private String type;
	
	private static final JPanel topNav = new JPanel();
	private static final JPanel bottomNav = new JPanel();
	private static final JLabel appName = new JLabel("DownTown Book Store");
	private static final JLabel copy = new JLabel("© ALL COPY RIGHT RESERVED 2013/2021");
	
	private static final JLabel title = new JLabel("Book Title : ");
	private static final JLabel author = new JLabel("Book Author : ");
	private static final JLabel category = new JLabel("Book Category : ");
	private static final JLabel publisher = new JLabel("Book Publisher : ");
	private static final JLabel edition = new JLabel("Book Edition");
	private static final JLabel isbn = new JLabel("Book ISBN : ");
	
	private JTextField titleTxt = new JTextField();
	private JTextField authorTxt = new JTextField();
	private JTextField categoryTxt = new JTextField();
	private JTextField publisherTxt = new JTextField();
	private JTextField editionTxt = new JTextField();
	private JTextField isbnTxt = new JTextField();
	
	private JButton buttons[] = new JButton[3];
	private boolean updateMode = false;
	private BookDAO bookDAO = new BookDAO();
	private BookClass book;
	
	public AddBook(String type, BookClass book, boolean mode) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(440, 450);
		setVisible(true);
		setLocation(620, 150);
		setTitle(type + " Book");
		setResizable(false);
		setLayout(null);
		
		for(int i = 0; i < btnName.length; i++)
		{	buttons[i] = new JButton(btnName[i]);
			buttons[i].setFocusable(false);
		}
		this.type = type;
		this.book = book;
		updateMode = mode;
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
			bottomNav.setBounds(0,392,440,30);
			FlowLayout Layout = new FlowLayout();
			Layout.setAlignment(FlowLayout.LEFT);
			bottomNav.setLayout(Layout);
			add(bottomNav);
			
		//center navigator
			title.setBounds(70,80,120,25);
			author.setBounds(70, 120, 120, 25);
			category.setBounds(70, 160, 120, 25);
			publisher.setBounds(70, 200, 120, 25);
			edition.setBounds(70, 240, 120, 25);
			isbn.setBounds(70, 280, 120, 25);
			
			titleTxt.setBounds(180,80,200,25);
			authorTxt.setBounds(180, 120, 200, 25);
			categoryTxt.setBounds(180, 160, 200, 25);
			publisherTxt.setBounds(180, 200, 200, 25);
			editionTxt.setBounds(180, 240, 200, 25);
			isbnTxt.setBounds(180, 280, 200, 25);
			
			buttons[0].setBounds(90, 320, 100, 25);
			buttons[1].setBounds(190, 320, 100, 25);
			buttons[2].setBounds(290, 320, 80, 25);
			
			if(updateMode)
				populate(book);
			
			add(title);
			add(titleTxt);
			add(author);
			add(authorTxt);
			add(category);
			add(categoryTxt);
			add(publisher);
			add(publisherTxt);
			add(edition);
			add(editionTxt);
			add(isbn);
			add(isbnTxt);
			
		//adding to action listener
			for(int i = 0; i < btnName.length; i++)
			{	add(buttons[i]);
				buttons[i].addActionListener(this);
			}
	}
	public boolean checkTextField()
	{
		boolean result = true;
		
		String t = titleTxt.getText();
		String a = authorTxt.getText();
		String c = categoryTxt.getText();
		String p = publisherTxt.getText();
		String e = editionTxt.getText();
		if(t.trim().length() == 0 || a.trim().length() == 0 || c.trim().length() == 0 || p.trim().length() == 0 || e.trim().length() == 0)
		{
			titleTxt.setText(" ");
			authorTxt.setText(" ");
			categoryTxt.setText(" ");
			publisherTxt.setText(" ");
			editionTxt.setText(" ");
			result = false;
		}
		return result;
	}
	public void populate(BookClass b)
	{
		titleTxt.setText(b.getTitle());
		authorTxt.setText(b.getAuthor());
		categoryTxt.setText(b.getCategory());
		publisherTxt.setText(b.getPublisher());
		editionTxt.setText(String.valueOf(b.getEdition()));
		isbnTxt.setText(b.getIsbn());
	}
	public BookClass getBook()
	{
		String title = titleTxt.getText();
		String author = authorTxt.getText();
		String category = categoryTxt.getText();
		String publisher = publisherTxt.getText();
		String isbn = isbnTxt.getText();
		int id = 0;
		if(updateMode)
			id = book.getId();
		int edition;
		try
		{
			edition = Integer.parseInt(editionTxt.getText());
		}
		catch(Exception e)
		{
			edition = 1;
		}
		BookClass b = new BookClass(title, author, category, 0, publisher, id, isbn, edition, 0);	
		
		return b;
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == buttons[0])
		{
			if(checkTextField())
			{	
				BookClass temp = getBook();
				
				if(updateMode)
					bookDAO.update_book(temp);
				else
					bookDAO.insert_book(temp);
				JOptionPane.showMessageDialog(null, type + " Completed Successfully");
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Form is not complete");
		}
		else if (a.getSource() == buttons[1])
		{
			titleTxt.setText(" ");
			authorTxt.setText(" ");
			categoryTxt.setText(" ");
			publisherTxt.setText(" ");
			editionTxt.setText(" ");
		}
		else if(a.getSource() == buttons[2])
		{
			dispose();
		}
	}
}
