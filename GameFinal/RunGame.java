import java.util.Scanner;
import java.util.Stack;

public class RunGame {

   Scanner kbd = new Scanner(System.in);
   public int N;
   String StartTower = "";
   String TargetTower = "";
   int count = 0;
   int x, X;
   Stack<Integer>[] tower = new Stack[4];
   
   public static void main(String[]args) throws Exception {
      RunGame game1 = new RunGame();
      game1.start();
   }
   
   public void start() throws Exception {
      System.out.println("Enter number of disks: ");
      int disks = kbd.nextInt();
      N = disks;
      int base = 2;
      int minTurns = 1;
      while(disks != 0) {
         minTurns *= base;
         disks--;
      }
      int turns = minTurns -1;
      System.out.println("Minimum turns: " + turns );
      tower[1] = new Stack<Integer>();
      tower[2] = new Stack<Integer>();
      tower[3] = new Stack<Integer>();
      RunGame(N);
   } 
   // Pushing disks into stack
   public void RunGame(int n) throws Exception {
      // Accepting number of disks
      boolean running = true;
      for(int d = n; d > 0; d--) 
         tower[1].push(d);
      display();
      while(running == true) {
         if(!checkUserWon()) { 
            if(checkUserInput()) {
               checkMove();
            }
         } else {
            running = false;
         }
         display();
      }
   }
   public boolean checkUserInput() throws Exception {
      System.out.print("Enter starting tower: ");
      StartTower = kbd.next();
      if(!StartTower.toLowerCase().equals("a") 
         && !StartTower.toLowerCase().equals("b") 
         && !StartTower.toLowerCase().equals("c")) {
            System.out.println("Choose a different tower.");
            return false;
      }
      System.out.print("Enter target tower: ");
      TargetTower = kbd.next();
      if(!TargetTower.toLowerCase().equals("a") 
         && !TargetTower.toLowerCase().equals("b") 
         && !TargetTower.toLowerCase().equals("c") 
         || TargetTower == StartTower) {
            System.out.println("Swap to different tower.");
            return false;
      }
      return true;
   }
   public int convertLetterToValue(String str) {
      int num = 0;
      switch(str) {
         case "a":
            num = 1;
            break;
         case "b":
            num = 2;
            break;
         case "c":
            num = 3;
            break;
            
         default:
            break;
      }
      return num;
   }
   public void checkMove() throws Exception {
      int StartIndex = convertLetterToValue(StartTower);
      int TargetIndex = convertLetterToValue(TargetTower);
      int StartPeek, TargetPeek;
      if(tower[StartIndex].size() > 0) {
         StartPeek = tower[StartIndex].peek();
      } else {
         StartPeek = 0;
      }
      if(tower[TargetIndex].size() > 0) {
         TargetPeek = tower[TargetIndex].peek();
      } else {
         TargetPeek = 0;
      }
      if(StartPeek < TargetPeek || TargetPeek == 0) {
         tower[TargetIndex].push(StartPeek);
         tower[StartIndex].pop();
         count++;
         System.out.println("Succesfully swapped towers.");
         
      } else {
         System.out.println("Swap with different tower.");
      }
   }
   public boolean checkUserWon() {
      if(tower[1].size() == 0 && tower[2].size() == 0) {
         System.out.println("You finished in " + count + " moves.");
         return true;
      }
      return false;
   }
   public void display() {
      System.out.println("   A   |   B   |   C   ");
      System.out.println("_______________________");
      for(int i = N -1; i >= 0; i--) {
         String t1 = " ", t2 = " ", t3 = " ";
         
         try {
            t1 = String.valueOf(tower[1].get(i));
         } catch(Exception e) {
            
         }
         try {
            t2 = String.valueOf(tower[2].get(i));
         } catch(Exception e) {
         
         }
         try {
            t3 = String.valueOf(tower[3].get(i));
         } catch(Exception e) {
         
         }
         System.out.println("   " +t1+ "   |   " +t2+ "   |   " +t3);
      }
   
      System.out.println("Turns: "+ count);
   }
}