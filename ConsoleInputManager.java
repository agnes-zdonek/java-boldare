import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputManager{
    public static void main(String[] args){
        
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

        System.out.println("Witaj w programie do wydawania reszt! Oto stan kasy: \n" + piggyBank.toString());
        Scanner scanner = new Scanner(System.in);
        List<Double> reszty = new ArrayList<>();
        //kiedy suma reszt > kasa daj komunikat!!
        double sumaReszt = 0.0;
        System.out.println("Podaj reszty do wydania (po każdej reszcie wciśnij enter, wpisz 'q' aby zakończyć):");
        

        //if not a double, wyswietl ze sproboj ponownie
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }

            if (input.matches("\\d+\\.\\d{2}")) {
                double reszta = Double.parseDouble(input);
                reszty.add(reszta);
                sumaReszt += reszta;
            } else {
                System.out.println("Kwota powinna być liczbą i mieć dwa miejsca po przecinku - format X.XX\n");
            }
            
        }

        for(Double d : reszty){
            piggyBank.coinMachine(d);
        }

        /*
         * to do:
         * readme
         * opis wszystkich metod
         * lepszy format ConsoleInpoyM
         * co się stanie gdy nie mamy pieniedzy w kasie a jeeszcze reszty w arraylist?
         */


        
    }
}