package sukhov.myList;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(SinglyLinkedList<T> copy) {
        if (copy.head == null) {
            throw new NullPointerException("Переданный список пустой");
        }

        head = new ListItem<>(copy.getFirstData());
        count = copy.count;
        ListItem<T> copyList = copy.head;
        ListItem<T> list = head;

        for (int i = 1; i < count; i++) {
            copyList = copyList.getNext();
            ListItem<T> temp = new ListItem<>(copyList.getData());

            list.setNext(temp);
            list = list.getNext();
        }
    }

    public int getSize() {
        return count;
    }

    public T getFirstData() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        return head.getData();
    }

    private ListItem<T> iterationList(int index) {
        ListItem<T> list = head;

        for (int i = 0; i < index; i++) {
            list = list.getNext();
        }

        return list;
    }

    public T getData(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        return iterationList(index).getData();
    }

    public T setData(int index, T newData) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        T data = getData(index);
        iterationList(index).setData(newData);

        return data;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > count) {
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
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        boolean listHasData = false;

        if (head.getData() == data) {
            head = head.getNext();
            listHasData = true;

            count--;
        } else {
            for (ListItem<T> list = head; list.getNext() != null; list = list.getNext()) {
                if (list.getNext().getData() == data) {
                    list.setNext(list.getNext().getNext());
                    listHasData = true;

                    count--;
                    break;
                }
            }
        }

        return listHasData;
    }

    public void getReversal() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        ListItem<T> increasing;
        ListItem<T> decreasing = head;
        ListItem<T> temp = null;

        for (int i = 0; i < count; i++) {
            increasing = decreasing;
            decreasing = decreasing.getNext();

            increasing.setNext(temp);
            temp = increasing;

            head = increasing;
        }
    }

@Override
    public String toString() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");
        for (ListItem<T> list = head; list != null; list = list.getNext()) {
            stringBuilder.append(list.getData()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}
