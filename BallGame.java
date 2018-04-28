/******************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science:   An Interdisciplinary Approach Book
 *  
 *  Run the skeleton code with arguments : 1  basic  0.08
 *******************************************************************************/
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.io.Console;
import java.lang.reflect.Constructor;

public class BallGame { 

    public static void main(String[] args) throws Exception{
  
    	// number of bouncing balls
    	int numBalls = Integer.parseInt(args[0]);
    	//ball types
    	String ballTypes[] = new String[numBalls];
    	//sizes of balls
    	double ballSizes[] = new double[numBalls];
    	
    	//retrieve ball types
    	int index =1;
    	for (int i=0; i<numBalls; i++) {
            ballTypes[i] = args[index];
    		index = index+2;
    	}
    	//retrieve ball sizes
    	index = 2;
    	for (int i=0; i<numBalls; i++) {
    		ballSizes[i] = Double.parseDouble(args[index]);
    		index = index+2;
    	}
     
    	//TO DO: create a Player object and initialize the player game stats.  
        Player player = new Player();
        // map ball type to frequency that it's hit
        HashMap<String, Integer> ballsHit = new HashMap<String, Integer>();
        ballsHit.put("Basic", 0);
        ballsHit.put("Shrink", 0);
        ballsHit.put("Bounce", 0);
        ballsHit.put("Split", 0);
            
    	//number of active balls
    	int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800, 800);
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create colored balls 
        //TO DO: Create "numBalls" balls (of types given in "ballTypes" with sizes given in "ballSizes") and store them in an Arraylist
        ArrayList<BasicBall> numBallsList = new ArrayList<BasicBall>(numBalls);
        for (int i = 0; i < numBalls; i++) {
            if (ballTypes[i].equals("basic")){
                numBallsList.add(new BasicBall(ballSizes[i], Color.RED));
            } else if (ballTypes[i].equals("shrink")){
                numBallsList.add(new ShrinkBall(ballSizes[i], Color.BLACK));
            } else if (ballTypes[i].equals("bounce")){
                numBallsList.add(new BounceBall(ballSizes[i], Color.WHITE));
            } else if (ballTypes[i].equals("split")){
                numBallsList.add(new SplitBall(ballSizes[i], Color.BLUE));
            }
        } 
   		//TO DO: initialize the numBallsinGame
   		numBallsinGame++;
        
        // do the animation loop
        StdDraw.enableDoubleBuffering();
        while (numBallsinGame > 0) {

            //Move all balls
            for(int i = 0; i < numBallsList.size(); i++){
                numBallsList.get(i).move();
            }

            //Check if the mouse is clicked
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                //Check whether a ball is hit. Check each ball. 
                for(int i = 0; i < numBallsList.size(); i++){
                    BasicBall ball = numBallsList.get(i);
                    if (ball.isHit(x,y)) {
                        int numBallsReset = numBallsList.get(i).reset();
                        if (numBallsReset == 2) {
                            numBallsList.add(new SplitBall(numBallsList.get(i)));
                        }
                    	//TO DO: Update player statistics
                        player.addHits();
                        player.addScore(ball.getScore());

                        //Update the ball's hit frequency
                        String hashKey = numBallsList.get(i).name;
                        ballsHit.put(hashKey, ballsHit.get(hashKey) + 1);

                        //Next, determine the ball that has been hit the most
                        int mostHits = Collections.max(ballsHit.values());   // Take the greatest value
                        for (Map.Entry<String, Integer> entry : ballsHit.entrySet()) {
                            if (entry.getValue() == mostHits) {   //Get the Key
                                player.setMostHits(entry.getKey());    
                                break;
                            }
                        }
                    }
                } 
            }
                
            numBallsinGame = 0;
            // draw the n balls
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            
            //DONE TO DO: check each ball and see if they are still visible. numBallsinGame should hold the number of visible balls in the game.  
            for(int i = 0; i < numBallsList.size(); i++){
                BasicBall ball = numBallsList.get(i);
                if (ball.isOut == false) { 
                    ball.draw();
                    numBallsinGame++;
                }
            }
            
            //Print the game progress
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.67, 0.90, "Number of balls in game  : "+ String.valueOf(numBallsinGame));
            //TO DO: print the rest of the player statistics
            StdDraw.text(-0.65, 0.85, "Number of successful hits: "+ String.valueOf(player.getHits()));
            StdDraw.text(-0.65, 0.80, "Score: "+ String.valueOf(player.getScore()));
            StdDraw.show();
            StdDraw.pause(20);
        }
        while (true) {
            StdDraw.setPenColor(StdDraw.BLUE);
            Font font = new Font("Arial", Font.BOLD, 60);
            StdDraw.setFont(font);
            StdDraw.text(0, 0, "GAME OVER");
            //TO DO: print the rest of the player statistics
            StdDraw.show();
            StdDraw.pause(10);           
            StdDraw.setPenColor(StdDraw.YELLOW);
            font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(0, 0.5, "Most Hit Ball  : "+ player.getMostHits());
        }
        	
        
    }
}
