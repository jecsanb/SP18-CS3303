/*
 * CCValidator.java
 * Copyright (C) 2018 Jecsan Blanco <jblancolicano1@buffs.wtamu.edu>
 *
 * Distributed under terms of the MIT license.
 *
 *   @author Jecsan Blanco
 *   @version 1.0
 *   @since  02/20/2018
 */
import  java.lang.Math;
public class CCValidator
{
    public static  boolean validateAMXNumber(String ccNumber){ 
        StringBuilder sb = new StringBuilder(ccNumber);
        char tmp;
        if( sb.length() == 15 ){
            if(sb.substring(0,2).equals("37") || sb.substring(0,2).equals("34")){
                tmp = sb.charAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
                sb.reverse();
                int t;
               for(int i = 0; i < sb.length(); i  += 2){
                   t = Integer.parseInt((sb.substring(i,i+2).toString()));
                   
               }
                


            }
        }

        System.out.println("false");
        return false;
    }
    public static void main(String[] args){

        validateAMXNumber("376402761821986");
        
	}
}
