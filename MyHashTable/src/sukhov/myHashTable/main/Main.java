package sukhov.myHashTable.main;

import sukhov.myHashTable.MyHashTable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> myHash = new MyHashTable<>(3);

        myHash.add(5);
        myHash.add(5);
        myHash.add(8);
        myHash.add(9);
        myHash.add(11);
        myHash.add(16);
        myHash.add(18);
        myHash.add(19);
        myHash.add(22);
        myHash.add(82);
        myHash.add(38);
        myHash.add(74);
        myHash.remove(120);

        MyHashTable<Integer> myHashTable1 = new MyHashTable<>(5);

        myHashTable1.add(5);
        myHashTable1.add(22);
        myHashTable1.add(9);
        myHashTable1.add(11);

        MyHashTable<Integer> myHashTable2 = new MyHashTable<>();

        myHashTable2.add(5);
        myHashTable2.add(55);
        myHashTable2.add(555);
        myHashTable2.add(5555);

        System.out.println("myHash removeAll: " + myHash.removeAll(myHashTable1));

        System.out.println("myHash addAll: " + myHash.addAll(myHashTable2));

        System.out.println("myHash contains 3: " + myHash.contains(3));

        System.out.println("myHash values: " + Arrays.toString(myHash.toArray()));
    }
}
