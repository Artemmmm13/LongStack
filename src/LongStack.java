
public class LongStack {
   private LongStack top;
   private long data;
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

   public void push (long a) {
      LongStack newNode = new LongStack();
      newNode.data = a;

      if (top != null) {
         newNode.next = top;
      }
      top = newNode;

   }

   public long pop() {
      return 0;
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

