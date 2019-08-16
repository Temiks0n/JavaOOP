package sukhov.myList.main;

import sukhov.myList.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();

        linkedList.addFirst(5);
        linkedList.addFirst(6);
        linkedList.addFirst(2);
        linkedList.add(0, 3);
        linkedList.remove(3);
        linkedList.removeElement(null);

        System.out.println("изменить по индексу 0 на значение 9: " + linkedList.setData(0, 9));
        System.out.println("удалить по индексу 1: " + linkedList.remove(0));
        System.out.println("удалить элемент 6: " + linkedList.removeElement(6));
        System.out.println("размер списка: " + linkedList.getSize());

        linkedList.addFirst(2);
        linkedList.addFirst(6);
        linkedList.addFirst(8);
        linkedList.add(3, 4);

        linkedList.getReversal();
        System.out.println("развернутый список: " + linkedList.toString());

        SinglyLinkedList<Integer> linkedListCopy = new SinglyLinkedList<>(linkedList);
        linkedList.setData(2, 9);

        System.out.println("копия списка: " + linkedListCopy.toString());

    }
}
