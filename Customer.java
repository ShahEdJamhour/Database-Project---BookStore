package application;

/**
 *
 * @author ShahEd
 */



public class Customer {
	private int id;
	private String cname;
	private String area;
	private int phone_num;

	
	public Customer(int id, String cname, String area,int phone_num) {
		this.id = id;
		this.cname = cname;
		this.area = area;
	
		this.phone_num = phone_num;
		
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	

	public int getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(int phone_num) {
		this.phone_num = phone_num;
	}
}

