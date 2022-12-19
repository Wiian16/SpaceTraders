import java.util.ArrayList;
import java.util.HashMap;

public class Ship {
    public String id, location, type, shipClass, manufacturer;
    public int x, y, spaceAvailable, maxCargo, speed, plating, weapons;
    public ArrayList<CargoItem> cargo;
    
    public Ship(String id, String location, String type, String shipClass, String manufacturer, int x, int y, int spaceAvailable, int maxCargo, int speed, int plating, int weapons) {
        cargo = new ArrayList<>();
        this.id = id;
        this.location = location;
        this.type = type;
        this.shipClass = shipClass;
        this.manufacturer = manufacturer;
        this.x = x;
        this.y = y;
        this.spaceAvailable = spaceAvailable;
        this.maxCargo = maxCargo;
        this.speed = speed;
        this.plating = plating;
        this.weapons = weapons;
    }
    
    public Ship(HashMap<String, String> map){
    
    }
}
