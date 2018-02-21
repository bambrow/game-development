package snakeauto;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

/**
 * The Snake Game (Auto-Play).
 * 
 * When it does not know what to do, it will ask for the player to help.
 * 
 * This is the Square class.
 * 
 * In this game... Well, snake game is so popular. I don't think I need to
 * introduce the rules.
 * 
 * @author bambrow
 *
 */

public class Square {

	private double rx;
	private double ry;
	private final double halfLength;

	public Square() {
		halfLength = 0.02;
		rx = initLocation();
		ry = initLocation();
	}

	private double initLocation() {
		double loc = (StdRandom.uniform(2, 48) * 2.0 * halfLength) + halfLength;
		return loc;
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledSquare(rx, ry, halfLength);
	}

	public double getRx() {
		return rx;
	}

	public double getRy() {
		return ry;
	}

	public double getHalfLength() {
		return halfLength;
	}

}
