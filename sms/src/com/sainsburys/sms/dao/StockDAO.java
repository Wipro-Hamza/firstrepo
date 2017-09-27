package com.sainsburys.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sainsburys.sms.beans.Stock;
import com.sainsburys.sms.util.DBUtil;

public class StockDAO {

	public boolean insertStock(Stock stock) {
		if (stockExists(stock.getProductID())) {
			return false;
		} else {
			try {
				Connection con = DBUtil.getConnection();
				String sql = "insert into Stocks values(?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, stock.getProductID());
				pst.setDouble(2, stock.getPrice());
				pst.setInt(3, stock.getQuantity());

				int count = pst.executeUpdate();

				if (count == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e1) {
				System.out.println("Unable to insert stock into database due to below errors: \n");
				e1.printStackTrace();
				return false;
			}
		}
	}

	public boolean deleteStock(int productID) {
		if (!stockExists(productID)) {
			return false;
		} else {
			try {
				Connection con = DBUtil.getConnection();
				String sql = "delete from Stocks where productID = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, productID);

				int count = pst.executeUpdate();
				
				if (count == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e1) {
				System.out.println("Unable to delete stock from database due to below errors: \n");
				e1.printStackTrace();
				return false;
			}
		}
	}

	public boolean updateStock(Stock stock) {
		if (!stockExists(stock.getProductID())) {
			return false;
		} else {
			try {
				Connection con = DBUtil.getConnection();
				String sql = "update Stocks set price = ?, quantity = ?, where productID = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setDouble(1, stock.getPrice());
				pst.setInt(2, stock.getQuantity());
				pst.setInt(3, stock.getProductID());

				int count = pst.executeUpdate();

				if (count == 1) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e1) {
				System.out.println("Unable to update stock in database due to below errors: \n");
				e1.printStackTrace();
				return false;
			}
		}
	}

	public Stock readStock(int productID) {
		if (!stockExists(productID)) {
			return null;
		} else {
			try {
				Connection con = DBUtil.getConnection();
				String sql = "select price, quantity from stocks where productID = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, productID);

				ResultSet result = pst.executeQuery();
				double price = 0;
				int quantity = 0;

				while (result.next()) {
					price = result.getDouble("price");
					quantity = result.getInt("quantity");
				}

				Stock stock = new Stock();
				stock.setProductID(productID);
				stock.setPrice(price);
				stock.setStockQuantity(quantity);
				return stock;
			} catch (SQLException e1) {
				System.out.println("Unable to read stock from database due to below errors: \n");
				e1.printStackTrace();
				return null;
			}
		}
	}

	public static boolean stockExists(int productID) {
		try {
			Connection con = DBUtil.getConnection();
			String sql = "select count(1) AS count from stocks where productID = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, productID);

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
			System.out.println("Unable to check if stock exists in database due to below errors: \n");
			e1.printStackTrace();
			return false;
		}
	}
}
