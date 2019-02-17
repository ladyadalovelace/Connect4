import java.util.Scanner;

public class Connect4 implements Game
{
    private boolean isRedTurn; //red is X, blue is O
    private String[][] board = new String[6][7];
    private int[] top = {6, 6, 6, 6, 6, 6, 6};

    private int row, col; //last played

    private boolean gamestart = false; 

    public Connect4() {
        //random turn
        isRedTurn = ((int)(Math.random()*1)) == 1 ? true : false;
    
    }

    //empties board.
    public void emptyBoard() { 

        board = new String[6][7];
        top = new int[7];
        gamestart = false;
    }

    //inserts piece into board, sets new turn to opposite color
    public void insertPiece() {
        if(isValidMove()){
            board[--top[col]][col] = (isRedTurn) ? "X" : "O";
            isRedTurn = !isRedTurn;
        }
    }

    public boolean isValidMove() { 
        //figure out if the row/col area is the top of the filled array
        if(top[col] > 0) return true;
        else 
            return false;
    }

    //row win

    public boolean rowWin(){

       // System.out.println("checking rows");

        int count = 3;
        int check = 3;
        String val = board[row][col];
        int newCol = col-1;
        while(newCol >=0 && check != 0){
            if (val.equals(board[row][newCol])) count --;
            if(count == 0) return true; 
            newCol --;
            check --;
        }
        newCol = col + 1;
        count = 3;
        check = 3;
        while(newCol < 7 && check != 0){
            if (val.equals(board[row][newCol])) count --;
            if(count == 0) return true; 
            newCol ++;
            check --;
        }
        return false;
    }

    public boolean colWin(){
     //  System.out.println("checking cols");
        int count = 1;
        String val = board[row][col];
        for(int i = board.length-1; i > 0; i--){
        if (val.equals(board[i][col]) && board[i][col].equals(board[i-1][col])) count ++;
            if(count >= 4) return true;
        }
        return false;
    }


    //top down
    public boolean leftDiagWin () { 
       // System.out.println("checking left diag");
        if(!gamestart) return false;
        int count = 1;
        String val = board[row][col];

        //check going up, left
        int newrow = row - 1;
        int newcol = col - 1;
        while(newrow >=0 && newcol >= 0)  {
            count += (val.equals(board[newrow][newcol])) ? 1 : 0;
            newrow --;
            newcol--;
        }

        //check going down, right
        newrow = row + 1;
        newcol = col + 1;
        while(newrow < 6 && newcol < 7)  {
            count += (val.equals(board[newrow][newcol])) ? 1 : 0;
            newrow ++;
            newcol ++;
        }

        //check if count > 4
        if(count >= 4) return true;
        else return false;
    }

    public boolean rightDiagWin() { 
      //  System.out.println("checking right diag");
        if(!gamestart) return false;
        int count = 1;
        String val = board[row][col];

        //check going up, right
        int newrow = row - 1;
        int newcol = col + 1;
        while(newrow >=0 && newcol < 7)  {
            count += (val.equals(board[newrow][newcol])) ? 1 : 0;
            newrow --;
            newcol ++;
        }

        //check going down, left
        newrow = row + 1;
        newcol = col - 1;
        while(newrow < 6 && newcol >= 0)  {
            count += (val.equals(board[newrow][newcol])) ? 1 : 0;
            newrow ++;
            newcol --;
        }

        //check if count > 4
        if(count >= 4) return true;
        else return false;
    }

    public void printBoard() { 
        int row = 0;
        for(String[] r : board) {
            System.out.print(row + "\t");
            row ++;
            for(String c: r)
            {
                if(c != null){
                    System.out.print(c +"\t");
                }
                else {
                    System.out.print(" \t");
                }
                
            }
            System.out.println();
        }

        for(int i = 0; i < board[0].length; i++) { 
            System.out.print("\t" + i);
        }
        System.out.println();
        
    }
    public String turnStatement() { 
        return (isRedTurn) ? "Red's turn (X)" : "Blue's turn (O)";
    }

    //Interface method 1: play()

    public void play() { 
        Scanner keyboard = new Scanner(System.in); 
        System.out.println("\t\t\t\t*****CONNECT 4*****\t\t\t\t");
        while(!isGameOver() && gamestart){
            System.out.println("It's " + turnStatement());
            System.out.print("Your move (col): ");
            col = keyboard.nextInt();
            keyboard.nextLine();

            while(!isValidMove()){
                System.out.println("invalid move");
                System.out.println("It's " + turnStatement());
                System.out.print("Your move (col): ");
                col = keyboard.nextInt();
                keyboard.nextLine();
            }
            insertPiece();
            row = top[col];
            printBoard();
        }

        String winner = !isRedTurn ? "Red" : "Blue";
        System.out.println(winner + " wins!");


    }




    //Interface method 2: isGameOver()
    // the game is over when there's a win left diag, right diag, row, or col...
    public boolean isGameOver() { 
        if(gamestart){
            return leftDiagWin() || rightDiagWin() || rowWin() || colWin();
        }
        
        else {
            gamestart = true;
            return false;
        }
    }
}