import edu.princeton.cs.introcs.StdDraw;

/**
 * The Dodge Game.
 * 
 * This is the PlaerBall class.
 * 
 * In this game, the player controls a grey ball with the mouse, to avoid red
 * balls and collect blue squares. The amount of red balls increases as the
 * player collects more blue squares. Each blue square worths 5 points. The game
 * is over as soon as the player collides a red ball.
 * 
 * @author bambrow
 *
 */

public class PlayerBall {

	private double rx;
	private double ry;
	private final double radius;

	public PlayerBall() {
		this.rx = StdDraw.mouseX();
		this.ry = StdDraw.mouseY();
		this.radius = 0.05;
	}

	public void updateBall() {
		rx = StdDraw.mouseX();
		ry = StdDraw.mouseY();
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

	public double getRadius() {
		return radius;
	}

}
