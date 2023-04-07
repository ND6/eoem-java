package org.eoem.lang.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 使用迭代器
 */
public class HowToIterator {
    public static void main(String[] args) {
        testIterator();
    }
    
    public static void testIterator() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
//            System.out.println(iterator.next());// java.util.NoSuchElementException
            System.out.println("--");
        }
    }
}
