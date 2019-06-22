//ID: 1574354
//Nathan Baledio

import java.io.*;
import java.util.Scanner;

class ChessMoves{
	public static void main(String[] args) throws IOException{

      // check number of command line arguments is at least 2
      if(args.length < 2){
         System.out.println("Usage: java –jar FileTokens.jar <input file> <output file>");
         System.exit(1);
      }

      // open files
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));
	  
	  //**PART 1**
	  //Code below is setup. Creates a linkedlist of chesspieces and an array of moves to be executed
	  //-------------------------------------------------------------------------------------------------------------------

      // read lines from in, extract and print tokens from each line
      while( in.hasNextLine() ){
		  int arraycounter = 0; //index for Chesspiece array
		  int arraysize = 0; //size of Chesspiece array
		  int movescounter = 0; //index for moves array
		  int movessize = 0; //size of Moves array
		  LinkedList list = new LinkedList();

         // trim leading and trailing spaces, then add one trailing space so 
         // split works on blank lines
         String line = in.nextLine().trim() + " "; 

         // split line around white space 
         String[] token = line.split("\\s+"); 
		 int n = token.length;	
		 
		 // scans through inputs to find size of array for chessboard pieces
		 for(int i=0;i<n;i++){
			 arraysize++;
			 if(token[i].contains(":") == true){
				 break;
			 }
		 }
		 
		 //scans through inputs to find size of array for movements
		 boolean startcount = false; //
		 for(int i=0;i<n;i++){
			 if (startcount == true){
				 movessize++;
			 }
			 if(token[i].contains(":") == true){
				 startcount = true;
			 }
		 }
		 
		 //Array to hold chesspiece inputs
		 String[] chesspieces = new String[arraysize];
		 
		 //Fills String array of chesspiece inputs
		 for(int i=0;i<n;i++){
			 chesspieces[i] = token[i];
			 if(chesspieces[i].contains(":") == true){
				 chesspieces[i] = chesspieces[i].replace(":","");
				 break;
			 }
		 }
		 
		 //Array that holds moves to be made
		 String[] moves = new String[movessize];
		 
		 //Fills String array of movement inputs
		 startcount = false;
		 for(int i = 0;i<n;i++){
			 if (startcount == true){
				 moves[movescounter] = token[i];
				 movescounter++;
			 }
			 if(token[i].contains(":") == true){
				 startcount = true;
			 }  
		 }
		 
		 //Array to hold chesspieces
		 Chesspiece[] array = new Chesspiece[arraysize/3];

		 // Creates array of chesspieces, then adds them individually to a linkedlist based on inputs
		 for(int i=0; i<chesspieces.length; i++){
			 switch(chesspieces[i]){
				 case "K": array[arraycounter] = new King();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "k": array[arraycounter] = new King();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "Q": array[arraycounter] = new Queen();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "q": array[arraycounter] = new Queen();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "B": array[arraycounter] = new Bishop();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "b": array[arraycounter] = new Bishop();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "R": array[arraycounter] = new Rook();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "r": array[arraycounter] = new Rook();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "N": array[arraycounter] = new Knight();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
				 case "n": array[arraycounter] = new Knight();
					 i++;
					 array[arraycounter].column = Integer.parseInt(chesspieces[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(chesspieces[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
					 default:;
			 }
			 arraycounter++;
		 }
		 
		 //-------------------------------------------------------------------------------------------------------------
		 
		 //**PART 2**
		 //Code below is the actual checking through conditions to see if all moves are valid
		 
		 boolean colorchecker = true; //boolean value used to check that colors are alternating properly (White first, black next, white next...)
		 
		 
		 //1. Checks to see if there is a piece on the space that the player wants to move
		 for(int i=0; i<moves.length;i+=4){
			 if(list.CheckValid() == false){//Checks for a valid board
				out.println(moves[i] + " " + moves[i+1] + " " + moves[i+2] + " " + moves[i+3] + " " + "illegal");
				break;
			 }
			 if (list.CheckPiece(Integer.parseInt(moves[i]),Integer.parseInt(moves[i+1])).row != -86){
				 //2. Checks to see if color is alternating properly
				 if(colorchecker == list.CheckPiece(Integer.parseInt(moves[i]),Integer.parseInt(moves[i+1])).color){
					 // 3. Checks if piece is moving according to the rules (creates dummy chesspiece of destination space and checks if dummy is in attack range)
					 Chesspiece dummy = new Chesspiece();
					 //Changes to opposite color for dummy so it can be scanned properly
					 if(colorchecker == true){
						colorchecker = false;
					}else if(colorchecker == false){
						colorchecker = true;
					}
					 dummy.column = Integer.parseInt(moves[i+2]);
					 dummy.row = Integer.parseInt(moves[i+3]);
					 dummy.color = colorchecker;
					 if(list.CheckPiece(Integer.parseInt(moves[i]),Integer.parseInt(moves[i+1])).isAttacking(dummy) == true){
						 //Checks if piece to be captured is a king
						 if(list.CheckPiece(Integer.parseInt(moves[i+2]),Integer.parseInt(moves[i+3])).letter().equals("k") || list.CheckPiece(Integer.parseInt(moves[i+2]),Integer.parseInt(moves[i+3])).letter().equals("K")){
							 out.println(moves[i] + " " + moves[i+1] + " " + moves[i+2] + " " + moves[i+3] + " " + "illegal");
							 break;
						 }
							//4. Checks if destination space has a piece. If it does it then checks to make sure that piece has a different color
							if(list.CompareColor(list.CheckPiece(Integer.parseInt(moves[i]),Integer.parseInt(moves[i+1])), Integer.parseInt(moves[i+2]),Integer.parseInt(moves[i+3])) == true){
								//5. Checks to see if path is blocked by any other pieces
								if(list.CheckPath(list.CheckPiece(Integer.parseInt(moves[i]),Integer.parseInt(moves[i+1])), dummy) == true){
									 //Changes color back 
									if(colorchecker == true){
										colorchecker = false;
									}else if(colorchecker == false){
										colorchecker = true;
									}
									
									//Checks to see if there is a piece at the target space and deletes it
									if(list.CheckPiece(Integer.parseInt(moves[i+2]),Integer.parseInt(moves[i+3])).row != -86){
										list.delete(list.CheckPiece(Integer.parseInt(moves[i+2]),Integer.parseInt(moves[i+3])));
									}
									//Moves target piece to target space
									list.MovePiece(Integer.parseInt(moves[i]),Integer.parseInt(moves[i+1]),Integer.parseInt(moves[i+2]),Integer.parseInt(moves[i+3]));
									//6. Checks to see if king is in attack range of a piece after move is made		
									if(list.CheckAttackRange(list.FindKing(colorchecker)) == true){
										//Checks for all enemy pieces that have player's king in attack range
										//System.out.println("check");
										if(list.isKinginCheck(list.FindKing(colorchecker)) == false){
											//Changes color for next set of inputs
											if(colorchecker == true){
												colorchecker = false;
											}else if(colorchecker == false){
												colorchecker = true;
											}
											if( i == (moves.length - 4)){
												out.println("legal");
											}
											continue;
										}
									//If king is not in check, then change color for next set of inputs, or print legal if there are no more inputs	
									}else{
										//Changes color for next set of inputs
										if(colorchecker == true){
											colorchecker = false;
										}else if(colorchecker == false){
											colorchecker = true;
										}
										if( i == (moves.length - 4)){
										out.println("legal");
										}
										continue;
									}									
								}
							}
						}
						
					 }
				 }
				 out.println(moves[i] + " " + moves[i+1] + " " + moves[i+2] + " " + moves[i+3] + " " + "illegal");
				 break;
			 }
		 }
		  // close files
		  in.close();
		  out.close();
      }
	}


//Parent class that holds all basic properties of chess pieces
class Chesspiece{
	boolean color; //false = black, true = white
	int column;
	int row;
	boolean isAttacking(Chesspiece A){
		return false;
	}
	boolean isAttacking2(Chesspiece A){
		return false;
	}
	boolean isPathBlocked(Chesspiece A, Chesspiece B){
		return false;
	}
	String letter(){
		return "";
	}
}

class King extends Chesspiece{
	//Takes Chesspiece as an argument and checks if it is in attack range
	boolean isAttacking(Chesspiece A){
		//Checks top space
		if(A.column == column && A.row == row+1 && A.color != color) {
			return true;
		}
		//Checks top-right space
		if(A.column == column+1 && A.row == row+1 && A.color != color) {
			return true;
		}
		//Checks right space
		if(A.column == column+1 && A.row == row && A.color != color) {
			return true;
		}
		//Checks bottom right space
		if(A.column == column+1 && A.row == row-1 && A.color != color) {
			return true;
		}
		//Checks bottom space
		if(A.column == column && A.row == row-1 && A.color != color) {
			return true;
		}
		//Checks bottom-left space
		if(A.column == column-1 && A.row == row-1 && A.color != color) {
			return true;
		}
		//Checks left space
		if(A.column == column-1 && A.row == row && A.color != color) {
			return true;
		}
		//Checks top-left space
		if(A.column == column-1 && A.row == row+1 && A.color != color){
			return true;
		}
		return false;
	}
	
	// Checks if piece is in attack range regardless of color
	boolean isAttacking2(Chesspiece A){
		//Checks top space
		if(A.column == column && A.row == row+1) {
			return true;
		}
		//Checks top-right space
		if(A.column == column+1 && A.row == row+1) {
			return true;
		}
		//Checks right space
		if(A.column == column+1 && A.row == row) {
			return true;
		}
		//Checks bottom right space
		if(A.column == column+1 && A.row == row-1) {
			return true;
		}
		//Checks bottom space
		if(A.column == column && A.row == row-1) {
			return true;
		}
		//Checks bottom-left space
		if(A.column == column-1 && A.row == row-1) {
			return true;
		}
		//Checks left space
		if(A.column == column-1 && A.row == row) {
			return true;
		}
		//Checks top-left space
		if(A.column == column-1 && A.row == row+1){
			return true;
		}
		return false;
	}
	
	boolean isPathBlocked(Chesspiece A, Chesspiece B){
		//Checks top space
		if(A.column == column && A.row == row+1 && A.color == color) {
			if(B.column == column && B.row == row+1){
				return true;
			}
		}
		//Checks top-right space
		if(A.column == column+1 && A.row == row+1 && A.color == color) {
			if(B.column == column+1 && B.row == row+1){
				return true;
			}
		}
		//Checks right space
		if(A.column == column+1 && A.row == row && A.color == color) {
			if(B.column == column+1 && B.row == row){
				return true;
			}
		}
		//Checks bottom right space
		if(A.column == column+1 && A.row == row-1 && A.color == color) {
			if(B.column == column+1 && B.row == row-1){
				return true;
			}
		}
		//Checks bottom space
		if(A.column == column && A.row == row-1 && A.color == color) {
			if(B.column == column && B.row == row-1){
				return true;
			}
		}
		//Checks bottom-left space
		if(A.column == column-1 && A.row == row-1 && A.color == color) {
			if(B.column == column-1 && B.row == row-1){
				return true;
			}
		}
		//Checks left space
		if(A.column == column-1 && A.row == row && A.color == color) {
			if(B.column == column-1 && B.row == row){
				return true;
			}
		}
		//Checks top-left space
		if(A.column == column-1 && A.row == row+1 && A.color == color){
			if(B.column == column-1 && B.row == row+1){
				return true;
			}
		}
		return false;
	}	

	
	String letter(){
		if(color == false){
			return "K";
		}
		return "k";
	}
}


class Queen extends Chesspiece{
	//Takes Chesspiece as an argument and checks if it is in attack range
	boolean isAttacking(Chesspiece A) {
		//Checks top spaces
		for(int i = row+1; i <= 8; i++) {
			if(A.column == column && A.row == i && A.color != color) {
				return true;
			}
		}
		//Checks right spaces
		for(int i = column+1; i <= 8; i++) {
			if(A.column == i && A.row == row && A.color != color) {
				return true;
			}
		}
		//Checks bottom spaces
		for(int i = row-1; i >=1; i--) {
			if(A.column == column && A.row == i && A.color != color) {
				return true;
			}
		}
		//Checks left spaces
		for(int i = column-1; i >= 1; i--) {
			if(A.column == i && A.row == row && A.color != color) {
				return true;
			}
		}
		//Checks top-right spaces
				int j = row+1;
				for(int i = column+1; i<= 8; i++) {
					if(A.column == i && A.row == j && A.color != color) {
						return true;
					}
					j++;
				}
		//Checks bottom-right spaces
				j = row-1;
				for(int i = column+1; i<=8; i++) {
					if(A.column == i && A.row == j && A.color != color) {
						return true;
					}
					j--;
				}
		//Checks bottom-left spaces
				j = row-1;
				for(int i = column-1; i>=1; i--) {
					if(A.column == i && A.row == j && A.color != color) {
						return true;
					}
					j--;
				}
		//Checks top-left spaces
				j = row+1;
				for(int i = column-1; i>=1; i--) {
					if(A.column == i && A.row == j && A.color != color) {
						return true;
					}
					j++;
				}
		return false;
	}
	
	// Checks if piece is in attack range regardless of color
	boolean isAttacking2(Chesspiece A) {
		//Checks top spaces
		for(int i = row+1; i <= 8; i++) {
			if(A.column == column && A.row == i) {
				return true;
			}
		}
		//Checks right spaces
		for(int i = column+1; i <= 8; i++) {
			if(A.column == i && A.row == row) {
				return true;
			}
		}
		//Checks bottom spaces
		for(int i = row-1; i >=1; i--) {
			if(A.column == column && A.row == i) {
				return true;
			}
		}
		//Checks left spaces
		for(int i = column-1; i >= 1; i--) {
			if(A.column == i && A.row == row) {
				return true;
			}
		}
		//Checks top-right spaces
				int j = row+1;
				for(int i = column+1; i<= 8; i++) {
					if(A.column == i && A.row == j) {
						return true;
					}
					j++;
				}
		//Checks bottom-right spaces
				j = row-1;
				for(int i = column+1; i<=8; i++) {
					if(A.column == i && A.row == j) {
						return true;
					}
					j--;
				}
		//Checks bottom-left spaces
				j = row-1;
				for(int i = column-1; i>=1; i--) {
					if(A.column == i && A.row == j) {
						return true;
					}
					j--;
				}
		//Checks top-left spaces
				j = row+1;
				for(int i = column-1; i>=1; i--) {
					if(A.column == i && A.row == j) {
						return true;
					}
					j++;
				}
		return false;
	}
	
	//Method to check if path is blocked. A will be a chesspiece on the board, and B will be a dummy piece representing the target space
	boolean isPathBlocked(Chesspiece A, Chesspiece B){
		boolean foundpiece = false;
		
		//Checks top spaces
		for(int i = row+1; i <= 8; i++) {
			if(B.column == column && B.row == i){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == column && A.row == i) {
				foundpiece = true;
			}
		}
		
		foundpiece = false;
		
		//Checks right spaces
		for(int i = column+1; i <= 8; i++) {
			if(B.column == i && B.row == row){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == i && A.row == row) {
				foundpiece = true;
			}
		}
		
		foundpiece = false;
		
		//Checks bottom spaces
		for(int i = row-1; i >=1; i--) {
			if(B.column == column && B.row == i){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == column && A.row == i) {
				foundpiece = true;
			}
		}
		foundpiece = false;
		
		//Checks left spaces
		for(int i = column-1; i >= 1; i--) {
			if(B.column == i && B.row == row){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == i && A.row == row) {
				foundpiece = true;
			}
		}
		
		foundpiece = false;
		
		//Checks top-right spaces
				int j = row+1;
				for(int i = column+1; i<= 8; i++) {
					if(B.column == i && B.row == j){
					if(foundpiece == true){
					return true;
				}else{
					return false;
					}
				}
				if(A.column == i && A.row == j) {
					foundpiece = true;
				}
					j++;
				}
				
		foundpiece = false;	
		
		//Checks bottom-right spaces
				j = row-1;
				for(int i = column+1; i<=8; i++) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
							return true;
						}else{
							return false;
						}
					}
					if(A.column == i && A.row == i) {
						foundpiece = true;
					}
					j--;
				}
				
		foundpiece = false;		
		
		//Checks bottom-left spaces
				j = row-1;
				for(int i = column-1; i>=1; i--) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
							return true;
						}else{
							return false;
						}
					}
					if(A.column == i && A.row == i) {
						foundpiece = true;
					}
					j--;
				}
				
		foundpiece = false;		
				
		//Checks top-left spaces
				j = row+1;
				for(int i = column-1; i>=1; i--) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
							return true;
						}else{
							return false;
						}
					}
					if(A.column == i && A.row == i) {
						foundpiece = true;
					}
					j++;
				}
		return false;
	}
	String letter(){
		if(color == false){
			return "Q";
		}
		return "q";
	}
}

