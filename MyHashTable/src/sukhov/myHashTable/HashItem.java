package sukhov.myHashTable;

public class HashItem<T> {
    private int hash;
    private T data;
    private HashItem<T> next;

    public HashItem(T data, int hash) {
        this.data = data;
        this.hash = hash;
    }

    public HashItem<T> getNext() {
        return next;
    }

    public void setNext(HashItem<T> next) {
        this.next = next;
    }

    public int getHash() {
        return hash;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        HashItem<T> d = (HashItem<T>) o;
        return d.hash == hash && d.data == data;
    }
}
