package ua.epam.training.mylists;

import java.util.Iterator;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static ua.epam.training.tests.helper.MyTestHelper.*;

/**
 * Test methods of MyLinkedList class
 * @author Сергей
 */
public class MyLinkedListTest {
    
    MyLinkedList instance;

    public MyLinkedListTest() {
    }

    @Before
    public void setUp() {
        instance = new MyLinkedList();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
    
    //<editor-fold defaultstate="collapsed" desc="constructor">
    /**
     * Test of constructor with parameter, of class MyLinkedList.
     */
    @Test
    public void testConstructor_ListAs1ParamHasSomeElements_SizeEquels() {
        MyList someList = new MyArrayList();
        addFourValues(someList);
        instance = new MyLinkedList(someList);
        int expResult = 4;
        int result = instance.size();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of constructor with parameter, of class MyLinkedList.
     */
    @Test
    public void testConstructor_ListAs1ParamHasSomeElements_ElementsEquels() {
        MyList someList = new MyArrayList();
        addFourValues(someList);
        instance = new MyLinkedList(someList);
        Object[] expResult = someList.toArray();
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);

    }
    
    
    /**
     * Test of constructor with parameter, of class MyLinkedList.
     */
    @Test
    public void testConstructor_ListAs1ParamNull_SizeZero() {
        MyList someList = null;
        instance = new MyLinkedList(someList);
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="descendingIterator">
    /**
     * Test of descendingIterator method, of class MyLinkedList.
     */
    @Test
    public void testDescendingIterator_WithNoElement_HasNextFalse() {
        boolean expResult = false;
        Iterator iter = instance.descendingIterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);

    }

    /**
     * Test of descendingIterator method, of class MyLinkedList.
     */
    @Test
    public void testDescendingIterator_WithSomeElements_HasNextTrue() {
        addFourValues(instance);
        boolean expResult = true;
        Iterator iter = instance.descendingIterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of descendingIterator method, of class MyLinkedList.
     */
    @Test
    public void testDescendingIterator_WithSomeElements_next() {
        addFourValues(instance);
        int expResult = val4;
        Iterator iter = instance.descendingIterator();
        int result = (int) iter.next();
        assertEquals(expResult, result);
    }

    /**
     * Test of descendingIterator method, of class MyLinkedList.
     */
    @Test(expected = NullPointerException.class)
    public void testDescendingIterator_WithNoElements_next_NullPointerExceptionThrown() {
        Object expResult = null;
        Iterator iter = instance.descendingIterator();
        Object result = iter.next();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of iterator method, of class MyLinkedList.
     */
    @Test
    public void testDescendingIterator_WithSomeElements_Remove_SizeDecrised() {
        addFourValues(instance);
        Iterator iter = instance.descendingIterator();
        iter.next();
        iter.remove();
        int expResult = 3;
        int result = instance.size;
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="iterator">
    /**
     * Test of iterator method, of class MyLinkedList.
     */
    @Test
    public void testIterator_WithNoElement_HasNextFalse() {
        boolean expResult = false;
        Iterator iter = instance.iterator();
        boolean result = iter.hasNext();
        assertEquals(expResult, result);

    }

    /**
     * Test of iterator method, of class MyLinkedList.
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
     * Test of iterator method, of class MyLinkedList.
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
     * Test of iterator method, of class MyLinkedList.
     */
    @Test(expected = NullPointerException.class)
    public void testIterator_WithNoElements_next_NullPointerExceptionThrown() {
        Object expResult = null;
        Iterator iter = instance.iterator();
        Object result = iter.next();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of iterator method, of class MyLinkedList.
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
    
    //<editor-fold defaultstate="collapsed" desc="push">
    /**
     * Test of push method, of class MyLinkedList.
     */
    @Test
    public void testPush_ListWithSomeValues_AddedAsLastElement() {
        addFourValues(instance);
        int val5 = 5;
        instance.push(val5);
        int expected = 0;
        int actual = instance.indexOf(val5);
        assertEquals(expected, actual);
    }
    
    /**
     * Test of push method, of class MyLinkedList.
     */
    @Test
    public void testPush_WithSomeValues_SizeIncresed() {
        addFourValues(instance);
        int val5 = 5;
        int expResult = 5;
        instance.push(val5);
        Object result = instance.size();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="pop">
     /**
     * Test of pop method, of class MyLinkedList.
     */
    @Test
    public void testPop_WithSomeValues_FirstElement() {
        addFourValues(instance);
        Object expResult = val1;
        Object result = instance.pop();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of pop method, of class MyLinkedList.
     */
    @Test
    public void testPop_WithSomeValues_SizeDecresed() {
        addFourValues(instance);
        Object expResult = 3;
        instance.pop();
        Object result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testPop_WithoutValues_MyIndexOutOfBoundsExceptionThrown() {
        instance.pop();
    }
    //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="offer">
    
    /**
     * Test of offer method, of class MyLinkedList.
     */
    @Test
    public void testOffer_ListWithSomeValues_AddedAsLastElement() {
        addFourValues(instance);
        int val5 = 5;
        instance.offer(val5);
        int expected = 4;
        int actual = instance.indexOf(val5);
        assertEquals(expected, actual);
    }
    
    /**
     * Test of offer method, of class MyLinkedList.
     */
    @Test
    public void testOffer_WithSomeValues_SizeIncresed() {
        addFourValues(instance);
        int val5 = 5;
        int expResult = 5;
        instance.offer(val5);
        Object result = instance.size();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="peek">
    /**
     * Test of peek method, of class MyLinkedList.
     */
    @Test
    public void testPeek_WithSomeValues_FirstElement() {
        addFourValues(instance);
        Object expResult = val1;
        Object result = instance.peek();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of peek method, of class MyLinkedList.
     */
    @Test
    public void testPeek_WithSomeValues_SizeNotChanged() {
        addFourValues(instance);
        Object expResult = 4;
        instance.peek();
        Object result = instance.size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of peek method, of class MyLinkedList.
     */
    @Test(expected = NullPointerException.class)
    public void testPeek_WithoutValues_NullPointerExceptionThrown() {
        instance.peek();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="poll">
    /**
     * Test of poll method, of class MyLinkedList.
     */
    @Test
    public void testPoll_WithSomeValues_FirstElement() {
        addFourValues(instance);
        Object expResult = val1;
        Object result = instance.poll();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of poll method, of class MyLinkedList.
     */
    @Test
    public void testPoll_WithSomeValues_SizeDecresed() {
        addFourValues(instance);
        Object expResult = 3;
        instance.poll();
        Object result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testPoll_WithoutValues_MyIndexOutOfBoundsExceptionThrown() {
        instance.poll();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="add">
    /**
     * Test of add method, of class MyLinkedList.
     */
    @Test
    public void testAdd_AddInEmptyLinked() {
        Object e = val1;
        instance.add(e);
        Object[] expected = new Object[]{val1};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of add method, of class MyLinkedList.
     */
    @Test
    public void testAdd_AddInNotEmptyLinked() {
        Object e = val1;
        addFourValues(instance);
        instance.add(e);
        Object[] expected = new Object[]{val1, val2, val3, val4, val1};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }
    
    
     /**
     * Test of add method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAdd_IndexAs1ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        Object e = val1;
        addFourValues(instance);
        instance.add(-1,e);
    }
    
    /**
     * Test of add method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAdd_IndexAs1ParamBiggerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        Object e = val1;
        addFourValues(instance);
        instance.add(6,e);
    }
    
    /**
     * Test of add method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAdd_IndexAs1ParamEquelSize_MyIndexOutOfBoundsExceptionThrown() {
        Object e = val1;
        addFourValues(instance);
        instance.add(4,e);
    }
    
    /**
     * Test of add method, of class MyLinkedList.
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
    
    //<editor-fold defaultstate="collapsed" desc="addFirst">
    /**
     * Test of add method, of class MyLinkedList.
     */
    @Test
    public void testAddFirst_EmptyList_FirstEquelsLast() {
        instance.addFirst(val1);
        boolean expected = true;
        boolean actual = instance.getFirst().equals(instance.getLast());
        assertEquals(expected, actual);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addAll">
    /**
     * Test of addAll method, of class MyLinkedList.
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
     * Test of addAll method, of class MyLinkedList.
     */
    @Test
    public void testAddAll_InEmptyLinked() {
        Object[] arr = new Object[]{val2, val3, val4};
        instance.addAll(arr);
        Object[] expected = arr;
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyLinkedList.
     */
    @Test
    public void testAddAll_WhenParameterIsEmptyLinked_SameLinked() {
        addFourValues(instance);
        Object[] expected = instance.toArray();
        instance.addAll(null);
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAddAll_IndexLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        Object[] arr = new Object[]{1, 2, 3};
        instance.addAll(index, arr);
    }
    
    /**
     * Test of addAll method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testAddAll_IndexAs1ParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        Object[] arr = new Object[]{1, 2, 3};
        instance.addAll(index, arr);
    }

    /**
     * Test of addAll method, of class MyLinkedList.
     */
    @Test
    public void testAddAll_ZeroIndex_AddInBeginningOfLinked() {
        int index = 0;
        addFourValues(instance);
        instance.addAll(index, new Object[]{val2, val3, val4});
        Object[] expected = new Object[]{val2, val3, val4, val1, val2, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }

    /**
     * Test of addAll method, of class MyLinkedList.
     */
    @Test
    public void testAddAll_SomeIndex_AddInOrderPlaceOfLinked() {
        int index = 2;
        addFourValues(instance);
        instance.addAll(index, new Object[]{val1, val2, val3, val4});
        Object[] expected = new Object[]{val1, val2, val1, val2, val3, val4, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }
    
    /**
     * Test of addAll method, of class MyLinkedList.
     */
    @Test
    public void testAddAll_ArrayAs2ParamNull_NotChanged() {
        int index = 2;
        addFourValues(instance);
        instance.addAll(index,null);
        Object[] expected = new Object[]{val1, val2, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expected, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get">
    /**
     * Test of get method, of class MyLinkedList.
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
     * Test of get method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testGet_IndexAsParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        instance.get(index);
    }
    
    /**
     * Test of get method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testGet_IndexAsParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        instance.get(index);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getLast">
    @Test
    public void testGetLast_lastValue() {
        addFourValues(instance);
        Object expResult = val4;
        Object result = instance.getLast();
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeLast">
    /**
     * Test of remove method, of class MyLinkedList.
     */
    @Test
    public void testRemoveLast_SizeDecrease() {
        addFourValues(instance);
        instance.removeLast();
        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyLinkedList.
     */
    @Test
    public void testRemovelast_EmptyList_Null() {
        Object expResult = null;
        Object result = instance.removeLast();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MyLinkedList.
     */
    @Test
    public void testRemovelast_ListWithSomeValues_LastValue() {
        Object expResult = null;
        Object result = instance.removeLast();
        assertEquals(expResult, result);
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="remove">
    /**
     * Test of remove method, of class MyLinkedList.
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
     * Test of remove method, of class MyLinkedList.
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
     * Test of remove method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testRemove_IndexAs1ParamLessBiggerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        instance.remove(index);
    }
    
    /**
     * Test of remove method, of class MyLinkedList.
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
     * Test of clear method, of class MyLinkedList.
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
     * Test of clear method, of class MyLinkedList.
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
     * Test of isEmpty method, of class MyLinkedList.
     */
    @Test
    public void testIsEmpty_WithNoElements_True() {
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class MyLinkedList.
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
     * Test of set method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSet_IndexAsParamLargerThenSize_MyIndexOutOfBoundsExceptionThrown() {
        int index = 5;
        addFourValues(instance);
        instance.set(index, index);
    }
    
    /**
     * Test of set method, of class MyLinkedList.
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testSet_IndexAsParamLessThenZero_MyIndexOutOfBoundsExceptionThrown() {
        int index = -1;
        addFourValues(instance);
        instance.set(index, index);
    }

    /**
     * Test of set method, of class MyLinkedList.
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
     * Test of indexOf method, of class MyLinkedList.
     */
    @Test
    public void testIndexOf_ElementIsNotInTheLinkedList_MinusOne() {
        Object o = val2;
        instance.add(val1);
        int expResult = -1;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of indexOf method, of class MyLinkedList.
     */
    @Test
    public void testIndexOf_ElementIsInTheLinkedList() {
        Object o = val2;
        instance.add(val1);
        instance.add(val2);
        int expResult = 1;
        int result = instance.indexOf(o);
        assertEquals(expResult, result);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="size">
    /**
     * Test of size method, of class MyLinkedList.
     */
    @Test
    public void testSize_WithNoElements() {
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class MyLinkedList.
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
     * Test of toArray method, of class MyLinkedList.
     */
    @Test
    public void testToLinked_WithSomeElements() {
        addFourValues(instance);
        Object[] expResult = new Object[]{val1, val2, val3, val4};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of toArray method, of class MyLinkedList.
     */
    @Test
    public void testToLinked_WithNoElements_EmtyLinked() {
        Object[] expResult = new Object[0];
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
    }
    //</editor-fold>
}
