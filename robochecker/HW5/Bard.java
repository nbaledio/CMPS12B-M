import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Set;
import java.util.ArrayList;

class Bard{
	public static void main(String[] args) throws IOException{
	  // holds shakespeare.txt file	
	  File shakespeare = new File("shakespeare.txt");
	  // reader to read through shakespeare.txt file
	  BufferedReader reader = new BufferedReader(new FileReader(shakespeare));
	  // creates a hashtable. uses a word as the key and its frequency as the value
	  Hashtable <String, Integer> hashtable = new Hashtable<String, Integer>();
	  // string that stores text of current line it is reading in shakespeare.txt
	  String line1 = reader.readLine();
	  // check number of command line arguments is at least 2
      if(args.length < 2){
         System.out.println("Usage: java –jar NQueens.jar <input file> <output file>");
         System.exit(1);
      }
	  
	  // reads through shakespeare.txt line by line. for current line, it tokenizes words
	  // into an array of strings. Next it replaces punctuation marks/symbols with whitespace
	  // and converts the word into lowercase. It then checks to see if the word exists in the
	  // hashtable. if it does, it increments the frequency, otherwise it adds the word with a
	  // frequency of 1
	  while(line1 != null){
		  line1 = line1.replace("?" , " ");
		  line1 = line1.replace("," , " ");
		  line1 = line1.replace("." , " ");
		  line1 = line1.replace("!" , " ");
	   	  line1 = line1.replace(":" , " ");
		  line1 = line1.replace(";" , " ");
		  line1 = line1.replace("[" , " ");
		  line1 = line1.replace("]" , " ");
		  String[] token1 = line1.trim().split ("\\s+");
		  for(int i = 0; i < token1.length; i++){
			  token1[i] = token1[i].toLowerCase();
				if(hashtable.containsKey(token1[i]) == true){
					hashtable.replace(token1[i], hashtable.get(token1[i]), hashtable.get(token1[i])+1);
				}else{
					hashtable.put(token1[i],1);
				}
		  }	 
		 line1 = reader.readLine();
	  }
	  
	  //At this point, a hashtable should be created
	  
	  
	  Set<String> keys = hashtable.keySet();
	  //for(String key: keys){
		  //System.out.println(key);
	  //}
	  
	  //System.out.println(hashtable);
	  
	  // open files
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));
	  
	  //Code below will scan and parse input file.
	  
	  while( in.hasNextLine() ){
		// trim leading and trailing spaces, then add one trailing space so 
        // split works on blank lines
        String line = in.nextLine().trim() + " "; 
        // split line around white space 
        String[] token = line.split("\\s+");  //array holds tokens of all inputs
		//Checks for a proper input
		if(token.length != 2){
			out.println("-");
			continue;
		}
		// iterates through hashtable and adds words of the given length to an
		//arraylist
		ArrayList<Word> arr = new ArrayList<Word>();
		int checker = 0;
		for(String key: keys){
			if(key.length() == Integer.parseInt(token[0])){
				Word dummy = new Word(key, hashtable.get(key));
				arr.add(dummy);
				checker++;
			}
		}
		
		Collections.sort(arr, new SortByFrequency());
		
		if(Integer.parseInt(token[1]) >= arr.size() || checker == 0 || Integer.parseInt(token[0]) <= 0){
			out.println("-");
			continue;
		}else{
			Word solution = arr.get(Integer.parseInt(token[1]));
			out.println(solution.word);
		}
	  }
	  // close files
      in.close();
      out.close();	
	}
}

class Word{
	String word;
	int number;
	Word(String word, int number){
		this.word = word;
		this.number = number;
	}
	int getNumber(){
		return number;
	}
	String getWord(){
		return word;
	}

}

class SortByFrequency implements Comparator<Word>{
	public int compare(Word a, Word b){
		if(a.number != b.number){
			return b.number - a.number;
		}else{
			return a.word.compareTo(b.word);
		}
	}
}