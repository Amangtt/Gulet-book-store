package classes;

public class Sale extends Recipet{
	
	private String customer;
	private double discount;
	
	public Sale(int id, int invoice, int employeeId, int bookId, int quantity, String customer, double discount, int orderId)
	{	
		super(id, invoice, employeeId, bookId, quantity, orderId);
		this.customer = customer;
		this.discount = discount;
	}
	public String getCustomer()
	{
		return customer;
	}
	public double getDiscount()
	{
		return discount;
	}
}
