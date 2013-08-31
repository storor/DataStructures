package ua.epam.training.mymaps;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Associated map based on hash table 
 * @author Serhii_Tolok
 */
public class MyHashMap<K, V> implements MyMap<K,V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //Factor which in result of multiplication on the lenght of hash table gives
    //threshold
    private float loadFactor;
    //Real number of elements
    private int size;
    //Number of element after which capacity of hash table will increase
    private int threshold;
    //Array of chais of elements places by hash code
    private SimpleEntry<K, V>[] hashTable;
    
    /**
     * Entry of map
     * @param <K>Type of key
     * @param <V>Type of value
     */
    static class SimpleEntry<K, V> implements MyMap.Entry<K, V> {

        final K key;
        V value;
        SimpleEntry<K, V> next;
        int hash;

        /**
         * Creates new entry.
         */
        SimpleEntry(int hash, K key, V value, SimpleEntry<K, V> next) {
            this.value = value;
            this.next = next;
            this.key = key;
            this.hash = hash;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = (V) value;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof MyMap.Entry)) {
                return false;
            }
            MyMap.Entry e = (MyMap.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public final int hashCode() {
            return (key == null ? 0 : key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }

        @Override
        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }


    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (loadFactor <= 0||initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        threshold = Math.round(initialCapacity * loadFactor);
        hashTable = new SimpleEntry[initialCapacity];
    }

    /**
     * Calculate the index in the hashTable for special key
     */
    private int indexFor(int hash, int newCapacity) {
        return hash & (newCapacity - 1);
    }

    private void addEntry(int hash, K key, V value, int index) {
        if (size >= threshold) {
            resize(2 * hashTable.length);
            index = indexFor(hash, hashTable.length);
        }
        createEntry(hash, key, value, index);
    }

    private void createEntry(int hash, K key, V value, int index) {
        size++;
        SimpleEntry<K, V> prevEntry = hashTable[index];
        hashTable[index] = new SimpleEntry<>(hash, key, value, prevEntry);
    }

    /**
     * When elements overloaded we need to ensure hashTable
     * @param newCapacity 
     */
    private void resize(int newCapacity) {
        transfer(newCapacity);
        threshold = (int) (newCapacity * loadFactor);
    }

    private void transfer(int newCapacity) {
        SimpleEntry<K,V>[] newTable = new SimpleEntry[newCapacity];
        int i;
        SimpleEntry<K, V> next;
        for (SimpleEntry<K, V> entry : hashTable) {
            while (null != entry) {
                next = entry.next;
                i = indexFor(entry.hash, newCapacity);
                entry.next = newTable[i];
                newTable[i] = entry;
                entry = next;
            }
        }
        hashTable = newTable;
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size =0;
    }

    @Override
    public boolean containsKey(K key) {
        SimpleEntry entry = hashTable[indexFor(hash(key.hashCode()),
                hashTable.length)];
        if (entry != null) {
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        Iterator iterator = entryIterator();
        while (iterator.hasNext()) {
            if (((SimpleEntry<K,V>)iterator.next()).value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        SimpleEntry<K,V> entry = hashTable[indexFor(hash(key.hashCode()),
                hashTable.length)];
        if (entry != null) {
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        if (key != null) {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, hashTable.length);
            SimpleEntry<K,V> entry = hashTable[index];
            if (entry != null) {//Check out if there already is an entry with
                                //the same key
                while (entry != null) {
                    if (entry.hash == hash && (entry.key == key || key.equals(entry.key))) {
                        V oldValue = (V) entry.value;
                        entry.value = value;
                        return oldValue;
                    }
                    entry = entry.next;
                }
            }
            addEntry(hash, (K) key, (V) value, index);
        }

        return null;
    }

    /**
     * Standart algorithm for calculating hash of key 
     * @param h hashCode of key
     * @return 
     */
    private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    public V remove(K key) {
        int index = indexFor(hash(key.hashCode()),hashTable.length);
        SimpleEntry<K,V> entry = hashTable[index];
        SimpleEntry<K,V> prev = null;
        SimpleEntry<K,V> result;
        if (entry != null) {
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    result = entry;
                    if (prev == null) {
                        hashTable[index] = entry.next;
                    }else{
                        prev.next = entry.next;
                    }
                    size--;
                    return result.value;
                }
                prev = entry;
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator entryIterator() {

        class EntryIterator implements Iterator {

            SimpleEntry<K,V> next;
            SimpleEntry<K,V> currentEntry;
            int currentIndex;

            EntryIterator() {
                for (currentIndex = 0; currentIndex < hashTable.length; currentIndex++) {
                    if ((next = hashTable[currentIndex]) != null) {
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public SimpleEntry<K,V> next() {
                return getNext();
            }

            @Override
            public void remove() {
                if(currentEntry!=null){
                    MyHashMap.this.remove(currentEntry.key);
                }
            }

            private SimpleEntry getNext() {
                if (next != null) {
                    currentEntry = next;
                    if (next.next == null) {
                        if (currentIndex == hashTable.length - 1) {
                            next = null;
                        } else {
                            //Look through the hashTable
                            for (int i = currentIndex + 1; i < hashTable.length; i++) {
                                if ((next = hashTable[i]) != null) {
                                    currentIndex = i;
                                    break;
                                }
                            }
                        }
                    } else {
                        next = next.next;
                    }
                    return currentEntry;
                } else {
                    return null;
                }
            }
        }
        return new EntryIterator();
    }
}
