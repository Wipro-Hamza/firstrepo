package com.sainsburys.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sainsburys.sms.beans.Customer;
import com.sainsburys.sms.util.DBUtil;

public class CustomerDAO {
	public static boolean customerExists(int customerID) {
		try {
			Connection con = DBUtil.getConnection();
			String sql = "select count(1) AS count from Customers where customerID = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, customerID);

			ResultSet result = pst.executeQuery();
			int count = 0;

			while (result.next()) {
				count = result.getInt("count");
			}

			if (count == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e1) {
			System.out
					.println("Unable to check if customer exists in database due to below errors: \n");
			e1.printStackTrace();
			return false;
		}
	}

	public boolean insertCustomer(Customer c) {

		if (customerExists(c.getcustomerId())) {
			return false;
		} else {

			Connection con = DBUtil.getConnection();
			String sql = "INSERT into Customers VALUES(?,?,?,?)";
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, c.getcustomerId());
				pst.setString(2, c.getCustomerName());
				pst.setString(3, c.getAddress());
				pst.setString(4, c.getcontactNumber());

				int count = pst.executeUpdate();

				if (count == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				System.out
						.println("Unable to insert customer into database due to the errors below: \n");
				e.printStackTrace();
				return false;
			}

		}
	}

	public boolean updateCustomer(Customer c) {

		if (!customerExists(c.getcustomerId())) {
			return false;
		} else {
			Connection con = DBUtil.getConnection();
			String sql = "UPDATE Customers SET name= ? , address = ?, contactNumber = ? WHERE customerID = ?";
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(4, c.getcustomerId());
				pst.setString(1, c.getCustomerName());
				pst.setString(2, c.getAddress());
				pst.setString(3, c.getcontactNumber());

				int count = pst.executeUpdate();

				if (count == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				System.out
						.println("Unable to update customer into database due to the errors below: \n");
				e.printStackTrace();
				return false;
			}

		}

	}

	public Customer readCustomer(int c) {

		if (!customerExists(c)) {
			return null;
		} else {

			try {
				Connection con = DBUtil.getConnection();
				String sql = "select name, address, contactNumber from Customers where customerID = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, c);

				ResultSet result = pst.executeQuery();
				String name = null;
				String address = null;
				String contactNumber = null;

				while (result.next()) {
					name = result.getString("name");
					address = result.getString("address");
					contactNumber = result.getString("contactNumber");
				}

				Customer customer = new Customer();
				customer.setcustomerId(c);
				customer.setCustomerName(name);
				customer.setAddress(address);
				customer.setcNumber(contactNumber);
				return customer;
			} catch (SQLException e1) {
				System.out
						.println("Unable to read customer from database due to below errors: \n");
				e1.printStackTrace();
				return null;
			}
		}
	}

	public boolean deleteCustomer(int id) {

		if (!customerExists(id)) {
			return false;
		} else {
			try {
				Connection con = DBUtil.getConnection();
				String sql = "delete from Customers where customerID = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id);

				int count = pst.executeUpdate();

				if (count == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e1) {
				System.out
						.println("Unable to delete customer from database due to below errors: \n");
				e1.printStackTrace();
				return false;
			}
		}
	}

}
