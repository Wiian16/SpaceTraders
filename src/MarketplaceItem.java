import java.util.HashMap;

public class MarketplaceItem {
    private int pricePerUnit, purchasePricePerUnit, quantityAvailable, spread, volumePerUnit;
    private String symbol;

    public MarketplaceItem(int pricePerUnit, int purchasePricePerUnit, int quantityAvailable, int spread, int volumePerUnit, String symbol) {
        this.pricePerUnit = pricePerUnit;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.quantityAvailable = quantityAvailable;
        this.spread = spread;
        this.volumePerUnit = volumePerUnit;
        this.symbol = symbol;
    }

    public MarketplaceItem(HashMap<String, String> map){
        pricePerUnit = Integer.parseInt(map.get("pricePerUnit"));
        purchasePricePerUnit = Integer.parseInt(map.get("purchasePricePerUnit"));
        quantityAvailable = Integer.parseInt(map.get("quantityAvailable"));
        spread = Integer.parseInt(map.get("spread"));
        volumePerUnit = Integer.parseInt(map.get("volumePerUnit"));
        symbol = map.get("symbol");
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Symbol: ").append(symbol).append("\n");
        str.append("Sell Price: ").append(pricePerUnit).append("\n");
        str.append("Purchase Price: ").append(purchasePricePerUnit).append("\n");
        str.append("Quantity: ").append(quantityAvailable).append("\n");
        str.append("Volume Per Unit: ").append(volumePerUnit).append("\n");
        str.append("Spread: ").append(spread).append("\n");
        return str.toString();
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public int getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public int getSpread() {
        return spread;
    }

    public int getVolumePerUnit() {
        return volumePerUnit;
    }

    public String getSymbol() {
        return symbol;
    }
}
