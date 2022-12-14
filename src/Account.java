import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Account {
    private double credits;
    private int shipCount, structureCount;
    private Date joinedAt;
    private String username;

    public Account(double credits, Date joinedAt, int shipCount, int structureCount, String username){
        this.credits = credits;
        this.joinedAt = joinedAt;
        this.shipCount = shipCount;
        this.structureCount = structureCount;
        this.username = username;
    }

    public Account(HashMap<String, String> map) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");
        this.credits = Double.parseDouble(map.get("credits"));
        this.joinedAt = df.parse(map.get("joinedAt"));
        this.shipCount = Integer.parseInt(map.get("shipCount"));
        this.structureCount = Integer.parseInt(map.get("structureCount"));
        this.username = map.get("username");
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Username: ").append(username).append("\n");
        str.append("Joined At: ").append(joinedAt).append("\n");
        str.append("Credits: ").append(credits).append("\n");
        str.append("Ships: ").append(shipCount).append("\n");
        str.append("Structures: ").append(structureCount);
        return str.toString();
    }

    public double getCredits() {
        return credits;
    }

    public int getShipCount() {
        return shipCount;
    }

    public int getStructureCount() {
        return structureCount;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public String getUsername() {
        return username;
    }
}
