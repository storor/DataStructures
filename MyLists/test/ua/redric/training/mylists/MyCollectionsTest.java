/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.redric.training.mylists;

import java.util.Comparator;
import java.util.RandomAccess;
import static org.junit.Assert.*;
import org.junit.Test;
import static ua.redric.training.tests.helper.MyTestHelper.*;

/**
 * Test methods of MyCollections class
 * @author Сергей
 */
public class MyCollectionsTest {

    public MyCollectionsTest() {
    }

    //<editor-fold defaultstate="collapsed" desc="sort">
    /**
     * Test of sort method, of class MyCollections.
     */
    @Test(expected = NullPointerException.class)
    public void testSort_MyListAs1ParamNull_NullPointerExceptionThrown() {
        MyList<? extends Comparable> list = null;
        MyCollections.sort(list);
    }

    /**
     * Test of sort method, of class MyCollections.
     */
    @Test(expected = NullPointerException.class)
    public void testSort_ComparatorAs2ParamIsNull_NullPointerExceptionThrown() {
        MyList list = new MyArrayList();
        Comparator c = null;
        MyCollections.sort(list, c);
    }

    @Test
    public void testSort_NormalParametersWithoutComparator_SortedArray() {
        MyList list = new MyArrayList();
        list.add(val2);
        list.add(val1);
        list.add(val4);
        list.add(val3);
        MyCollections.sort(list);

        Object[] expArr = new Object[]{val1, val2, val3, val4};
        Object[] actualArr = list.toArray();
        assertArrayEquals(expArr, actualArr);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="swap">
    /**
     * Test of swap method, of class MyCollections.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSwap_IndexAs2ParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        MyList list = new MyArrayList();
        addFourValues(list);
        int i = 5;
        int j = 0;
        MyCollections.swap(list, i, j);
    }

    /**
     * Test of swap method, of class MyCollections.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSwap_IndexAs3ParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        MyList list = new MyArrayList();
        addFourValues(list);
        int i = 0;
        int j = 5;
        MyCollections.swap(list, i, j);
    }

    /**
     * Test of swap method, of class MyCollections.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSwap_IndexAs2ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        MyList list = new MyArrayList();
        addFourValues(list);
        int i = -1;
        int j = 0;
        MyCollections.swap(list, i, j);
    }

    /**
     * Test of swap method, of class MyCollections.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSwap_IndexAs3ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        MyList list = new MyArrayList();
        addFourValues(list);
        int i = 0;
        int j = -1;
        MyCollections.swap(list, i, j);
    }

    /**
     * Test of swap method, of class MyCollections.
     */
    @Test
    public void testSwap_NormalParams_SwapElements() {
        MyList list = new MyArrayList();
        addFourValues(list);
        int i = 0;
        int j = 2;
        MyCollections.swap(list, i, j);
        Object[] expArr = new Object[]{val3, val2, val1, val4};
        Object[] actualArr = list.toArray();
        assertArrayEquals(expArr, actualArr);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="copy">
    /**
     * Test of copy method, of class MyCollections.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testCopy_DestSizeLessThenSrcSize_MyIndexOutOfBoundsExceptionThrown() {
        MyList dest = new MyArrayList();
        MyList src = new MyArrayList();
        addFourValues(src);
        addFourValues(src);
        addFourValues(dest);
        MyCollections.copy(dest, src);
    }

    /**
     * Test of copy method, of class MyCollections.
     */
    @Test
    public void testCopy_EquelSizes_EquelArrays() {
        MyList dest = new MyArrayList();
        MyList src = new MyArrayList();
        addFourValues(src);
        addFourValues(dest);
        MyCollections.copy(dest, src);
        Object[] expArr = src.toArray();
        Object[] actualArr = dest.toArray();
        assertArrayEquals(expArr, actualArr);
    }

    /**
     * Test of copy method, of class MyCollections.
     */
    @Test
    public void testCopy_DestSizeLargerThenSrcSize_FirstPartOfArraysEquel() {
        MyList dest = new MyArrayList();
        MyList src = new MyArrayList();
        addFourValues(src);
        addFourValues(dest);
        addFourValues(dest);
        MyCollections.copy(dest, src);
        Object[] expArr = src.toArray();
        Object[] actualArr = new Object[src.size()];
        System.arraycopy(dest.toArray(), 0, actualArr, 0, src.size());
        assertArrayEquals(expArr, actualArr);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="reverse">
    /**
     * Test of reverse method, of class MyCollections.
     */
    @Test(expected = NullPointerException.class)
    public void testReverse_MyListAs1ParamNull_NullPointerExceptionThrown() {
        MyList list = null;
        MyCollections.reverse(list);
    }

    /**
     * Test of reverse method, of class MyCollections.
     */
    @Test
    public void testReverse_MyListHasOneElement_SameList() {
        MyList list = new MyArrayList();
        list.add(val1);
        MyCollections.reverse(list);
        Object[] expecteds = new Object[]{val1};
        Object[] actuals = list.toArray();
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of reverse method, of class MyCollections.
     */
    @Test
    public void testReverse_MyListWithSomeElements_ReversedList() {
        MyList list = new MyArrayList();
        addFourValues(list);
        MyCollections.reverse(list);
        Object[] expecteds = new Object[]{val4, val3, val2, val1};
        Object[] actuals = list.toArray();
        assertArrayEquals(expecteds, actuals);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="binarySearch">
    /**
     * Test of binarySearch method, of class MyCollections.
     */
    @Test
    public void testBinarySearch_ListHasKey_IndexOfKey() {
        RandomAccess list = new MyArrayList();
        Object key = val2;
        addFourValues((MyArrayList)list);
        int expResult = 1;
        int result = MyCollections.binarySearch(list, key);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of binarySearch method, of class MyCollections.
     */
    @Test
    public void testBinarySearch_ListHasNotKey_PossibleIndexOfKey() {
        RandomAccess list = new MyArrayList();
        Object key = 5;
        addFourValues((MyArrayList)list);
        ((MyArrayList)list).add(6);
        int expResult = -4-1;
        int result = MyCollections.binarySearch(list, key);
        assertEquals(expResult, result);
    }
    //</editor-fold>
}
