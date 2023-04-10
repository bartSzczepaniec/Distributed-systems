package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyMagazine butter = new MyMagazine("butter");
        MyMagazine ham = new MyMagazine("ham");
        MyMagazine bread = new MyMagazine("bread");
        MyMagazine cheese = new MyMagazine("cheese");
        // magazyny do danych producentow
        List<MyMagazine> dairy_l = new ArrayList<MyMagazine>();
        dairy_l.add(butter);
        dairy_l.add(cheese);
        List<MyMagazine> ham_l = new ArrayList<MyMagazine>();
        ham_l.add(ham);
        List<MyMagazine> bread_l = new ArrayList<MyMagazine>();
        bread_l.add(bread);
        // listy na przepisy
        List<MyMagazine> sandwitch_with_ham = new ArrayList<MyMagazine>();
        sandwitch_with_ham.add(ham);
        sandwitch_with_ham.add(bread);
        sandwitch_with_ham.add(butter);
        List<MyMagazine> sandwitch_with_cheese = new ArrayList<MyMagazine>();
        sandwitch_with_cheese.add(cheese);
        sandwitch_with_cheese.add(bread);
        sandwitch_with_cheese.add(butter);
        // tworzenie producentów
        MyProducer breadProducer = new MyProducer(bread_l,"Bread");
        MyProducer hamProducer = new MyProducer(ham_l,"Meat");
        MyProducer dairyProducer = new MyProducer(dairy_l,"Dairy");
        Thread thread1 = new Thread(breadProducer);
        thread1.start();
        Thread thread2 = new Thread(hamProducer);
        thread2.start();
        Thread thread3 = new Thread(dairyProducer);
        thread3.start();
        // tworzenie konsumentow
        int amount_of_consumers = 30;
        Thread[] consumers_threads = new Thread[amount_of_consumers];
        for(int i=0;i<amount_of_consumers;i++)
        {
            consumers_threads[i] = new Thread(new MyConsumer(sandwitch_with_ham, sandwitch_with_cheese, i));
        }
        for (int i=0; i<amount_of_consumers;i++)
        {
            consumers_threads[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String quit = scanner.next();
            if (quit.equals("q"))
            {   // przerwanie wątków
                thread1.interrupt();
                thread2.interrupt();
                thread3.interrupt();
                for (int i=0; i<amount_of_consumers;i++)
                {
                    consumers_threads[i].interrupt();
                }
                return;
            }
        }
    }
}
