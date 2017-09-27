package com.sainsburys.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection con = null;

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl1";
		String uname = "scott";
		String pwd = "tiger";
		try {
			con = DriverManager.getConnection(url, uname, pwd);
			System.out.println("Connected");
		} catch (SQLException e) {
			System.out.println("Unable to connect to Database due to below errors: \n");
			e.printStackTrace();
		}
		return con;

	}
}
