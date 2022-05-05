import java.lang.Math;
import java.util.Scanner;

//The main gameplay of the Numble game
public class Game {
    Player Player1 = new Player("User");
    Player Player2 = new Player("Computer");
    Scanner inObj = new Scanner(System.in);
    NumberGenerator random = new NumberGenerator();
    private int guessNo;

    public int getGuessNo(){
        return guessNo;
    }
    public void addGuess(){
        guessNo++;
    }

    // Welcome message and name input
    public void welcome(){
        System.out.println("Welcome to the Numble Game");
        System.out.println("Enter your name: ");
        Player1.setName(inObj.nextLine());
        Player2.setName("Computer");
    }

    // Binary Search Algorithm for computer
    public int binarySearchCount(int arr[], int x){
        int l = 0, r = arr.length - 1, count=0, i=0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x){
                Player2.RoundGuessArr[i]=arr[m];
                return count;
            }
            if (arr[m] < x){
                Player2.RoundGuessArr[i]=arr[m];
                l = m + 1;
                count++;
            }else{
                Player2.RoundGuessArr[i]=arr[m];
                r = m - 1;
                count++;
            }
            i++;
        }
        return -1;
    }

    // Function to determine number of turns it took for reaching key
    public static int computer(int key){
        int [] arr = new int[100];
        for (int i = 0; i<100; i++){
            arr[i]=i+1;
        }
        Game ob = new Game();
        int compscore = ob.binarySearchCount(arr, key);
        return compscore;
    }

    // Method to score for correct guess
    public void score(int key){
        if(Player1.getGuess()==key){
            switch(getGuessNo()){
                case 0: Player1.addScore(18);break;
                case 1: Player1.addScore(12);break;
                case 2: Player1.addScore(8);break;
                case 3: Player1.addScore(5);break;
                case 4: Player1.addScore(3);break;
                case 5: Player1.addScore(2);break;
            }
        }
        if(Player2.RoundGuessArr[getGuessNo()]==key){
            switch(getGuessNo()){
                case 0: Player2.addScore(18);break;
                case 1: Player2.addScore(12);break;
                case 2: Player2.addScore(8);break;
                case 3: Player2.addScore(5);break;
                case 4: Player2.addScore(3);break;
                case 5: Player2.addScore(2);break;
            }
        }
    }

    //Method to give output of cumulative score after each round
    public void scoreOutput(){

        System.out.print(Player1.getName() + "'s Score: ");
        System.out.println(Player1.getScore());
        System.out.print(Player2.getName() + "'s Score: ");
        System.out.println(Player2.getScore());
    }

    // Gameplay of each round
    public void round(){
        int key = random.genRandom();
        Player2.setRoundScore(computer(key));        
        Player1.setRoundScore(1);
        int guessLimit = 0;
        boolean win = false;
        int min=0, max=100;
        // Loop for guesses
        while(guessLimit<3 && win==false){
            int abandonNumberIndicator = random.genRandom20();

            //For computer to abandon game
            if (abandonNumberIndicator==20){
                switch(getGuessNo()){
                    case 1: Player1.addScore(18);break;
                    case 2: Player1.addScore(12);break;
                    case 3: Player1.addScore(8);break;
                    case 4: Player1.addScore(5);break;
                    case 5: Player1.addScore(3);break;
                    case 6: Player1.addScore(2);break;
                }
                win=true;
                break;
            }
            System.out.print("Guess a number: ");
            // To give warnings wrt input given by user
            try{
                Player1.setGuess(inObj.nextInt());
                if(Player1.getGuess()==999){
                    switch(getGuessNo()){
                        case 0: Player2.addScore(18);break;
                        case 1: Player2.addScore(12);break;
                        case 2: Player2.addScore(8);break;
                        case 3: Player2.addScore(5);break;
                        case 4: Player2.addScore(3);break;
                        case 5: Player2.addScore(2);break;
                    }
                    win=true;
                    break;
                }
                if(Player1.getGuess()>100 || Player1.getGuess()<1){
                    System.out.println("Enter a number between 1-100: ");
                    Player1.setGuess(inObj.nextInt());
                }
                if(Player1.getGuess()>max || Player1.getGuess()<min){
                    System.out.println("Warning! Number out of possible range.");
                }
            }
            catch(Exception e){
                System.out.println("Incorrect input! Enter a number between 1-100: ");
                Player1.setGuess(inObj.nextInt());
            }
            // For user to abandon game
            
            Player1.RoundGuessArr[getGuessNo()]=Player1.getGuess();

            if(Player1.getGuess()==key){
                System.out.println(Player1.getName()+" Wins");
                score(key);
                win=true;
                break;
            }
            if (Player2.RoundGuessArr[getGuessNo()]==key){
                System.out.println(Player2.getName()+" Wins");
                score(key);
                win=true;
                break;
            }
            if(Player1.getGuess() < key){
                min=Player1.getGuess();
                Player1.setRoundScore(1);
                System.out.println("Guess a number higher than "+ Player1.getGuess());
            }
            else if(Player1.getGuess() > key){
                max=Player1.getGuess();
                Player1.addRoundScore(1);
                System.out.println("Guess a number lower than "+Player1.getGuess());
            }
            else{
                System.out.println("Error");
            }
            score(key);
            guessLimit++; 
            addGuess();
        }
        // If no winner, finding player closest to key
        if(!win){
            int diff1 = Math.abs(Player1.getGuess()-key);
            int diff2 = Math.abs(Player2.RoundGuessArr[2]-key);
            if(diff1>diff2){
                Player2.addScore(1);
            }
            if(diff2>diff1){
                Player1.addScore(1);
            }
        }
        scoreOutput();
    }

    // Main Function
    public static void main(String args[]){        
        Game gameObj = new Game();
        gameObj.welcome();
        // To iterate across the 4 rounds
        for(int i=1; i<=4; i++){
            System.out.println("Round "+i);
            gameObj.round();
        }
    }
}