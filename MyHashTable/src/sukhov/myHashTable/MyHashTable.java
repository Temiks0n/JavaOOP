package sukhov.myHashTable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private HashItem<T>[] table;
    private int size;
    private int modCount;

    private class HashTableIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountIterator = modCount;
        private int index;
        HashItem<T> next;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (modCountIterator != modCount) {
                throw new ConcurrentModificationException("Были изменения");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Метод hasNext не использовался");
            }

            currentIndex++;

            while (currentIndex < size) {
                if (table[index] != null) {
                    if (next == null) {
                        next = table[index];
                    }

                    T data = next.getData();
                    next = next.getNext();

                    if (next == null) {
                        index++;
                    }

                    return data;
                }
                index++;
            }

            return null;
        }
    }

    public MyHashTable() {
        //noinspection unchecked
        table = new HashItem[20];
    }

    public MyHashTable(int length) {
        if (length < 0) {
            throw new IndexOutOfBoundsException("Длина таблицы не может быть меньше нуля");
        }

        //noinspection unchecked
        table = new HashItem[length];
    }

    public int size() {
        return size;
    }

    private int getHash(T data) {
        return Math.abs(data.hashCode() % table.length);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object data) {
        for (T d : this) {
            if (d == data) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        //noinspection unchecked
        T[] arrayData = (T[]) new Object[size];
        int i = 0;
        for (T data : this) {
            arrayData[i] = data;
            i++;
        }

        return arrayData;
    }

    @Override
    public boolean add(T data) {
        if (data == null) {
            throw new IndexOutOfBoundsException("Null значения");
        }

        int hash = getHash(data);
        HashItem<T> hashItem = new HashItem<>(data, hash);

        if (table[hash] == null) {
            table[hash] = hashItem;
        } else {
            if (table[hash].getData().equals(hashItem.getData())) {
                return false;
            }

            HashItem<T> temp = table[hash];

            while (temp.getNext() != null) {
                if (hashItem.getData().equals(temp.getNext().getData())) {
                    temp.setNext(hashItem);

                    size++;
                    modCount++;
                    return true;
                }
                temp = temp.getNext();
            }
            temp.setNext(hashItem);
        }

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object data) {
        if (data == null) {
            throw new IndexOutOfBoundsException("Null значения");
        }

        //noinspection unchecked
        int hash = getHash((T) data);

        if (table[hash] == null) {
            return false;
        }

        if (table[hash].getData().equals(data)) {
            table[hash] = table[hash].getNext();
            size--;

            return true;
        }

        HashItem<T> temp = table[hash];

        while (temp.getNext() != null) {
            if (data.equals(temp.getNext().getData())) {
                if (temp.getNext() != null) {
                    temp.setNext(temp.getNext().getNext());
                }
                size--;

                return true;
            }
            temp = temp.getNext();
        }

        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        boolean isAddAll = false;
        for (Object data : collection) {
            //noinspection unchecked
            add((T) data);
            isAddAll = true;
        }

        return isAddAll;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        table = new HashItem[20];
        size = 0;
        modCount = 0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        boolean isRetainAll = false;

        while (size >= collection.size()) {
            for (Object data : this) {
                if (!collection.contains(data)) {
                    remove(data);
                    isRetainAll = true;
                }
            }
        }

        return isRetainAll;
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean isRemoveAll = false;

        for (Object data : collection) {
            if (collection.contains(data)) {
                remove(data);
                isRemoveAll = true;
            }
        }

        return isRemoveAll;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object data : collection) {
            if (!contains(data)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray(Object[] array) {
        T[] arrayData;

        if (array.length < size) {
            arrayData = (T[]) new Object[size];
        } else {
            arrayData = (T[]) new Object[array.length];
        }

        int i = 0;
        for (T v : this) {
            arrayData[i] = v;
            i++;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return arrayData;
    }
}
