import java.util.HashMap;

public class Location {
    private boolean allowsConstruction;
    private int dockedShips, x, y;
    private String name, symbol, type;
    
    public Location(boolean allowsConstruction, int dockedShips, int x, int y, String name, String symbol, String type) {
        this.allowsConstruction = allowsConstruction;
        this.dockedShips = dockedShips;
        this.x = x;
        this.y = y;
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }
    
    public Location(HashMap<String, String> map){
        allowsConstruction = Boolean.parseBoolean(map.get("allowsConstruction"));
        dockedShips = Integer.parseInt(map.get("dockedShips"));
        name = map.get("name");
        symbol = map.get("symbol");
        type = map.get("type");
        x = Integer.parseInt("x");
        y = Integer.parseInt("y");
    }
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Name: ").append(name).append("\n");
        str.append("Symbol: ").append(symbol).append("\n");
        str.append("Type: ").append(type).append("\n");
        str.append("Position: ").append(x).append(", ").append(y).append("\n");
        str.append("Docked Ships: ").append(dockedShips).append("\n");
        str.append("Allows Construction: ").append(allowsConstruction);
        return str.toString();
    }
    
    public boolean isAllowsConstruction() {
        return allowsConstruction;
    }
    
    public int getDockedShips() {
        return dockedShips;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public String getType() {
        return type;
    }
}
