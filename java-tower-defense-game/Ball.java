package defense;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

/**
 * The Defense Game.
 * 
 * This is the Ball class.
 * 
 * In this game, the player controls a grey ball with the mouse, to avoid and
 * shoot red balls. The grey ball is in the middle of the screen and cannot be
 * moved. The player can use mouse to control the shooting direction of the grey
 * ball. Using the left mouse button or the space key on keyboard, the player
 * can control the grey ball to shoot. The game is over as soon as the player
 * collides a red ball.
 * 
 * @author bambrow
 *
 */

public class Ball {

	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;

	public Ball() {
		double[] cord = initLocation();
		this.rx = cord[0];
		this.ry = cord[1];
		this.vx = initXvelocity();
		this.vy = initYvelocity();
		this.radius = 0.04;
	}

	private double[] initLocation() {
		double[] cord = new double[2];
		double random = Math.random();
		if (random < 0.25) {
			cord[0] = StdRandom.uniform(-1.2, -1.05);
			cord[1] = StdRandom.uniform(-1.05, 1.2);
		} else if (random < 0.5) {
			cord[0] = StdRandom.uniform(-1.2, 1.05);
			cord[1] = StdRandom.uniform(-1.2, -1.05);
		} else if (random < 0.75) {
			cord[0] = StdRandom.uniform(1.05, 1.2);
			cord[1] = StdRandom.uniform(-1.2, 1.05);
		} else {
			cord[0] = StdRandom.uniform(-1.05, 1.2);
			cord[1] = StdRandom.uniform(1.05, 1.2);
		}
		return cord;
	}
	
	private double initXvelocity() {
		return -this.rx / 100;
	}

	private double initYvelocity() {
		return -this.ry / 100;
	}


	public void update() {
		rx += vx;
		ry += vy;
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(rx, ry, radius);
	}

	public double getRx() {
		return rx;
	}

	public double getRy() {
		return ry;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getRadius() {
		return radius;
	}

}
