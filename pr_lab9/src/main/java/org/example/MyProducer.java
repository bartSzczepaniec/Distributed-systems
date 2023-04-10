package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyProducer implements Runnable{
    private List<MyMagazine> resource;
    private String name;
    private List<String> items;
    private Random randomizer;
    public MyProducer(List<MyMagazine> resource, String name)
    {
        this.resource = resource;
        this.name = name;
        items = new ArrayList<>();
        randomizer = new Random();
        // W zależności od nazwy dodaje składniki, które produkuje
        if(name.equals("Meat"))
        {
            items.add("ham");
        }
        else if(name.equals("Bread"))
        {
            items.add("bread");
        }
        else {  // "Dairy"
            items.add("butter");
            items.add("cheese");
        }

    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            for (MyMagazine res : resource) {  // próby wyprodukowania i dostarczenia składników
                try {
                    if (!items.contains(res.getType()))
                        return;
                    int randomized = randomizer.nextInt(4) + 1; // losowa ilość od 1 do 4
                    res.put(res.getType(), randomized);
                } catch (InterruptedException e) {
                    break;
                }
            }
            try {  // losowe opóźnienie
                int randomized = randomizer.nextInt(1000) + 500;
                Thread.sleep(randomized);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
