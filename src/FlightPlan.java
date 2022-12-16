import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FlightPlan {
    private Date arrivesAt, createdAt, terminatedAt;
    private String departure, destination, id, shipId;
    private int distance, fuelConsumed, fuelRemaining, timeRemaining;
    
    public FlightPlan(Date arrivesAt, Date createdAt, Date terminatedAt, String departure, String destination, String id, String shipId, int distance, int fuelConsumed, int fuelRemaining, int timeRemaining) {
        this.arrivesAt = arrivesAt;
        this.createdAt = createdAt;
        this.terminatedAt = terminatedAt;
        this.departure = departure;
        this.destination = destination;
        this.id = id;
        this.shipId = shipId;
        this.distance = distance;
        this.fuelConsumed = fuelConsumed;
        this.fuelRemaining = fuelRemaining;
        this.timeRemaining = timeRemaining;
    }
    
    public FlightPlan(HashMap<String, String> map) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");
        
        id = map.get("id");
        shipId = map.get("shipId");
        createdAt = df.parse(map.get("createdAt"));
        arrivesAt = df.parse(map.get("arrivesAt"));
        destination = map.get("destination");
        departure = map.get("departure");
        distance = Integer.parseInt(map.get("distance"));
        fuelConsumed = Integer.parseInt(map.get("fueConsumed"));
        fuelRemaining = Integer.parseInt(map.get("fuelRemaining"));
        terminatedAt = df.parse(map.get("terminatedAt"));
        timeRemaining = Integer.parseInt(map.get("timeRemainingInSeconds"));
    }
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Id: ").append(id).append("\n");
        str.append("Ship Id: ").append(shipId).append("\n");
        str.append("Created At: ").append(createdAt).append("\n");
        str.append("Arrives At: ").append(arrivesAt).append("\n");
        str.append("Destination: ").append(destination).append("\n");
        str.append("Departure: ").append(departure).append("\n");
        str.append("Distance: ").append(distance).append("\n");
        str.append("Fuel Consumed: ").append(id).append("\n");
        str.append("Fuel Remaining: ").append(fuelRemaining).append("\n");
        str.append("Terminated At: ").append(terminatedAt).append("\n");
        str.append("Time Remaining: ").append(timeRemaining).append("\n");
        return str.toString();
    }
    
    public Date getArrivesAt() {
        return arrivesAt;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public Date getTerminatedAt() {
        return terminatedAt;
    }
    
    public String getDeparture() {
        return departure;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public String getId() {
        return id;
    }
    
    public String getShipId() {
        return shipId;
    }
    
    public int getDistance() {
        return distance;
    }
    
    public int getFuelConsumed() {
        return fuelConsumed;
    }
    
    public int getFuelRemaining() {
        return fuelRemaining;
    }
    
    public int getTimeRemaining() {
        return timeRemaining;
    }
}
