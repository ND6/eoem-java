package org.eoem.enums;

import lombok.var;

import java.lang.annotation.ElementType;
import java.util.Arrays;


public class HowToEnum {
    public static void main(String[]args){
        
        new HowToEnum().TestEnum();
    }
    public void TestEnum(){
        // 枚举实例方法
        var e= ElementType.TYPE;
        System.out.println(e.name());
        System.out.println(e.ordinal());
        System.out.println(e.toString());
        System.out.println(e.toString());
        // 枚举类方法
        System.out.println("### 枚举类方法 values");
        var ets = ElementType.values();
        Arrays.stream(ets).forEach(ele-> System.out.println(ele));
        System.out.println("### 枚举类方法 valueOf(Class<T> enumType, String name)");
        var type = ElementType.valueOf(ElementType.class,"TYPE");
        System.out.println(type);
        System.out.println("### 枚举类方法 valueOf( String name)");
        var type2 = ElementType.valueOf("TYPE");
        System.out.println(type);
    
    }
}
