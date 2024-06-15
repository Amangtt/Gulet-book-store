package classes;

public class PurchaseClass extends Recipet{
	
	private int supplier;
	private double price;
	
	public PurchaseClass(int id, int invoice, int employeeId, int isbn, int quantity, int supplier, int orderId, double price)
	{
		super(id, invoice, employeeId, isbn, quantity, orderId);
		this.supplier = supplier;
		this.price = price;
	}
	public int getSupplier()
	{
		return supplier;
	}
	public double getPrice()
	{
		return price;
	}
}
