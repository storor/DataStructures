package ua.redric.training.tests.helper;

import ua.redric.training.mylists.MyList;
import ua.redric.training.mymaps.MyMap;

/**
 * Class helper methods of which fill collections by some elements 
 * @author Сергей
 */
public class MyTestHelper {
    
    public static final int val1 = 1;
    public static final int val2 = 2;
    public static final int val3 = 3;
    public static final int val4 = 4;
    
    public static final String key1 = "A";
    public static final String key2 = "B";
    public static final String key3 = "C";
    public static final String key4 = "D";

    private MyTestHelper(){
        
    }
    
    /**
     * Adding four (4) test values to instance of MyList
     *
     * @param instance instance of MyList
     */
    public static void addFourValues(MyList instance) {
        instance.add(val1);
        instance.add(val2);
        instance.add(val3);
        instance.add(val4);
    }
    
    /**
     * Adding four (4) test values and keys to instance of MyMap
     *
     * @param instance instance of MyMap
     */
    public static void putFourValues(MyMap instance) {
        instance.put(key1,val1);
        instance.put(key2,val2);
        instance.put(key3,val3);
        instance.put(key4,val4);
    }
    
     /**
     * Adding Twenty (20) test values and keys to instance of MyMap
     *
     * @param instance instance of MyMap
     */
    public static void putTwentyValues(MyMap instance) {
        instance.put(key1,val1);
        instance.put(key2,val2);
        instance.put(key3,val3);
        instance.put(key4,val4);
        instance.put("q", 5);
        instance.put("w", 7);
        instance.put("e", 8);
        instance.put("r", 9);
        instance.put("t", 51);
        instance.put("y", 52);
        instance.put("u", 53);
        instance.put("i", 54);
        instance.put("o", 55);
        instance.put("p", 56);
        instance.put("a", 57);
        instance.put("s", 58);
        instance.put("d", 59);
        instance.put("f", 15);
        instance.put("g", 25);
        instance.put("n", 15);
    }
}
