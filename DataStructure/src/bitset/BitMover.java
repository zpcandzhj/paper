package bitset;
/*
 * java 位操作运算符
 * >>	算术右移操作符(带符号右移),操作过程：舍弃二进制数的最后一位,开头补符号位(正数补0，负数补1)	a>>b等价于a/2^(b%32)
 * >>>	逻辑右移操作符(无符号右移,移动过程中开头都是补0),操作过程：舍弃二进制数的最后一位,开头补0
 * <<	算术左移操作符(带符号右移),操作过程：舍弃二进制数的开头一位,在二进制数的尾部增加0
 */
public class BitMover {  
	 //交换一个字节高四位与低四位的位置  
    public static byte swap(byte b){  
        int lowBits=b&0xF;//获得低4位  
        int highBits=b&0xF0;//获得高4位 
        //把低4位左移4位变成高4位  
        //把高4位无符号右移4位变成低4位  
        //再把两者进行位或  
        int result=(lowBits<<4)|(highBits>>>4);  
        return (byte)result;//截取int型数据后8位
    }  
    public static void main(String[] args) {  
    	System.out.println(Integer.toBinaryString(10));  
        System.out.println(swap((byte)10));  //结果是-96
        String res=Integer.toBinaryString(-96);
        System.out.println(res.substring(res.length()-8, res.length()));//输出补码形式
        System.out.println(Integer.toBinaryString(1));  
        System.out.println(swap((byte)1));  
        System.out.println(Integer.toBinaryString(-1));  
        System.out.println(swap((byte)-1));//在内存中要以补码运算  
        
        int g=0xf0;  
        byte bt=12;  
        System.out.println("bt的二进制:"+Integer.toBinaryString(bt));  
        System.out.println("bt的高4位:"+Integer.toBinaryString(g&bt));  
        System.out.println("g=>"+Integer.toBinaryString(g));  
        System.out.println("g=>"+g); 
        
    }  
}  
