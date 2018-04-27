import java.awt.Color;

public class BounceBall extends BasicBall{

    private int numberOfBounce = 0;

    public BounceBall(double radius, Color color){
        super(radius, color);
    }

    public void move() {
        rx = rx + vx;
        ry = ry + vy;
        if ((Math.abs(rx) > 1.0) || (Math.abs(ry) > 1.0)){
            if (numberOfBounce <= 3) {
                // Increment number of bounces
                numberOfBounce++;
                // Change direction of ball
                if (Math.abs(rx) > 1.0) {
                    vx = vx * -1.0;
                } else {
                    vy = vy * -1.0;
                }
            }
            else {
                isOut = true;
            }

        }
    
    }

    public void draw() { 
    	if ((Math.abs(rx) <= 1.0) && (Math.abs(ry) <= 1.0)) {
    		StdDraw.setPenColor(color);
            StdDraw.filledCircle(rx, ry, radius);
    	} else {
            if (numberOfBounce > 3){
                isOut = true;
            }
        }
    	
    }

    public int getScore() {
    	return 15;
    }

}