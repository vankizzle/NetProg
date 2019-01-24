
public class ClientWork {

	
	public static void main(String[] args) throws Exception {
	
		Client client = new Client();
		
		while(true) {
			Client.SendGet();
			client.StartHacking();
		}
		
	}
	
}
