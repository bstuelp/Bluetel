package com.bluetel.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bluetel.rest.Service;

public class Junit
{
	private Service classUnderTest;

	@Before
	public void setUp() throws Exception
	{
		classUnderTest = new Service();
	}

	@Test
	public void testGetMetrics()
	{
		assertEquals(200, classUnderTest.getMetrics(1).getStatus());
		assertEquals(400, classUnderTest.getMetrics(10000).getStatus());
	}

	@Test
	public void testSetMeterReaders()
	{
		String json = "{\"customerId\": 20,\"serialNumber\": \"123\",\"mpxn\": \"456\",";
		assertEquals(400, classUnderTest.setMeterReaders(json).getStatus());
	}

	@Test
	public void testValidJson()
	{
		String json = "{\"customerId\": 20,\"serialNumber\": \"123\",\"mpxn\": \"456\",";
		assertEquals(false, classUnderTest.validJson(json));
		
		json = "{\"customerId\": 20,\"serialNumber\": \"123\",\"mpxn\": \"456\"}";
		assertEquals(true, classUnderTest.validJson(json));
	}

}
