import java.util.ArrayList;
import java.util.List;

public class PiggyBank {
    private static PiggyBank instance;
    private List<Coin> coinList;

    private PiggyBank(){
        coinList = new ArrayList<>();
    }

    public static PiggyBank getInstance() {
        if (instance == null) {
            synchronized (PiggyBank.class) {
                if (instance == null) {
                    instance = new PiggyBank();
                }
            }
        }
        return instance;
    }

    public List<Coin> getPiggyBank() {
        return coinList;
    }

    public void addCoins(int value, String denomination, int quantity){
        coinList.add(new Coin(value, denomination, quantity));
    }

    public void takeCoins(int value, String denomination, int quantity){
        for (Coin coin : coinList){
            if(coin.getValue() == value && coin.getDenomination() == denomination){
                if(coin.getQuantity() >= quantity){
                    coin.setQuantity(quantity);
                }else{
                    System.out.println("not enough coins\n");
                }
                return;
            }
        }
        System.out.println("coins of that denomination not found, nothing was taken\n");
    }

    public String toString(){
        String toPrint = "";
        for (Coin coin : coinList){
            toPrint+=coin.toString();
        }
        return toPrint;
    }
}