class Rook extends Chesspiece{
	//Takes Chesspiece as an argument and checks if it is in attack range
	boolean isAttacking(Chesspiece A) {
		//Checks top spaces
		for(int i = row+1; i <= 8; i++) {
			if(A.column == column && A.row == i && A.color != color) {
				return true;
			}
		}
		//Checks right spaces
		for(int i = column+1; i <= 8; i++) {
			if(A.column == i && A.row == row && A.color != color) {
				return true;
			}
		}
		//Checks bottom spaces
		for(int i = row-1; i >=1; i--) {
			if(A.column == column && A.row == i && A.color != color) {
				return true;
			}
		}
		//Checks left spaces
		for(int i = column-1; i >= 1; i--) {
			if(A.column == i && A.row == row && A.color != color) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if piece is in attack range regardless of color
	boolean isAttacking2(Chesspiece A) {
		//Checks top spaces
		for(int i = row+1; i <= 8; i++) {
			if(A.column == column && A.row == i) {
				return true;
			}
		}
		//Checks right spaces
		for(int i = column+1; i <= 8; i++) {
			if(A.column == i && A.row == row) {
				return true;
			}
		}
		//Checks bottom spaces
		for(int i = row-1; i >=1; i--) {
			if(A.column == column && A.row == i) {
				return true;
			}
		}
		//Checks left spaces
		for(int i = column-1; i >= 1; i--) {
			if(A.column == i && A.row == row) {
				return true;
			}
		}
		return false;
	}
	
	//Method to check if path is blocked. A will be a chesspiece on the board, and B will be a dummy piece representing the target space
	boolean isPathBlocked(Chesspiece A, Chesspiece B){
		boolean foundpiece = false;
		//Checks top spaces
		for(int i = row+1; i <= 8; i++) {
			if(B.column == column && B.row == i){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == column && A.row == i) {
				foundpiece = true;
			}
		}
		
		foundpiece = false;
		
		//Checks right spaces
		for(int i = column+1; i <= 8; i++) {
			if(B.column == i && B.row == row){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == i && A.row == row) {
				foundpiece = true;
			}
		}
		
		foundpiece = false;
		
		//Checks bottom spaces
		for(int i = row-1; i >=1; i--) {
			if(B.column == column && B.row == i){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == column && A.row == i) {
				foundpiece = true;
			}
		}
		foundpiece = false;
		
		//Checks left spaces
		for(int i = column-1; i >= 1; i--) {
			if(B.column == i && B.row == row){
				if(foundpiece == true){
					return true;
				}else{
					return false;
				}
			}
			if(A.column == i && A.row == row) {
				foundpiece = true;
			}
		}
		return false;
	}
	String letter(){
		if(color == false){
			return "R";
		}
		return "r";
	}
}

class Bishop extends Chesspiece{
	//Takes Chesspiece as an argument and checks if it is in attack range
	boolean isAttacking(Chesspiece A) {
		//Checks top-right spaces
		int j = row+1;
		for(int i = column+1; i<= 8; i++) {
			if(A.column == i && A.row == j && A.color != color) {
				return true;
			}
			j++;
		}
		//Checks bottom-right spaces
		j = row-1;
		for(int i = column+1; i<=8; i++) {
			if(A.column == i && A.row == j && A.color != color) {
				return true;
			}
			j--;
		}
		//Checks bottom-left spaces
		j = row-1;
		for(int i = column-1; i>=1; i--) {
			if(A.column == i && A.row == j && A.color != color) {
				return true;
			}
			j--;
		}
		//Checks top-left spaces
		j = row+1;
		for(int i = column-1; i>=1; i--) {
			if(A.column == i && A.row == j && A.color != color) {
				return true;
			}
			j++;
		}
		return false;
	}
	
	// Checks if piece is in attack range regardless of color
	boolean isAttacking2(Chesspiece A) {
		//Checks top-right spaces
		int j = row+1;
		for(int i = column+1; i<= 8; i++) {
			if(A.column == i && A.row == j) {
				return true;
			}
			j++;
		}
		//Checks bottom-right spaces
		j = row-1;
		for(int i = column+1; i<=8; i++) {
			if(A.column == i && A.row == j) {
				return true;
			}
			j--;
		}
		//Checks bottom-left spaces
		j = row-1;
		for(int i = column-1; i>=1; i--) {
			if(A.column == i && A.row == j) {
				return true;
			}
			j--;
		}
		//Checks top-left spaces
		j = row+1;
		for(int i = column-1; i>=1; i--) {
			if(A.column == i && A.row == j) {
				return true;
			}
			j++;
		}
		return false;
	}
	
	//Method to check if path is blocked. A will be a chesspiece on the board, and B will be a dummy piece representing the target space
	boolean isPathBlocked(Chesspiece A, Chesspiece B){
		boolean foundpiece = false;
		//Checks top-right spaces
				int j = row+1;
				for(int i = column+1; i<= 8; i++) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
						return true;
					}else{
						return false;
					}
				}
				if(A.column == i && A.row == j) {
					foundpiece = true;
				}
					j++;
				}
				
