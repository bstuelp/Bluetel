package com.bluetel.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bluetel.DAO.PersDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("/meter-read")
public class Service implements IService
{
	@GET
	@Path("/present/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getMetrics(@PathParam("id") int id)
	{
		PersDao pd = new PersDao();
		String jsonResult = pd.getPresent(id);

		if (jsonResult == "error")
		{
			return(Response.status(Response.Status.BAD_REQUEST).entity("Error: Data not found.").build());
		}

		return(Response.status(Response.Status.OK).entity(jsonResult).build());
	}

	@POST
	@Path("/store")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response setMeterReaders(String json)
	{
		if (!this.validJson(json))
		{
			return(Response.status(Response.Status.BAD_REQUEST).entity("Error: Invalid Json.").build());
		}
		else
		{
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
			
			int customerID = Integer.parseInt(jsonObject.get("customerId").toString());
			String serialNumber = jsonObject.get("serialNumber").getAsString();
			String mpxn = jsonObject.get("mpxn").getAsString();
			String readDate = jsonObject.get("readDate").getAsString();

			JsonArray jsonArray = jsonObject.getAsJsonArray("read");

			PersDao pd = new PersDao();

			//Customer ! exists -> store new customer
			if (!pd.checkCustomer(customerID))
			{
				customerID = pd.storeNewCostumer(serialNumber, mpxn, readDate);
			}

			String type = "";
			String registerId = "";
			int value = 0;
			for (int i = 0; i < jsonArray.size(); i++)
			{
				JsonObject obj = (JsonObject) jsonArray.get(i);

				type = obj.get("type").getAsString();
				registerId = obj.get("registerId").getAsString();
				value = obj.get("registerId").getAsInt();

				pd.storeReaders(customerID, type, registerId, value);
			}
		}

		return(Response.status(Response.Status.OK).entity("Stored!").build());
	}

	@Override
	public boolean validJson(String json)
	{
		Gson gson = new Gson();

		try
		{
			gson.fromJson(json, Object.class);
			
			return (true);
		}
		catch(com.google.gson.JsonSyntaxException ex)
		{
			return false;
		}
	}
}