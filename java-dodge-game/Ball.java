package dodge;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

/**
 * The Dodge Game.
 * 
 * This is the Ball class.
 * 
 * In this game, the player controls a grey ball with the mouse, to avoid red
 * balls and collect blue squares. The amount of red balls increases as the
 * player collects more blue squares. Each blue square worths 5 points. The game
 * is over as soon as the player collides a red ball.
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
		this.rx = initLocation();
		this.ry = initLocation();
		this.vx = initVelocity();
		this.vy = initVelocity();
		this.radius = 0.03;
	}

	private double initVelocity() {
		if (Math.random() < 0.5) {
			return StdRandom.uniform(0.005, 0.010);
		} else {
			return StdRandom.uniform(-0.010, -0.005);
		}
	}

	private double initLocation() {
		if (Math.random() < 0.5) {
			return StdRandom.uniform(0.10, 0.90);
		} else {
			return StdRandom.uniform(-0.90, -0.10);
		}
	}

	public void updateBall() {
		if (Math.abs(rx + vx) > 1.0 - radius) {
			vx = -vx;
		}
		if (Math.abs(ry + vy) > 1.0 - radius) {
			vy = -vy;
		}
		rx += vx;
		ry += vy;
	}

	public void drawBall() {
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
