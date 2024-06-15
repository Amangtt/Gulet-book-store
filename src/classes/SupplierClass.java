package classes;

public class SupplierClass {
		
	private String name;
	private String tin;
	private int id;
	private String address;
	private String tel;
	private String tel2;
	private String email;
	
	public SupplierClass(int id, String name, String tin, String address, String tel, String tel2, String email)
	{
		this.name = name;
		this.id = id;
		this.tin = tin;
		this.address = address;
		this.tel = tel;
		this.tel2 = tel2;
		this.email = email;
	}
	public String getName()
	{
		return name;
	}
	public int getId()
	{
		return id;
	}
	public String getTin()
	{
		return tin;
	}
	public String getAddress()
	{
		return address;
	}
	public String getTel()
	{
		return tel;
	}
	public String getTel2()
	{
		return tel2;
	}
	public String getEmail()
	{
		return email;
	}
}
