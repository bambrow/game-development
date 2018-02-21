package spaceinvader;

import edu.princeton.cs.introcs.StdDraw;

/**
 * Space Invader.
 * 
 * This is the AlienB class.
 * 
 * @author bambrow
 *
 */

public class AlienB implements ISpaceInvador{
	
	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;
	private int lives;
	
	/**
	 * initiator.
	 */
	public AlienB() {
		this.rx = 1.1;
		this.ry = 0.5;
		this.vx = -0.005;
		this.vy = initVelocity();
		this.radius = 0.03;
		this.lives = 25;
	}
	
	/**
	 * return random velocity.
	 * @return
	 */
	private double initVelocity() {
		if (Math.random() < 0.5) {
			return 0.005;
		} else {
			return -0.005;
		}
	}
	
	/**
	 * update the position of alien.
	 */
	public void update() {
		if (rx <= 0.95) {
			vx = 0;
		}
		if (ry <= 0.1 || ry >= 0.9) {
			vy = -vy;
		}
		rx += vx;
		ry += vy;
	}
	
	/**
	 * draw the alien.
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.filledCircle(rx, ry, radius);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(rx, ry, "S");
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

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
}
