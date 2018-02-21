package dodge;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

/**
 * The Dodge Game.
 * 
 * This is the main program for Surface Pro 4.
 * 
 * In this game, the player controls a grey ball with the mouse, to avoid red
 * balls and collect blue squares. The amount of red balls increases as the
 * player collects more blue squares. Each blue square worths 5 points. The game
 * is over as soon as the player collides a red ball.
 * 
 * @author bambrow
 *
 */

public class DemoSurface {

	public static double distance(double mx, double my, double rx, double ry) {
		return Math.sqrt(Math.pow((mx - rx), 2) + Math.pow((my - ry), 2));
	}

	public static boolean initCollide(Ball ball, PlayerBall playerBall) {
		if (distance(playerBall.getRx(), playerBall.getRy(), ball.getRx(), ball.getRy()) < 1.0) {
			return true;
		}
		return false;
	}

	public static Ball generateBall(PlayerBall playerBall) {
		Ball ball = new Ball();
		if (!initCollide(ball, playerBall))
			return ball;
		else
			return generateBall(playerBall);
	}

	public static boolean initCollide(Square square, PlayerBall playerBall) {
		if (distance(playerBall.getRx(), playerBall.getRy(), square.getRx(), square.getRy()) < 1.0) {
			return true;
		}
		return false;
	}

	public static Square generateSquare(PlayerBall playerBall) {
		Square square = new Square();
		if (!initCollide(square, playerBall))
			return square;
		else
			return generateSquare(playerBall);
	}

	public static boolean collide(Ball ball, PlayerBall playerBall) {
		if (distance(playerBall.getRx(), playerBall.getRy(), ball.getRx(), ball.getRy()) < playerBall.getRadius()
				+ ball.getRadius()) {
			return true;
		}
		return false;
	}

	public static boolean collideGroup(List<Ball> ball, PlayerBall playerBall) {
		for (Ball b : ball) {
			if (collide(b, playerBall))
				return true;
		}
		return false;
	}

	public static boolean collide(Square square, PlayerBall playerBall) {
		if (distance(playerBall.getRx(), playerBall.getRy(), square.getRx(), square.getRy()) < playerBall.getRadius()
				+ square.getLength()) {
			return true;
		}
		return false;
	}

	public static void updateScore(int score, double x, double y) {
		StdDraw.text(x, y, "Score: " + score);
	}

	public static void draw(List<Ball> ball) {
		for (Ball b : ball) {
			b.drawBall();
		}
	}

	public static void update(List<Ball> ball) {
		for (Ball b : ball) {
			b.updateBall();
		}
	}

	public static void main(String[] args) {

		StdDraw.setCanvasSize(1500, 1500);
		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);

		Font font = new Font("sans-serif", 0, 40);
		StdDraw.setFont(font);
		
		StdDraw.text(0, 0, "Press any key to start");

		while (true) {

			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.pause(100);
			}

			StdDraw.clear(StdDraw.LIGHT_GRAY);
			StdDraw.enableDoubleBuffering();

			PlayerBall playerBall = new PlayerBall();
			Square square = generateSquare(playerBall);
			List<Ball> ball = new ArrayList<Ball>();
			int score = 0;

			while (true) {

				StdDraw.clear(StdDraw.LIGHT_GRAY);
				StdDraw.enableDoubleBuffering();

				if (collide(square, playerBall)) {
					square = generateSquare(playerBall);
					ball.add(generateBall(playerBall));
					score += 5;
				}

				if (collideGroup(ball, playerBall)) {
					break;
				}

				StdDraw.setPenColor(StdDraw.DARK_GRAY);
				playerBall.updateBall();
				playerBall.drawBall();

				StdDraw.setPenColor(StdDraw.RED);
				update(ball);
				draw(ball);

				StdDraw.setPenColor(StdDraw.CYAN);
				square.drawSquare();

				StdDraw.setPenColor(StdDraw.BLACK);
				updateScore(score, 0, -0.9);

				StdDraw.show();
				StdDraw.pause(30);

			}

			StdDraw.clear(StdDraw.LIGHT_GRAY);
			StdDraw.enableDoubleBuffering();
			StdDraw.setPenColor(StdDraw.BLACK);
			updateScore(score, 0, -0.9);
			StdDraw.text(0, 0, "Game over");
			StdDraw.text(0, -0.1, "Press any key to restart");
			StdDraw.show();

			while (StdDraw.hasNextKeyTyped()) {
				StdDraw.nextKeyTyped();
			}

		}
	}
}
