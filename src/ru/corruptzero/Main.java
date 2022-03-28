package ru.corruptzero;

import com.github.sh0nk.matplotlib4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main{

    public static void main(String[] args) throws InterruptedException, PythonExecutionException, IOException {
        /* Вычислить и вывести числа фибоначчи от 1 до N.
            Каждое число вычислить в отдельном потоке "с нуля".
            Нужно использовать семафор.
            Одновременно должны работать не более M потоков.
            В терминал вывести
                входное число,
                получившееся число Фибоначчи,
                время вычисления значения.

         */
        int threads = 40;

        long startTimeMillis = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(8);
        for (int i = 0; i < threads; i++) {
            FibonacciThread fibonacciThread = new FibonacciThread(i, semaphore, startTimeMillis, Mode.RECURSIVE);
            Thread thread = new Thread(fibonacciThread);
            thread.start();
            thread.join();
        }
        List<Long> list = new ArrayList<>(Storage.getInstance().getMsMap().values());
        Plot plt = Plot.create();
        plt.plot()
                .add(list)
                .label("Recursive")
                .linestyle("-");
        plt.xlabel("Threads");
        plt.ylabel("Time (ms)");
        plt.title("Execution time vs. the number of threads");
        plt.legend();
        plt.show();

    }



}