		foundpiece = false;	
		
		//Checks bottom-right spaces
				j = row-1;
				for(int i = column+1; i<=8; i++) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
							return true;
						}else{
							return false;
						}
					}
					if(A.column == i && A.row == i) {
						foundpiece = true;
					}
					j--;
				}
				
		foundpiece = false;		
		
		//Checks bottom-left spaces
				j = row-1;
				for(int i = column-1; i>=1; i--) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
							return true;
						}else{
							return false;
						}
					}
					if(A.column == i && A.row == i) {
						foundpiece = true;
					}
					j--;
				}
				
		foundpiece = false;		
				
		//Checks top-left spaces
				j = row+1;
				for(int i = column-1; i>=1; i--) {
					if(B.column == i && B.row == j){
						if(foundpiece == true){
							return true;
						}else{
							return false;
						}
					}
					if(A.column == i && A.row == i) {
						foundpiece = true;
					}
					j++;
				}
				return false;
	}	
	String letter(){
		if(color == false){
			return "B";
		}
		return "b";
	}
}

class Knight extends Chesspiece{
	//Takes Chesspiece as an argument and checks if it is in attack range
	boolean isAttacking(Chesspiece A) {
		//Checks top spaces
		if(A.column == column+1 && A.row == row+2 && A.color != color) {
			return true;
		}
		if(A.column == column-1 && A.row == row+2 && A.color != color) {
			return true;
		}
		//Checks right spaces
		if(A.column == column+2 && A.row == row+1 && A.color != color) {
			return true;
		}
		if(A.column == column+2 && A.row == row-1 && A.color != color) {
			return true;
		}
		//Checks bottom spaces
		if(A.column == column+1 && A.row == row-2 && A.color != color) {
			return true;
		}
		if(A.column == column-1 && A.row == row-2 && A.color != color) {
			return true;
		}
		//Checks left spaces
		if(A.column == column-2 && A.row == row+1 && A.color != color) {
			return true;
		}
		if(A.column == column-2 && A.row == row-1 && A.color != color) {
			return true;
		}
		return false;
	}
	
