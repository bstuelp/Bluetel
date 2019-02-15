package com.bluetel.rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IService 
{
	public Response getMetrics(@PathParam("id") int id);
	
	public Response setMeterReaders(String json);

	public boolean validJson(String json);
}
