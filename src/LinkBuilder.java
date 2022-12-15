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
            STATUS = "https://api.spacetraders.io/game/status",
            AVAILABLE_LOANS = "https://api.spacetraders.io/types/loans",
            FLIGHT_PLANS = "https://api.spacetraders.io/types/loans",
            LEADERBOARD = "https://api.spacetraders.io/game/leaderboard/net-worth",
            LOANS = "https://api.spacetraders.io/my/loans",
            PAY_OFF_LOAN = "https://api.spacetraders.io/my/loans/:loanId",
            LOCATIONS = "https://api.spacetraders.io/locations/:locationSymbol",
            LOCATION_MARKETPLACE = "https://api.spacetraders.io/locations/:locationSymbol/marketplace",
            LOCATION_SHIPS = "https://api.spacetraders.io/locations/:locationSymbol/ships",
            PURCHASE_ORDERS = "https://api.spacetraders.io/my/purchase-orders",
            SELL_ORDERS = "https://api.spacetraders.io/my/sell-orders",
            SHIPS = "https://api.spacetraders.io/my/ships",
            SHIP_INFO = "https://api.spacetraders.io/my/ships/:shipId",
            JETTISON_CARGO = "https://api.spacetraders.io/my/ships/:shipId/jettison",
            STRUCTURES = "";

    public LinkBuilder(String url, String token){
        link = new StringBuilder(url);
        link.append('?').append("token=").append(token);
    }

    public LinkBuilder addParameter(String key, String value){
        link.append('&').append(key).append('=').append(value);
        return this;
    }

    public JsonObject getLinkContent() throws IOException {
        JsonObject obj = null;
        URL url = this.getURL();
        //opening request to server
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        int responseCode = con.getResponseCode();
        System.out.println("Opening connection to " + url);
        System.out.println("Response code: " + responseCode);

        if(responseCode == HttpURLConnection.HTTP_OK){ //success
            //creating json object from server response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            JsonReader reader = Json.createReader(in);
            obj = reader.readObject();
            //grabbing values from jsonObject
            //readAllJsonValues(content, obj);
        } else { //failure
            System.out.println("Request Failed");
        }

        return obj;
    }

    public URL getURL() throws MalformedURLException {
        return new URL(link.toString());
    }

    public String toString(){
        return link.toString();
    }
}