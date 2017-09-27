package com.sainsburys.sms.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.sainsburys.sms.beans.Billing;
import com.sainsburys.sms.beans.Customer;
import com.sainsburys.sms.beans.Stock;
import com.sainsburys.sms.dao.BillingDAO;
import com.sainsburys.sms.dao.CustomerDAO;
import com.sainsburys.sms.dao.StockDAO;

public class Service {
	private static Scanner scanner = new Scanner(System.in);
	
	CustomerDAO dao = new CustomerDAO();
	Customer bean = new Customer();

	public static void main(String[] args) {
		System.out.println("This is Sainsbury's Stock Management System, and will manage Customers, PRoducts and Billing for Sainsbury's");
		createMenu();
	}
	
	private static void createMenu() {
		boolean cont = true;
		while(cont) {
			System.out.println("Please enter the menu option from below\n");
			System.out.println("Home Menu");
			System.out.println("1. Customer Management");
			System.out.println("2. Product Management");
			System.out.println("3. Billing Management");
			System.out.println("4. End Program\n");
			try {
				System.out.println("Enter Option: ");
				int option = scanner.nextInt();
				switch (option) {
				case 1:
					createCustomerMenu();
					break;
				case 2:
					createProductMenu();
					break;
				case 3:
					createBillingMenu();
					break;
				case 4:
					System.out.println("Thank you for using Sainsbury's Stock Management System");
					cont = false;
					break;
				}
			} catch(InputMismatchException e) {
				System.out.println("You need to enter an Integer - 1, 2, 3 or 4");
			}
		}
	}
	
	private static void createCustomerMenu() {
		boolean status = true;

		while (status) {
			menu();
			int input = scanner.nextInt();

			switch (input) {
			case 1:
				newCustomer();
				break;

			case 2:
				updateCustomer();
				break;

			case 3:
				readCustomer();
				break;

			case 4:
				deleteCustomer();
				break;

			case 5:
				status = false;
				break;
			}

		}
		System.out.println("Thank you for using the system.");

	}

	private static void menu() {
		System.out.println("Customer menu");
		System.out.println("To create new Customer details enter 1");
		System.out.println("To update existing Customer details enter 2");
		System.out.println("To view existing Customer details enter 3");
		System.out.println("To delete Customer details enter 4");
		System.out.println("To exit menu enter 5");

	}

	private static void newCustomer() {

		System.out.println("You entered 1 to create new Customer details");
		System.out.println("Enter Customer ID");
		int id = scanner.nextInt();
		System.out.println("Enter name");
		String name = scanner.next();
		System.out.println("Enter address");
		String address = scanner.next();
		System.out.println("Enter number");
		String number = scanner.next();
		
		Customer bean = new Customer();
		CustomerDAO dao = new CustomerDAO();

		bean.setcustomerId(id);
		bean.setCustomerName(name);
		bean.setAddress(address);
		bean.setcNumber(number);

		dao.insertCustomer(bean);

	}

	private static void updateCustomer() {

		System.out.println("You entered 2 to update Customer details");

		System.out.println("Enter Customer ID");
		int id = scanner.nextInt();

		System.out.println("Enter new customer details below");
		System.out.println("Enter name");
		String name = scanner.next();
		System.out.println("Enter address");
		String address = scanner.next();
		System.out.println("Enter number");
		String number = scanner.next();
		
		Customer bean = new Customer();
		CustomerDAO dao = new CustomerDAO();

		bean.setcustomerId(id);
		bean.setCustomerName(name);
		bean.setAddress(address);
		bean.setcNumber(number);

		dao.updateCustomer(bean);
	}
	
	private static void readCustomer() {
		System.out.println("You entered 3 to view existing Customer details");
		
		System.out.println("Enter customer ID you wish to read: ");
		int id = scanner.nextInt();
		
		Customer bean = new Customer();
		CustomerDAO dao = new CustomerDAO();
		
		bean = dao.readCustomer(id);
		System.out.println(bean.toString());
	}
	
	private static void deleteCustomer() {
		
		System.out.println("You entered 4 to delete Customer details");
		System.out.println("Enter customer ID you wish to delete: ");
		int id = scanner.nextInt();
		
		CustomerDAO dao = new CustomerDAO();
		
		if(dao.deleteCustomer(id)) {
			System.out.println("Customer Deleted Successfully");
		} else {
			System.out.println();
		}
	}
	
	private static void createProductMenu() {
		boolean cont = true;
		while(cont) {
			System.out.println("Please select Product Mangement Option from menu below");
			System.out.println("\n\nProduct Management Menu");
			System.out.println("To create a new product enter 1");
			System.out.println("To update an existing product enter 2");
			System.out.println("To view a product enter 3");
			System.out.println("To delete a product enter 4");
			
			System.out.println("Enter Option: ");
			
			int input=scanner.nextInt();
			switch (input){

				case 1:
					cont = false;
					createProduct();
					break;
				case 2:
					cont = false;
					updateProduct();
					break;
				case 3:
					cont = false;
					readProduct();
					break;
				case 4:
					cont = false;
					deleteProduct();
				default:
					System.out.println("Please select a valid option");
					break;
			}
		}
	}
	
