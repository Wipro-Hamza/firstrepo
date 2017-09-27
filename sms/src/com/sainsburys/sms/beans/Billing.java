package com.sainsburys.sms.beans;

public class Billing {
	
	private String billingID;
	private String customerID;
	private String productID;
	private int billingQuantity;
	private double billprice;
	
	public String getBillingID() {
		return billingID;
	}
	public void setBillingID(String billingID) {
		this.billingID = billingID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getBillingQuantity() {
		return billingQuantity;
	}
	public void setBillingQuantity(int billingQuantity) {
		this.billingQuantity = billingQuantity;
	}
	public double getBillprice() {
		return billprice;
	}
	public void setBillprice(double billprice) {
		this.billprice = billprice;
	}
}