	// Checks if piece is in attack range regardless of color
	boolean isAttacking2(Chesspiece A) {
		//Checks top spaces
		if(A.column == column+1 && A.row == row+2) {
			return true;
		}
		if(A.column == column-1 && A.row == row+2) {
			return true;
		}
		//Checks right spaces
		if(A.column == column+2 && A.row == row+1) {
			return true;
		}
		if(A.column == column+2 && A.row == row-1) {
			return true;
		}
		//Checks bottom spaces
		if(A.column == column+1 && A.row == row-2) {
			return true;
		}
		if(A.column == column-1 && A.row == row-2) {
			return true;
		}
		//Checks left spaces
		if(A.column == column-2 && A.row == row+1) {
			return true;
		}
		if(A.column == column-2 && A.row == row-1) {
			return true;
		}
		return false;
	}
	
	boolean isPathBlocked(Chesspiece A, Chesspiece B){
		//Checks top spaces
		if(A.column == column+1 && A.row == row+2 && A.color == color) {
			if(B.column == column+1 && B.row == row+2){
				return true;
			}
		}
		if(A.column == column-1 && A.row == row+2 && A.color == color) {
			if(B.column == column-1 && B.row == row++){
				return true;
			}
		}
		//Checks right spaces
		if(A.column == column+2 && A.row == row+1 && A.color == color) {
			if(B.column == column+2 && B.row == row+1){
				return true;
			}
		}
		if(A.column == column+2 && A.row == row-1 && A.color == color) {
			if(B.column == column+2 && B.row == row-1){
				return true;
			}
		}
		//Checks bottom spaces
		if(A.column == column+1 && A.row == row-2 && A.color == color) {
			if(B.column == column+1 && B.row == row-2){
				return true;
			}
		}
		if(A.column == column-1 && A.row == row-2 && A.color == color) {
			if(B.column == column-1 && B.row == row-2){
				return true;
			}
		}
		//Checks left spaces
		if(A.column == column-2 && A.row == row+1 && A.color == color) {
			if(B.column == column-2 && B.row == row+1){
				return true;
			}
		}
		if(A.column == column-2 && A.row == row-1 && A.color == color) {
			if(B.column == column-2 && B.row == row-1){
				return true;
			}
		}
		return false;
	}

	
	String letter(){
		if(color == false){
			return "N";
		}
		return "n";
	}
	
	
}