	private static void createBillingMenu() {
		System.out.println("This is the Customer Menu");		 
		 boolean status = true;
		 while(status)
		 {
			 int input = scanner.nextInt();
			 select();
			 
			 switch(input)
			 {
			 case 1:
				 newBill();
				 break;
			 case 2: 
				 updateBill();
				 break;
			 case 3:
				 viewBill();
				 break;
			 case 4: 
				 deleteBill();
				 break;
			 case 5:
				 status = false;
				 break;
			} 
		 }
		 System.out.println("Thank you for using the service.");
	}
	
	private static void select(){
		System.out.println("Billing menu");
		System.out.println("To enter a new Bill press 1");
		System.out.println("To update a Bill press 2");
		System.out.println("To view a  Bill press 3");
		System.out.println("To delete a Bill press 4");
		System.out.println("To exit the menu press 5");
	}

	

	private static void newBill()
	{
		//Read values from user
		System.out.println("Enter billingID, customerId, productID, billingQuantity and billprice");
		String billingID=scanner.nextLine();
		String customerID = scanner.nextLine();
		String productID = scanner.nextLine();
		int billingQuantity = Integer.parseInt(scanner.next());
		double billprice = Double.parseDouble(scanner.next());
		
		//create a bean
		Billing bean=new Billing();
		bean.setBillingID(billingID);
		bean.setCustomerID(customerID);
		bean.setProductID(productID);
		bean.setBillingQuantity(billingQuantity);
		bean.setBillprice(billprice);
			
		//invoke the DAO method
		BillingDAO dao=new BillingDAO();
		if (dao.insertBilling(bean))
			System.out.println("Record Inserted");
		else
			System.out.println("Record not Inserted");
	}
	
	private static void updateBill()
	{
		//Read values from user
		System.out.println("Enter productID and Quantity to be updated, and billingID");
		String productID = scanner.nextLine();
		int billingQuantity = Integer.parseInt(scanner.nextLine());
		String billingID = scanner.nextLine();
				
		//create a bean  
		Billing bean=new Billing();
		bean.setProductID(productID);
		bean.setBillingQuantity(billingQuantity);
		bean.setBillingID(billingID);
	
		//invoke the DAO method
		BillingDAO dao=new BillingDAO();
		if (dao.update(bean))
			System.out.println("Record Updated");
		else	
			System.out.println("Record not Updated");
	}
	
	private static void viewBill()
	{
		//Read values from user
		System.out.println("Enter customer id to display information");
		String customerID = scanner.nextLine();
		
		//create a bean
		Billing bean=new Billing();
		bean.setCustomerID(customerID);
	
		//invoke the DAO method
		BillingDAO dao=new BillingDAO();
		dao.read(bean);
	}
	
	private static void deleteBill()
	{
		//Read values from user
		System.out.println("Enter billing id to be deleted");
		String billingID = scanner.nextLine();
		
		//create a bean
		Billing bean=new Billing();
		bean.setBillingID(billingID);

		//invoke the DAO method
		BillingDAO dao = new BillingDAO();
		if (dao.delete(bean))
			System.out.println("Record Deleted");
		else	
			System.out.println("Record not Deleted");		
	}
	
	private static void createProduct() {
		System.out.println("\nCreate Stock");
        Stock stock = new Stock();
        StockDAO test = new StockDAO();
        System.out.println("Enter product ID: ");
        int x = scanner.nextInt();
        System.out.println("Set the price: ");
        double y = scanner.nextDouble();
        System.out.println("Set the quantity: ");
        int z = scanner.nextInt();
        stock.setProductID(x);
        stock.setPrice(y);              
        stock.setStockQuantity(z);
        
        test.insertStock(stock);
	}

	private static void updateProduct() {
       System.out.println("\nUpdate Stock");
       Stock stock = new Stock();
       StockDAO test = new StockDAO();
       int x = scanner.nextInt();
       int y = scanner.nextInt();
       int z = scanner.nextInt();
       stock.setProductID(x);
       stock.setPrice(y);
       stock.setStockQuantity(z);
       
       test.updateStock(stock);
	}


	private static void readProduct() {
       System.out.println("\nView Stock");
       Stock stock = new Stock();
       StockDAO test = new StockDAO();
       int x = scanner.nextInt();
       stock = test.readStock(x);
       
       System.out.println(stock.toString());
	}


	private static void deleteProduct() {
       System.out.println("\nDelete Stock");
       StockDAO test = new StockDAO();
       System.out.println("Enter Product ID");
       int x = scanner.nextInt();      
       test.deleteStock(x);
	}
}
