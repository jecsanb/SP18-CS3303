package com.example.jb963962.cardvalidator;//Author: Jecsan Blanco
//Copyright: Jblancolicano1@buffs.wtamu.edu
import java.util.regex.Pattern;
import java.util.Scanner;
public  class AMXCCValidator
{
    public static boolean validate(String cardNumber){
        //returns true if the number is valid otherwise false;
        //check if the given string matches needed patters
        if(!Pattern.matches("(37|34)[0-9]{13}",cardNumber))
            return false;
        //Now we know that we have exactly 15 chars and a valid pattern.
       
       // so we don't  have to deal with an on object
       char[] nums = cardNumber.toCharArray();
 
       int sum = 0;
       int num = 0;
       //going backwards on the array, better than reversing.
       for( int i = 13; i >= 0; i--){
           // conversion from char to a number, thanks Haiduk.
           num = nums[i] - '0';
           if( i % 2 != 0){
               num *= 2;
               // System.out.printf("%d", ( num > 9) ? (num - 9):(num));
               sum +=  ( num > 9) ? (num - 9):(num);
           }else{
               // System.out.printf(" + %d + ",num);
               sum += num;
           }
       
        }
        sum += nums[14] - '0';
        // System.out.printf("%c",nums[14]);
        // System.out.println("sum is: " + sum );
        return  (sum % 10 == 0);
    }
 
    public static void main(String[] args){
 
           String  ccNumber;
           Scanner input = new Scanner(System.in);
           String ans;
           while(true){
               System.out.printf("Enter a AMX CC number to validate: ");
               ccNumber = input.nextLine();
               ccNumber.replace(" ","");
               System.out.printf("ccNumber is %s.\n",validate(ccNumber) ? ("valid"):("invalid"));
 
               System.out.printf("Try another (Y,n) ");
               ans = input.nextLine();
               if(ans.contains("n") || ans == null){
                   System.out.println("Bye!");
                   break;
               }
 
           }
 
 
 
 
    }
}
