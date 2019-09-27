package sukhov.myHashTable.main;

import sukhov.myHashTable.MyHashTable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("sup", 1);
        hashtable.put("Sup", 2);
        hashtable.put("sUp", 3);
        hashtable.put("suP", 4);
        hashtable.remove("sup", 3);
        hashtable.put("sup", 2);
        System.out.println(hashtable.get("spu"));

        MyHashTable<Integer, Integer> myHash = new MyHashTable<>(7);

        myHash.put(6, 4);
        myHash.put(5, 6);
        myHash.put(5, 4);
        myHash.put(5, 5);
        myHash.put(2, 4);
        myHash.put(1, 6);
        myHash.put(7, 4);
        myHash.put(12, 5);
        myHash.put(14, 4);
        myHash.put(11, 6);
        myHash.put(17, 4);
        myHash.put(18, 5);
        myHash.remove(12);
        myHash.remove(6, 3);
        System.out.println("myHash put (key 7 , value 5): " + myHash.put(7, 5));
        System.out.println("myHash key remove(key 6, value 4):" + myHash.remove(6, 4));

        System.out.println("myHash contains 3: " + myHash.contains(3));

        System.out.println("myHash key 6: " + myHash.getValue(6));
        System.out.println("myHash key 17: " + myHash.getValue(17));
        System.out.println("myHash values: " + Arrays.toString(myHash.toArray()));
    }
}
