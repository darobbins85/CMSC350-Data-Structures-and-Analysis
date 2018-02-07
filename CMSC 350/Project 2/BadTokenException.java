package project2;

/*
 * File: BadTokenException
 * Author: David Robbins
 * Date: April 8, 2017 
 * Purpose: Exception class for catching the bad token
 */

class BadTokenException extends Exception
{    
      //Parameterless Constructor
      public BadTokenException(String token) {
          super(token);          
      }
}
