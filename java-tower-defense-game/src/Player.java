import edu.princeton.cs.introcs.StdDraw;

/**
 * The Defense Game.
 * 
 * This is the Player class.
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

public class Player {

	private final double rx;
	private final double ry;
	private double angle;
	private double x;
	private double y;
	private final double radius;
	private final double smallRadius;

	public Player() {
		this.rx = 0.0;
		this.ry = 0.0;
		this.radius = 0.1;
		this.smallRadius = 0.02;
		this.angle = findAngle(StdDraw.mouseX(), StdDraw.mouseY());
	}

	private double findAngle(double x, double y) {
		return Math.atan((y - ry) / (x - rx));
	}

	public void update() {
		angle = findAngle(StdDraw.mouseX(), StdDraw.mouseY());
		x = radius * Math.cos(angle);
		y = radius * Math.sin(angle);
		if (StdDraw.mouseX() < 0) {
			x = -x;
			y = -y;
		}
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledCircle(rx, ry, radius);
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(x, y, smallRadius);
	}

	public double getRx() {
		return rx;
	}

	public double getRy() {
		return ry;
	}

	public double getAngle() {
		return angle;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getRadius() {
		return radius;
	}

	public double getSmallRadius() {
		return smallRadius;
	}

}
