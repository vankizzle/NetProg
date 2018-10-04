

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String,String> mymap = new HashMap<String,String>();   

    public MyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		   out.println( "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
				   		"<html>\n" +
			            "<head></head>\n" +
			            "<body bgcolor = \"#f0f0f0\">\n" 
			      );
			      
		   out.println( "<table style = \"width:50%\">" + 
			    		"<tr>" +
			    		"<th> Key </th>" + 
			    		"<th> Value </th>" +
			    		"</tr>"
			      );
		   
			      for(Map.Entry<String, String> entry : mymap.entrySet()) {
			    	  String key = entry.getKey();
			    	  String value = entry.getValue();
			    	  out.println( "<tr>" + 
			    			  	   "<td>" + key + "</td>" +
			    			  	   "<td>" + value + "</td>" +
			    			  	   "</tr>"
			    	  );
			      }
			      
			      	  out.println( "</table>" +
			      			  	   "</body>" +
			      			  	   "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String k = request.getParameter("newkey");
		String v = request.getParameter("newval");
		mymap.put(k, v);
		doGet(request, response);
	}

}
