package sukhov.myHashTable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private List<T>[] table;
    private int size;
    private int modCount;

    private class HashTableIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountIterator = modCount;
        private int arrayIndex;
        private int linkIndex;
        private T data;
        private boolean isChange = false;

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

            isChange = false;
            currentIndex++;

            while (currentIndex < size) {
                if (table[arrayIndex] != null && table[arrayIndex].size() != 0) {
                    if (linkIndex == table[arrayIndex].size()) {
                        linkIndex = 0;
                    }

                    data = table[arrayIndex].get(linkIndex);
                    linkIndex++;

                    if (linkIndex == table[arrayIndex].size()) {
                        linkIndex = 0;
                        arrayIndex++;
                    }

                    return data;
                }
                arrayIndex++;
            }

            return null;
        }

        @Override
        public void remove() {
            if (modCountIterator != modCount) {
                throw new ConcurrentModificationException("Были изменения");
            }

            if (isChange) {
                if (hasNext()) {
                    next();
                }
                throw new NoSuchElementException("Таблица пустая");
            }

            if (linkIndex <= 0) {
                arrayIndex--;
                linkIndex = table[arrayIndex].size() - 1;
            } else {
                linkIndex--;
            }

            table[arrayIndex].remove(linkIndex);

            isChange = true;
            size--;
        }
    }

    public MyHashTable() {
        //noinspection unchecked
        table = new List[20];
    }

    public MyHashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина таблицы должна быть больше нуля");
        }

        //noinspection unchecked
        table = new List[length];
    }

    public int size() {
        return size;
    }

    private int getHash(Object data) {
        if (data == null) {
            return 0;
        }

        return Math.abs(data.hashCode() % table.length);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object data) {
        int index = getHash(data);

        if (table[index] == null) {
            return false;
        }

        return table[index].contains(data);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arrayData = new Object[size];
        int i = 0;
        for (T data : this) {
            arrayData[i] = data;
            i++;
        }

        return arrayData;
    }

    @Override
    public boolean add(T data) {
        int hash = getHash(data);

        if (table[hash] != null) {
            table[hash].add(data);
        } else {
            List<T> list = new LinkedList<>();
            list.add(data);

            table[hash] = list;
        }

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object data) {
        int hash = getHash(data);

        if (table[hash] == null) {
            return false;
        }

        if (table[hash].remove(data)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean isAddAll = false;
        for (T data : collection) {
            add(data);
            isAddAll = true;
        }

        return isAddAll;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        table = new List[20];
        size = 0;
        modCount++;
    }

    private boolean removeCollection(Collection<?> collection, boolean removeAll) {
        boolean removeCollection = false;
        boolean isChange = true;

        while (isChange) {
            Iterator<T> iterator = iterator();
            isChange = false;
            while (iterator.hasNext()) {
                T data = iterator.next();
                if (collection.contains(data) == removeAll) {
                    iterator.remove();
                    removeCollection = true;
                    isChange = true;
                }
            }
        }

        return removeCollection;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return removeCollection(collection, false);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return removeCollection(collection, true);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object data : collection) {
            if (!contains(data)) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Object[] array) {
        T[] arrayData = (T[]) array;

        if (array.length > size) {
            arrayData = (T[]) Arrays.copyOf(array, array.length);
        }

        int i = 0;
        for (T v : this) {
            arrayData[i] = v;
            i++;
        }

        if (arrayData.length > size) {
            arrayData[size] = null;
        }

        return arrayData;
    }
}
