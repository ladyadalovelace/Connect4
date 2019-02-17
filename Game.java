public interface Game { 

    //plays the Game
    public abstract void play();

    //determine if game is over (not necessarily won, just over)
    public abstract boolean isGameOver();
}