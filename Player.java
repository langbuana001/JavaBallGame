import java.util.*;

public class Player{

    private int numOfHits;
    private int currentScore;
    private Map<String, Integer> stats = new HashMap<String, Integer>();

    public Player(){
        numOfHits = 0;
        currentScore = 0;
        stats.put("BasicBall", 0);
        stats.put("ShrinkBall", 0);
        stats.put("BounceBall", 0);
        stats.put("SplitBall", 0);
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

}