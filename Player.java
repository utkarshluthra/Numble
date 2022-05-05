public class Player {
    private String name;
    private int guess;
    private int score;
    private int roundScore; // Guess number for the round
    public int RoundGuessArr[] = new int[7]; // An array of numbers to store the history of guesses in each round

    // Constructor
    Player(String name){
        this.name="";
        this.score=0;
        this.guess=0;
    }
    //Accessor Methods
    public String getName(){
        return name;
    }
    public int getGuess(){
        return guess;
    }
    public int getScore(){
        return score;
    }
    public int getRoundScore(){
        return roundScore;
    }
    // Modifier Methods
    public void setName(String username){
        name = username;
    }
    public void addScore(int scorePoints){
        score += scorePoints;
    }
    public void setGuess(int userGuess){
        guess = userGuess;
    }
    public void setRoundScore(int roundScore){
        roundScore = roundScore;
    }
    public void addRoundScore(int roundScore){
        roundScore += roundScore;
    }
}