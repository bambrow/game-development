import edu.princeton.cs.introcs.StdDraw;

/**
 * The Defense Game.
 * 
 * This is the Bullet class.
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

public class Bullet {

	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;

	public Bullet(double x, double y) {
		this.rx = x;
		this.ry = y;
		this.vx = x / 7;
		this.vy = y / 7;
		this.radius = 0.02;
	}

	public void update() {
		rx += vx;
		ry += vy;
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.YELLOW);
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
