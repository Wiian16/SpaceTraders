import java.util.HashMap;

public class AvailableLoan {
    private int amount, rate, termInDays;
    private boolean collateralRequired;
    private String type;

    public AvailableLoan(int amount, int rate, boolean collateralRequired, String type, int termInDays){
        this.amount = amount;
        this.rate = rate;
        this.collateralRequired = collateralRequired;
        this.type = type;
        this.termInDays = termInDays;
    }

    public AvailableLoan(HashMap<String, String> map){
        this.amount = Integer.parseInt(map.get("amount"));
        this.rate = Integer.parseInt(map.get("rate"));
        this.collateralRequired = Boolean.parseBoolean(map.get("collateralRequired"));
        this.type = map.get("type");
        this.termInDays = Integer.parseInt(map.get("termInDays"));
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Type: ").append(type).append("\n");
        str.append("Rate: ").append(rate).append("\n");
        str.append("Collateral Required: ").append(collateralRequired).append("\n");
        str.append("Term in Days: ").append(termInDays).append("\n");

        return str.toString();
    }
}
