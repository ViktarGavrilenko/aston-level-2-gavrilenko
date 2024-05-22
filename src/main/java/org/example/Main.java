package org.example;

import org.example.map.MyHashMap;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put(null, "Cat");
        for (int i = 0; i < 35; i++) {
            myHashMap.put("i" + i, "Dog" + i);
        }
        System.out.println("i0: "  +  myHashMap.get("i0"));
        myHashMap.put(null, "Frog");

        System.out.println("null: "  +  myHashMap.get(null));
        myHashMap.put(null, "Frog3");
        System.out.println("i20: "  +  myHashMap.get("i20"));
        myHashMap.put(null, "Frog4");
        System.out.println("i35: "  +  myHashMap.get("i35"));
        myHashMap.put(null, "Frog5");

        for (int i = 50; i < 80; i++) {
            myHashMap.put("i2" + i, "Cat" + i);
        }

        System.out.println("i50: "  +  myHashMap.get("i50"));
        myHashMap.put(null, "Frog6");

        System.out.println();
        myHashMap.put(null, "Frog7");
        System.out.println("null: "  +  myHashMap.get(null));
        myHashMap.put(null, "Frog8");
        System.out.println("i80: "  +  myHashMap.get("i80"));
        myHashMap.put(null, "Frog5");

        HashMap<Integer, Integer> qwe = new HashMap<>();

    }
}