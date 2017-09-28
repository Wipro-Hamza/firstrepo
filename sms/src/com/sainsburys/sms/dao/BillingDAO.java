package com.sainsburys.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sainsburys.sms.beans.Billing;
import com.sainsburys.sms.util.DBUtil;

public class BillingDAO {
	public static boolean billingExists(int billingID)
	{
		try{
			Connection con = DBUtil.getConnection();
			String sql = "select count(1) AS count from Billings where billingID = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, billingID);
			
			ResultSet result = pst.executeQuery();
			int count = 0;
			
			while(result.next())
			{
				count = result.getInt("count");
			}
			
			if(count==1)
				return true;
			else
				return false;
		}catch(SQLException e1){
			System.out.println("Unable to check if bill exists in the database due to errors below: \n");
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean insertBilling(Billing b){
		if(billingExists(b.getBillingID()))
			return false;
		else{
			Connection con = DBUtil.getConnection();
			String sql="insert into Billings values(?,?,?,?,?)";
			try {			
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, b.getBillingID());
			pst.setInt(2, b.getCustomerID());
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
				System.out.println("Unable to insert bill into the database due to the errors below: \n");
				e1.printStackTrace();
				return false;
			}
		}
	}

	public Billing read(int b)
	{		
		if(!billingExists(b))
			return null;
		else{	
			try{
				Connection con = DBUtil.getConnection();
				String sql = "select * from Billings where customerID = ?";
				PreparedStatement pst  = con.prepareStatement(sql);
				pst.setInt(1, b);
			
				ResultSet rs = pst.executeQuery();	
				int bill = 0;
				int customerID = 0;
				String product = null;
				int quantity = 0;
				Double price = 0.0;
				
				while(rs.next())
				{
					bill = rs.getInt("billingID");
					customerID = rs.getInt("customerID");
					product = rs.getString("productID");
					quantity = rs.getInt("billingQuantity");
					price = rs.getDouble("billPrice");
				}
				
				Billing billing = new Billing();
				billing.setBillingID(bill);
				billing.setCustomerID(customerID);
				billing.setProductID(product);
				billing.setBillingQuantity(quantity);
				billing.setBillprice(price);
				return billing;
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
		}
	}
		
	public boolean update(Billing b)
	{
		if(!billingExists(b.getBillingID()))
			return false;
		else
		{
			Connection con = DBUtil.getConnection();
			String sql = "update Billings set productID = ?, billingQuantity = ? where billingID = ?";
		
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, b.getProductID());
				pst.setInt(2, b.getBillingQuantity());
				pst.setInt(3, b.getBillingID());
				
				int count=pst.executeUpdate();
			
				if (count==1)
					return true;
				else
					return false;
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to update bill in database due to errors below: \n");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean delete(int b)
	{
		if(!billingExists(b))
			return false;
		else{
				try {
					Connection con = DBUtil.getConnection();
					String sql = "Delete from Billings where billingID = ?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, b);
					
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
}