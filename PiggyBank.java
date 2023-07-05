import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;

/**
 * Reprezentuje kasę drobnych
 */
public class PiggyBank {
    /**
     * @param instance jedyna instancja klasy PiggyBank
     * @param zlotowki lista ze złotówkami
     * @param grosze lista z groszami
     */
    private static PiggyBank instance;
    private List<Coin> zlotowki;
    private List<Coin> grosze;

    /**
     * Prywatny konstruktor dla klasy PiggyBank
     */
    private PiggyBank(){
        zlotowki = new ArrayList<>();
        grosze = new ArrayList<>();
    }

    /**
     * Metoda, która gwarantuje istnienie tylko jedenej istancji klasy PiggyBank
     * @return instancja klasy PiggyBank
     */
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

    /**
     * Getter dla całej zawartości klasy PiggyBank
     * @return jedna lista, na którą składają się obie listy z pieniędzmi 
     */
    public List<Coin> getPiggyBank() {
        List<Coin> temp = new ArrayList<>();
        temp.addAll(zlotowki);
        temp.addAll(grosze);
        return temp;
    }

    /**
     * Getter dla listy ze złotówkami
     * @return
     */
    public List<Coin> getZlotowki(){
        return zlotowki;
    }

    /**
     * Getter dla listy z groszami
     * @return
     */
    public List<Coin> getGrosze(){
        return grosze;
    }

    /**
     * Metoda pozwalająca dodać monety o wybranym nominale do kasy z drobnymi
     * @param value wartość monety 
     * @param denomination zł dla złotówek lub gr dla groszy
     * @param quantity ilość monet
     */
    public void addCoins(int value, String denomination, int quantity){
        if(denomination=="zl"){
        zlotowki.add(new Coin(value, denomination, quantity));
        } else {
            grosze.add(new Coin(value, denomination, quantity));
        }
    }

    /**
     * Metoda zabierająca wybraną ilość danych monet z kasy z drobnymi
     * @param value wartość monety 
     * @param denomination zł dla złotówek lub gr dla groszy
     * @param quantity ilość monet
     */
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
                    return;
                }else{
                    System.out.println("za mało monet\n");
                    return;
                }
            }
        }
        System.out.println("Monety o tym nominale nie zostały znalezione, nic nie zostało zabrane\n");
    }

    /**
     * Metoda sortuje listy złotówek i groszy tak aby watrości były ustawione malejąco
     */
    public void sort(){
        Collections.sort(zlotowki, new Comparator<Coin>() {
            @Override
            public int compare(Coin coin1, Coin coin2) {
                if (coin1.getValue() > coin2.getValue()) {
                    return -1;
                } else if (coin1.getValue() < coin2.getValue()) {
                    return 1; 
                } else {
                    return 0; 
                }
            }
        });

        Collections.sort(grosze, new Comparator<Coin>() {
            @Override
            public int compare(Coin coin1, Coin coin2) {
                if (coin1.getValue() > coin2.getValue()) {
                    return -1;
                } else if (coin1.getValue() < coin2.getValue()) {
                    return 1; 
                } else {
                    return 0; 
                }
            }
        });
    }

    /**
     * Redefinicja metody toString, zwraca string z zawartością kasy
     */
    @Override
    public String toString(){
        String toPrint = "";
        for (Coin coin : this.getPiggyBank()){
            toPrint+=coin.toString();
        }
        return toPrint;
    }

    /**
     * Dzieli liczbę double na liczbę przed przecinkiem i liczbę po przecinku.
     * Zwraca tabelkę z obiema liczbami, liczba przed przecinkiem jest pierwsza 
     * @param num liczba o formacie X.YZ
     * @return tabelka [X, YZ]
     */
    public static int[] doubleSplit(double num) {
        BigDecimal number = BigDecimal.valueOf(num);
        BigDecimal integerPart = number.setScale(0, RoundingMode.DOWN);
        BigDecimal fractionalPart = number.subtract(integerPart);

        int[] values = new int[2];
        values[0] = integerPart.intValue();
        values[1] = fractionalPart.multiply(BigDecimal.valueOf(100)).intValue();

        return values;
    }

    /**
     * Metoda wydaje resztę dla danej kwoty i zabiera użyte monety z kasy z drobnymi
     * @param suma kwota, dla której wydajemy resztę
     */
    public void coinMachine(double suma){

        String printSumsOfMoney = "";
        int[] zlotowkiGrosze = doubleSplit(suma);
        System.out.println("Dla reszty "+ zlotowkiGrosze[0]+"."+zlotowkiGrosze[1]+"zł\n");
        // zlotówki loop
        int sumaNow = zlotowkiGrosze[0];
        if(sumaNow >= 1 ){
            for(Coin coin : zlotowki){
                if(sumaNow >= coin.getValue()){
                    int ileMonet = coin.coinsAvailable(sumaNow);
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
                    int ileMonet = coin.coinsAvailable(sumaNow);
                    if(ileMonet != 0){
                        printSumsOfMoney+="Wydaj "+ ileMonet + " monet "+ coin.getValue()+" gr\n";
                        this.takeCoins(coin.getValue(), "gr", ileMonet);
                        sumaNow=sumaNow-coin.getValue()*ileMonet;
                    }
                }
            }
        }
        if(printSumsOfMoney!="" && sumaNow > 0){
            System.out.println("Reszta będzie wydana częściowo, nie ma wystarczająco pieniędzy w kasie");
        } else if (printSumsOfMoney==""){
            System.out.println("Brak pieniędzy w kasie by wydać tą reszte");
            return;
        }
        System.out.print(printSumsOfMoney);
        System.out.print("\n");
    }
    
}