class LinkedList {
	Node head;
	LinkedList() {
		head = null;
	}
	
	//Method to insert another Chesspiece to the linked list
	void insert(Chesspiece A) {
		Node latest = new Node(A);
		latest.next = head;
		head = latest;
	}
	
	//Method to delete a Chesspiece from the linked list
	Node delete(Chesspiece A){
		Node prev = null;
		Node curr = head;
		while(curr.data != A && curr != null){
			prev = curr;
			curr = curr.next;
		}
		if(curr == null){
			return null;
		}
		if(prev == null){
			head = head.next;
		}else{
			prev.next = curr.next;
		}
		return curr;
	}
	
boolean isKinginCheck(Chesspiece A){
	boolean check = false; //Will be used to check if king is in check
	boolean block = false;
	for(Node current = head; current != null; current=current.next){
		// Goes through all pieces and checks individually if King is in attack range of current piece
		if(current.data.isAttacking(A) == true){
			// Goes through all pieces and finds another piece in the attack range
			for(Node current2 = head; current2 != null; current2=current2.next){				
				if(current.data.isAttacking2(current2.data) == true && current2.data != A){
					// If a piece is found, checks if the found piece doesn't block the king 
					if(current.data.isPathBlocked(current2.data, A) == false && current2.data != A){
						check = true;
						break;	
					}else{
						block = true;
						continue;
					}
				}
			}
			if(check == true){
				break;
			} else if(block == true){
				continue;
			}
			check = true;
		}
			if(check == true){
				break;
			}
		}
		
	if(check == true){
		return true;
	}
	return false;
}
	
