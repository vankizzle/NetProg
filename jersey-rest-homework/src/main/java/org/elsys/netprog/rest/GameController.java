package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/game")
public class GameController {
	
	Map<String,BnCGame> Games = new HashMap<String,BnCGame>();
//	List Games = new ArrayList<>():
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response  startGame() throws URISyntaxException{
		String uniqueID = UUID.randomUUID().toString();
		BnCGame NewGame = new BnCGame(uniqueID);
		Games.put(NewGame.GetID(), NewGame);
		return Response.created(new URI("/games")).status(201).entity(NewGame.GetID()).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		if(Games.containsKey(gameId)) {
			BnCGame NewGame = Games.get(gameId);
			if(NewGame.Guess(guess) == -1) {
				return Response.status(400).build();
			}else {
				JSONObject jObject = new JSONObject();		
				
				jObject.put("gameId", NewGame.GetID());
				jObject.put("cowsNumber", NewGame.GetCows());
				jObject.put("bullsNumber", NewGame.GetBulls());
				jObject.put("turnsCount", NewGame.GetTurns());
				jObject.put("success", NewGame.GetSuccess());
				
				return Response.status(200).entity(jObject.toString()).build();
			}
		}
		return Response.status(404).build();
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() throws JSONException {
		//TODO: Add your code here
		//JSON all games from Games
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();		
		
		 for (Map.Entry<String, BnCGame> entry : Games.entrySet()) {
			 BnCGame Value = entry.getValue();
			 
			 String temp = Value.GetSecret().toString();
			 if(Value.GetSuccess() == false) temp = "****";
				
			 jObject.put("gameId", Value.GetID());
			 jObject.put("turnsCount", Value.GetTurns());
			 jObject.put("secret", temp);
			 jObject.put("success", Value.GetSuccess());
				
			 jArray.put(jObject);
		 }
			return Response.status(200).entity(jArray.toString()).build();
	}
}
