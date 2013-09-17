package ua.redric.training.mylists;

import java.util.*;
import java.util.Iterator;
import java.util.RandomAccess;

/**
 * List based on array
 *
 * @author Serhii_Tolok
 */
public class MyArrayList<T> implements MyList<T>, RandomAccess {

    Object[] dataArray;
    int currentIndex;
    int size;
    public static final int DEFAULT_CAPACITY = 10;
    public static final int MIN_CAPACITY = 5;

    public MyArrayList() {
        dataArray = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(MyList<T> c) {
        if (c != null) {
            dataArray = c.toArray();
            size = c.size();
        }

    }

    public MyArrayList(int initialCapacity) {
        dataArray = new Object[(MIN_CAPACITY < initialCapacity)
                ? initialCapacity : MIN_CAPACITY];
    }

    /**
     * When array is full, we need to add some space for new data
     *
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > dataArray.length) {
            //Capacity ensures by folowing formule
            // newCapacity = old *1,5 +1
            Object[] copy = new Object[minCapacity * 3 / 2 + 1];
            System.arraycopy(dataArray, 0, copy, 0, size);
            dataArray = (T[]) copy;
        }
    }

    /**
     * When we need to decrease memory using to exactly that count which
     * elements in array
     */
    public void trimToSize() {
        if (size != dataArray.length) {
            Object[] copy = new Object[size > MIN_CAPACITY ? size : MIN_CAPACITY];
            System.arraycopy(dataArray, 0, copy, 0, size);
            dataArray = copy;
        }
    }

    @Override
    public void add(T e) {
        ensureCapacity(size + 1);
        dataArray[size++] = (T) e;
    }

    @Override
    public void add(int index, T e) {
        if (index < size && index >= 0) {
            dataArray[index] = (T) e;
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(T[] c) {
        if (c != null) {
            ensureCapacity(size + c.length);
            System.arraycopy(c, 0, dataArray, size, c.length);
            size += c.length;
        }
    }

    @Override
    public void addAll(int index, T[] c) {
        if (index <= size && index >= 0) {
            ensureCapacity(size + c.length);
            System.arraycopy(dataArray, index, dataArray, index + c.length, size - index);
            System.arraycopy(c, 0, dataArray, index, c.length);
            size += c.length;
        } else {
            throw new MyIndexOutOfBoundsException();
        }

    }

    public Object[] getArray(){
        return Collections.unmodifiableList(Arrays.asList(this.dataArray)).toArray();
    }
    
    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) dataArray[index];
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T result = (T) dataArray[index];
            System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
            size--;
            return result;
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            dataArray[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(int index, T e) {
        if (index < size && index >= 0) {
            dataArray[index] = (T) e;
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(T o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                //Return index of first null in array
                if (dataArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(dataArray[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(dataArray, 0, array, 0, size);
        return array;
    }

    @Override
    public Iterator iterator() {
        Iterator iterator = new Iterator() {
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return (T) dataArray[currentIndex++];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(currentIndex);
            }
        };
        return iterator;
    }
}
