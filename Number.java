/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_2;

/**
 *
 * @author adamzalzal
 */
public class Number {
    int value; //value
    char sign; //sign
    Number prev; //previous
    Number next; //next
    Number(Number n, Number p, char v) //Digit constructor when input is a char
    {
        next = n;
        prev = p;
        value = v-48; //subtract 48 to get integer value
    }
     Number(Number n, Number p, int v) // when input is integer
    {
        next = n;
        prev = p;
        value = v;
        
    }
    
}
