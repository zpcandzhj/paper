package jdbcExc;

import java.util.ArrayList;  

public class ArrayListTest {  
    public static void main(String[] args) {  
        final int SIZE = 90000000;  
        Object obj = new Object();  
        ArrayList list = new ArrayList();  
  
        long a1 = System.currentTimeMillis();  
        for (int i = 0; i < SIZE; i++) {  
            list.add(obj);  
        }  
        long a2 = System.currentTimeMillis();  
        System.out.println("costs:" + (a2 - a1) + "ms");  
  
        list = new ArrayList();  
        a1 = System.currentTimeMillis();  
        // 调用ensureCapacity预先设置大小  
        list.ensureCapacity(SIZE);  
        for (int i = 0; i < SIZE; i++) {  
            list.add(obj);  
        }  
        a2 = System.currentTimeMillis();  
        System.out.println("costs:" + (a2 - a1) + "ms");  
    }  
  
} 
