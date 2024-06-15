package classes;

public class Employee {

	private String fName;
	private String lName;
	private String tel1;
	private String tel2;
	private String typeOfUser;
	private String userName;
	private String password;
	private double salary;
	private String gender;
	private int id;
	private int managerId;
	
	public Employee(String fName, String lName, String tel1, String tel2, String typeOfUser, 
					String userName, String password, double salary2,String gender, int id, int managerId)
	{
		this.fName = fName;
		this.lName = lName;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.typeOfUser = typeOfUser;
		this.userName = userName;
		this.password = password;
		this.salary = salary2;
		this.gender = gender;
		this.id = id;
		this.managerId = managerId;
	}
	public Employee()
	{
		
	}
	
	public String getFname()
	{
		return fName;
	}
	public String getLname()
	{
		return lName;
	}
	public String getTel1()
	{
		return tel1;
	}
	public String getTel2()
	{
		return tel2;
	}
	public String getTypeofUser()
	{
		return typeOfUser;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	public double getSalary()
	{
		return salary;
	}
	public String getGender()
	{
		return gender;
	}
	public int getId()
	{
		return id;
	}
	public int getManagerId()
	{
		return managerId;
	}
	
}
