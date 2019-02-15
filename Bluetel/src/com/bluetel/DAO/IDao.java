package com.bluetel.DAO;

import java.sql.Connection;

public interface IDao
{
	public String getPresent(int customerID);
	
	public boolean checkCustomer(int customerID);
	
	public int storeNewCostumer(String serialNumber, String mpxn, String readDate);
	
	public int getNewCustomerID(Connection objConn2);
	
	public int storeReaders(int customerID, String type, String registerId, int value);
}
