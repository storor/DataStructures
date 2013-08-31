package ua.redric.training.mymaps;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Based on black-red tree implementation of TreeMap
 *
 * @author Сергей
 */
public class MyTreeMap<K extends Comparable, V> implements MyMap<K, V> {

    //Root of tree
    private SimpleEntry<K, V> root;
    private Comparator comparator;
    //Total number of elements
    int size;
    
    /**
     * Entry of map
     * @param <K>Type of key
     * @param <V>Type of value
     */
    static final class SimpleEntry<K, V> implements MyMap.Entry<K, V> {

        K key;
        V value;
        SimpleEntry<K, V> left;
        SimpleEntry<K, V> right;
        SimpleEntry<K, V> parent;
        boolean isRed = false;

        SimpleEntry(K key, V value, SimpleEntry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
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
            V old = this.getValue();
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Constructs a new, empty tree map, using the natural ordering of its keys
     */
    public MyTreeMap() {
    }

    /**
     * Constructs a new, empty tree map, ordered according to the given comparator
     * @param comparator user defined comparator
     */
    public MyTreeMap(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Iterator it = entryIterator();
        while (it.hasNext()) {
            if (((SimpleEntry) it.next()).value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        SimpleEntry<K, V> entry = getEntry(key);
        if (entry != null) {
            return entry.value;
        }
        return null;
    }

    private SimpleEntry<K, V> getEntry(K key) {
        SimpleEntry<K, V> entry = root;
        while (entry != null) {
            if (comparator != null) {
                int cmp = comparator.compare(key, entry.key);
                if (cmp == 0) {
                    return entry;
                } else if (cmp < 0) {
                    entry = entry.left;
                } else {
                    entry = entry.right;
                }
            } else {
                int cmp = ((Comparable) key).compareTo(((Comparable) entry.key));
                if (cmp == 0) {
                    return entry;
                } else if (cmp < 0) {
                    entry = entry.left;
                } else {
                    entry = entry.right;
                }
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
        SimpleEntry<K, V> entry = root;
        //If root is null - we have the new root
        if (entry == null) {
            root = new SimpleEntry(key, value, null);
            size = 1;
        } else {
            SimpleEntry<K, V> parent;
            int cmp = 0;
            do {
                //There two possible ways to compare keys: by using comparator
                //or if it is null - Comparable.compareTo
                if (comparator != null) {
                    cmp = comparator.compare(key, entry.key);
                    if (cmp == 0) {
                        //Rewrite value
                        return entry.setValue(value);
                    } else {
                        parent = entry;
                        if (cmp < 0) {
                            entry = entry.left;
                        } else {
                            entry = entry.right;
                        }
                    }
                } else {
                    cmp = ((Comparable) key).compareTo(((Comparable) entry.key));
                    if (cmp == 0) {
                        //Rewrite value
                        return entry.setValue(value);
                    } else {
                        parent = entry;
                        if (cmp < 0) {
                            entry = entry.left;
                        } else {
                            entry = entry.right;
                        }
                    }
                }
            } while (entry != null);
            size++;
            entry = new SimpleEntry(key, value, parent);
            if (cmp > 0) {
                parent.right = entry;
            } else {
                parent.left = entry;
            }
            entry.isRed = true;
            balancing(entry);
        }
        return null;
    }

    /**
     * After adding new node to the tree we need to check out if all rules of
     * black-red tree are satisfied
     *
     * @param entry fresh added node
     */
    private void balancing(SimpleEntry entry) {
        insertCase1(entry);
    }

    //<editor-fold defaultstate="collapsed" desc="insectCases">
    /**
     * In case if new node is the root
     *
     * @param entry
     */
    private void insertCase1(SimpleEntry entry) {
        if (entry.parent == null) {
            root = entry;
            entry.isRed = false;
        } else {
            insertCase2(entry);
        }
    }

    /**
     * In case if parent of new node is black
     *
     * @param entry
     */
    private void insertCase2(SimpleEntry entry) {
        if (!entry.parent.isRed) {
        } else {
            insertCase3(entry);
        }
    }

    /**
     * In case if both parent and uncle of node are red
     *
     * @param entry
     */
    private void insertCase3(SimpleEntry entry) {
        SimpleEntry uncle = getUncle(entry);
        if (uncle != null && uncle.isRed && entry.parent.isRed) {
            entry.parent.isRed = false;
            uncle.isRed = false;
            SimpleEntry gr = getGrandpa(entry);
            gr.isRed = true;
            insertCase1(gr);
        } else {
            insertCase4(entry);
        }
    }

    /**
     * Parent is red but the uncle is black
     *
     * @param entry
     */
    private void insertCase4(SimpleEntry entry) {
        SimpleEntry grandpa = getGrandpa(entry);
        if (entry.parent.right == entry
                && grandpa.left == entry.parent) {
            rotateLeft(entry.parent);
            entry = entry.left;

        } else if (entry.parent.left == entry
                && grandpa.right == entry.parent) {
            rotateRight(entry.parent);
            entry = entry.right;
        }
        insertCase5(entry);
    }

    /**
     * Parent is red but the uncle is black
     *
     * @param entry
     */
    private void insertCase5(SimpleEntry entry) {
        entry.parent.isRed = false;
        SimpleEntry grandpa = getGrandpa(entry);
        grandpa.isRed = true;
        if (entry == entry.parent.left && entry.parent == grandpa.left) {
            rotateRight(grandpa);
        } else {
            rotateLeft(grandpa);
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="rotates">
    /**
     * Replace entry with it's parent
     *
     * @param entry
     */
    private void rotateLeft(SimpleEntry<K, V> entry) {
        if (entry != null) {
            SimpleEntry<K, V> rightEntry = entry.right;
            entry.right = rightEntry.left;
            if (rightEntry.left != null) {
                rightEntry.left.parent = entry;
            }
            rightEntry.parent = entry.parent;
            if (entry.parent == null) {
                root = rightEntry;
            } else if (entry.parent.left == entry) {
                entry.parent.left = rightEntry;
            } else {
                entry.parent.right = rightEntry;
            }
            rightEntry.left = entry;
            entry.parent = rightEntry;
        }
    }

    /**
     * Replace entry with it's parent
     *
     * @param entry
     */
    private void rotateRight(SimpleEntry<K, V> entry) {
        if (entry != null) {
            SimpleEntry<K, V> leftEntry = entry.left;
            entry.left = leftEntry.right;
            if (leftEntry.right != null) {
                leftEntry.right.parent = entry;
            }
            leftEntry.parent = entry.parent;
            if (entry.parent == null) {
                root = leftEntry;
            } else if (entry.parent.right == entry) {
                entry.parent.right = leftEntry;
            } else {
                entry.parent.left = leftEntry;
            }
            leftEntry.right = entry;
            entry.parent = leftEntry;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="relatives">
    /**
     * Getting parent of parent of entry
     *
     * @param entry
     * @return
     */
    private SimpleEntry getGrandpa(SimpleEntry entry) {
        SimpleEntry grandpa = null;
        if (entry != null && entry.parent != null) {
            grandpa = entry.parent.parent;
        }
        return grandpa;
    }

    /**
     * Getting nearby node of parent of entry
     *
     * @param entry
     * @return
     */
    private SimpleEntry getUncle(SimpleEntry entry) {
        SimpleEntry uncle = null;
        if (entry.parent != null) {
            if (entry.parent.parent != null) {
                uncle = (entry.parent.equals(entry.parent.parent.left)
                        ? entry.parent.parent.right : entry.parent.parent.left);
            }
        }
        return uncle;
    }

    //</editor-fold>
    
    @Override
    public V remove(K key) {
        SimpleEntry<K, V> entry = getEntry(key);
        V result = null;
        if (entry != null) {
            result = entry.value;
            deleteEntry(entry);
        }
        return result;
    }

    /**
     * Returns the successor of the specified Entry, or null if no such.
     */
    private SimpleEntry successor(SimpleEntry<K, V> entry) {
        if (entry == null) {
            return null;
        } else if (entry.right != null) {
            SimpleEntry<K, V> right = entry.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        } else {
            SimpleEntry<K, V> parent = entry.parent;
            SimpleEntry<K, V> temp = entry;
            while (parent != null && temp == parent.right) {
                temp = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    /**
     * Deleting existing entry
     *
     * @param entry
     */
    private void deleteEntry(SimpleEntry entry) {

        // If strictly internal, copy successor's element to p and then make p
        // point to successor.
        if (entry.left != null && entry.right != null) {
            SimpleEntry<K, V> s = successor(entry);
            entry.key = s.key;
            entry.value = s.value;
            entry = s;
        }

        // Start fixup at replacement node, if it exists.
        SimpleEntry<K, V> replacement = (entry.left != null ? entry.left : entry.right);

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = entry.parent;
            if (entry.parent == null) {
                root = replacement;
            } else if (entry == entry.parent.left) {
                entry.parent.left = replacement;
            } else {
                entry.parent.right = replacement;
            }

            entry.left = entry.right = entry.parent = null;

            // Fix replacement
            if (entry.isRed == false) {
                balancing(replacement);
            }
        } else if (entry.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (entry.isRed == false) {
                balancing(entry);
            }

            if (entry.parent != null) {
                if (entry == entry.parent.left) {
                    entry.parent.left = null;
                } else if (entry == entry.parent.right) {
                    entry.parent.right = null;
                }
                entry.parent = null;
            }
        }
        size--;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator entryIterator() {
        class EntryIterator implements Iterator {

            SimpleEntry<K, V> currentEntry;
            //Stack will contain all entries in tree
            Stack<SimpleEntry<K, V>> stack;

            EntryIterator() {
                stack = new Stack();
                lookOnLeftSide(root);
            }

            private void lookOnLeftSide(SimpleEntry<K, V> entry) {
                while (entry != null) {
                    stack.push(entry);
                    entry = entry.left;
                }
            }

            @Override
            public SimpleEntry<K, V> next() {
                if (!stack.empty()) {
                    SimpleEntry<K, V> entry = stack.pop();

                    if (entry.right != null) {
                        lookOnLeftSide(entry.right);
                    }
                    currentEntry = entry;
                    return entry;
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return !stack.empty();
            }

            @Override
            public void remove() {
                if (currentEntry != null) {
                    MyTreeMap.this.remove(currentEntry.key);
                }
            }
        }
        return new EntryIterator();
    }
}
