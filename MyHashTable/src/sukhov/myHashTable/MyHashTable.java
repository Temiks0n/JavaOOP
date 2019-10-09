package sukhov.myHashTable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<List<T>> table;
    private int size;
    private int length;
    private int modCount;

    private class HashTableIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountIterator = modCount;
        private int arrayIndex;
        private int linkIndex;

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
                if (table.get(arrayIndex) != null) {
                    if (linkIndex == table.get(arrayIndex).size()) {
                        linkIndex = 0;
                    }

                    T data = table.get(arrayIndex).get(linkIndex);
                    linkIndex++;

                    if (linkIndex == table.get(arrayIndex).size()) {
                        linkIndex = 0;
                        arrayIndex++;
                    }

                    return data;
                }
                arrayIndex++;
            }

            return null;
        }
    }

    public MyHashTable() {
        table = new ArrayList<>(20);
        length = 20;
        for (int i = 0; i < length; i++) {
            table.add(i, null);
        }
    }

    public MyHashTable(int length) {
        if (length < 0) {
            throw new IndexOutOfBoundsException("Длина таблицы не может быть меньше нуля");
        }

        table = new ArrayList<>(length);
        this.length = length;

        for (int i = 0; i < length; i++) {
            table.add(i, null);
        }

    }

    public int size() {
        return size;
    }

    private int getHash(T data) {
        return Math.abs(data.hashCode() % length);
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

        if (table.get(hash) == null) {
            List<T> list = new LinkedList<>();
            list.add(data);

            table.remove(hash);
            table.add(hash, list);
        } else {
            for (T d : table.get(hash)) {
                if (d.equals(data)) {
                    return false;
                }
            }
            table.get(hash).add(data);
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

        if (table.get(hash) == null) {
            return false;
        }

        if (table.get(hash).remove(data)) {
            size--;

            return true;
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
        table = new ArrayList<>(20);
        size = 0;
        modCount = 0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        boolean isRetainAll = false;
        boolean isChange = true;

        while (isChange) {
            Iterator<T> iterator = iterator();
            isChange = false;
            while (iterator.hasNext()) {
                T data = iterator.next();
                if (!collection.contains(data)) {
                    remove(data);
                    isRetainAll = true;
                    isChange = true;
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