	//Method that checks if the board is valid. Checks if two input pieces are on the same position, and if all pieces are within the 8x8 restriction
	boolean CheckValid() {
		//Checks if two pieces are on the same spot
		for(Node current = head; current != null; current=current.next) {
			for(Node current2 = head; current2 != null; current2=current2.next) {
				if(current2.next != current.next) {
					if(current2.data.column == current.data.column && current2.data.row == current.data.row) {
						return false;
					}
				}
			}
		}
		//Checks if pieces are on 8x8 board and not on a space out of bounds
		for(Node current = head; current != null; current=current.next) {
			if(current.data.column > 8 || current.data.row > 8 || current.data.column < 1 || current.data.row < 1) {
				return false;
			}
		}
		return true;
	}
	
	Chesspiece FindKing(boolean color){
		Chesspiece empty = new Chesspiece();
		for(Node current = head; current != null; current=current.next) {
			if(color == true){
				if(current.data.letter().equals("k")){
					return current.data;
				}
			}
			if(color == false){
				if(current.data.letter().equals("K")){
					return current.data;
				}
			}
		}
		return empty;
	}
	
	//Method that checks if there is a Chesspiece on the given coordinate. Returns that piece if one exists, or a dummy piece if one doesn't
	Chesspiece CheckPiece(int x, int y) {
		Chesspiece nothing = new Chesspiece();
		nothing.row = -86;
		for(Node current = head; current != null; current=current.next) {
			if(current.data.column == x && current.data.row == y) {
				return current.data;
			}
		}
		return nothing;
	}
	
