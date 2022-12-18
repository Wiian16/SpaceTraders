import javax.json.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HTTPie {
    private String url;
    private final String KEY = "cb3ff87f-0953-436f-92f3-7d5a0787edcd", USERNAME = "Wiian16";

    public HTTPie(){
        url = "api.spacetraders.io";
    }
    //returns a hashmap of key, value from account link
    public Account getAccount() throws ParseException {
        JsonObject account;

        try {
            account = new LinkBuilder(LinkBuilder.ACCOUNT, KEY).getLinkContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Account(readAllJsonValues(new HashMap<String, String>(), account));
    }

    public ArrayList<AvailableLoan> getAvailableLoans(){
        ArrayList<AvailableLoan> availableLoans = new ArrayList<>();
        JsonObject loansObj;

        try{
            loansObj = new LinkBuilder(LinkBuilder.AVAILABLE_LOANS, KEY).getLinkContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonArray arr = loansObj.getJsonArray("loans");
        for(JsonValue value : arr){
            HashMap<String, String> temp = new HashMap<>();
            for(String key : value.asJsonObject().keySet()){
                temp.put(key, value.asJsonObject().get(key).toString().replace("\"", ""));
            }
            availableLoans.add(new AvailableLoan(temp));
        }

        return availableLoans;
    }
    
    public FlightPlan getFlightPlan(String flightPlanId) throws ParseException {
        JsonObject flightPlanObj = null;
        
        try{
            LinkBuilder linkBuilder = new LinkBuilder(LinkBuilder.GET_FLIGHT_PLAN, KEY);
            linkBuilder.replace(":flightPlanId", flightPlanId);
            flightPlanObj = linkBuilder.getLinkContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new FlightPlan(readAllJsonValues(new HashMap<>(), flightPlanObj));
    }

    public FlightPlan submitFlightPlan(String shipId, String destination) throws ParseException {
        JsonObject flightPlanObj = null;

        try{
            flightPlanObj = new LinkBuilder(LinkBuilder.SUBMIT_FLIGHT_PLAN, KEY).addParameter("shipId", shipId)
                    .addParameter("destination", destination).getLinkContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new FlightPlan(readAllJsonValues(new HashMap<>(), flightPlanObj));
    }
    
    public Location getLocation(String locationSymbol) throws IOException {
        JsonObject locationObj = null;
        
        locationObj = new LinkBuilder(LinkBuilder.LOCATIONS, KEY).replace(":locationSymbol", locationSymbol).getLinkContent();
        
        return new Location(readAllJsonValues(new HashMap<>(), locationObj));
        
    }

    public ArrayList<MarketplaceItem> getMarketplace(String locationSymbol) throws IOException {
        ArrayList<MarketplaceItem> marketplaceItems = new ArrayList<>();
        JsonObject marketplaceObj;
        marketplaceObj = new LinkBuilder(LinkBuilder.LOCATION_MARKETPLACE, KEY).replace(":locationSymbol", locationSymbol).getLinkContent();
        JsonArray array = marketplaceObj.getJsonArray("marketplace");
        for(JsonValue value : array){
            marketplaceItems.add(new MarketplaceItem(readAllJsonValues(new HashMap<>(), value.asJsonObject())));
        }
        
        return marketplaceItems;
    }

    public ArrayList<HashMap<String, String>> getLeaderboard(){
        ArrayList<HashMap<String, String>> arr = new ArrayList<>();
        JsonObject obj = null;

        try{
            obj = new LinkBuilder(LinkBuilder.LEADERBOARD, KEY).getLinkContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonArray jsonArray = obj.getJsonArray("netWorth");
        for(JsonValue value : jsonArray){
            HashMap<String, String> temp = new HashMap<>();
            for(String key : value.asJsonObject().keySet()){
                temp.put(key, value.asJsonObject().get(key).toString().replace("\"", ""));
            }
            arr.add(temp);
        }

        arr.add(readAllJsonValues(new HashMap<String, String>(), obj.get("userNetWorth").asJsonObject()));

        return arr;
    }

    public String getStatus(){
        JsonObject status;

        try {
            status = new LinkBuilder(LinkBuilder.STATUS, KEY).getLinkContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return status.get("status").toString().replace("\"", "");
    }

    public static HashMap<String, String> readAllJsonValues(HashMap<String, String> content, JsonObject obj){
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

    public static void main(String [] args) throws ParseException {
        HTTPie h1 = new HTTPie();
        System.out.println(h1.getStatus());
        for(HashMap<String, String> map : h1.getLeaderboard()){
            for(String str : map.keySet()){
                System.out.println(str + ": " + map.get(str));
            }
            System.out.println();
        }
    }
}
