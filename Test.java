public class Test{
    public static void main(String[] args){
        PiggyBank piggyBank = PiggyBank.getInstance();
        piggyBank.addCoins(5, "zł", 10);
        piggyBank.addCoins(2, "zł", 100);
        piggyBank.addCoins(50, "gr", 3);
        piggyBank.addCoins(1, "gr", 1000);

        System.out.println(piggyBank.toString());

    }
}