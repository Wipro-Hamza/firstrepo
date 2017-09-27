package com.sainsburys.sms.beans;

public class Customer {

	// customerName
	// address
	// contact number
	private String customerName;
	private String address;
	private String contactNumber;
	private int customerId;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getcustomerId() {
		return customerId;
	}

	public void setcustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [\ncustomerName=" + customerName + "\naddress="
				+ address + "\ncontactNumber=" + contactNumber
				+ "\ncustomerId=" + customerId + "\n]";
	}

	public String getcontactNumber() {
		return contactNumber;
	}

	public void setcNumber(String contactNumber2) {
		this.contactNumber = contactNumber2;
	}

}
