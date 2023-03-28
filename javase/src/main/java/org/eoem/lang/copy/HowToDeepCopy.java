package org.eoem.lang.copy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;


/**
 * 如何实现深拷贝
 * ！！被序列化的子类的父类也要实现 {@link Serializable} ,不然会报错{@link java.io.NotSerializableException}
 */
public class HowToDeepCopy implements Serializable {
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Address implements Serializable {
        
        private String street;
        private String city;
        private String country;
        
        @Override
        public Object clone() {
            try {
                return (Address) super.clone();
            } catch (CloneNotSupportedException e) {
                return new Address(this.street, this.getCity(), this.getCountry());
            }
        }
        
        // standard constructors, getters and setters
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User implements Serializable {
        
        private String firstName;
        private String lastName;
        private Address address;
        
        @Override
        public Object clone() {
            User user = null;
            try {
                user = (User) super.clone();
            } catch (CloneNotSupportedException e) {
                user = new User(
                        this.getFirstName(), this.getLastName(), this.getAddress());
            }
            user.address = (Address) this.address.clone();
            return user;
        }
        
        
        // standard constructors, getters and setters
    }
    
    public static void main(String[] args) {
        new HowToDeepCopy().TestSerializationUtilsClone();
    }
    
    /**
     * 使用 Apache Commons Lang SerializationUtils#clone方法
     */
    public void TestSerializationUtilsClone() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
        System.out.println(pm);
        
        User deepCopy = (User) SerializationUtils.clone(pm);
//        User2 deepCopy2 = SerializationUtils.clone((Object)pm);
        
        address.setCountry("Great Britain");
        System.out.println("after setCountry");
        System.out.println(pm);
        System.out.println(deepCopy);
        System.out.println((deepCopy.getAddress().getCountry()).equals(pm.getAddress().getCountry()));
        
    }
}
