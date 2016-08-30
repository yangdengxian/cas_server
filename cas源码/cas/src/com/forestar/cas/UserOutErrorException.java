package com.forestar.cas;

import org.jasig.cas.authentication.handler.AuthenticationException;
public class UserOutErrorException extends
        AuthenticationException {

    private static final long serialVersionUID = 1L;


     public static final UserOutErrorException ERROR = new UserOutErrorException();

     /** The code description of this exception. */
     private static final String CODE= "userouttime.error";

     public UserOutErrorException(){
         super(CODE);
     }

     public UserOutErrorException(final Throwable throwable) {
         super(CODE,throwable);
     }

     public UserOutErrorException(final String code) {
         super(code);
     }

     public UserOutErrorException(final String code,
         final Throwable throwable){
         super(code,throwable);
     }
}
