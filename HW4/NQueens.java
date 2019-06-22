//Nathan Baledio
//ID: 1574354

import java.io.*;
import java.util.Scanner;

class NQueens{
   public static void main(String[] args) throws IOException{
	   int  boardsize = 0; //int value that holds the NxN board size

      // check number of command line arguments is at least 2
      if(args.length < 2){
         System.out.println("Usage: java –jar NQueens.jar <input file> <output file>");
         System.exit(1);
      }

      // open files
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      // read lines from in, extract and print tokens from each line
      while( in.hasNextLine() ){
         // trim leading and trailing spaces, then add one trailing space so 
         // split works on blank lines
         String line = in.nextLine().trim() + " "; 

         // split line around white space 
         String[] token = line.split("\\s+");  //array holds tokens of all inputs
		 boardsize = Integer.parseInt(token[0]); //Sets boardsize to size N
		 Queen[] Queens = new Queen[boardsize]; //Array that holds N queens. Automatically resets for every new line of input
		 
		 for(int i = 0; i < Queens.length; i++){ //Loop that fills the array of N Queens with their default values ((0,0), not placed)
			 Queens[i] = new Queen();
		 }
		 
		 int a = 0; //Index of Queens array, used to keep track of current Queen being placed
		 
		 for(int i = 1;i < token.length;i+=2){ //For loop that creates Queens for all inputs
			 Queens[a].column = Integer.parseInt(token[i]); //Sets column;
			 Queens[a].row = Integer.parseInt(token[i+1]); //Sets row
			 Queens[a].isplaced = true; //Places Queen on board
			 a++; //Increments index of Queens array
		 }
		 
		 int FirstQueenIndex = a; // Value to keep track of the index of the first Solution Queen
		 boolean validinput = true;
		 
		 //Code below checks for a valid board of input pieces
		 
		 for(int i = 0; i < a; i++){
			 if(Queens[i].column <1 || Queens[i].column > boardsize){ //Checks if inputs queens are within column constrictions
				 validinput = false;
				 break;
			 }
			 if(Queens[i].row <1 || Queens[i].row > boardsize){ //Checks if input queens are within row constrictions
				 validinput = false;
				 break;
			 }
		 }
		 
		 for(int i = 0; i < a; i++){
			 if(validinput == false){ //If board is not valid, break this loop
				 break;
			 }
			 for(int j = 0; j < Queens.length; j++){ //Checks to see if input queen is in attack range of other input Queens
				 if(Queens[i].IsAttacking(Queens[j], boardsize) == true && Queens[i] != Queens[j]){
					 validinput = false;
					 break;
				 }
			 }
			 
		 }
		 
		 if(validinput == false){ //For an invalid board, print no solution and continue to the next line of input
			 out.println("No solution");
			 continue;
		 }
		 
		 //At this point, the boardsize should be set, and the Queens from the input line should be placed. Now a solution must be found
		 
		 boolean solution = false;
		 FindQueenColumn(Queens[a], Queens); //Places first Solution Queen in first available column
		 for(int i = 0; i < Queens.length; i++){ //Loop that compares current Solution Queen to every Queen on the board
			 if(Queens[a].row > boardsize){ //Condition that checks if the Solution Queen has been moved outside of the board length
					 if(a == FirstQueenIndex){ //If this Queen is the First Solution Queen, then break the loop. There is no solution
						 break;
					 }else{
						 Queens[a].ResetQueen(); //Reset current Solution Queen's position
						 a--; //Decrement index of Current Solution Queen
						 Queens[a].MoveQueenRow(); //Move previous Solution Queen up one row
						 i = -1; //Reset loop
						 continue;
					 }
			 }
			 if(Queens[a].IsAttacking(Queens[i],boardsize) == true && Queens[a] != Queens[i]){ //Checks is current Solution Queen is in attack range of other Queens
				 Queens[a].MoveQueenRow(); //If true, moves current Solution Queen up one row
				 i = -1; //Resets loop
				 continue;
			 }
			 if(i == Queens.length-1){ //Checks if current compared Queen is the last Queen to be compared in the array
				if(a == Queens.length-1){ //If the last Solution Queen is placed successfully, then a solution exists
					solution = true;
					break;
				}
				 a++; //Increments Solution Queen Index
				 Queens[a].PlaceQueen();
				 FindQueenColumn(Queens[a], Queens); //Sets next Solution Queen to next available column
				 i = -1;//Resets loop
			 }
		 }
		 if(solution == false){
			 out.println("No solution");
		 }else{ 
			 int properorder = 1; //Integer value that makes sure solution is printed in order of ascending column
			 for(int i = 0;i < Queens.length; i++){
				 if(Queens[i].column == properorder){
					 if(properorder == 1){
						out.print(Queens[i].column + " "  + Queens[i].row);
						if(properorder==boardsize){
							break;
						}
						properorder++;
						i=-1;
						continue;
					 }else{
						 if(properorder==boardsize){
							 out.println(" " + Queens[i].column + " "  + Queens[i].row);
							 break;
						 }else{
							 out.print(" " + Queens[i].column + " "  + Queens[i].row);
							 properorder++;
							 i=-1;
							 continue;
						 }
					 }
				}
		 
			}
		 }
	  }

      // close files
      in.close();
      out.close();
   }
   
