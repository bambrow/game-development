import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

/**
 * The Dodge Game.
 * 
 * This is the Square Class.
 * 
 * In this game, the player controls a grey ball with the mouse, to avoid red
 * balls and collect blue squares. The amount of red balls increases as the
 * player collects more blue squares. Each blue square worths 5 points. The game
 * is over as soon as the player collides a red ball.
 * 
 * @author bambrow
 *
 */

public class Square {

	private double rx;
	private double ry;
	private final double length;

	public Square() {
		rx = initLocation();
		ry = initLocation();
		length = 0.05;
	}

	private double initLocation() {
		if (Math.random() < 0.5) {
			return StdRandom.uniform(0.10, 0.90);
		} else {
			return StdRandom.uniform(-0.90, -0.10);
		}
	}

	public void drawSquare() {
		StdDraw.filledSquare(rx, ry, length);
	}

	public double getRx() {
		return rx;
	}

	public double getRy() {
		return ry;
	}

	public double getLength() {
		return length;
	}

}
