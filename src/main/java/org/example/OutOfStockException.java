package org.example;

public class OutOfStockException extends Exception{
    public OutOfStockException(){
        super((String.format("Out of stock!"))) ;
    }
}
