import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

import org.json.JSONObject;

public class Client {
	private static String Url = "http://localhost:8084/jersey-rest-homework/game";
	private static int Len_;
	private String OtherHash_;
	private static String hash_;
	private static String input_;
	
//	public void SetLen(int len) {
//		this.Len_ = len;
//	}
//	
//	public void SetOtherHash(String h) {
//		this.OtherHash_ = h;
//	}
//	
	public void StartHacking() throws Exception{
		while(true) {
			long Start = System.nanoTime();
			byte[] b = new byte[Len_];
			new Random().nextBytes(b);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] HashArr = md.digest(b);
			hash_ = Base64.getUrlEncoder().encodeToString(HashArr);
			if(hash_.equals(OtherHash_)) {
				long End = System.nanoTime();
				System.out.println("Took "+(End - Start) + " ns ");
				SendPost(OtherHash_, Base64.getUrlEncoder().encodeToString(b));
				break;
			}
		}
	}
	
	//https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
	public static void SendPost(String hash, String input)throws Exception {
		URL url = new URL(Url + "/sendinput");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		String urlParameters = "hash=" + hash_ + "&input=" + input_;
		con.setDoInput(true);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("Code: " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {

			response.append(inputLine);

		}
		in.close();
	}
	
	public static void SendGet()  throws Exception {
		URL url = new URL(Url + "/startgame");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject myJson = new JSONObject(response.toString());

		Len_ = myJson.getInt("LENGTH");

		hash_ = myJson.getString("HASH");
	}
	
	
}
