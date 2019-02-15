package com.bluetel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn
{
	public Connection openConn()
	{
		Connection conn = null;

		try
		{
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);

			String url = "jdbc:mysql://localhost:3306/bluetel?useSSL=true";

			String username = "root";
			String password = "root";

			conn = DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException|SQLException e)
		{
			System.out.println(e.getMessage());
		}

		return (conn);
	}

	public void close(Connection c)
	{
		close(c);
	}
}