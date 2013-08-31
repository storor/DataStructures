package ua.redric.training.mylists;

import java.util.Iterator;

/**
 *
 * @author Serhii_Tolok
 */
public interface MyList<T> extends Iterable {

    void add(T e);

    void add(int index, T e);

    void addAll(T[] c);

    void addAll(int index, T[] c);

    T get(int index);

    T remove(int index);

    void clear();

    boolean isEmpty();

    void set(int index, T e);

    int indexOf(T o);

    int size();

    Object[] toArray();

    @Override
    Iterator iterator();
}
