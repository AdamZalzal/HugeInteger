/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package lab1_2;

import java.util.Random;
/**
 *
 * @author adamzalzal
 */
public class HugeInteger {
    Number head; //dummy head
    Number tail; //dummy tail
    int size; //# of digits
    public HugeInteger(String val) throws IndexOutOfBoundsException //constructor from string
    {
        this.head = new Number(null,null,0); //dummy head
        this.tail = new Number(null, null,0); // dummy tail
        int i=0,size1=val.length(),start=0; //counter
        this.size = val.length(); //set # of digits
        char c; //character storage
        Number k = this.head; //linked list interation variable
        c = val.charAt(0);
        
        if(c=='-')
                {
                   k.value = 1;
                   this.size-=1;
                   start = 1;
                }
        else
        {
            k.value = 0;
        }
       boolean flag = true;
       for(i=start;i<size1;i++) //loop through length of string
       {
           
            c = val.charAt(i); //look at each character
            if(c=='0'&&flag) //check if its a leading 0
            {
                this.size-=1; //decrease size and skip
                continue;
            }
            else if(c!='0')
            {
                flag=false;
            }
            k.next = new Number(null,k,c); //store the value of each character-48

            
            if(k.next.value<0)
            {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
            else if(k.next.value>=10)
            {
                throw new IndexOutOfBoundsException("Invalid index.");
            }
            k = k.next; //move to next
       }
       if(this.size==0)
       {
           this.size = 1;
           this.head.next = new Number(this.tail,k,0);
       }
       
        this.tail.prev=k; //tail to last node
        k.next = this.tail; //connect last node to tail
        
    }
    public HugeInteger() //empty constructor
    {
        this.head = new Number(null,null,0); //dummy head
        this.tail = new Number(null, null,0);//dummy tail
    }
    public HugeInteger(int n)
    {
        this.head = new Number(null,null,0); //dummy head
        this.tail = new Number(null, null,0); //dummy tail
        Number k = this.head; //set counter to head
        int i;
        Random num = new Random(); //new variable of type random
        int randomNum = num.nextInt(9)+1; //random number between 1-9 that is not 0
        k.next = new Number(null,k,randomNum); //new element of list with value of that #
        k = k.next; // move to the next
        for(i=1;i<n;i++) //move throught remaining digits
        {
            Random num1 = new Random(); //new random num
            int randomNum1 = num1.nextInt(9); //between 0-9
            k.next= new Number(null,k,randomNum1); //new element
            k = k.next;// move to the next
        }
        this.tail.prev=k; //connect tail to last node
        k.next = this.tail; //connect last node to tail
        this.size = n; //size n
    }
    public HugeInteger(int n, String s)
    {int i;
    this.head = new Number(null,null,0); //dummy head
        this.tail = new Number(null, null,0);//dummy tail
        Number k = this.head;
        this.size = n;
        for(i=0;i<n;i++)
        {
           k.next= new Number(null,k,0);
           k = k.next;
           
        }
        this.tail.prev=k;
        k.next = this.tail;
    }
    public HugeInteger add(HugeInteger h) 
    {int i,val,carryout = 0,count,count2; //initiating variables
        HugeInteger sum = new HugeInteger(); //new HugeInteger
        Number k,j; //node countrs
        Number l = sum.tail; //node counter for tail of new integer
       if(h.head.value == 1 && this.head.value ==0) //if statements to check if integers are positive or negative
       {
          h.head.value=0; // if h is negative and this is positive, make h positive and subtract it from this
          sum =  this.subtract(h);
          h.head.value=1;
          return sum;
          
       }
       else if(h.head.value == 0 && this.head.value ==1) //if h is positive and this is negative
       {
           this.head.value=0; //make this positive
           sum = h.subtract(this); //subtract this from h
           this.head.value=1;
           return sum;
       }
       
       if(h.size>this.size) //depending on which integer is larger
       {
           count = this.size; //assign size to a count variable
           count2 = h.size;
            k = this.tail.prev; //assign last node to a counter variable
         j = h.tail.prev;
         sum.size = h.size; //set size of new integer to size of largest input integer
      }
       else 
       {
           count=h.size;
         count2 = this.size;
            j = this.tail.prev;
         k = h.tail.prev;
         sum.size = this.size;
       }
      
           for(i=0;i<count;i++) //for size of smaller integer
           {
              
              val = k.value + j.value + carryout; //add sum equivalent nodes in each list and add any carry
              
              if(val>=10) // if the sum of the nodes is greater than 10
              {
                  val = val%10; //store least significant digit
                  carryout = 1; //set carry  to 1
              }
              else
              {
                  carryout = 0; //else carry is 0
              }
              l.prev = new Number(l,null,val); //make new node for new integer with sum of input nodes
              k=k.prev; //iterate through each list
              j=j.prev;
              l=l.prev;
              
           }
           if(h.size ==this.size) // if the sizes of the two inputs are equal than skip
           {
               
           }
           else{
           for(int z = count; count<count2;count++) //add the remaining digits from larger integer
              {
                  val = j.value+carryout;
                   if(val>=10)
              {
                  val = val%10;
                  carryout = 1;
              }
              else
              {
                  carryout = 0;
              }
                  
                  l.prev = new Number(l,null,val);
                  l=l.prev;
                  j=j.prev;
              }
           }
      if (carryout==1) //if at end of process carry = 1
      {
          l.prev= new Number(l,sum.head,1); //make new node with value 1
          sum.head.next = l.prev; //connect newest node to head
          sum.size+=1; //increase size of sum by one
      }
      else
      {
          sum.head.next = l; //connect last node to head
      }
      
       if(this.head.value==1 && h.head.value==1) //if both input variables are negative the result must be negative
        {
            sum.head.value = 1;
            
           
        }
              return sum; //return sum
    }
    public HugeInteger subtract(HugeInteger h)
    {   int count, count2, i, val,carryout=0;
        HugeInteger sum = new HugeInteger(); //new HugeInteger
        Number k,j,m; //node countrs
        Number l = sum.tail; //node counter for tail of new integer
         if(this.CompareTo(h)==0) //if this is the same as h return new huge int of value 0
        {
            sum = new HugeInteger("0");
           
            return sum;
        }
        if(h.head.value == 1 && this.head.value ==0) //if h is negative and this is positive
        {
            h.head.value = 0; //make h positive
            sum = this.add(h); //add it to this
            h.head.value=1;
            return sum;
        }
         if(h.head.value == 0 && this.head.value ==1) //if this is negative and h is positive
         {
             this.head.value = 0; //make this positive
             sum = this.add(h); //add this to h
             this.head.value=1;
             sum.head.value=1; //make the result negative
             return sum;
         }
         if(h.head.value==1 && this.head.value==1) //if both are negative
         {
             h.head.value=0;
             sum = this.add(h); //make h positive and add it to this
             h.head.value=1;
             return sum;
         }
       if(this.CompareTo(h)==-1) //depending on which integer is larger
       {
           count = this.size; //assign size to a count variable
           count2 = h.size;
            k = this.tail.prev; //assign last node to a counter variable
         j = h.tail.prev;
         sum.size = h.size; //set size of new integer to size of largest input integer
        sum.head.value=1;
      }
       else
       {
           
           count=h.size;
         count2 = this.size;
            j = this.tail.prev;
         k = h.tail.prev;
         sum.size = this.size;
       }
      
           for(i=0;i<count;i++) //loop through smaller input
           {
               if(j.value<k.value) //if the result of the two digits is less than 0
               {
                   m = j.prev; //set counter
                   
                   
                   while(m.value==0 && m.prev!=null) //count until next nonzero digit 
                   {
                       m.value=9; //set each 0 inbetween as 9
                       m=m.prev; //move back one
                       
                   }
                   m.value= m.value -1; //take one from next nonzero digit
                   carryout = 10; //make carryout 10
               }
               else{ //else carryout is 0
                   carryout =0;
               }
                val = j.value - k.value + carryout; // subtract the two equivalent nodes in each list and add any carry
              
             
              l.prev = new Number(l,null,val); //make new node for new integer with result of input nodes
              k=k.prev; //iterate through each list
              j=j.prev;
              l=l.prev; 
           }
           if(h.size ==this.size) // if the sizes of the two inputs are equal than skip
           {
               
           }
           else{
               carryout = 0;
           for(int z = count; count<count2;count++) //add the remaining digits from larger integer
              {
                  val = j.value+carryout;
                   if(val>=10)
              {
                  val = val%10;
                  carryout = 1;
              }
              else
              {
                  carryout = 0;
              }
                  
                  l.prev = new Number(l,null,val);
                  l=l.prev;
                  j=j.prev;
              }
           }
           sum.head.next = l; //connect last node to head
        return sum;
    }
    public HugeInteger multiply(HugeInteger h)
    {
        int i,val,carryout = 0,flag; //initiating variables
        HugeInteger sum = new HugeInteger((h.size+this.size+1),""); //new HugeInteger
      
        Number k,j; //node countrs
        Number p = sum.tail; //node counter for tail of new integer
        Number l =p;
       
        if(this.size==1 && this.head.next.value ==0) //if this is 0 return new huge int of value 0
        {
            sum = new HugeInteger("0");
            return sum;
        }
        if(h.size==1 && h.head.next.value ==0) //if h is 0 return new huge int of value 0
        {
            sum = new HugeInteger("0");
            return sum;
        }
        if(this.head.value!=h.head.value) //if signs of integers do not match product will be negative
        {
            sum.head.value=1;
        }
         if(h.size>this.size) //depending on which integer is larger
       {
          
            k = this.tail.prev; //assign last node to a counter variable
         j = h.tail.prev;
          flag = 0;
      }
       else 
       {
          
            j = this.tail.prev;
         k = h.tail.prev;
         flag =1;
       }
         
         while(j.prev!=null) //loop through bigger input
           {
                     
                     carryout = 0; //set carryout to 0
                     l=p; //set a second counter
                     if(flag == 0) //flag variable to recall which list is assigned to 'k'
                     {
                         k = this.tail.prev; //reset k to last item
                     }
                     else{
                         k = h.tail.prev;
                     }
                while(k.prev!=null) //loop through smaller list
                {
                    
                    val = j.value*k.value +carryout; //find product of digits and add carryout
                    l.prev.value+=val%10; //find remainder when divided by 10
                    carryout = val/10 + l.prev.value/10; //carryout = carry from product + carry from adding to sum of this digit
                    l.prev.value = l.prev.value%10; //make sure this digit is of value <10
                    k=k.prev; //move through smaller input
                    l = l.prev; //move through product
                    
                }
                if(carryout>0) //if there is a carryout add it
                {
                    l.prev.value+=carryout;
                }
                p=p.prev; //move through second counter of product
                j=j.prev; //move through larger input
                
      }
       return sum;
    }
    public int CompareTo(HugeInteger h)
    {
      int flag=1;
        Number j,k;
        j = this.head.next; //initialize counters
        k = h.head.next;
        if(h.head.value>this.head.value)
        {
            return 1;
        }
        else if(this.head.value>h.head.value)
        {
            return -1;
        }
        if(this.head.value==1 && h.head.value==1){
            flag = -1;
        }
        if(this.size>h.size) //if this size is bigger
        {
            return (1*flag); 
        }
        else if(h.size>this.size)// if h size is bigger
        {
            return (-1*flag);
        }
        else{//else if sizes are equal
            while(j.value == k.value && (k.next!=null))// while items are equal
            {
                k=k.next; //loop through
                j=j.next;
            }
            if(j.value>k.value) // at end of while if last digit of this is greater
            {
                return (1 * flag);
            }
            else if(k.value>j.value)//if last digit of h is greater
                return (-1 *flag);
            }
        return 0; //else return 0
        
    }
    
    public String toString()
    {
        String output=""; //initialize empty output string
       
        Number k; //initialize node counter
        
       
        k = this.head; //set counter to head
        if(this.size==1) //if huge int is one digit long
        {
            output+=this.tail.prev.value; //add value to output and return
            return output;
        }
        
       
        k=this.head;
        boolean flag = true;
        if(k.value==1) //if the value of first node is less than 0 (because of ASCII)
        {
            output+='-'; //add the sign to the front
            for(k=this.head.next;k.next!=null;k=k.next) //loop through remaining list 
        { 
                if(k.value==0 &&flag==true) //check for leading 0
                {
                    continue; //skip it
                }
                else if(k.value!=0)
                {
                    flag=false;
                }
            output += k.value; //add each digit to the output
        }
        }
        else{
            for(k=this.head.next;k.next!=null;k=k.next) //else loop through list
        {
                if(k.value==0 &&flag) //check for leading 0
                {
                    continue; //skip it
                }
                else if(k.value!=0)
                {
                    flag=false;
                }
            output += k.value; //add each digit to output
        }
        }
        
        return output; //return output
    }
}
