import javax.json.*;
import java.io.IOException;
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
    public HashMap<String, String> getAccount(){
        JsonObject account;

        try {
            account = new LinkBuilder(LinkBuilder.ACCOUNT, KEY).getLinkContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return readAllJsonValues(new HashMap<String, String>(), account);
    }

    public ArrayList<Loan> getAvailableLoans(){
        ArrayList<Loan> loans = new ArrayList<>();
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
            loans.add(new Loan(temp));
        }

        return loans;
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

    public static HashMap<String, String> readAllJsonValues(HashMap<String, String> content, javax.json.JsonObject obj){
        Set<String> keySet = obj.keySet();

        for(String key : keySet){
            //grabbing child of jsonObject
            Object child = obj.get(key);
            if(child instanceof JsonString || child instanceof JsonNumber){ //child is a value
                //getting string and removing any quotation marks
                String value = child.toString().replace("\"", "");
                //storing key, value pair in hashmap
                content.put(key, value);
            } else if(child instanceof JsonArray) {
                JsonArray arr = (JsonArray) child;
                for(int i = 0; i < arr.size(); i++){

                }
            } else if(child instanceof JsonObject){ //child is another jsonObject
                //reading all values from child
                readAllJsonValues(content, (JsonObject) child);
            }
        }

        return content;
    }

    public static void main(String [] args){
        HTTPie h1 = new HTTPie();
        System.out.println(h1.getStatus());
        for(Loan l : h1.getAvailableLoans()){
            System.out.println(l + "\n");
        }
    }
}
