package sukhov.myHashTable;

import java.util.*;

public class MyHashTable<K, V> implements Collection<V> {
    private HashItem<K, V>[] table;
    private int size;
    private int modCount;

    private class HashTableIterator implements Iterator<V> {
        private int currentIndex = -1;
        private int modCountIterator = modCount;
        private int index;
        HashItem<K, V> next;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public V next() {
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

                    V value = next.getValue();
                    next = next.getNext();

                    if (next == null) {
                        index++;
                    }

                    return value;
                }
                index++;
            }

            return null;
        }
    }

    public MyHashTable() {
        //noinspection unchecked
        table = new HashItem[15];
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

    public int getHash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    public boolean put(K key, V value) {
        if (key == null) {
            throw new IndexOutOfBoundsException("Неверный ключ");
        }

        int hash = getHash(key);
        HashItem<K, V> hashItem = new HashItem<>(key, value, hash);

        if (table[hash] == null) {
            table[hash] = hashItem;
        } else {
            if (table[hash].equals(hashItem)) {
                table[hash] = hashItem;
                return true;
            }

            HashItem<K, V> temp = table[hash];

            while (temp.getNext() != null) {
                if (hashItem.equals(temp.getNext())) {
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

    public V getValue(K key) {
        if (key == null) {
            throw new IndexOutOfBoundsException("Неверный ключ");
        }

        int hash = getHash(key);
        HashItem<K, V> temp = table[hash];

        while (temp != null) {
            if (key.equals(temp.getKey())) {
                return temp.getValue();
            }
            temp = temp.getNext();
        }

        return null;
    }

    public boolean remove(K key, V value) {
        if (table[getHash(key)].getValue().equals(value)) {
            return remove(key);
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        for (V v : this) {
            if (v == value) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        //noinspection unchecked
        V[] arrayValues = (V[]) new Object[size];
        int i = 0;
        for (V v : this) {
            arrayValues[i] = v;
            i++;
        }

        return arrayValues;
    }

    @Override
    public boolean add(V v) {
        return false;
    }

    @Override
    public boolean remove(Object key) {
        if (key == null) {
            throw new IndexOutOfBoundsException("Неверный ключ");
        }

        //noinspection unchecked
        int hash = getHash((K) key);

        if (table[hash] == null) {
            throw new IndexOutOfBoundsException("Неверный ключ");
        }

        if (table[hash].getKey().equals(key)) {
            table[hash] = table[hash].getNext();

            size--;
            modCount--;

            return true;
        }

        HashItem<K, V> temp = table[hash];

        while (temp.getNext() != null) {
            if (key.equals(temp.getNext().getKey())) {
                if (temp.getNext() != null) {
                    temp.setNext(temp.getNext().getNext());
                }

                size--;
                modCount--;

                return true;
            }
            temp = temp.getNext();
        }

        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
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
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
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
        V[] arrayValues;

        if (array.length < size) {
            arrayValues = (V[]) new Object[size];
        } else {
            arrayValues = (V[]) new Object[array.length];
        }

        int i = 0;
        for (V v : this) {
            arrayValues[i] = v;
            i++;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return arrayValues;
    }
}
