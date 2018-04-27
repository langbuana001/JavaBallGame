import java.awt.Color;

public class SplitBall extends BasicBall{

    public SplitBall(double radius, Color color){
        super(radius, color);
    }

    public SplitBall(BasicBall motherBall) {
        super(motherBall.radius,motherBall.color);
    }

    public void draw() { 
    	if ((Math.abs(rx) <= 1.0) && (Math.abs(ry) <= 1.0)) {
    		StdDraw.setPenColor(color);
    		StdDraw.filledCircle(rx, ry, radius);
    	} else
    		isOut = true;
    }

    public int reset() {
        rx = 0.0;
        ry = 0.0;  	
        // TO DO: assign a random speed 
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        return 2;
    }

    public int getScore() {
    	return 10;
    }

}