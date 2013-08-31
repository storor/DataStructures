package ua.epam.training.mylists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;

/**
 * Class helper which works whith MyList
 *
 * @author Serhii_Tolok
 */
public class MyCollections {

    private MyCollections() {
    }

    public static void sort(MyList<? extends Comparable> list) {
        if (list instanceof RandomAccess) {
            quickSort(list, 0, list.size() - 1);
        } else {
            bubleSort(list);
        }
    }

    public static void sort(MyList list, Comparator c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (list instanceof RandomAccess) {
            quickSort(list, 0, list.size() - 1, c);
        } else {
            bubleSort(list, c);
        }
    }

    public static void swap(MyList list, int i, int j) {
        if (i < 0 || j < 0) {
            throw new MyIndexOutOfBoundsException();
        }
        Object ob1 = list.get(i);
        Object ob2 = list.get(j);
        list.set(i, ob2);
        list.set(j, ob1);
    }

    public static void copy(MyList dest, MyList src) {
        if (dest.size() < src.size()) {
            throw new MyIndexOutOfBoundsException();
        } else {
            Iterator iteratorSrc = src.iterator();
            int i = 0;
            while (iteratorSrc.hasNext()) {
                dest.set(i, iteratorSrc.next());
                i++;
            }
        }

    }

    public static void reverse(MyList list) {
        int j = list.size() - 1;
        for (int i = 0; i < list.size() / 2; i++) {
            swap(list, i, j);
            j--;
        }
    }

    public static int binarySearch(RandomAccess list, Object key) {
        return binarySearchDirect((MyList<? extends Comparable>)list, key);
                //binarySearchRecursvie((MyList<? extends Comparable>) list, key,
                //0, ((MyList) list).size()) - 1;
    }

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private static int binarySearchRecursvie(MyList<? extends Comparable> list, Object key, int min, int max) {
        if (min > max) {
            return (-(min) - 1);
        } else if (min == max) {
            return min;
        } else {
            int midInd = (min + max) / 2;
            Comparable midEl = ((Comparable) list.get(midInd));
            if (midEl.compareTo((Comparable) key) > 1) {
                return binarySearchRecursvie(list, key, min, midInd - 1);
            } else if (midEl.compareTo((Comparable) key) < 1) {
                return binarySearchRecursvie(list, key, midInd + 1, max);
            } else {
                return midInd;
            }
        }
    }

    private static int binarySearchDirect(MyList<? extends Comparable> list, Object key) {
        if (list.isEmpty()) {
            return -1;
        }
        int low = 0;
        int high = list.size() - 1;
        int midInd = 0;
        while (low <= high) {
            midInd = (low + high) / 2;
            Comparable midEl = ((Comparable) list.get(midInd));
           if (midEl.compareTo((Comparable) key) ==0){ // The element has been found
                return midInd;
            }else if (midEl.compareTo((Comparable) key) < 1) {
                low = midInd + 1;
            } else {
                high = midInd - 1;
            }
        }
        return (-(midInd) - 1);
    }

    private static void bubleSort(MyList<? extends Comparable> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (((Comparable) list.get(i)).compareTo(((Comparable) list.get(j))) > 0) {
                    swap(list, i, j);
                }
            }
        }
    }

    private static void quickSort(MyList<? extends Comparable> list, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        Comparable pivot = (Comparable) list.get(low + (high - low) / 2);

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (((Comparable) list.get(i)).compareTo(pivot) < 0) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (((Comparable) list.get(j)).compareTo(pivot) > 0) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j) {
            quickSort(list, low, j);
        }
        if (i < high) {
            quickSort(list, i, high);
        }
    }

    private static void bubleSort(MyList list, Comparator c) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (c.compare(list.get(i), list.get(j)) > 0) {
                    swap(list, i, j);
                }
            }
        }
    }

    private static void quickSort(MyList list, int low, int high, Comparator c) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        Object pivot = list.get(low + (high - low) / 2);

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (c.compare(list.get(i), pivot) < 0) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (c.compare(list.get(j), pivot) > 0) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j) {
            quickSort(list, low, j);
        }
        if (i < high) {
            quickSort(list, i, high);
        }
    }
    //</editor-fold>
}
