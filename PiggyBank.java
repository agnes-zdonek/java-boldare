import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PiggyBank {
    private static PiggyBank instance;
    private List<Coin> zlotowki;
    private List<Coin> grosze;

    private PiggyBank(){
        zlotowki = new ArrayList<>();
        grosze = new ArrayList<>();
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
        List<Coin> temp = new ArrayList<>();
        temp.addAll(zlotowki);
        temp.addAll(grosze);
        return temp;
    }

    public List<Coin> getZlotowki(){
        return zlotowki;
    }

    public List<Coin> getGrosze(){
        return grosze;
    }

    public void addCoins(int value, String denomination, int quantity){
        if(denomination=="zl"){
        zlotowki.add(new Coin(value, denomination, quantity));
        } else {
            grosze.add(new Coin(value, denomination, quantity));
        }
    }

    public void takeCoins(int value, String denomination, int quantity){
        List<Coin> coinList;
        if(denomination=="zl") {
            coinList = zlotowki;
        } else {
            coinList = grosze;
        }
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
        for (Coin coin : this.getPiggyBank()){
            toPrint+=coin.toString();
        }
        return toPrint;
    }

    public static int[] doubleSplit(double num) {
        BigDecimal number = BigDecimal.valueOf(num);
        BigDecimal integerPart = number.setScale(0, RoundingMode.DOWN);
        BigDecimal fractionalPart = number.subtract(integerPart);

        int[] values = new int[2];
        values[0] = integerPart.intValue();
        values[1] = fractionalPart.multiply(BigDecimal.valueOf(100)).intValue();

        return values;
    }

     
    public void coinMachine(double suma){

        String printSumsOfMoney = "";
        int[] zlotowkiGrosze = doubleSplit(suma);
        System.out.println("Dla reszty "+ zlotowkiGrosze[0]+"."+zlotowkiGrosze[1]+"zł\n");
        // zlotówki loop
        int sumaNow = zlotowkiGrosze[0];
        if(sumaNow >= 1 ){
            for(Coin coin : zlotowki){
                if(sumaNow >= coin.getValue()){
                    int ileMonet = coin.coinsAviable(sumaNow);
                    if(ileMonet != 0){
                        printSumsOfMoney+="Wydaj "+ ileMonet + " monet "+ coin.getValue()+" zł\n";
                        this.takeCoins(coin.getValue(), "zl", ileMonet);
                        sumaNow=sumaNow-coin.getValue()*ileMonet;
                    }
                }
            }
        }

        //grosze loop
        sumaNow = sumaNow*100+ zlotowkiGrosze[1];
        if(sumaNow > 0){
            for(Coin coin : grosze){
                if(sumaNow >= coin.getValue()){
                    int ileMonet = coin.coinsAviable(sumaNow);
                    if(ileMonet != 0){
                        printSumsOfMoney+="Wydaj "+ ileMonet + " monet "+ coin.getValue()+" gr\n";
                        this.takeCoins(coin.getValue(), "gr", ileMonet);
                        sumaNow=sumaNow-coin.getValue()*ileMonet;
                    }
                }
            }
        }
        System.out.print(printSumsOfMoney);
        System.out.print("\n");
    }
    
}
