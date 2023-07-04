public class Test{
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

        System.out.println(piggyBank.toString());

        //int[] testArray;
        //testArray = piggyBank.doubleSplit(11.70);
        //System.out.println(testArray[0]+" "+testArray[1]);
        
        piggyBank.coinMachine(1.30);
        piggyBank.coinMachine(11.70);
        piggyBank.coinMachine(6.70);
        piggyBank.coinMachine(4.30);

        /*
         * co się stanie gdy nie mozemy rozmienić pieniedzy??
         */
        piggyBank.coinMachine(5.30);
        piggyBank.coinMachine(6.30);
        

    }
}