package org.elsys.netprog.rest;

import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/game")
public class GameController {
	
	int len_ = 2;
	static Game Mygame_;

	@POST
	@Path("/sendinput")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response  startGame(@FormParam("hash") String hash,@FormParam("input") String input) throws URISyntaxException{
		System.out.println("Hash: " + hash + "\nInput: " + input);
		
		if(Mygame_.Compare(input,hash) == 1) {
			Mygame_ = new Game(len_);
			return Response.status(200).build();
		}
		
		return Response.status(406).build();
	}
	
	@GET
	@Path("/startgame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		Mygame_ = new Game(len_);
		
		JSONObject jObject = new JSONObject();		
		
		try {
			jObject.put("LENGTH",Mygame_.GetLen());
			jObject.put("HASH",Mygame_.GetHash());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).entity(jObject.toString()).build();
	}
}
