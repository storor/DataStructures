/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.training.mymaps;

import java.util.Iterator;

/**
 *
 * @author Serhii_Tolok
 */
public interface MyMap<K,V> {

    void clear();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    boolean isEmpty();

    V put(K key, V value);

    V remove(K key);

    int size();

    Iterator entryIterator();

    interface Entry<K,V> {

        @Override
        boolean equals(Object o);

        K getKey();

        V getValue();

        @Override
        int hashCode();

        V setValue(V value);
    }
}
