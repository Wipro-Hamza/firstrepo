package com.sainsburys.sms.beans;

public class Billing {
	
	private int billingID;
	private int customerID;
	private String productID;
	private int billingQuantity;
	private double billprice;
	
	public int getBillingID() {
		return billingID;
	}
	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
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