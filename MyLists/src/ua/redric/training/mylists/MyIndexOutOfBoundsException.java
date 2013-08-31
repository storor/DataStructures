package ua.redric.training.mylists;

/**
 * Exception which throws when index is lewer then 0 or biger then size of array
 * @author Serhii_Tolok
 */
public class MyIndexOutOfBoundsException extends RuntimeException{
    public MyIndexOutOfBoundsException(){
        super();
    }
    
     public MyIndexOutOfBoundsException(String s){
        super(s);
    }
}
