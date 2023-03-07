// one more submission that improved all the remarks you gave -> added context s to op and pol to interpret
import java.util.NoSuchElementException;
public class LongStack {
   // private instance variable 'top' which defines the top of the stack
   private LongStack top;
   // private instance variable data, that will hold the data of newly-created object of the class
   private long data;
   /**
    * private instance variable next, which point to the next element in the stack of
    * particular object in the LongStack class
    */
   private LongStack next;

   public static void main (String[] argum) {

   }

   // the linked-list implementation of stack
   LongStack() {
      this.top = null;
      this.next = null;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
     LongStack newStack = new LongStack();

// created new LongStack instance and write logic for new instance
      if (this.top != null){
         // if top != null I assign cloned object into LongStack object
         newStack.top = new LongStack();
         newStack.top.data = this.top.data;
         LongStack cur = this.top.next;
         LongStack prev = newStack.top;
         while (cur != null){
            // since I copied the first object to clone.top I started from the second
            LongStack newStack1 = new LongStack();
            newStack.data = cur.data;
            prev.next = newStack1;
            prev = newStack1;
            cur = cur.next;
         }
      }
      return newStack;
   }

   // self-explanatory method if top is null -> stack is empty, otherwise -> not
   public boolean stEmpty() {
      return top == null;
   }

   public void push (long a) {

      // first step is to create new LongStack node
      LongStack newNode = new LongStack();
      // then assign given value 'a' as its data
      newNode.data = a;

      // then we check if the stack isn't empty, if not we assign the current top as its .next
      if (top != null) {
         newNode.next = top;
      }

      // otherwise we say that the top of the stack is our new node
      top = newNode;

   }

   public long pop(){

      // self-explanatory condition, we can't pop anything if the stack is empty
      if (top == null){
         throw new NoSuchElementException("The given stack is empty,push element into it;" +
                 "\nOtherwise nothing can be popped.");
      }
      // otherwise, we create a variable that will contain data of current top
      else{
         long value = top.data;
         // then we reassign top to the .next of the current top
         top = top.next;
         // finally returning data of the previous top
         return value;
      }
   }

   /*
   * in this method we always one action: popping two elements out of the stack
   * and performing the given operation, this is mainly the sense of postfix or
   * RPN notation*/

   public void op (String s) {
      switch (s) {
         case "+" :
            try {
               long b = pop();
               long a = pop();
               push(a + b);
            } catch (NoSuchElementException e){
               throw new NoSuchElementException("Stack is empty, nothing can be popped" + s);
            }
            break;
         case "-" :
            try {
               long b1 = pop();
               long a1 = pop();
               push(a1 - b1);
            } catch (NoSuchElementException e){
               throw new NoSuchElementException("Stack is empty, nothing can be popped" + s);
            }
            break;
         case "*" :
            try {
               long b2 = pop();
               long a2 = pop();
               push(a2 * b2);
            } catch (NoSuchElementException e){
               throw new NoSuchElementException("Stack is empty, nothing can be popped" + s);
            }
            break;
         case "/" :
            try{
               long b3 = pop();
               long a3 = pop();
               // here we have to make sure that b3 != 0
               if (b3 == 0){
                  throw new ArithmeticException("Division by zero is impossible" + s);
               }
               push(a3 / b3);
            } catch (NoSuchElementException e){
               throw new NoSuchElementException("Stack is empty, nothing can be popped" + s);
            }
            break;
            // if operator is some illegal symbol we throw an exception
         default :
            throw new IllegalArgumentException("The given operator is invalid: " + s);
      }
   }

   // self-explanatory method if stack isn't empty we return top.data, otherwise -> exception
   public long tos() {
      if (top != null) return top.data;
      throw new NoSuchElementException("The stack is empty, nothing can be taken form top of it");
   }

   @Override
   public boolean equals (Object o) {
      // basic check if o is not an instance of LongStack -> they are not equal
      if (!(o instanceof LongStack)) {
         return false;
      }

      // casting object o to the class LongStack
      LongStack one = (LongStack) o;
      // creating two objects of LongStack class, in which we will clone o object and this object
      LongStack curStack = null;
      LongStack nextStack = null;

      try {
         curStack = (LongStack) this.clone();
         nextStack = (LongStack) one.clone();
      } catch (CloneNotSupportedException e) {
         return false;
      }

      // check if both stacks are empty
      if (curStack.stEmpty() && nextStack.stEmpty()) {
         return true;
      }

      // popping elements out of the stack if they are not equal -> stacks are not equal
      while (!curStack.stEmpty() && !nextStack.stEmpty()) {
         if (curStack.pop() != nextStack.pop()) {
            return false;
         }
      }
      // additional check in case one stack was bigger than another
      return curStack.stEmpty() && nextStack.stEmpty();
   }


   @Override
   public String toString() {
      // created new object of class StringBuilder
      StringBuilder stringStack = new StringBuilder();
      // append this string for the sake of beauty
      stringStack.append("Stack [");

      LongStack cur = top;
      /*after having assigned cur to the topmost element of the stack
      * I iterate through appending data of the cur element to the stringStack*/
      while (cur != null){
         stringStack.append(cur.data);
         if (cur.next != null){
            stringStack.append(", ");
         }
         cur = cur.next;
      }
      stringStack.append("]");
      return stringStack.toString();
   }


   public static long interpret(String pol) {
      if (pol == null || pol.isEmpty()) {
         throw new RuntimeException("The given string is either null or contains whitespace characters only:" + pol);
      }
      /*
      * here I check whether pol matches any sequence of characters that contain (0-9) digit
      * and that is longer than one character*/

      else if (!pol.matches(".*\\d.*")) {
         // the input string contains no digits
         throw new RuntimeException("The given string does not contain any digits:" + pol);
      } else {
         // if string didn't satisfy previous conditions, I create new instance of LongStack
         LongStack newStack = new LongStack();
         /*
         * array of substrings which was created from pol from which were removed whitespaces from left and right side
         * the resulted array is array of substring which are split using whitespace character as a delimiter*/
         String[] chars = pol.trim().split("\\s+");
         boolean hasDigits = false;

         for (String ch : chars) {
            /*if array contains elements which are digits represented by a string
            * I push it to the newStack*/
            if (ch.matches("-?\\d+")) {
               hasDigits = true;
               newStack.push(Long.parseLong(ch));

               /*if string satisfied this condition it means that it is an operator
               *,so I apply op method to it*/
            } else if (ch.matches("[+\\-*/]")) {
               try {
                  newStack.op(ch);
               } catch (NoSuchElementException e) {
                  throw new RuntimeException("Not enough numbers for operation:" + pol);
               } catch (RuntimeException e) {
                  throw new RuntimeException("The expression contains invalid operation symbols:" + pol);
               }
            } else {
               throw new RuntimeException("Invalid symbol: " + ch);
            }
         }

         if (!hasDigits) {
            // the input string contains no digits
            throw new RuntimeException("The given string does not contain any digits:" + pol);
         } else if (newStack.stEmpty()) {
            throw new RuntimeException("The expression contains too few numbers:" + pol);
         }

         long result = newStack.pop();

         if (!newStack.stEmpty()) {
            throw new RuntimeException("The expression contains too many numbers:" + pol);
         }
         return result;
      }
   }







}
