package com.crazzyghost.alphavantage;

/**
 * Exception class for the library
 * @since 1.0.0
 * @author crazzyghost
 */
public class AlphaVantageException extends RuntimeException{

    public AlphaVantageException(){
        super();
    }

    public AlphaVantageException(String msg){
        super(msg);
    }

}
