package sukhov.myList;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(SinglyLinkedList<T> copy) {
        if (copy.count == 0) {
            return;
        }

        head = new ListItem<>(copy.getFirstData());
        count = copy.count;
        ListItem<T> copyListItem = copy.head;
        ListItem<T> listItem = head;

        for (int i = 1; i < count; i++) {
            copyListItem = copyListItem.getNext();
            ListItem<T> temp = new ListItem<>(copyListItem.getData());

            listItem.setNext(temp);
            listItem = listItem.getNext();
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

    private ListItem<T> getListItem(int index) {
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

        return getListItem(index).getData();
    }

    public T setData(int index, T newData) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Неверный индекс списка");
        }

        ListItem<T> list = getListItem(index);
        T data = list.getData();
        list.setData(newData);

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

        ListItem<T> addition = new ListItem<>(data);

        if (index == 0) {
            addition.setNext(head);
            head = addition;
        } else {
            ListItem<T> p = getListItem(index - 1);
            addition.setNext(p.getNext());
            p.setNext(addition);
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

        ListItem<T> temp;
        if (index == 0) {
            temp = head;
            head = head.getNext();
        } else {
            ListItem<T> list = getListItem(index - 1);
            temp = list.getNext();
            list.setNext(list.getNext().getNext());
        }

        count--;

        return temp.getData();
    }

    public boolean removeElement(T data) {
        if (count == 0) {
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();

            count--;
            return true;
        } else {
            for (ListItem<T> list = head; list.getNext() != null; list = list.getNext()) {
                if (data == null && list.getNext().getData() == null) {
                    list.setNext(list.getNext().getNext());

                    count--;
                    return true;
                }

                if (list.getNext().getData().equals(data)) {
                    list.setNext(list.getNext().getNext());

                    count--;
                    return true;
                }
            }
        }

        return false;
    }

    public void reverse() {
        if (count == 0) {
            return;
        }

        ListItem<T> decreasing = head;
        ListItem<T> temp = null;

        for (int i = 0; i < count; i++) {
            ListItem<T> increasing = decreasing;
            decreasing = decreasing.getNext();

            increasing.setNext(temp);
            temp = increasing;

            head = increasing;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (count == 0) {
            return stringBuilder.append("{}").toString();
        }

        stringBuilder.append("{");
        for (ListItem<T> list = head; list != null; list = list.getNext()) {
            stringBuilder.append(list.getData()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}