	//Method that checks to see if given Chesspiece is in attack range of another 
	boolean CheckAttackRange(Chesspiece A) {
		for(Node current = head; current != null; current=current.next) {
			if(current.data.isAttacking(A) == true) {
				return true;
			}
		}
		return false;
	}
	
	//Method to return Chesspiece is CheckAttackRange is true
	Chesspiece getAttackingPiece(Chesspiece A){
		Chesspiece a = new Chesspiece();
		for(Node current = head; current != null; current=current.next) {
			if(current.data.isAttacking(A) == true) {
				return current.data;
			}
		}
		return a;
	}
	
	//Method that moves piece. Searches for piece at given coordinates a,b and moves it to coordinates c,d
	void MovePiece(int a, int b, int c, int d){
		for(Node current = head; current != null; current=current.next) {
			if(current.data.column == a && current.data.row == b){
				current.data.column = c;
				current.data.row = d;
				break;
			}
		}
	}
	
	//Method that checks if there is a piece on target space. If there is, it checks to make sure it is a different color than input piece. Returns true for valid move, false for invalid move
	boolean CompareColor(Chesspiece A, int x, int y){
		for(Node current = head; current != null; current=current.next) {
			if(current.data.column == x && current.data.row == y){
				if(A.color != current.data.color){
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	//Checks to see if attack path is valid. Returns true if there is no piece in the way. False, if there is. A is piece to be moved, B is target space
	boolean CheckPath(Chesspiece A,Chesspiece B){
		for(Node current = head; current != null; current=current.next) {
			if(A.isPathBlocked(current.data, B) == true){
				return false;
			}
		}
		return true;
	}
}

//Node class. Holds Chesspiece value and pointer
class Node{
	Chesspiece data;
	Node next;
	public Node(){
		next = null;
	}
	public Node(Chesspiece data) {
		this.data = data;
		next = null;
	}
}