package classes;

public class Inventory {
	
	private int BookId;
	private double purchased;
	private double soled;
	public Inventory(int bookId, double purchased, double soled)
	{
		BookId = bookId;
		this.purchased = purchased;
		this.soled = soled;
	}
	public int getBookId()
	{
		return BookId;
	}
	public double getPurchased()
	{
		return purchased;
	}
	public double getSold()
	{
		return soled;
	}
}
