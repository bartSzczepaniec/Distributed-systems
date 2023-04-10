package org.example;

import java.util.List;
import java.util.Random;

public class MyConsumer implements Runnable{
    private List<MyMagazine> resources1;
    private List<MyMagazine> resources2;
    private Random randomizer;
    int nr;
    public MyConsumer(List<MyMagazine> resources1, List<MyMagazine> resources2, int nr)
    {
        this.resources1 = resources1;
        this.resources2 = resources2;
        this.nr = nr;
        this.randomizer = new Random();
    }
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                String sandwitch = "Consumer " + nr + " created sandwitch";
                sandwitch += " with: ";

                int randomized1 = randomizer.nextInt(2); // losowanie przepisu
                List<MyMagazine> resources;
                if(randomized1 == 0)
                    resources = resources1;
                else
                    resources = resources2;

                for (MyMagazine res: resources) {  // z każdego magazynu bierze skladnik
                    List<String> items = res.take();
                    sandwitch += items.get(0);
                    sandwitch += " ";
                }
                System.out.println(sandwitch);
                int randomized2 = randomizer.nextInt(1000) + 1000; // losowe opóźnienia
                Thread.sleep(randomized2);
                //System.out.println(numberToCheck);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
