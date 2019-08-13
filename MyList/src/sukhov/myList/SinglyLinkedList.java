package sukhov.myList;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count = 0;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(SinglyLinkedList<T> copy) {
        head = new ListItem<>(copy.getFirstData());
        count = copy.count;
        ListItem<T> list = head;

        for (int i = 1; i < count; i++) {
            ListItem<T> temp = new ListItem<>(copy.getData(i));
            list.setNext(temp);
            list = list.getNext();
        }
    }

    public int getSize() {
        return count;
    }

    public T getFirstData() {
        return head.getData();
    }

    public T getData(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        ListItem<T> list = head;

        for (int i = 0; i < index; i++) {
            list = list.getNext();
        }

        return list.getData();
    }

    public T setData(int index, T e) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        T data = getData(index);
        ListItem<T> list = head;

        for (int i = 0; i < index; i++) {
            list = list.getNext();
        }
        list.setData(e);

        return data;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void add(int index, T data) {
        if (index < 0 || index >= count + 1) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        ListItem<T> list = new ListItem<>(data);
        ListItem<T> p = head;

        if (index == 0) {
            list.setNext(head);
            head = list;
        } else {
            for (int i = 1; i < index; i++) {
                p = p.getNext();
            }
            list.setNext(p.getNext());
            p.setNext(list);
        }

        count++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        ListItem<T> temp = head;
        head = head.getNext();

        count--;

        return temp.getData();
    }

    public T remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        ListItem<T> list = head;
        ListItem<T> temp;

        if (index == 0) {
            temp = head;
            head = head.getNext();
        } else {
            for (int i = 1; i < index; i++) {
                list = list.getNext();
            }
            temp = list.getNext();
            list.setNext(list.getNext().getNext());
        }

        count--;

        return temp.getData();
    }

    public boolean removeElement(T data) {
        boolean isEqual = false;

        if (head.getData() == data) {
            head = head.getNext();
            isEqual = true;
            count--;
        } else {
            for (ListItem<T> list = head; list != null; list = list.getNext()) {
                if (list.getNext().getData() == data) {
                    list.setNext(list.getNext().getNext());
                    isEqual = true;
                    count--;
                    break;
                }
            }
        }

        return isEqual;
    }

    public void reversal() {
        ListItem<T> decreasing = head;
        ListItem<T> increasing;
        ListItem<T> temp = null;

        for (int i = 0; i < count; i++) {
            increasing = decreasing;
            decreasing = decreasing.getNext();

            increasing.setNext(temp);
            temp = increasing;

            head = increasing;
        }
    }

    public void print() {
        for (ListItem<T> list = head; list != null; list = list.getNext()) {
            System.out.println(list.getData());
        }
    }
}
