/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.redric.training.mymaps;

import java.util.Iterator;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static ua.redric.training.tests.helper.MyTestHelper.*;

/**
 *
 * @author Сергей
 */
public class MyTreeMapTest {

    MyTreeMap instance;

    @Before
    public void setUp() {
        instance = new MyTreeMap();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    //<editor-fold defaultstate="collapsed" desc="clear">
    /**
     * Test of clear method, of class MyHashMap.
     */
    @Test
    public void testClear_WithSomeValues_SizeZero() {
        putFourValues(instance);
        instance.clear();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of clear method, of class MyHashMap.
     */
    @Test
    public void testClear_WithNoValues_SizeZero() {
        instance.clear();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="containsKey">
    /**
     * Test of containsKey method, of class MyHashMap.
     */
    @Test
    public void testContainsKey_MapContainsKey_True() {
        String key = key2;
        putFourValues(instance);
        boolean expResult = true;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsKey method, of class MyHashMap.
     */
    @Test
    public void testContainsKey_MapNotContainsKey_False() {
        String key = "E";
        putFourValues(instance);
        boolean expResult = false;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsKey method, of class MyHashMap.
     */
    @Test
    public void testContainsKey_MapHasNoElements_False() {
        String key = "E";
        boolean expResult = false;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="containsValue">
    /**
     * Test of containsValue method, of class MyHashMap.
     */
    @Test
    public void testContainsValue_MapContainsValue_True() {
        Object value = val2;
        putFourValues(instance);
        boolean expResult = true;
        boolean result = instance.containsValue(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsValue method, of class MyHashMap.
     */
    @Test
    public void testContainsValue_MapDoesNotContainValue_False() {
        Object value = 5;
        putFourValues(instance);
        boolean expResult = false;
        boolean result = instance.containsValue(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsValue method, of class MyHashMap.
     */
    @Test
    public void testContainsValue_MapHasNoElements_False() {
        Object value = val2;
        boolean expResult = false;
        boolean result = instance.containsValue(value);
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get">
    /**
     * Test of get method, of class MyHashMap.
     */
    @Test
    public void testGet_MapHasKey() {
        String key = key2;
        Object expResult = val2;
        putFourValues(instance);
        Object result = instance.get(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class MyHashMap.
     */
    @Test
    public void testGet_MapHasNotKey_Null() {
        String key = "E";
        Object expResult = null;
        putFourValues(instance);
        Object result = instance.get(key);
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="isEmpty">
    /**
     * Test of isEmpty method, of class MyHashMap.
     */
    @Test
    public void testIsEmpty_WithSomeValues_False() {
        putFourValues(instance);
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class MyHashMap.
     */
    @Test
    public void testIsEmpty_WithNoValues_True() {
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="put">
    /**
     * Test of put method, of class MyHashMap.
     */
    @Test
    public void testPut_MapHasNotKey_SizeIncreased() {
        String key = "E";
        Object value = 5;
        putFourValues(instance);
        Object expResult = 5;
        instance.put(key, value);
        Object result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class MyHashMap.
     */
    @Test
    public void testPut_MapKey_SizeDoesNotChange() {
        String key = "A";
        Object value = 5;
        putFourValues(instance);
        Object expResult = 4;
        instance.put(key, value);
        Object result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class MyHashMap.
     */
    @Test
    public void testPut_MapHasNotKey_Null() {
        String key = "E";
        Object value = 5;
        putFourValues(instance);
        Object expResult = null;
        Object result = instance.put(key, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class MyHashMap.
     */
    @Test
    public void testPut_MapHasKey_OldValue() {
        String key = "A";
        Object value = 5;
        putFourValues(instance);
        Object expResult = val1;
        Object result = instance.put(key, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of put method, of class MyHashMap.
     */
    @Test
    public void testPut_EntriesSortedByKeys() {
        instance.put(key4, val4);
        instance.put(key2, val2);
        instance.put(key1, val1);
        instance.put(key3, val3);
        Iterator iter = instance.entryIterator();
        Object[] expected = new Object[]{ val1,val2,val3,val4};
        Object[] result = new Object[]{
            ((MyTreeMap.SimpleEntry)iter.next()).value,
             ((MyTreeMap.SimpleEntry)iter.next()).value,
              ((MyTreeMap.SimpleEntry)iter.next()).value,
               ((MyTreeMap.SimpleEntry)iter.next()).value
        };
        assertArrayEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="remove">
    /**
     * Test of remove method, of class MyHashMap.
     */
    @Test
    public void testRemove_MapHasNotKey_Null() {
        String key = "E";
        putFourValues(instance);
        Object expResult = null;
        Object result = instance.remove(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyHashMap.
     */
    @Test
    public void testRemove_MapHasKey_Value() {
        String key = key2;
        putFourValues(instance);
        Object expResult = val2;
        Object result = instance.remove(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyHashMap.
     */
    @Test
    public void testRemove_MapHasKey_SizeDecreased() {
        String key = key2;
        putFourValues(instance);
        Object expResult = 3;
        instance.remove(key);
        Object result = instance.size();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of remove method, of class MyHashMap.
     */
    @Test
    public void testRemove_EntriesSortedByKeys() {
        instance.put(key4, val4);
        instance.put(key2, val2);
        instance.put(key1, val1);
        instance.put(key3, val3);
        instance.remove(key1);
        Iterator iter = instance.entryIterator();
        Object[] expected = new Object[]{ val2,val3,val4};
        Object[] result = new Object[]{
            ((MyTreeMap.SimpleEntry)iter.next()).value,
             ((MyTreeMap.SimpleEntry)iter.next()).value,
              ((MyTreeMap.SimpleEntry)iter.next()).value,
        };
        assertArrayEquals(expected, result);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="size">
    /**
     * Test of size method, of class MyHashMap.
     */
    @Test
    public void testSize_WithSomeValues() {
        putFourValues(instance);
        int expResult = 4;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class MyHashMap.
     */
    @Test
    public void testSize_WithNoValues() {
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="entryIterator">
    /**
     * Test of iterator method, of class MyHashMap.
     */
    @Test
    public void testIterator_WithNoElement_HasNextFalse() {
        boolean expResult = false;
        Iterator iter = instance.entryIterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);

    }

    /**
     * Test of iterator method, of class MyHashMap.
     */
    @Test
    public void testIterator_WithSomeElements_HasNextTrue() {
        putFourValues(instance);
        boolean expResult = true;
        Iterator iter = instance.entryIterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class MyHashMap.
     */
    @Test
    public void testIterator_WithOneElement_next() {
        instance.put(key1, val1);
        int expResult = val1;
        Iterator iter = instance.entryIterator();
        int result = (int) ((MyTreeMap.SimpleEntry) iter.next()).value;
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class MyHashMap.
     */
    @Test
    public void testIterator_WithNoElements_next() {
        Object expResult = null;
        Iterator iter = instance.entryIterator();
        Object result = iter.next();
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
