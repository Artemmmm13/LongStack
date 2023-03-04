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

   LongStack() {
      this.top = null;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return this;
   }

   public boolean stEmpty() {
      return top == null;
   }

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
         throw new NoSuchElementException("The given stack is empty,push element into it;\nOtherwise nothing can be popped.");
      }
      else{
         long value = top.data;
         top = top.next;
         return value;
      }
   }

   public void op (String s) {
      switch (s) {
         case "+" -> {
            long b = pop();
            long a = pop();
            push(a + b);
         }
         case "-" -> {
            long b = pop();
            long a = pop();
            push(a - b);
         }
         case "*" -> {
            long b = pop();
            long a = pop();
            push(a * b);
         }
         case "/" -> {
            long b = pop();
            long a = pop();
            if (b == 0){
               throw new ArithmeticException("Division by zero is impossible");
            }
            push(a / b);
         }
         default -> throw new IllegalArgumentException("The given operator is invalid: " + s);
      }


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
      if (pol == null || pol.trim().isEmpty()) {
         throw new IllegalArgumentException("The given string is either null or contains whitespace characters only");
      }
      else {
         LongStack newStack = new LongStack();
         String[] chars = pol.split("\\s+");
         for (String ch : chars) {
            if (ch.matches("-?\\d+")) {
               newStack.push(Long.parseLong(ch));
            } else {
               try {
                  newStack.op(ch);
               } catch (NoSuchElementException e) {
                  throw new NoSuchElementException("Not enough numbers for operation" + ch);
               } catch (IllegalArgumentException e) {
                  throw new IllegalArgumentException("The expression contains invalid operation symbols" + ch);
               }
            }
         }

         if (newStack.stEmpty()) {
            throw new IllegalArgumentException("The expression contains too many numbers");
         }

         long result = newStack.pop();

         if (!newStack.stEmpty()) {
            throw new IllegalArgumentException("The given expression has too few numbers");
         }
         return result;
      }
   }
}

