/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.redric.training.tests;

import java.util.Iterator;
import java.util.Random;
import ua.redric.training.mylists.*;
import ua.redric.training.mymaps.*;

/**
 * Testing our own collections and maps
 * @author Serhii_Tolok
 */
public class MyListsTest {

    /**
     * @param args the command line arguments
     */
    volatile static int a =0;
    public static void main(String[] args) throws InterruptedException {
        /*Using  MyLinkedList*/
//        MyList<String> linkedList = new MyLinkedList();
//        linkedList.add("First element");
//        linkedList.add("Second element");
//        linkedList.add("Thirth element");
//        linkedList.add("Fourth element");
//        linkedList.add("Fifth element");
//        //linkedList.remove(2);
//        //linkedList.set(2, "replaced");
//        linkedList.addAll(0, new String[]{"A","B","C"});
//        //linkedList.add(0,"a");
//        for (Object ob:linkedList) {
//            System.out.println(ob);
//        }
//        /*Using  MyArrayList*/
//        MyList<String> arrayList = new MyArrayList();
//
//        for (int i = 0; i < 15; i++) {
//            arrayList.add("Var" + (i + 1));
//        }
//        arrayList.addAll(15, new Object[]{"Ins 1", "Ins 2"});
//        arrayList.add(2, "Sigle");
//        System.out.println(arrayList.indexOf("Fuuu"));
//        
        /*Using  MyCollections*/
//        MyList arrayList = new MyArrayList();
//        arrayList.add("A");
//        arrayList.add("B");
//        arrayList.add("C");
//        arrayList.add("D");
//        //arrayList.add("E");
//        arrayList.add("F");
//        
//        
        //MyCollections.reverse(arrayList);
//        MyCollections.sort(arrayList);
//        for (Object ob : arrayList) {
//            System.out.println(ob);
//        }
        //System.out.println(MyCollections.binarySearch((MyArrayList)arrayList, "E"));
        //System.out.println(arrayList.size());
        //System.out.println(MyCollections.binarySearch((MyArrayList)arrayList, "Var9"));
//
//         /*Using  MyHashMap*/
//        MyMap hashMap = new MyHashMap();
//        for (int i = 0; i < 30; i++) {
//            hashMap.put("Key"+i, "Value"+i);
//        }
//        
//        System.err.println(hashMap.size());
//        Iterator it = hashMap.entryIterator();
//        int i=1;
//        while (it.hasNext()) {
//            System.out.println(i++ +": Value "+it.next());
//        }
//        System.out.println(hashMap.containsKey("Key200"));
//        System.out.println(hashMap.containsValue("Value2"));
//        System.out.println(hashMap.get("Key2"));
//        System.out.println(hashMap.remove("Key2"));
//        
//        it = hashMap.entryIterator();
//        i=1;
//        while (it.hasNext()) {
//            System.out.println(i++ +": Value "+it.next());
//            it.remove();
//        }
//        System.out.println(hashMap.size());
        
        /*Using  MyTreeMap*/
//        Random rn = new Random();
//        MyMap treeMap = new MyTreeMap();
//        for (int j = 0; j < 10; j++) {
//            treeMap.put("Key"+(j+1), "Val"+(j+1));
//        }
//        Thread.sleep(500);
//        System.out.println(treeMap.size());
//        
//        Iterator it = treeMap.entryIterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
//        
//        treeMap.remove("Key10");
//        while (it.hasNext()) {
//            System.out.println(it.next());
//            //it.remove();
//        }
//        Thread.sleep(500);
//        System.out.println(treeMap.size());
//        it = treeMap.entryIterator();
//        
//        while (it.hasNext()) {
//            it.next();
//            it.remove();
//        }
//        
//        Thread.sleep(500);
//        System.out.println(treeMap.size());

    }
}
