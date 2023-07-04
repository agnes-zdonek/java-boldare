/**
 * Reprezentuje monetę o wybranym nominale i jej ilosć
 */
public class Coin {
    /**
     * @param value wartość monety ex. 1,5,20
     * @param denomination zł dla złotówek lub gr dla groszy
     * @param quantity ilość monet
     */
    private final int value;
    private final String denomination;
    private int quantity;

    /**
     * Konstruktor dla klasy Coin
     * @param value wartość monety
     * @param denomination zł dla złotówek lub gr dla groszy
     * @param quantity ilość monet
     */
    public Coin(int value, String denomination, int quantity){
        this.value = value;
        this.denomination = denomination;
        this.quantity = quantity;
    }

    /**
     * Getter dla pola value
     * @return wartość monety
     */
    public int getValue(){
        return value;
    }

    /**
     * Getter dla pola denomination
     * @return zł dla złotówek lub gr dla groszy
     */
    public String getDenomination(){
        return denomination;
    }

    /**
     * Getter dla pola quantity
     * @return ilość monet
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Setter dla pola quantity
     * @param minusThatQuantity odejmij taką ilość monet 
     */
    public void setQuantity(int minusThatQuantity){
        this.quantity = this.quantity-minusThatQuantity;
    }

    /**
     * Redefinicja metody toString
     * @return X zł/gr: Y szt.
     */
    public String toString(){
        return value+denomination + ": "+ quantity+ "szt.\n";
    }

    /**
     * Jaką maksymalną ilość monet (o aktualnym nominale) możemy użyć do wyadania reszty
     * @param suma Kwota dla którą wydajemy resztę
     * @return ilość monet potrzebnych do wydania części reszty
     */
    public int coinsAvailable(int suma){

        int iloscDzielenie = suma/value;
        if(quantity >= iloscDzielenie){
            return iloscDzielenie;
        }
        return quantity;
    }

}
