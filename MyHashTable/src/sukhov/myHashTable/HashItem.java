package sukhov.myHashTable;

public class HashItem<K, V> {
    private int hash;
    private K key;
    private V value;
    private HashItem<K, V> next;

    public HashItem(K key, V value, int hash) {
        this.key = key;
        this.value = value;
        this.hash = hash;
    }

    public HashItem<K, V> getNext() {
        return next;
    }

    public void setNext(HashItem<K, V> next) {
        this.next = next;
    }

    public int getHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
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
        HashItem<K, V> k = (HashItem<K, V>) o;
        return k.hash == hash && k.key == key && k.value == value;
    }
}
