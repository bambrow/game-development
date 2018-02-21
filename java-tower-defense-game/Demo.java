package defense;

import java.util.ArrayList;
import java.util.List;

import com.sun.glass.events.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

/**
 * The Defense Game.
 * 
 * This is the main program.
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

public class Demo {

	public static double distance(double mx, double my, double rx, double ry) {
		return Math.sqrt(Math.pow((mx - rx), 2) + Math.pow((my - ry), 2));
	}

	public static boolean collide(Ball ball, Player player) {
		if (distance(player.getRx(), player.getRy(), ball.getRx(), ball.getRy()) < player.getRadius()
				+ ball.getRadius()) {
			return true;
		}
		return false;
	}

	public static boolean collideGroup(List<Ball> ball, Player player) {
		for (Ball b : ball) {
			if (collide(b, player))
				return true;
		}
		return false;
	}

	public static boolean collide(Ball ball, Bullet bullet) {
		if (distance(bullet.getRx(), bullet.getRy(), ball.getRx(), ball.getRy()) < bullet.getRadius()
				+ ball.getRadius()) {
			return true;
		}
		return false;
	}

	public static boolean collideGroup(List<Ball> ball, List<Bullet> bullet) {
		for (Ball b : ball) {
			for (Bullet bu : bullet) {
				if (collide(b, bu)) {
					ball.remove(b);
					bullet.remove(bu);
					return true;
				}
			}
		}
		return false;
	}

	public static void updateScore(int score, double x, double y) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(x, y, "Score: " + score);
	}

	public static int calculateInterval(int score) {
		if (score >= 200)
			return 10;
		else if (score >= 150)
			return 15;
		else if (score >= 100)
			return 20;
		else if (score >= 60)
			return 25;
		else if (score >= 30)
			return 30;
		else if (score >= 10)
			return 35;
		else
			return 40;
	}

	public static void update(List<Bullet> bullet, List<Ball> ball) {
		for (Bullet bu : bullet) {
			bu.update();
		}
		for (Ball b : ball) {
			b.update();
		}
		for (Bullet bu : bullet) {
			if (bu.getRx() < -1.05 || bu.getRx() > 1.05 || bu.getRy() < -1.05 || bu.getRy() > 1.05) {
				bullet.remove(bu);
				break;
			}
		}
	}

	public static void draw(List<Bullet> bullet, List<Ball> ball) {
		for (Bullet bu : bullet) {
			bu.draw();
		}
		for (Ball b : ball) {
			b.draw();
		}
	}

	public static void main(String[] args) {

		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);

		StdDraw.text(0, 0, "Press any key to start");

		while (true) {

			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.pause(100);
			}

			StdDraw.clear(StdDraw.LIGHT_GRAY);
			StdDraw.enableDoubleBuffering();

			Player player = new Player();
			List<Ball> ball = new ArrayList<Ball>();
			List<Bullet> bullet = new ArrayList<Bullet>();
			int score = 0;
			boolean mousePressed = true;
			boolean keyPressed = true;
			int spawn = 0;

			while (true) {

				StdDraw.clear(StdDraw.LIGHT_GRAY);
				StdDraw.enableDoubleBuffering();

				if (collideGroup(ball, player)) {
					break;
				}

				if (collideGroup(ball, bullet)) {
					score += 2;
				}

				if (spawn % calculateInterval(score) == 0) {
					ball.add(new Ball());
				}
				
				if (StdDraw.mousePressed() && (!mousePressed)) {
					bullet.add(new Bullet(player.getX(), player.getY()));
					mousePressed = true;
				}
				if (!StdDraw.mousePressed()) {
					mousePressed = false;
				}

				if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && (!keyPressed)) {
					bullet.add(new Bullet(player.getX(), player.getY()));
					keyPressed = true;
				}
				if (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
					keyPressed = false;
				}

				player.update();
				player.draw();
				update(bullet, ball);
				draw(bullet, ball);

				updateScore(score, 0, -0.9);
				spawn++;

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
