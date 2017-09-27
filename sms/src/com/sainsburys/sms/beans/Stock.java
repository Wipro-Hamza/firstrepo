package com.sainsburys.sms.beans;

public class Stock {
	
	private int productID;
	private double price;
	private int quantity;
	
	public Stock() {
		
	}

	public int getProductID() {
		return this.productID;
	}

	public double getPrice() {
		return this.price;
	}

	public int getQuantity() {
		return this.quantity;
	}
	
	public String toString() {
		return "Product ID: " + this.getProductID() + "\nPrice: £" + this.getPrice()
				+ "\nQuantity: " + this.getQuantity();
	}
	
	public void setStockID(int productID) {
		this.productID = productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStockQuantity(int quantity) {
		this.quantity = quantity;
	}
}
