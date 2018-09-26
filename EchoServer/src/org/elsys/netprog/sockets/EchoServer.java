package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			int numConnections = 0;
			serverSocket = new ServerSocket(10001,3);
			while(numConnections < 3){
			    Socket clientSocket = serverSocket.accept();
			    Thread t = new Thread(new ConnectionHandler(clientSocket));
			    t.start();
			    numConnections++;
			    System.out.println("client connected from " + clientSocket.getInetAddress());
			} 
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
				System.out.println("Server closed");
			}					
		}
	}

}

class ConnectionHandler implements Runnable{
    Socket clientSocket;
    public ConnectionHandler(Socket connection){
         this.clientSocket = connection;
    }

    public void run(){
    	PrintWriter out = null;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    BufferedReader in = null;
			try {
				in = new BufferedReader(
				    new InputStreamReader(clientSocket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    String inputLine;

		    try {
				while ((inputLine = in.readLine()) != null) {
				    out.println(inputLine);
				    System.out.println(inputLine);
				    if (inputLine.equals("exit"))
				        break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}