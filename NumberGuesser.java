import java.util.Scanner;


public class NumberGuesser implements Game{

    private int rando = (int) (Math.random() * 100) + 1;
    private int userGuess = -1;

    public void play(){

            System.out.println("Welcome to the guessing game!");
            
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Guess my number!");

            int guesses = 0;

            while(!isGameOver()){
                System.out.print("Enter a number from 1-100 please: ");
                userGuess = keyboard.nextInt();
                keyboard.nextLine();
                guesses ++;
            }

            System.out.println("You win!!");
            System.out.println("You took: " + guesses + " guesses to figure out my number.");

    }

    public boolean isGameOver(){

        if(userGuess == rando) return true;
        else{
            if(userGuess < rando) { 
                System.out.println("Too low!");
            }
            else {
                System.out.println("Too big!");
            }

            return false;
        }



    }


}