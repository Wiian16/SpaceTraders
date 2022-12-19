import java.util.HashMap;

public class CargoItem {
    private String good;
    private int quantity, totalVolume;
    
    public CargoItem(String good, int quantity, int totalVolume) {
        this.good = good;
        this.quantity = quantity;
        this.totalVolume = totalVolume;
    }
    
    public CargoItem(HashMap<String, String> map){
        good = map.get("good");
        quantity = Integer.parseInt(map.get("quantity"));
        totalVolume = Integer.parseInt(map.get("totalVolume"));
    }
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Good: ").append(good).append("\n");
        str.append("Quantity: ").append(quantity).append("\n");
        str.append("Total Volume: ").append(totalVolume).append("\n");
        return str.toString();
    }
    
    public String getGood() {
        return good;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public int getTotalVolume() {
        return totalVolume;
    }
}
