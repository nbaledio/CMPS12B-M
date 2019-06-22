//ID: 1574354
//Nathan Baledio

import java.io.*;
import java.util.Scanner;

class ChessBoard{
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
		  int x = 0; //User input column
		  int y = 0; //User input row
		  int counter = 1;
		  int arraycounter = 0;
		  LinkedList list = new LinkedList();
		  lineNumber++;

         // trim leading and trailing spaces, then add one trailing space so 
         // split works on blank lines
         String line = in.nextLine().trim() + " "; 

         // split line around white space 
         String[] token = line.split("\\s+"); 
		 int n = token.length;		 
		 
		 //Deletes colon on token if it contains one
		 for(int i=0; i < n; i++){
			if(token[i].contains(":") == true){
				token[i] = token[i].replace(":", "");
			}
		 }
		 
		 Chesspiece[] array = new Chesspiece[(token.length-2)/3];
		 
		 //creates Nodes for inputs and adds them to the linked list
		 for(int i = 0;i < n; i++){
			 //Sets user input column
			 if(counter == 1){
				 x = Integer.parseInt(token[i]);
				 counter++;
				 continue;
			 }
			 //Sets user input row
			 if(counter == 2){
				 y = Integer.parseInt(token[i]);
				 counter++;
				 continue;
			 }
			 //Checks input and creates a new Chesspiece with the assignede coordinates and type based on input
			 if(counter%3 == 0){
				 switch(token[i]){
					 
					 case "K": array[arraycounter] = new King();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "k": array[arraycounter] = new King();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "Q": array[arraycounter] = new Queen();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "q": array[arraycounter] = new Queen();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "B": array[arraycounter] = new Bishop();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "b": array[arraycounter] = new Bishop();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "R": array[arraycounter] = new Rook();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "r": array[arraycounter] = new Rook();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "N": array[arraycounter] = new Knight();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = false;
					 list.insert(array[arraycounter]);
					 break;
					 
					 case "n": array[arraycounter] = new Knight();
					 i++;
					 array[arraycounter].column = Integer.parseInt(token[i]);
					 i++;
					 array[arraycounter].row = Integer.parseInt(token[i]);
					 array[arraycounter].color = true;
					 list.insert(array[arraycounter]);
					 break;
					 
					 default:;
				 }
				arraycounter++;
				counter+=3;
				continue; 
			 }
			 
		 }
		
		//Checks for valid Chessboard
		if(list.CheckValid() == true){
			//Checks for a piece on given coordinate
			if(list.CheckPiece(x,y).row != -86){
				Chesspiece holder = list.CheckPiece(x,y);
				out.print(holder.letter());
				//Checks attack range if there is a piece on the given coordinate
				if(list.CheckAttackRange(list.CheckPiece(x,y)) == true){
					out.println(" y");
				}else{
					out.println(" n");
				}
			}else{
				out.println("-");
			}
		}else{
			out.println("Invalid");
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
	
	//Method that checks if there is a Chesspiece on the given coordinate
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
			if(A.isAttacking(current.data) == true) {
				return true;
			}
		}
		return false;
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