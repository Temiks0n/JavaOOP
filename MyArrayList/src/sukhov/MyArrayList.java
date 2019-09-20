package sukhov;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            currentIndex++;
            return items[currentIndex];
        }

        public void remove() {
            if (currentIndex == -1) {
                throw new IllegalCallerException("Методы hasNext/next не использовались");
            }

            System.arraycopy(items, currentIndex + 1, items, currentIndex, size - currentIndex);
            currentIndex--;
        }

        public void add() {
            if (currentIndex == -1) {
                throw new IllegalCallerException("Методы hasNext/next не использовались");
            }

            System.arraycopy(items, currentIndex, items, currentIndex + 1, size - currentIndex);
            currentIndex--;
        }
    }

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public MyArrayList(int length) {
        if (length < 0) {
            throw new IndexOutOfBoundsException("Длина массива не может быть меньше нуля");
        }

        //noinspection unchecked
        items = (T[]) new Object[length];
    }

    private void increaseItems() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (T[]) new Object[10];
            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int length) {
        if (length < size) {
            return;
        }

        items = Arrays.copyOf(items, length);
    }

    public void trimToSize() {
        if (items.length == size) {
            return;
        }

        ensureCapacity(size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object data) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(data, items[index])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size, items.getClass());
    }

    @Override
    public <S> S[] toArray(S[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (S[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }

    @Override
    public boolean add(T data) {
        if (size >= items.length) {
            increaseItems();
        }

        items[size] = data;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object data) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(data, items[index])) {
                System.arraycopy(items, index + 1, items, index, size);
                size--;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (T data : this) {
            if (collection.contains(data)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        while (size + collection.size() >= items.length) {
            increaseItems();
        }

        //noinspection unchecked
        System.arraycopy((T[]) collection.toArray(), 0, items, size, collection.size());

        size += collection.size();

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        while (size + collection.size() >= items.length) {
            increaseItems();
        }

        //noinspection unchecked
        System.arraycopy((T[]) collection.toArray(), 0, items, index, collection.size());

        size += collection.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isRemoveAll = false;
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
                size--;
                isRemoveAll = true;
            }
        }

        return isRemoveAll;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isRetainAll = false;
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                size--;
                isRetainAll = true;
            }
        }

        return isRetainAll;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        items = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс массива");
        }

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс массива");
        }

        return items[index] = element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Неверный индекс массива");
        }

        if (size >= items.length) {
            increaseItems();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Неверный индекс массива");
        }

        T element = items[index];
        System.arraycopy(items, index + 1, items, index, size - index);
        size--;

        return element;
    }

    @Override
    public int indexOf(Object data) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(data, items[index])) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object data) {
        for (int index = size - 1; index >= 0; index--) {
            if (Objects.equals(data, items[index])) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}
