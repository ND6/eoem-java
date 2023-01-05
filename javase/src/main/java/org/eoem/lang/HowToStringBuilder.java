package org.eoem.lang;

import lombok.var;

public class HowToStringBuilder {
    public static void main(String[] args) {
        testStringBuilder();
    }
    
    //StringBuilder 的count 什么时候赋值的
    public static void testStringBuilder() {
        var sb= new StringBuilder(17);
        int count = sb.length();
        System.out.println("####p1 length :");
        sb.append("jojo");
        System.out.println(count);
        count = sb.length();
        System.out.println("####p2 length :");
        System.out.println(count);
        
        int capacity = sb.capacity();
        System.out.println("#### capacity :");
        System.out.println(capacity);
    }
}
