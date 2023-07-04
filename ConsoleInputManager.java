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
        //Scanner scanner = new Scanner(System.in);


        
    }
}