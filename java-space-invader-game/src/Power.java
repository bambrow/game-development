
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdDraw;

/**
 * Space Invader.
 * 
 * This is the Power class.
 * 
 * @author bambrow
 *
 */

public class Power implements ISpaceInvador{

	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;
	
	/**
	 * initiator.
	 */
	public Power() {
		this.rx = initLocation();
		this.ry = initLocation();
		this.vx = initVelocity();
		this.vy = initVelocity();
		this.radius = 0.02;
	}
	
	/**
	 * return random velocity.
	 * @return
	 */
	private double initVelocity() {
		if (Math.random() < 0.5) {
			return -0.002;
		} else {
			return 0.002;
		}
	}
	
	/**
	 * return random location.
	 * @return
	 */
	private double initLocation() {
		return StdRandom.uniform(0.3, 0.7);
	}
	
	/**
	 * update the position of power.
	 */
	public void update() {
		if (rx + vx < this.radius || rx + vx > 1.0 - this.radius) {
			vx = -vx;
		}
		if (ry + vy < this.radius || ry + vy > 1.0 - this.radius) {
			vy = -vy;
		}
		rx += vx;
		ry += vy;
	}
	
	/**
	 * draw the power.
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(rx, ry, radius);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(rx, ry, "P");
	}

	/**
	 * getters and setters.
	 */
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
