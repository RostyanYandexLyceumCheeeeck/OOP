package ru.nsu.syspro.zagitov.hashtable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * Class represent generic hashtable.
 *
 * @param <K> the type of keys.
 * @param <V> the type of values.
 */
public class HashTable<K, V> implements Iterable<HashTable.Entry<K, V>>  {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private LinkedList<Entry<K, V>>[] table;
    private int size = 0;

    /**
     * The default constructor.
     */
    public HashTable() {
        table = new LinkedList[INITIAL_CAPACITY];
    }

    /**
     * An auxiliary class for representing an element of a hash table.
     *
     * @param <K> the type key.
     * @param <V> the type value.
     */
    public static class Entry<K, V> {
        K key;
        V value;

        /**
         * The default constructor.
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key + ":" + value + ")";
        }
    }

    /**
     * Checking the emptiness of the hashtable.
     *
     * @return {@code true} if hashtable is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns size hashtable.
     *
     * @return size hashtable.
     */
    public int size() {
        return size;
    }

    /**
     * Key-value insertion.
     *
     * @param key the key.
     * @param value the value.
     * @throws IllegalArgumentException if given (key or value) is null.
     */
    public void put(K key, V value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;

        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    /**
     *
     *
     * @param key the key.
     * @return {@code null} if not found else value by key.
     * @throws IllegalArgumentException if given key is null.
     */
    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    /**
     * Deleting an item by key.
     *
     * @param key the key.
     * @throws NoSuchElementException if key not found in hashtable.
     * @throws IllegalArgumentException if given key is null.
     */
    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] == null) {
            throw new NoSuchElementException(key + " is not in the HashTable");
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                table[index].remove(entry);
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                size--;
                return;
            }
        }
        throw new NoSuchElementException(key + " is not in the HashTable");
    }

    /**
     * Getting the index for the key in the hash table.
     *
     * @param key the key.
     * @return index in hashtable.
     * @throws IllegalArgumentException if given key is null.
     */
    private int getIndex(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return Math.abs(key.hashCode() % table.length);
    }

    /**
     * Increasing the size of the hash table.
     */
    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HashTable<K, V> other = (HashTable) obj;
        if (size != other.size) {
            return false;
        }
        for (Entry<K, V> entry : this) {
            if (other.get(entry.key) != entry.value) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{\n");
        Entry<K, V> previous = null;
        for (Entry<K, V> entry : this) {
            if (previous != null && getIndex(previous.key) != getIndex(entry.key)) {
                str.append("\n");
                previous = null;
            }
            if (previous != null) {
                str.append(", ");
            }
            str.append(entry);
            previous = entry;
        }
        str.append("\n}");
        return str.toString();
    }

    /**
     * Returns an iterator of the elements in the hashtable.
     *
     * @return an iterator of the elements in the hashtable.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new IteratorHashTable();
    }

    /**
     * Constructs an iterator over the hashtable.
     */
    private class IteratorHashTable implements Iterator<Entry<K, V>> {
        private final int frozenSize = size;
        private int targetIndex = 0;
        private int iterIndex = 0;

        @Override
        public boolean hasNext() {
            if (frozenSize != size) {
                throw new ConcurrentModificationException();
            }
            while (iterIndex < table.length &&
                    (table[iterIndex] == null || table[iterIndex].isEmpty())) {
                iterIndex++;
                }
            return iterIndex < table.length;
        }

        @Override
        public Entry<K, V> next() {
            if (frozenSize != size) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Entry<K, V> result = table[iterIndex].get(targetIndex);
            targetIndex++;

            if (targetIndex >= table[iterIndex].size()) {
                targetIndex = 0;
                iterIndex++;
            }
            return result;
        }
    }
}