import java.util.ArrayList;
import java.util.List;

/**
 * Testy dla metod klas Coin, PiggyBank
 */

public class Test{
    public static void main(String[] args){

        // Kreacja monety 
        System.out.println("Utworzenie 100 monet o wartości 5zł.");
        Coin piecZlotyCoin = new Coin(5, "zl", 100);
        System.out.print(piecZlotyCoin.toString());

        // Modyfikacja ilości pięciozłotówek
        System.out.println("Zabranie jednej monety");
        piecZlotyCoin.setQuantity(1);
        System.out.print(piecZlotyCoin.toString());
        System.out.println("Próba zabrania 100 monet o wartości 5zł.");
        piecZlotyCoin.setQuantity(100);
        System.out.print(piecZlotyCoin.toString());

        System.out.print("\n");

        // Ile monet pięciozłotowych może być wykorzystanych do wydania poszczególych reszt?
        System.out.println("Jaka maksymalna ilość monet 5zł (obecny stan "+piecZlotyCoin.getQuantity()+" monet) może być wykorzystana do wydania poszczególnych reszt?");
        System.out.println("14zł: "+piecZlotyCoin.coinsAvailable(14)+" monety");
        System.out.println("100zł: "+piecZlotyCoin.coinsAvailable(100)+" monet");
        System.out.println("690zł: "+piecZlotyCoin.coinsAvailable(690)+" monet");
        System.out.println("0zł: "+piecZlotyCoin.coinsAvailable(0)+" monet");

        System.out.print("\n");
        
        // Kreacja kasy z drobnymi
        System.out.println("Utworzenie kasy z drobnymi i dodanie do niej pieniędzy.");
        PiggyBank piggyBank = PiggyBank.getInstance();
        piggyBank.addCoins(2, "zl", 3);
        piggyBank.addCoins(1, "zl", 5);
        piggyBank.addCoins(5, "zl", 1);
        
        piggyBank.addCoins(20, "gr", 20);
        piggyBank.addCoins(10, "gr", 200);
        piggyBank.addCoins(5, "gr", 100);
        piggyBank.addCoins(2, "gr", 100);
        piggyBank.addCoins(50, "gr", 10);
        piggyBank.addCoins(1, "gr", 11000);

        // Nieposortowana kasa 
        System.out.println("Nieposortowana kasa z drobnymi:");
        System.out.println(piggyBank.toString());
        // Posortowana kasa
        System.out.println("Posortowana kasa z drobnymi:");
        piggyBank.sort();
        System.out.println(piggyBank.toString());

        // Zabranie monet
        System.out.println("Zabranie 1000 monet o wartości 1gr, stan części kasy z groszami:");
        piggyBank.takeCoins(1, "gr", 1000);
        for(Coin coin : piggyBank.getGrosze()){System.out.println(coin.getValue()+coin.getDenomination()+" "+coin.getQuantity()+" szt.");}
        System.out.println("\nPróba zabrania 10001 monet 1gr.");
        piggyBank.takeCoins(1, "gr", 10001);
        System.out.println("Próba zabrania 10 nieistniejących monet o wartości 7 zł");
        piggyBank.takeCoins(7, "zl", 10);

        // Rozdzielanie liczby na część przed i po przecinku (dla liczb z maksymalnie dwoma miejscami po przecinku)
        System.out.println("Podzielenie liczby 11.79 na 11 i 79");
        int[] testArray;
        testArray = PiggyBank.DoubleSplit(11.79);
        System.out.println(testArray[0]+" "+testArray[1]);
        System.out.println("Podzielenie liczby 1805 na 1805 i 0");
        testArray = PiggyBank.DoubleSplit(1805);
        System.out.println(testArray[0]+" "+testArray[1]);
        System.out.println("Próba podzielenia liczby 148.999");
        testArray = PiggyBank.DoubleSplit(148.999);
        System.out.println("Próba podzielenia liczby 777.888111");
        testArray = PiggyBank.DoubleSplit(777.888111);

        System.out.print("\n");
        System.out.println("------------------------------\n");
        System.out.println("Testy z przykładu z pliku z zadaniem rekrutacyjnym:");
        System.out.println("Stan kasy z drobnymi:");
        piggyBank.sort();
        System.out.println(piggyBank.toString());
        List<Double> reszty = new ArrayList<>(List.of(1.30, 11.70, 6.70,4.30));
        for(Double doub : reszty){
            piggyBank.coinMachine(doub);
        }
    }
}