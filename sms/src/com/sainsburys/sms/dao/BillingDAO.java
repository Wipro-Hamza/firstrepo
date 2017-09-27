package com.sainsburys.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sainsburys.sms.beans.Billing;
import com.sainsburys.sms.util.DBUtil;

public class BillingDAO {
	
	public boolean insertBilling(Billing b){
		   
		try {
			Connection con= DBUtil.getConnection();
			String sql="insert into Billings values(?,?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, b.getBillingID());
			pst.setString(2, b.getCustomerID());
			pst.setString(3, b.getProductID());
			pst.setInt(4, b.getBillingQuantity());
			pst.setDouble(5, b.getBillprice());;
			
			int count=pst.executeUpdate();
			
			if (count==1)
				return true;
			else
				return false;
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
	}

	public Billing read(Billing b)
	{
		try{
			Connection con = DBUtil.getConnection();
			String sql = "select * from Billings where customerID = ?";
			PreparedStatement pst  = con.prepareStatement(sql);
			pst.setString(1, b.getCustomerID());
			
			ResultSet rs = pst.executeQuery();	
			while(rs.next())
			{
				b.setBillingID(rs.getString(1));
				b.setCustomerID(rs.getString(2));
				b.setProductID(rs.getString(3));
				b.setBillingQuantity(rs.getInt("billingQuantity"));
				b.setBillprice(rs.getDouble("billprice"));
				
				System.out.println("Billing ID: "+b.getBillingID());
				System.out.println("Customer ID: "+b.getCustomerID());
				System.out.println("Product ID: "+b.getProductID());
				System.out.println("Billing quantity: "+b.getBillingQuantity());
				System.out.println("Billing price: "+b.getBillprice());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return b;
	}
		
	public boolean update(Billing b)
	{
		try {
			Connection con = DBUtil.getConnection();
			String sql = "update Billings set productID = ?, billingQuantity = ? where billingID = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, b.getProductID());
			pst.setInt(2, b.getBillingQuantity());
			pst.setString(3, b.getBillingID());
			
			int count=pst.executeUpdate();
			
			if (count==1)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Billing b)
	{
		try {
			Connection con = DBUtil.getConnection();
			String sql = "Delete from Billings where billingID = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, b.getBillingID());
					
			int count=pst.executeUpdate();
			
			if (count==1)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}