package classes;

public class Recipet {
	
	protected int id;
	protected int invoice;
	protected int employeeId;
	protected int isbn;
	protected int quantity;
	protected int orderId;
	
	public Recipet(int id, int invoice, int employeeId, int bookId, int quantity, int orderId) 
	{
		this.id = id;
		this.invoice = invoice;
		this.employeeId = employeeId;
		this.isbn = bookId;
		this.quantity = quantity;
		this.orderId = orderId;
	}
	public int getId()
	{
		return id;
	}
	public int getOrderId()
	{
		return orderId;
	}
	public int getInvoice()
	{
		return invoice;
	}
	public int getEmployeeId()
	{
		return employeeId;
	}
	public int getIsbn()
	{
		return isbn;
	}
	public int getQuanity()
	{
		return quantity;
	}
}
