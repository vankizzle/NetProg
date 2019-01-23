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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	Game Mygame_ = new Game(10);
	
//	List Games = new ArrayList<>():
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response  startGame(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException{
		
		return Response.created(new URI("/games")).status(201).entity(NewGame.GetID()).build();
	}
	
	@GET
	@Path("/startgame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() throws JSONException {
		
		//TODO: Add your code here
		//JSON all games from Games
	//	JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();		
		jObject.put(Mygame_.GetHash(),Mygame_.GetLen());
		//jArray.put(jObject);
			return Response.status(200).entity(jObject.toString()).build();
	}
}
