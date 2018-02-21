package spaceinvader;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

/**
 * Space Invader.
 * 
 * This is the Player class.
 * 
 * @author bambrow
 *
 */

public class Player implements ISpaceInvador{

	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;
	private final Color color;
	private int rank;
	
	/**
	 * initiator.
	 * @param rx	x position
	 * @param ry	y position
	 * @param color		color
	 */
	public Player(double rx, double ry, Color color) {
		this.rx = rx;
		this.ry = ry;
		this.vx = 0;
		this.vy = 0;
		this.radius = 0.02;
		this.color = color;
		this.rank = 2;
	}
	
	/**
	 * update the position of player.
	 */
	public void update() {
		if (this.rx + this.vx <= this.radius && isGoingLeft()) {
			return;
		}
		if (this.rx + this.vx >= 1 - this.radius && isGoingRight()) {
			return;
		}
		if (this.ry + this.vy <= this.radius && isGoingDown()) {
			return;
		}
		if (this.ry + this.vy >= 1 - this.radius && isGoingUp()) {
			return;
		}
		this.rx += vx;
		this.ry += vy;
	}
	
	/**
	 * draw the player.
	 */
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(rx + 0.015, ry, 0.005);
	}
	
	/**
	 * reset the velocities of player.
	 */
	public void reset() {
		this.vx = 0;
		this.vy = 0;
	}
	
	/**
	 * return if the player is going right.
	 * @return
	 */
	private boolean isGoingRight() {
		return this.vx > 0;
	}
	
	/**
	 * return if the player is going left.
	 * @return
	 */
	private boolean isGoingLeft() {
		return this.vx < 0;
	}

	/**
	 * return if the player is going up.
	 * @return
	 */
	private boolean isGoingUp() {
		return this.vy > 0;
	}
	
	/**
	 * return if the player is going down.
	 * @return
	 */
	private boolean isGoingDown() {
		return this.vy < 0;
	}
	
	/**
	 * getters and setters.
	 */
	public double getRx() {
		return rx;
	}

	public void setRx(double rx) {
		this.rx = rx;
	}

	public double getRy() {
		return ry;
	}

	public void setRy(double ry) {
		this.ry = ry;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getRadius() {
		return radius;
	}
	
	public Color getColor() {
		return color;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
