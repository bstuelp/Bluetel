package com.bluetel.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

public class PersDao implements IDao
{
	static Connection objConn = null;

	public PersDao()
	{
	
	}

	@Override
	public String getPresent(int id)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("	 c.customerID, ");
		sb.append("	 c.serialNumber, ");
		sb.append("	 c.mpxn, ");
		sb.append("	 date_format(c.readDate, '%d-%m-%Y %h:%i:%s') as readDate, ");
		sb.append("	 r.type, ");
		sb.append("	 r.value, ");
		sb.append("	 r.registerID ");
		sb.append("FROM ");
		sb.append("  customer as c ");
		sb.append("  join register as r ");
		sb.append("  on c.customerID = r.customerID ");
		
		if (id != 0)
		{
			sb.append("WHERE ");
			sb.append("  c.customerID = ? ");
		}

		PreparedStatement pst;
		ResultSet rs;

		JSONObject json = new JSONObject();
		JSONArray r = new JSONArray();
		try
		{
			objConn = new Conn().openConn();
			pst = objConn.prepareStatement(sb.toString());
			if (id != 0)
			{
				pst.setInt(1, id);
			}

			rs = pst.executeQuery();

			int c = 0;
			while (rs.next())
			{
				if (c == 0)
				{
					json.put("customerId", rs.getInt("customerID"));
					json.put("serialNumber", rs.getString("serialNumber"));
					json.put("mpxn", rs.getString("mpxn"));
					json.put("readDate", rs.getString("readDate"));
					c++;
				}

				JSONObject read = new JSONObject();
				read.put("type", rs.getString("type"));
				read.put("registerID", rs.getString("registerID"));
				read.put("value", rs.getString("value"));

				r.add(read);
			}
			
			if (c == 0)
			{
				return ("error");
			}

			json.put("read", r);

			pst.close();
			objConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return (new Gson().toJson(json));
	}

	@Override
	public boolean checkCustomer(int customerID)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("	 customerID ");
		sb.append("FROM ");
		sb.append("  customer ");
		sb.append("WHERE ");
		sb.append("  customerID = ? ");
		
		PreparedStatement pst;
		ResultSet rs;
		
		boolean ret = false;
		try
		{
			objConn = new Conn().openConn();
			pst = objConn.prepareStatement(sb.toString());
			pst.setInt(1, customerID);

			rs = pst.executeQuery();

			while (rs.next())
			{
				ret = true;
			}

			pst.close();
			objConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return (ret);
	}

	@Override
	public int storeNewCostumer(String serialNumber, String mpxn, String readDate)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO customer ");
		sb.append(" (serialNumber, mpxn, readDate) ");
		sb.append("VALUES ");
		sb.append(" (?, ?, ?)");
		
		PreparedStatement pst;

		int temp = 0;
		try
		{
			objConn = new Conn().openConn();
			pst = objConn.prepareStatement(sb.toString());
			pst.setString(1, serialNumber);
			pst.setString(2, mpxn);
			pst.setString(3, readDate);

			pst.executeUpdate();
			
			temp = this.getNewCustomerID(objConn);

			pst.close();
			objConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return (temp);
	}

	@Override
	public int getNewCustomerID(Connection objConn2)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT last_insert_id() as id");
		
		PreparedStatement pst;
		ResultSet rs;
		
		int id = 0;
		try
		{
			pst = objConn.prepareStatement(sb.toString());

			rs = pst.executeQuery();
			
			while (rs.next())
			{
				id = rs.getInt("id");
			}
			
			pst.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return (id);
	}

	@Override
	public int storeReaders(int customerID, String type, String registerId, int value)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO register ");
		sb.append(" (customerID, register.type, registerID, register.value) ");
		sb.append("VALUES ");
		sb.append(" (?, ?, ?, ?)");

		PreparedStatement pst;

		int temp = 0;
		try
		{
			objConn = new Conn().openConn();
			pst = objConn.prepareStatement(sb.toString());
			pst.setInt(1, customerID);
			pst.setString(2, type);
			pst.setString(3, registerId);
			pst.setInt(4, value);

			pst.executeUpdate();
			
			temp = this.getNewCustomerID(objConn);

			pst.close();
			objConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return (temp);
	}
}