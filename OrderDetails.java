package application;
/**
 *
 * @author ShahEd
 */


import java.sql.Date;

public class OrderDetails extends Orders{
	private int ISBN;
	private String title;
	private int count;
	private double price;
	
	public OrderDetails(int order_num, int ISBN, int cid ,String title, int count, double price, Date order_date){
		super(order_num, cid, order_date);
		this.count = count;
		this.ISBN = ISBN;
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

