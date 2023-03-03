import java.util.NoSuchElementException;
import java.util.Stack;

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

   LongStack() {
      this.top = null;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return this;
   }

   public boolean stEmpty() {
      return false;
   }
   // in the push method
   public void push (long a) {
      LongStack newNode = new LongStack();
      newNode.data = a;

      if (top != null) {
         newNode.next = top;
      }
      top = newNode;

   }

   public long pop(){
      if (top == null){
         throw new NoSuchElementException("The given stack is empty,push element into it, otherwise nothing can be popped");
      }
      else{
         long value = top.data;
         top = top.next;
         return value;
      }
   }

   public void op (String s) {

   }

   public long tos() {
      return 0;
   }

   @Override
   public boolean equals (Object o) {
      return true;
   }

   @Override
   public String toString() {
      return null;
   }

   public static long interpret (String pol) {
      return 0;
   }

}

