//Nathan Baledio
//ID 1574354
import java.io.*;
import java.util.Scanner;

class NQueens{
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

         //Creates a new array to hold tokens that will be converted into int values     
         int n = token.length;
		 int newarray[] = new int[n];
		 
		 //Parses tokens at int values and holds them in a new array
		 for(int i = 0; i < n; i++){		 
           newarray[i] = Integer.parseInt(token[i]);
         }
		 
		 //Divides array of ints into a double array. The first array holds input values in groups. The second holds board size, and initial Queen placement
		 int cutoff = 0;
		 int array1val = 0;
		 int array2val = 0;
		 int[][] values = new int [n/3][3];
		 for(int i = 0; i < n ; i++){
			 cutoff += 1;
			 values[array1val][array2val] = newarray[i];
			 array2val+=1;
			 if(cutoff % 3 == 0){
				 cutoff = 0;
				 array1val += 1;
				 array2val = 0;
			 }
		 }
		 
		 //Solves for every input in text file
		 for(int i = 0; i < (n/3); i++){
			 out.println(CheckQueen(values[i][0], values[i][1], values[i][2]));
			 
		 }
	}
		// close files
		in.close();
		out.close();
}
	
	//Method that solves NQueens problem based on input
	static String CheckQueen(int x, int y, int z) {	
		int N = x; //Holds board size
		int userinputQueenC = y; //Holds initial Queen's Column
		int userinputQueenR = z; //Holds initial Queen's row
		int CurrentQueen = 1;
		boolean SolutionExists = false;
		boolean ProblemSolved = false;
		boolean resetloop = false;
		String answer = "";
		Queen[] NQueens = new Queen[N];{ //Creates the array for N Queens
		NQueens[0] = new Queen(userinputQueenC, userinputQueenR);} //Adds user's Queen to array, indexed at 0
		
		//Checks for a 3x3 board or less, and provides no solution for them
		if(N < 4) {
			ProblemSolved = true;
		} else {
			//Fills the array of NQueens with the rest of the queens, all set to position (1,1) and set to not yet placed
			for(int i = 1; i < N; i++) {
				NQueens[i] = new Queen(1,1, false);
			}
		}
			//Loop that scans through all possible placements of other Queens
			while(ProblemSolved == false) {
				
				//Checks if current selected queen is placed on board. Sets to true if it isn't
				if(NQueens[CurrentQueen].isplaced == false) {
					NQueens[CurrentQueen].isplaced = true; 
				}
					
				// If second Queen has gone through all possible spaces, no solution exists. Exit loop.
				if(NQueens[1].column > N) {
					ProblemSolved = true;
					continue;
					
				}	
				
				//Cycles through all Queens in the array, checking to see if CurrentQueen is in attack range of another
				for(int i = 0; i < NQueens.length; i++) { 
					if(NQueens[CurrentQueen].IsAttacking(NQueens[i],N,N) == true) {//If CurrentQueen is in attack range of another Queen
						
						//Skips checking if CurrentQueen is checking itself and move to next loop
						if(CurrentQueen == i) {
							continue;
						}
						
						//If CurrentQueen has no more possible places
						if(NQueens[CurrentQueen].column == N && NQueens[CurrentQueen].row == N && CurrentQueen !=1) { 
							NQueens[CurrentQueen].ResetQueen();
							CurrentQueen-=1;
							
							//If Next CurrentQueen has no more possible places
							if(NQueens[CurrentQueen].column == N && NQueens[CurrentQueen].row == N) { 
								NQueens[CurrentQueen].ResetQueen();
								CurrentQueen-=1;
							}
						}
					
						
					//If Queen is at top row, reset it  and move to next column. Reset loop
					if(NQueens[CurrentQueen].row == N) { 
						NQueens[CurrentQueen].row = 1;
						NQueens[CurrentQueen].MoveQueenColumn();
						resetloop = true;
						break;
						}
					
					//If Queen is not at top row, just move it up one row
					NQueens[CurrentQueen].MoveQueenRow();
					resetloop = true;
					break;
					} 
			}
				
			//If CurrentQueen had to move its position, restart the loop
			if(resetloop == true) {
				resetloop = false;
				continue;
			}	
					
			//If this is the last Queen to be placed, then there is a solution. Exit the loop.
			if(CurrentQueen == N-1) { 
				SolutionExists = true;
				ProblemSolved = true;
				continue;
			}
				
			//If this is not the last Queen, move to the next and reset
			CurrentQueen+=1; 				
		}
			//Prints Solution to problem
			if(SolutionExists == false) {
				answer = "No solution";
				return answer;
			}else{
				Queen[] solution = new Queen [N]; //Array that holds Queens in proper order
				int columnstart = 1;
				for(int i = 0; i < NQueens.length; i++) {//Cycles through Solution array
					for(int j = 0; j < NQueens.length; j++) {// Scans through NQueens array and places Queens in new array, from lowest column number to highest
						if(NQueens[j].column == columnstart) {
							solution[i] = NQueens[j];
							columnstart += 1;
							break;
						}
					}
				}
			//Prints solution in order, if one exists	
			for(int i = 0; i < solution.length; i++) {
				answer += (solution[i].column + " " + solution[i].row + " ");
				}
			return answer;	
			}
		}
   }
   
