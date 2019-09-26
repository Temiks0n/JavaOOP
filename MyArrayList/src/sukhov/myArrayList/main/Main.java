package sukhov.myArrayList.main;

import sukhov.myArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();
        ArrayList list3 = new ArrayList();
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

        list1.trimToSize();

        list3.add(1);
        list3.add(12);
        list3.add(1);
        list3.add(1);



        list1.toArray(new Object[10]);

        System.out.println("list1: " + Arrays.toString(list1.toArray()));

        ArrayList<Integer> list2 = new ArrayList<Integer>();

        list2.add(3);
        list2.add(1);
        list2.add(8);
        list2.addAll(list1);

        System.out.println("list2 and list3 " + list2.containsAll(list3));

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
        myList.set(3, 7);
        myList.trimToSize();
        myList.ensureCapacity(20);
        myList.remove(3);
        myList.remove((Integer) 3);
        myList.retainAll(list2);
        myList.remove(0);
        myList.trimToSize();

        System.out.println("myList: " + Arrays.toString(myList.toArray()));

        System.out.println("myList size: " + myList.size());

        System.out.println("myList and list3 " + myList.containsAll(list3));

        myList.ensureCapacity(50);
    }
}
