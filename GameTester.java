import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class GameTester { 


    public static void main(String[] args) {
        
        List<Game> games = new ArrayList<Game>();

        games.add(new Connect4());
        games.add(new NumberGuesser());

        System.out.println("Welcome to the games program. Here are a listing of games. Would you like to play a random game?");

        System.out.println("Connect 4");
        System.out.println("Number Guesser");

        System.out.print("Y/n? ");

        Scanner keyboard = new Scanner(System.in);

        if(keyboard.nextLine().equalsIgnoreCase("Y")){
            games.get((int)(Math.random()*2)).play();
        }


    }




}