   static void FindQueenColumn(Queen A, Queen[] B){ //Method that places Queen in an available column
	    for(int i = 0; i < B.length; i++){ //for loop that finds an available column that is not occupied by another Queen
			 if(A.column == B[i].column && A != B[i]){ //Compares 1st Solution Queen's column with current Queen in list
				 A.MoveQueenColumn(); //Move the 1st Solution Queen to the right one column if the column is already occupied
				 i=-1; //reset the loop to the beginning
			 }
		 }
   }
}

//Queen Class
class Queen {
	int column = 0; // Stores column position
	int row = 0; //Stores row position
	boolean isplaced = true; //Checks to see if piece is placed on the board
	
	public Queen() {} //Default constructor
	
	public Queen(int column, int row) { //Constructor to set column and row
		this.column = column; //Sets Queen's column to user-input column
		this.row = row; //Sets Queen's row to user-input row
	}
	
	public Queen(int column, int row, boolean isplaced) { //Constructor to set column, row, and boolean value to see if it is placed on the board
		this.column=column; //Sets Queen's column to user-input column
		this.row=row; //Sets Queen's row to user-input row
		this.isplaced=isplaced; //Sets Queen's placement to on the board or off-the board
	}
		
	//Boolean method checks all 8 attacking directions. Returns true if Queen is in attack range of another queen and false otherwise
	boolean IsAttacking (Queen M, int x) { //Queen M is Queen to compare current Queen to and x is the board size

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
		}
		checkC = column; //Resets Column Number checker value
		checkR = row; //Resets Row Number checker value
		
		//Checks for Queens in top-left diagonal attack range
		while(checkC >= 1 || checkR <= x) {
			if (checkC==M.column && checkR == M.row &&  M.isplaced == true) {
				return true;
			}
			checkC-=1;
			checkR+=1;
		}
		checkC = column; //Resets Column Number checker value
		checkR = row; //Resets Row Number checker value
		
		//Checks for Queens in top-right diagonal attack range
		while(checkC <= x || checkR <= x) {
			if (checkC==M.column && checkR == M.row &&  M.isplaced == true) {
				return true;
			}
			checkC+=1;
			checkR+=1;
		}
		checkC = column; //Resets Column Number checker value
		checkR = row; //Resets Row Number checker value
		
		//Checks for Queens in bottom-right diagonal attack range
		while(checkC <= x || checkR >= 1) {
			if (checkC==M.column && checkR == M.row && M.isplaced == true) {
				return true;
			}
			checkC+=1;
			checkR-=1;
		}
		return false;
	}
	
	//Method that moves Queen up by one Row
	void MoveQueenRow() {
		row+=1;
	}
	
	void MoveQueenColumn(){
		column+=1;
	}
	
	//Method that places Queen on board, indexed at (1,1)
	void PlaceQueen(){
		column = 1;
		row = 1;
		isplaced = true;
	}
	
	//Resets Queen's position to (1,1) and removes from board
	void ResetQueen(){
		column = 0;
		row = 0;
		}
 	}