//Queen Class
class Queen {
	
	int column; // Stores column position
	int row; //Stores row position
	boolean isplaced = true; //Checks to see if piece is placed on the board
	
	//Default constructor
	public Queen() {} 
	
	//Constructor to set column and row
	public Queen(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	//Constructor to set column, row, and boolean value to see if it is placed on the board
	public Queen(int column, int row, boolean isplaced) {
		this.column=column;
		this.row=row;
		this.isplaced=isplaced;
	}
		
	//Boolean method checks all 8 attacking directions. Returns true if Queen is in attack range of another queen and false otherwise
	boolean IsAttacking (Queen M, int c, int r) {

		//Checks for other Queens in current column
		if(column==M.column &&  M.isplaced == true) {
			return true;
		}
	
		//Checks for other Queens in current row
		if(row==M.row &&  M.isplaced == true) {
			return true;
		}
		int checkC = column; //Checks Column Number
		int checkR = row; //Checks Row Number
		
		//Checks for Queens in bottom-left diagonal attack range
		while(checkC >= 1 || checkR >= 1) {
			if (checkC == M.column && checkR == M.row &&  M.isplaced == true) {
				return true;
			}
			checkC-=1;
			checkR-=1;
			//System.out.println(checkC + " " + checkR);
		}
		checkC = column; //Resets Column Number checker value
		checkR = row; //Resets Row Number checker value
		
		//Checks for Queens in top-left diagonal attack range
		while(checkC >= 1 || checkR <= r) {
			if (checkC==M.column && checkR == M.row &&  M.isplaced == true) {
				return true;
			}
			checkC-=1;
			checkR+=1;
			//System.out.println(checkC + " " + checkR);
		}
		checkC = column; //Resets Column Number checker value
		checkR = row; //Resets Row Number checker value
		
		//Checks for Queens in top-right diagonal attack range
		while(checkC <= c || checkR <= r) {
			if (checkC==M.column && checkR == M.row &&  M.isplaced == true) {
				return true;
			}
			checkC+=1;
			checkR+=1;
			//System.out.println(checkC + " " + checkR);
		}
		checkC = column; //Resets Column Number checker value
		checkR = row; //Resets Row Number checker value
		
		//Checks for Queens in bottom-right diagonal attack range
		while(checkC <= c || checkR >= 1) {
			if (checkC==M.column && checkR == M.row && M.isplaced == true) {
				return true;
			}
			checkC+=1;
			checkR-=1;
			//System.out.println(checkC + " " + checkR);
		}
		return false;
	}
	
	//Method that moves Queen up by one Row
	void MoveQueenRow() {
		row+=1;
	}
	
	//Method that moves Queen to the right by one column
	void MoveQueenColumn() {
		column+=1;
	}
	
	//Resets Queen's position to (1,1) and removes from board
	void ResetQueen(){
		column = 1;
		row = 1;
		isplaced = false;
		}
 	}
 

 

   
 
