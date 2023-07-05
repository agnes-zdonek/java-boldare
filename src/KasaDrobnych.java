import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Pozwala na działanie aplikacji
 */
public class KasaDrobnych{

    public static void main(String args[]){
        
        // Utworzenie kasy z drobnymi
        PiggyBank piggyBank = PiggyBank.getInstance();
        piggyBank.addCoins(5, "zl", 1);
        piggyBank.addCoins(2, "zl", 3);
        piggyBank.addCoins(1, "zl", 5);

        piggyBank.addCoins(50, "gr", 10);
        piggyBank.addCoins(20, "gr", 20);
        piggyBank.addCoins(10, "gr", 200);
        piggyBank.addCoins(5, "gr", 100);
        piggyBank.addCoins(2, "gr", 100);
        piggyBank.addCoins(1, "gr", 10000);
        piggyBank.sort();

        System.out.println("Witaj w programie do wydawania reszt! Oto stan kasy: \n" + piggyBank.toString());
        Scanner scanner = new Scanner(System.in);
        List<Double> reszty = new ArrayList<>();
        System.out.println("Podaj reszty do wydania (po każdej reszcie wciśnij enter, wpisz 'q' aby zakończyć):");
        
        // Pobieranie reszt
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }

            if (input.matches("\\d+\\.\\d{2}")) {
                double reszta = Double.parseDouble(input);
                reszty.add(reszta);
            } else {
                System.out.println("Kwota powinna być liczbą i mieć dwa miejsca po kropce - format X.YX");
            }
            
        }

        // Wydawanie reszt
        for(Double d : reszty){
            piggyBank.coinMachine(d);
        }
        
    }
}