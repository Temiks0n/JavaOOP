package sukhov.main;

import sukhov.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();

        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add("ss");

        System.out.println("list1: " + Arrays.toString(list1.toArray()));

        ArrayList<Integer> list2 = new ArrayList<Integer>();

        list2.add(3);
        list2.add(1);
        list2.add(8);
        list2.addAll(list1);

        System.out.println("list2: " + Arrays.toString(list2.toArray()));

        MyArrayList<Integer> myList = new MyArrayList<>(0);

        myList.add(1);
        myList.add(8);
        myList.add(null);
        myList.add(3);
        myList.add(2);
        myList.trimToSize();
        myList.addAll(list1);
        myList.removeAll(list1);
        myList.add(3, null);
        myList.remove(1);
        myList.remove((Integer) 3);
        myList.retainAll(list2);

        System.out.println("myList: " + Arrays.toString(myList.toArray()));

        System.out.println("myList size: " + myList.size());

        myList.ensureCapacity(50);
    }
}
