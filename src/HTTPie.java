import javax.json.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class HTTPie {
    private String url;
    private final String KEY = "cb3ff87f-0953-436f-92f3-7d5a0787edcd", USERNAME = "Wiian16";

    public HTTPie(){
        url = "api.spacetraders.io";

        try {
            HashMap<String, String> map = new LinkBuilder(LinkBuilder.ACCOUNT, KEY).getLinkContent();
            for(String key : map.keySet()){
                System.out.println(key + ": " + map.get(key));
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    //returns a hashmap of key, value from account link
    public HashMap<String, String> getAccount(){
        HashMap<String, String> account = new HashMap<>();

        try {
            URL link = new LinkBuilder(LinkBuilder.ACCOUNT, KEY).getURL();
        } catch(MalformedURLException e){
            e.printStackTrace();
        }



        return account;
    }

    public static void main(String [] args){
        new HTTPie();
    }
}
