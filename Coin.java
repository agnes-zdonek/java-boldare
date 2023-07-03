public class Coin {
    private final int value;
    private final String denomination;
    private int quantity;

    public Coin(int value, String denomination, int quantity){
        this.value = value;
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public int getValue(){
        return value;
    }

    public String getDenomination(){
        return denomination;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int minusThatQuantity){
        this.quantity = this.quantity-minusThatQuantity;
    }

    public String toString(){
        return +value+denomination + ": "+ quantity+ "szt.\n";
    }

}
