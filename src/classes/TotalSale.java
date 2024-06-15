package classes;

public class TotalSale {
	
	private double beforeVat;
	private double vat;
	private double total;
	public TotalSale(double beforeVat, double vat, double total)
	{
		this.beforeVat = beforeVat;
		this.vat = vat;
		this.total = total;
	}
	public double getBeforeVat()
	{
		return beforeVat;
	}
	public double getVat()
	{
		return vat;
	}
	public double getTotal()
	{
		return total;
	}
}
