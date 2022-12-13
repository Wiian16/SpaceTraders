import javax.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.glassfish.json.*;

public class LinkBuilder{
    private StringBuilder link;

    public static final String ACCOUNT = "https://api.spacetraders.io/my/account",
            STATUS = "https://api.spacetraders.io/game/status";

    public LinkBuilder(String url, String token){
        link = new StringBuilder(url);
        link.append('?').append("token=").append(token);
    }

    public LinkBuilder addParameter(String key, String value){
        link.append('&').append(key).append('=').append(value);
        return this;
    }

    public HashMap<String, String> getLinkContent() throws IOException {
        HashMap<String, String> content = new HashMap<>();
        URL url = this.getURL();
        //opening request to server
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();
        System.out.println("Response code: " + responseCode);

        if(responseCode == HttpURLConnection.HTTP_OK){ //success
            //creating json object from server response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            JsonReader reader = Json.createReader(in);
            JsonObject obj = reader.readObject();
            //grabbing values from jsonObject
            readAllJsonValues(content, obj);
        } else { //failure
            System.out.println("Request Failed");
        }

        return content;
    }

    public HashMap<String, String> readAllJsonValues(HashMap<String, String> content, javax.json.JsonObject obj){
        Set<String> keySet = obj.keySet();

        for(String key : keySet){
            //grabbing child of jsonObject
            Object child = obj.get(key);
            if(child instanceof JsonString || child instanceof JsonNumber){ //child is a value
                //getting string and removing any quotation marks
                String value = child.toString().replace("\"", "");
                //storing key, value pair in hashmap
                content.put(key, value);
            } else if(child instanceof JsonObject){ //child is another jsonObject
                //reading all values from child
                readAllJsonValues(content, (JsonObject) child);
            }
        }

        return content;
    }


    public URL getURL() throws MalformedURLException {
        return new URL(link.toString());
    }

    public String toString(){
        return link.toString();
    }
}