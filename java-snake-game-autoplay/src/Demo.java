import edu.princeton.cs.introcs.StdDraw;

/**
 * The Snake Game (Auto-Play).
 * 
 * When it does not know what to do, it will ask for the player to help.
 * 
 * This is the main program.
 * 
 * In this game... Well, snake game is so popular. I don't think I need to
 * introduce the rules.
 * 
 * @author bambrow
 *
 */

public class Demo {

	public static boolean equal(double a, double b) {
		if (Math.abs(a - b) <= 1e-6) {
			return true;
		}
		return false;
	}

	public static Square generateSquare(Snake snake) {
		Square square = new Square();
		double[][] pos = snake.getAll();
		for (int i = 0; i < snake.getN(); i++) {
			if (equal(pos[i][0], square.getRx()) && equal(pos[i][1], square.getRy())) {
				return generateSquare(snake);
			}
		}
		return square;
	}

	public static void updateScore(int score, double x, double y) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(x, y, "Score: " + score);
	}

	public static void main(String[] args) {

		StdDraw.clear(StdDraw.LIGHT_GRAY);
		StdDraw.setXscale(0.0, 2.0);
		StdDraw.setYscale(0.0, 2.0);
		StdDraw.text(1.0, 1.0, "Press any key to start");

		while (true) {

			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.pause(100);
			}

			StdDraw.enableDoubleBuffering();

			Snake snake = new Snake();
			Square square = generateSquare(snake);
			int score = 0;
			final int INTERVAL = 20;

			snake.draw();
			StdDraw.show();

			while (true) {

				StdDraw.clear(StdDraw.LIGHT_GRAY);

				snake.route(square.getRx(), square.getRy(), square);

				if (equal(snake.getRx() + snake.getVx(), square.getRx())
						&& equal(snake.getRy() + snake.getVy(), square.getRy())) {
					snake.add(square.getRx(), square.getRy());
					square = generateSquare(snake);
					score += 1;
					snake.draw();
					square.draw();
					updateScore(score, 1.0, 0.1);
					StdDraw.show();
					StdDraw.pause(INTERVAL);
					continue;
				}

				if (snake.selfCollide() || snake.collide()) {
					break;
				}

				snake.move();
				snake.draw();
				square.draw();
				updateScore(score, 1.0, 0.1);

				StdDraw.show();
				StdDraw.pause(INTERVAL);

			}

			StdDraw.clear(StdDraw.LIGHT_GRAY);
			StdDraw.enableDoubleBuffering();
			snake.draw();
			square.draw();
			updateScore(score, 1.0, 0.1);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(1.0, 1.0, "Game over");
			StdDraw.text(1.0, 0.9, "Press any key to restart");

			StdDraw.show();

			while (StdDraw.hasNextKeyTyped()) {
				StdDraw.nextKeyTyped();
			}

		}

	}

}
