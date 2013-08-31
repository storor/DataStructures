package ua.epam.training.mylists;

import java.util.Iterator;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static ua.epam.training.tests.helper.MyTestHelper.*;

/**
 * Test methods of MyArrayList class
 * @author Serhii_Tolok
 */
public class MyArrayListTest {
    
    MyArrayList instance;

    
    public MyArrayListTest() {
    }
    
    
    @Before
    public void setUp() {
        instance = new MyArrayList();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
     
    //<editor-fold defaultstate="collapsed" desc="constructor">
    /**
     * Test of constructor with parameter, of class MyArrayList.
     */
    @Test
    public void testConstructor_ListAs1ParamHasSomeElements_SizeEquels() {
        MyList someList = new MyLinkedList();
        addFourValues(someList);
        instance = new MyArrayList(someList);
        int expResult = 4;
        int result = instance.size();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of constructor with parameter, of class MyArrayList.
     */
    @Test
    public void testConstructor_ListAs1ParamHasSomeElements_ElementsEquels() {
        MyList someList = new MyLinkedList();
        addFourValues(someList);
        instance = new MyArrayList(someList);
        Object[] expResult = someList.toArray();
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);

    }
    
    
    /**
     * Test of constructor with parameter, of class MyArrayList.
     */
    @Test
    public void testConstructor_ListAs1ParamNull_SizeZero() {
        MyList someList = null;
        instance = new MyArrayList(someList);
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ensureCapacity">
    /**
     * Test of ensureCapacity method, of class MyArrayList.
     */
    @Test
    public void testEnsureCapacity_WithNoParameterLessThenCapacitu_OldCpacity() {
        int newLength = 20;
        instance = new MyArrayList(20);
        instance.ensureCapacity(newLength - 1);
        int expResult = newLength;
        int result = instance.dataArray.length;
        assertEquals(expResult, result);
    }

    /**
     * Test of ensureCapacity method, of class MyArrayList.
     */
    @Test
    public void testEnsureCapacity() {
        instance = new MyArrayList(MyArrayList.MIN_CAPACITY);
        int newLength = MyArrayList.MIN_CAPACITY + 1;
        instance.ensureCapacity(newLength);
        int expResult = newLength * 3 / 2 + 1;
        int result = instance.dataArray.length;
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="trimToSize">
    /**
     * Test of trimToSize method, of class MyArrayList.
     */
    @Test
    public void testTrimToSize_WithSizeLessThenMinCapacity_NewCapacityEquelsMinCapacity() {
        addFourValues(instance);
        instance.trimToSize();
        int expValue = MyArrayList.MIN_CAPACITY;
        int result = instance.dataArray.length;
        assertEquals(expValue, result);
    }

    @Test
    public void testTrimToSize_WhithSizeLargerThenMinCapacity_NewCapacityEquelsSize() {
        addFourValues(instance);
        addFourValues(instance);
        instance.trimToSize();
        int expValue = 8;
        int result = instance.dataArray.length;
        assertEquals(expValue, result);
    }
    
    @Test
    public void testTrimToSize_WhithSizeEquelsLength_NewCapacityEquelsSize() {
        addFourValues(instance);
        addFourValues(instance);
        instance.trimToSize();
        int expValue = 8;
        instance.trimToSize();
        int result = instance.dataArray.length;
        assertEquals(expValue, result);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="add">
    /**
     * Test of add method, of class MyArrayList.
     */
    @Test
    public void testAdd_AddInEmptyArray() {
        Object e = val1;
        instance.add(e);
        Object[] expected = new Object[]{val1};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of add method, of class MyArrayList.
     */
    @Test
    public void testAdd_AddInNotEmptyArray() {
        Object e = val1;
        addFourValues(instance);
        instance.add(e);
        Object[] expected = new Object[]{val1, val2, val3, val4, val1};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }
    
    /**
     * Test of add method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAdd_IndexAs1ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        Object e = val1;
        addFourValues(instance);
        instance.add(-1,e);
    }
    
    /**
     * Test of add method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAdd_IndexAs1ParamBiggerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        Object e = val1;
        addFourValues(instance);
        instance.add(6,e);
    }
    
    /**
     * Test of add method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAdd_IndexAs1ParamEquelSize_MyIndexOutOfBoundsExceptionThrown() {
        Object e = val1;
        addFourValues(instance);
        instance.add(6,e);
    }
    
    /**
     * Test of add method, of class MyArrayList.
     */
    @Test
    public void testAdd_IndexAs1ParamValid_ReplaceValues() {
        Object e = val1;
        addFourValues(instance);
        instance.add(1,e);
        Object[]expected = new Object[]{val1,val1,val3,val4};
        Object[]actual = instance.toArray();
        assertArrayEquals(expected, actual);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addAll">
    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test
    public void testAddAll_AfterLastElement() {
        Object[] arr = new Object[]{val2, val3, val4};
        instance.add(val1);
        instance.addAll(arr);
        Object[] expected = new Object[]{val1, val2, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test
    public void testAddAll_InEmptyArray() {
        Object[] arr = new Object[]{val2, val3, val4};
        instance.addAll(arr);
        Object[] expected = arr;
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test
    public void testAddAll_WhenParameterIsEmptyArray_SameArray() {
        addFourValues(instance);
        Object[] expected = instance.toArray();
        instance.addAll(null);
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAddAll_IndexAs1ParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        Object[] arr = new Object[]{1, 2, 3};
        instance.addAll(index, arr);
    }
    
    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAddAll_IndexAs1ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        Object[] arr = new Object[]{1, 2, 3};
        instance.addAll(index, arr);
    }

    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test
    public void testAddAll_ZeroIndex_AddInBeginningOfArray() {
        int index = 0;
        addFourValues(instance);
        instance.addAll(index, new Object[]{val2, val3, val4});
        Object[] expected = new Object[]{val2, val3, val4, val1, val2, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyArrayList.
     */
    @Test
    public void testAddAll_SomeIndex_AddInOrderPlaceOfArray() {
        int index = 2;
        addFourValues(instance);
        instance.addAll(index, new Object[]{val1, val2, val3, val4});
        Object[] expected = new Object[]{val1, val2, val1, val2, val3, val4, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get">
    /**
     * Test of get method, of class MyArrayList.
     */
    @Test
    public void testGet_ValidParamIndex_ValueByIndex() {
        int index = 2;
        addFourValues(instance);
        Object expResult = val3;
        Object result = instance.get(index);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of get method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testGet_IndexAsParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        instance.get(index);
    }
    
    /**
     * Test of get method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testGet_IndexAsParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        instance.get(index);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="remove">
    /**
     * Test of remove method, of class MyArrayList.
     */
    @Test
    public void testRemove_ValidParamIndex_SizeDecrease() {
        int index = 2;
        addFourValues(instance);
        instance.remove(index);
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyArrayList.
     */
    @Test
    public void testRemove_ValidParamIndex_ValueByIndex() {
        int index = 2;
        addFourValues(instance);
        Object expResult = val3;
        Object result = instance.remove(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testRemove_IndexAs1ParamLessBiggerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        instance.remove(index);
    }
    
    /**
     * Test of remove method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testRemove_IndexAs1ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        instance.remove(index);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="clear">
    /**
     * Test of clear method, of class MyArrayList.
     */
    @Test
    public void testClear_WithSomeValues_NoElements() {
        addFourValues(instance);
        instance.clear();
        Object[] expecteds = new Object[0];
        Object[] actuals = instance.toArray();
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of clear method, of class MyArrayList.
     */
    @Test
    public void testClear_WithSomeValues_SizeZero() {
        addFourValues(instance);
        instance.clear();
        int expecteds = 0;
        int actuals = instance.size();
        assertEquals(expecteds, actuals);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="isEmpty">
    /**
     * Test of isEmpty method, of class MyArrayList.
     */
    @Test
    public void testIsEmpty_WithNoElements_True() {
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class MyArrayList.
     */
    @Test
    public void testIsEmpty_WithSomeElements_False() {
        addFourValues(instance);
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="set">
    /**
     * Test of set method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSet_IndexAsParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        instance.set(index, index);
    }
    
    /**
     * Test of set method, of class MyArrayList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSet_IndexAsParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        instance.set(index, index);
    }

    /**
     * Test of set method, of class MyArrayList.
     */
    @Test
    public void testSet_WithSomeValidParamIndex() {
        int index = 1;
        instance.add(val1);
        instance.add(val2);
        instance.add(val3);
        instance.set(index, val4);
        assertEquals(instance.get(index), val4);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="indexOf">
    /**
     * Test of indexOf method, of class MyArrayList.
     */
    @Test
    public void testIndexOf_ElementIsNotInTheArrayList_MinusOne() {
        Object o = val2;
        instance.add(val1);
        int expResult = -1;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of indexOf method, of class MyArrayList.
     */
    @Test
    public void testIndexOf_ElementIsInTheArrayList() {
        Object o = val2;
        instance.add(val1);
        instance.add(val2);
        int expResult = 1;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of indexOf method, of class MyArrayList.
     */
    @Test
    public void testIndexOf_ElementAs1ParamNullArrayContainsNull_IndexOfFirstNull() {
        Object o = null;
        instance.add(val1);
        instance.add(null);
        instance.add(val2);
        instance.add(null);
        int expResult = 1;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of indexOf method, of class MyArrayList.
     */
    @Test
    public void testIndexOf_ElementAs1ParamNullArrayDoesNotContainNull_MinusOne() {
        Object o = null;
        instance.add(val1);
        instance.add(val2);
        int expResult = -1;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="size">
    /**
     * Test of size method, of class MyArrayList.
     */
    @Test
    public void testSize_WithNoElements() {
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class MyArrayList.
     */
    @Test
    public void testSize_WithSomeElements() {
        instance.add(1);
        instance.add(2);

        int expResult = 2;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toArray">
    /**
     * Test of toArray method, of class MyArrayList.
     */
    @Test
    public void testToArray_WithSomeElements() {
        addFourValues(instance);
        Object[] expResult = new Object[]{val1, val2, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of toArray method, of class MyArrayList.
     */
    @Test
    public void testToArray_WithNoElements_EmtyArray() {
        Object[] expResult = new Object[0];
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="iterator">
    /**
     * Test of iterator method, of class MyArrayList.
     */
    @Test
    public void testIterator_WithNoElement_HasNextFalse() {
        boolean expResult = false;
        Iterator iter = instance.iterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);

    }

    /**
     * Test of iterator method, of class MyArrayList.
     */
    @Test
    public void testIterator_WithSomeElements_HasNextTrue() {
        addFourValues(instance);
        boolean expResult = true;
        Iterator iter = instance.iterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class MyArrayList.
     */
    @Test
    public void testIterator_WithSomeElements_next() {
        addFourValues(instance);
        int expResult = val1;
        Iterator iter = instance.iterator();
        int result = (int) iter.next();
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class MyArrayList.
     */
    @Test
    public void testIterator_WithNoElements_next() {
        Object expResult = null;
        Iterator iter = instance.iterator();
        Object result = iter.next();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of iterator method, of class MyArrayList.
     */
    @Test
    public void testIterator_WithSomeElements_Remove_SizeDecrised() {
        addFourValues(instance);
        Iterator iter = instance.iterator();
        iter.next();
        iter.remove();
        int expResult = 3;
        int result = instance.size;
        assertEquals(expResult, result);
    }
    //</editor-fold>

    
}