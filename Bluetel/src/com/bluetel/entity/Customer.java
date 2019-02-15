package com.bluetel.entity;

import java.util.List;

public class Customer
{
	private int customerID;
	private String serialNumber;
	private String mxpn;
	private String readDate;
	private List<Read> read;

	public int getCustomerID()
	{
		return customerID;
	}

	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}

	public String getSerialNumber()
	{
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	public String getMxpn()
	{
		return mxpn;
	}

	public void setMxpn(String mxpn)
	{
		this.mxpn = mxpn;
	}

	public String getReadDate()
	{
		return readDate;
	}

	public void setReadDate(String readDate)
	{
		this.readDate = readDate;
	}

	public List<Read> getRead()
	{
		return read;
	}

	public void setRead(List<Read> read)
	{
		this.read = read;
	}
}
