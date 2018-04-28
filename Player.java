import java.util.*;

public class Player{

    private int numOfHits;
    private int currentScore;
    private String ballTypeWithMostHits;

    public Player(){
        numOfHits = 0;
        currentScore = 0;
    }

    public void addHits(){
        numOfHits = numOfHits + 1;
    }

    public int getHits(){
        return numOfHits;
    }

    public void addScore(int number){
        currentScore = currentScore + number;
    }

    public int getScore(){
        return currentScore;
    }

    public void setMostHits(String type) {
        ballTypeWithMostHits = type;
    }

    public String getMostHits() {
        return ballTypeWithMostHits;
    }

}