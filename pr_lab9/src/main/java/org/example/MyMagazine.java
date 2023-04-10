package org.example;

import java.util.*;

import static java.lang.Thread.sleep;

public class MyMagazine {
    private List<String> magazine;
    private int size;
    private String type;

    public MyMagazine(String type)
    {
        List <String> magazine = new ArrayList<>();
        this.magazine = Collections.synchronizedList(magazine);
        this.type = type;
        size = 30;  // maksymalna pojemność magazynu
    }
    public synchronized List<String> take() throws InterruptedException {
        List<String> takenItems = new ArrayList<>();
        while (magazine.isEmpty()) {  // czekanie, kiedy magazyn jest pusty
            wait();
        }
        takenItems.add(magazine.get(0));
        magazine.remove(0);
        notifyAll();
        return takenItems;
    }

    public synchronized void put(String value, int amount) throws InterruptedException {
        while (magazine.size() >= size) {
            return; // jesli magazyn jest pełny, to producent spróbuje dostarczyć następnym razem
            //wait();
        }
        int delivered_amount = amount;
        if(amount + magazine.size() > size) // jeśli wyprodukowano więcej niż jest miejsca to dostarcza tyle ile można
        {
            delivered_amount = size - magazine.size();
        }
        for(int i=0;i<delivered_amount;i++) {
            magazine.add(value);
        }
        System.out.println("Produced " + delivered_amount + " pieces of: " + this.getType());
        //System.out.println("Magazine of " + type + " = " + magazine.size());
        notifyAll();
    }

    public String getType() {
        return type;
    }
}
