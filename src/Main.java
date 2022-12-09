import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static final String USERNAME = "Wiian16", KEY = "cb3ff87f-0953-436f-92f3-7d5a0787edcd";
    public static final String API_URL = "https://api.spacetraders.io";
    public static void main(String[] args) throws IOException {
        //setting connection url
        URL statusURL = new URL(API_URL + "/game/status");
        HttpURLConnection con = (HttpURLConnection) statusURL.openConnection();
        //setting request method
        con.setRequestMethod("GET");
        //server response code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            //reading url request content
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            //writing content to StringBuffer
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
        } else {
            System.out.println("GET request did not work.");
        }


    }
}