package org.synch;

public class App {
    public static final int N_ACOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 100;
    public static final int DELAY = 10000;

    public static void main(String[] args) {
        Bank bank = new Bank(N_ACOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < N_ACOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread t = new Thread(r);
            t.start();
        }
    }
    
}