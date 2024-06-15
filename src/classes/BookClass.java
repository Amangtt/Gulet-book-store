package classes;

public class BookClass {
	
	private String title;
	private String author;
	private String category;
	private double price;
	private String publisher;
	private int id;
	private String isbn;
	private int edition;
	private int copy;
	
	public BookClass(String title, String author, String category, double price, String publisher, int id, String isbn, int edition, int copy)
	{
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.publisher = publisher;
		this.id = id;
		this.isbn = isbn;
		this.edition = edition;
		this.copy = copy;
	}
	public String getTitle()
	{
		return title;
	}
	public String getAuthor()
	{
		return author;
	}
	public String getCategory()
	{
		return category;
	}
	public double getPrice()
	{
		return price;
	}
	public String getPublisher()
	{
		return publisher;
	}
	public int getId()
	{
		return id;
	}
	public String getIsbn()
	{
		return isbn;
	}
	public int getEdition()
	{
		return edition;
	}
	public int getCopy()
	{
		return copy;
	}
}
