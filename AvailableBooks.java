package application;
/**
 *
 * @author ShahEd
 */

import java.sql.Date;

public class AvailableBooks {
	public String PublishingHouse;
	public int BookID;
	public String BookName;
	public String Category;
	public double Price;
	public double CostPrice;
	public int NumberOfCopies;
	public Date PublishDate;
	public String Author;
	
	public AvailableBooks(String PublishingHouse, int BookID, String BookName, String Category, double Price, double CostPrice, int NumberOfCopies, 
		String Author , Date PublishDate) {
		this.PublishingHouse = PublishingHouse;
		this.BookID = BookID;
		this.BookName = BookName;
		this.Category = Category;
		this.Price = Price;
		this.CostPrice = CostPrice;
		this.NumberOfCopies = NumberOfCopies;
		this.PublishDate = PublishDate;
		this.Author = Author;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String Author) {
		this.Author = Author;
	}

	public String getPublishingHouse() {
		return PublishingHouse;
	}

	public void setPublishingHouse(String PublishingHouse) {
		this.PublishingHouse = PublishingHouse;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int BookID) {
		this.BookID = BookID;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String BookName) {
		this.BookName = BookName;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String type) {
		this.Category = type;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double Price) {
		this.Price = Price;
	}

	public double getCostPrice() {
		return CostPrice;
	}

	public void setCostPrice(double CostPrice) {
		this.CostPrice = CostPrice;
	}

	public int getNumberOfCopies() {
		return NumberOfCopies;
	}

	public void setNumberOfCopies(int NumberOfCopies) {
		this.NumberOfCopies = NumberOfCopies;
	}

	public Date getPublishDate() {
		return PublishDate;
	}

	public void setPublishDate(Date PublishDate) {
		this.PublishDate = PublishDate;
	}
}

