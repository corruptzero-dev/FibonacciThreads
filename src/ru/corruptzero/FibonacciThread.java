package ru.corruptzero;

import java.util.concurrent.Semaphore;

public class FibonacciThread extends Thread {
    private final int n;
    private final Semaphore semaphore;
    private final Storage storage = Storage.getInstance();
    private final FW fw = new FW();
    private final Mode mode;
    private long startTimeMillis;


    public FibonacciThread(int n, Semaphore semaphore, long startTimeMillis, Mode mode) {
        this.n = n;
        this.semaphore = semaphore;
        this.startTimeMillis = startTimeMillis;
        this.mode = mode;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            startTimeMillis = System.currentTimeMillis();
            long result = fib(n, mode);
            long endTime = (System.currentTimeMillis() - startTimeMillis);
            storage.setMs(storage.getMs() + endTime);
            System.out.printf("Thread %d: %d -> %d ms\n", n, result, endTime);
            semaphore.release();
            //fw.write(String.format("%d,%d", n, storage.getMs()));
            storage.addToMsMap(n,endTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private long fib(int n, Mode mode) {
        switch (mode) {
            case LINEAR -> {
                long firstTerm = 0;
                long secondTerm = 1;
                for (int i = 1; i <= n; ++i) {
                    long nextTerm = firstTerm + secondTerm;
                    firstTerm = secondTerm;
                    secondTerm = nextTerm;
                }
                return secondTerm;
            }
            case RECURSIVE -> {
                if (n == 0)
                    return 0;
                else if (n == 1)
                    return 1;
                else
                    return fib(n - 1, Mode.RECURSIVE) + fib(n - 2, Mode.RECURSIVE);
            }
            default -> throw new RuntimeException("Ошибка в методе fib");
        }
    }
}
