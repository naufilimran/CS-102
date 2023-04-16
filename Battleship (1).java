import java.util.Scanner;

public class Battleship {
	  
	public static void main(String[] args) {
		//calling the main method
		final int ship_size = 4;
		final int dimension=10;
		char[][] board = new char[dimension][dimension];
		char[][] random_array=new char[dimension][dimension];
		createBoard(board);
		printBoard(board,random_array);
		gamePlay(board,random_array);
	}


		 //Initialize our board with dashes (empty positions)
  		public static void createBoard(char[][] board) {
  		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = '_';
		}
	}
	}


		//Make a function to draw the board according to the specifications
		public static void printBoard(char[][] board,char[][] random_array) {
			System.out.println("Battleship board");
		
			System.out.print(" ");
		
			for (char col = 'A'; col <= 'J'; col++) {
				System.out.print(" "+col);
		}
		
			System.out.println();

		//making the board and printing it out
		for(int i = 0; i < board.length; i++) {
			//The inner for loop prints out each row of the board
			System.out.print(i);
			for(int j = 0; j < board[i].length; j++) {
				System.out.print("|");
				System.out.print(board[i][j]);
				
			}
			//This print statement makes a new line so that each row is on a separate line
			System.out.print("|");
			System.out.println();
		}
	
		//calling the random method
		generateRandom(board,random_array);
	}
	
		public static void generateRandom(char [][] board,char[][] random_array) {
			//generate a random number between 0 and 1, if it is 1 then i will be using any row to generate 4 random slots on it and if it is zero I will use the columns
			int random=(int)(Math.random()*2);
			
			if (random==1){
				random_row(board,random_array);
			}

			else{
				random_column(board,random_array);
			}
		}

		public static void random_row(char[][] board,char[][] random_array){
			//method designed for rows to calculate 4 positions in a particular row.
			int random_no=(int)(Math.random()*10);
			int column_no=random_no%10;
			int row_no=random_no/10;
			if(row_no <=3){
         	random_array[row_no][column_no]='X';
         	for (int i=1;i<=3;i++){
            	random_array[row_no+i][column_no]='X';

      }
      }
      		else{
         		random_array[row_no][column_no]='X';
         	
         	for (int i=1;i<=3;i++){
            	random_array[row_no-i][column_no]='X';
      }

}
}
   		public static void random_column(char[][] board,char[][] random_array){
   			//method designed for rows to calculate 4 positions in a particular row.
   			int random_no=(int)(Math.random()*100);
			int column_no=random_no%10;
			int row_no=random_no/10;
			if(column_no <=3){
         		random_array[row_no][column_no]='X';
         	for (int i=1;i<=3;i++){
            	random_array[row_no][column_no+i]='X';

      }
      }
      		else{
         		random_array[row_no][column_no]='X';
         	
         	for (int i=1;i<=3;i++){
            	random_array[row_no][column_no-i]='X';
      }
   }
   	}	


	// starting the game here.
   		public static void gamePlay(char[][] board,char[][] random_array) {
   			Scanner in = new Scanner(System.in);

			System.out.println("Let's play Battleship Game");

			String input;

			boolean game_end=false;

			
			int guess=0;
			
			while(!game_end){
				int row_input = 0 ;
				int col_no;
				int col_input;
				char col_error='-';
				while(true) {
					System.out.println("Enter coordinate to target");

					input=in.nextLine();
					
					char[] arr = input.toCharArray();
					//checking for all the possible errors and when to throw an error					
					//checking if the user enters such as A11
					if (arr.length>2){
         				String string_1 = Integer.toString(Character.getNumericValue(arr[1]));
         				String string_2= Integer.toString(Character.getNumericValue(arr[2]));
         				String string_final=string_1+string_2;
         				int c = Integer.parseInt(string_final);
         				row_input=c;
         				col_error=arr[0];
         				col_input=(int)arr[0];
      				}
      				
      				else{
         				row_input= Character.getNumericValue(arr[1]);
         				col_error=arr[0];
         				col_input=(int)arr[0];
      				}
      				
      				//using this to check if input is out of errors or not
      				if (col_error <'A' || col_error >'J' || row_input<0 || row_input >9){
         				System.out.println("This position is off the bounds of the board! Try again.");
      				}

      				col_no=col_input-65;
      				//checking is user has already made a position at that move
      				if(board[row_input][col_no] == '#' ) {
						System.out.println("you have already made a move at this position! Try again.");
						
					}
      				//checking if the user does not enter the column number as an alphabet
      				boolean character= Character.isLetter(col_error);
      				if (character==false) {
      					System.out.println("Not a valid input");
      				}

      				//checking for inputs that are not within our range for inputs
      				for(char characters: arr) {
      					if (characters<48 && characters>57 && characters<65 && characters>74){
      						System.out.println(" Not a valid input");
      				}
      				}
      				
      				if (row_input >=0 && row_input <=10 && col_no >=0 && col_no<=10){
      					break;
      				}
      			}
      			//calling gamestart where we begin game as well as printing the board
      			gameStart(board, row_input,col_no,guess,game_end,random_array);
      			printBoard(board,random_array);
      		}
      	}
      				
	//starting the game over here.
      	public static int gameStart(char[][] board, int row, int col,int guess,boolean game_end,char[][] random_array){
      				
    		//checking for if the user gets the rows right
    		if (row <=3){
				if(board[row][col] == random_array[row][col] && board[row+1][col] ==random_array[row+1][col] && board[row+2][col]== random_array[row+2][col] && board[row+3][col]== random_array[row+3][col] ) {
				System.out.println("Congratulations, you have won");
				guess++;
				board[row][col]='X';
				board[row+1][col]='X';
				board[row+2][col]='X';
				board[row+3][col]='X';
				System.out.println("It took you "+ guess);
				game_end=true;
				return guess;
			}
		
		} 	
			
			else{
					
				if((board[row][col] == random_array[row][col] && board[row-1][col] ==random_array[row-1][col] && board[row-2][col]== random_array[row-2][col] && board[row-3][col]== random_array[row-3][col] )) {
				System.out.println("Congratulations, you have won");
				board[row][col]='X';
				board[row-1][col]='X';
				board[row-2][col]='X';
				board[row-3][col]='X';
				guess++;
				System.out.println("It took you "+ guess);
				game_end=true;
				return guess;
			}
		
		}
	
   

		//checking if the users gets the columns right
			if (col<=3){
				if(board[row][col] == random_array[row][col] && board[row][col+1] ==random_array[row][col+1] && board[row][col+2]== random_array[row][col+2] && board[row][col+3]== random_array[row][col+3]) {
				System.out.println("Congratulations, you have won");
				guess++;
				board[row][col]='X';
				board[row][col+1]='X';
				board[row][col+2]='X';
				board[row][col+3]='X';
				System.out.println("It took you "+ guess+ "guess to win");
				game_end=true;
				return guess;
			}
		
		}


		else{
			
			if(board[row][col] == random_array[row][col] && board[row][col-1] ==random_array[row][col-1] && board[row][col-2]== random_array[row][col-2] && board[row][col-3]== random_array[row][col-3]) {
			System.out.println("Congratulations, you have won");
			guess++;
			System.out.println("It took you "+ guess);
			board[row][col]='X';
			board[row][col-1]='X';
			board[row][col-2]='X';
			board[row][col-3]='X';
			game_end=true;
			return guess;
		}
		}
		//if user input is not equal 4 slots randomly chosen, then changing the dash with a # and adding the number of guesses.
		if (board[row][col]!=random_array[row][col]){
			board[row][col]='#';
			guess++;
			return guess;
		}
	
	return guess;
}
}