package carRentalSystem.in;

public class Customer {
	
	private String customerId;
	private String customerName;
	
	Customer(String customerId,String customerName) {
		this.customerId=customerId;
		this.customerName=customerName;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	
}
