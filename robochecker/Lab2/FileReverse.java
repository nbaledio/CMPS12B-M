//Nathan Baledio
//ID 1574354
import java.io.*;
import java.util.Scanner;
class FileReverse{
		 public static String stringReverse(String s) {
			 String string = s;
			 String reverse=""; 
			 for(int i=string.length()-1;i>=0;i--){
				 reverse+=string.charAt(i);
			 }
			 return reverse;
		 }
   public static void main(String[] args) throws IOException{

      int lineNumber = 0;

      // check number of command line arguments is at least 2
      if(args.length < 2){
         System.out.println("Usage: java –jar FileTokens.jar <input file> <output file>");
         System.exit(1);
      }

      // open files
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      // read lines from in, extract and print tokens from each line
      while( in.hasNextLine() ){
         lineNumber++;

         // trim leading and trailing spaces, then add one trailing space so 
         // split works on blank lines
         String line = in.nextLine().trim() + " "; 

         // split line around white space 
         String[] token = line.split("\\s+");  

         // print out tokens       
         int n = token.length;
         for(int i=0; i<n; i++){
            token[i]=stringReverse(token[i]);
         }
		 for(int i=0; i<n; i++){		 
            out.println(token[i]);
         }
		
		 }
		// close files
		in.close();
		out.close();
      }


   }



