package ru.nsu.syspro.zagitov.hashtable;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.zagitov.hashtable.HashTable.Entry;

public class TestHashTable {

    @Test
    public void testPut() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        Assertions.assertEquals(1, hashTable.get("A"));
        Assertions.assertEquals(2, hashTable.get("B"));
        Assertions.assertEquals(3, hashTable.get("C"));
    }

    @Test
    public void testRemove() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        hashTable.remove("A");
        Assertions.assertNull(hashTable.get("A"));
        hashTable.remove("B");
        Assertions.assertNull(hashTable.get("B"));
        hashTable.remove("C");
        Assertions.assertNull(hashTable.get("C"));
    }

    @Test
    public void testIsEmpty() {
        HashTable<String, Number> hashTable = new HashTable<>();
        Assertions.assertTrue(hashTable.isEmpty());

        hashTable.put("A", 1);
        Assertions.assertFalse(hashTable.isEmpty());

        hashTable.remove("A");
        Assertions.assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testSize() {
        HashTable<String, Number> hashTable = new HashTable<>();
        Assertions.assertEquals(0, hashTable.size());

        hashTable.put("A", 1);
        Assertions.assertEquals(1, hashTable.size());

        hashTable.remove("A");
        Assertions.assertEquals(0, hashTable.size());
    }

    @Test
    public void testUpdate() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        Assertions.assertEquals(1, hashTable.get("A"));
        hashTable.put("A", 3);
        Assertions.assertEquals(3, hashTable.get("A"));
    }

    @Test
    public void testIterator() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        Iterator<Entry<String, Number>> iterator = hashTable.iterator();
        Assertions.assertTrue(iterator.hasNext());

        iterator.next();
        Assertions.assertTrue(iterator.hasNext());

        iterator.next();
        Assertions.assertTrue(iterator.hasNext());

        iterator.next();
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNoSuchElementException() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);

        Iterator<Entry<String, Number>> iterator = hashTable.iterator();
        iterator.next();
        iterator.next();
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorConcurrentModificationException() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);

        Iterator<Entry<String, Number>> iterator = hashTable.iterator();
        hashTable.remove("A");
        Assertions.assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testResize() {
        HashTable<String, Number> hashTable = new HashTable<>();
        for (int i = 1; i < 30 * 7; i+=7) {
            hashTable.put("1000 - " + i + "?", 1000 - i);
        }
        Assertions.assertEquals(30, hashTable.size());
        for (int i = 1; i < 30 * 7; i+=7) {
            Assertions.assertEquals(1000 - i, hashTable.get("1000 - " + i + "?"));
        }
    }

    @Test
    public void testEquals() {
        HashTable<String, Number> firstHashTable = new HashTable<>();
        HashTable<String, Number> secondHashTable = new HashTable<>();
        firstHashTable.put("A", 1);
        firstHashTable.put("B", 2);
        firstHashTable.put("C", 3);
        secondHashTable.put("A", 1);
        secondHashTable.put("B", 2);
        secondHashTable.put("C", 3);

        Assertions.assertEquals(firstHashTable, secondHashTable);
        secondHashTable.remove("A");
        Assertions.assertNotEquals(firstHashTable, secondHashTable);
    }

    @Test
    public void testToString() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        String strHashTable = hashTable.toString();
        strHashTable = strHashTable.substring(2, strHashTable.length() - 1).replace("\n", ", ");
        String[] arr = strHashTable.split(", ");
        Arrays.sort(arr);

        String[] expected = new String[]{"(A:1)", "(B:2)", "(C:3)"};
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void testIllegalArgumentException() {
        HashTable<String, Number> hashTable = new HashTable<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> hashTable.put(null, null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> hashTable.remove(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> hashTable.get(null));
    }
}
