package application;

import java.sql.Date;
import java.io.IOException;
public class Bill {
	private int bill_num;
	private int cid;
	private int order_num;
	private double cash;
	private Date date;
	private boolean isPaid;
	
	public Bill(int bill_num, int cid, int order_num, double cash, Date date) {
		this.bill_num = bill_num;
		this.cid = cid;
		this.order_num = order_num;
		this.cash = cash;
		
		this.date = date;
		this.isPaid = false;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getBill_num() {
		return bill_num;
	}

	public void setBill_num(int bill_num) {
		this.bill_num = bill_num;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
