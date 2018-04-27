import java.awt.Color;

public class ShrinkBall extends BasicBall{

    private int initial;

    public ShrinkBall(double radius, Color color){
        super(radius, color);
        initial = 0;
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
        if (initial > 0){
            radius = radius * 2 / 3;
        }
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        initial++;
        return 1;
    }
    
    public boolean isHit(double x, double y) {
    	if ((Math.abs(rx-x)<=radius) && (Math.abs(ry-y)<=radius))
			return true;
		else return false; 

    }

    public int getScore() {
    	return 20;
    }

}