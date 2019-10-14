package sukhov.myArrayList;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int modCountIterator = modCount;

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
            return items[currentIndex];
        }

        public void remove() {
            if (modCountIterator != modCount) {
                throw new ConcurrentModificationException("Были изменения");
            }

            if (currentIndex == -1) {
                throw new IllegalStateException("Метод next не использовался");
            }

            System.arraycopy(items, currentIndex + 1, items, currentIndex, size - currentIndex - 1);

            currentIndex--;
            size--;
            modCountIterator++;
            modCount++;
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
        if (items.length < length) {
            items = Arrays.copyOf(items, length);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
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
        return indexOf(data) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <S> S[] toArray(S[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (S[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T data) {
        if (size >= items.length) {
            increaseItems();
        }

        items[size] = data;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object data) {
        int i = indexOf(data);

        if (i >= 0) {
            remove(i);
            return true;
        }

        return false;
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

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (size + collection.size() >= items.length) {
            ensureCapacity((items.length + collection.size()) * 2);
        }

        boolean isAddAll = false;

        for (T data : collection) {
            items[size] = data;

            isAddAll = true;
        }
        size += collection.size();
        modCount = isAddAll ? modCount + 1 : modCount;

        return isAddAll;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Неверный индекс массива");
        }

        if (size + collection.size() >= items.length) {
            ensureCapacity(size + collection.size() * 2);
        }

        System.arraycopy(items, index, items, collection.size() + index, size - index);
        boolean isAddAll = false;

        int i = index;
        for (T data : collection) {
            items[i] = data;
            i++;

            isAddAll = true;
        }
        size += collection.size();
        modCount = isAddAll ? modCount + 1 : modCount;

        return isAddAll;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isRemoveAll = false;
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
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
        modCount++;
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

        T changedElement = items[index];
        items[index] = element;

        return changedElement;
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
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Неверный индекс массива");
        }

        T element = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        size--;
        modCount++;

        return element;
    }

    @Override
    public int indexOf(Object data) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object data) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(data, items[i])) {
                return i;
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
