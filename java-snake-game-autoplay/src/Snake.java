import com.sun.glass.events.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

/**
 * The Snake Game (Auto-Play).
 * 
 * When it does not know what to do, it will ask for the player to help.
 * 
 * This is the Snake class. The snake is made of multiple blocks.
 * 
 * In this game... Well, snake game is so popular. I don't think I need to
 * introduce the rules.
 * 
 * @author bambrow
 *
 */

public class Snake {

	private Node first;
	private Node last;
	private double vx;
	private double vy;
	private int n;
	private final int INTERVAL = 20;

	private class Node {
		private Block block;
		private Node next;
		private Node previous;
	}

	public Snake() {
		first = new Node();
		first.block = new Block(0.98, 1.02);
		last = new Node();
		last.block = new Block(0.94, 1.02);
		first.next = last;
		last.previous = first;
		n = 2;
		add(new Block(1.02, 1.02));
		add(new Block(1.06, 1.02));
		add(new Block(1.10, 1.02));
		vx = 0.04;
		vy = 0.00;
	}

	private void add(Block block) {
		Node oldfirst = first;
		first = new Node();
		first.block = block;
		first.next = oldfirst;
		oldfirst.previous = first;
		n++;
	}

	private void add(Node block) {
		Node oldfirst = first;
		first = block;
		first.next = oldfirst;
		oldfirst.previous = first;
		n++;
	}

	public void add(double rx, double ry) {
		add(new Block(rx, ry));
	}

	public void move() {
		double firstX = first.block.rx;
		double firstY = first.block.ry;
		Node oldlast = last;
		last = last.previous;
		last.next = null;
		oldlast.previous = null;
		n--;
		oldlast.block.update(firstX + vx, firstY + vy);
		add(oldlast);
	}

	private void turnLeft() {
		double temp = this.vx;
		this.vx = -this.vy;
		this.vy = temp;
	}

	private void turnRight() {
		double temp = this.vx;
		this.vx = this.vy;
		this.vy = -temp;
	}

	private void randomTurn() {
		if (Math.random() < 0.5) {
			turnLeft();
		} else {
			turnRight();
		}
	}

	private boolean isGoingLeft() {
		return this.vx < 0;
	}

	private boolean isGoingRight() {
		return this.vx > 0;
	}

	private boolean isGoingUp() {
		return this.vy > 0;
	}

	private boolean isGoingDown() {
		return this.vy < 0;
	}

	private int selfDetect() {
		int value = 0;
		for (Node x = first.next.next; x != last; x = x.next) {
			if (equal(first.block.rx + vx, x.block.rx) && equal(first.block.ry + vy, x.block.ry))
				value += 1; // front is blocked
			else if (equal(first.block.rx - vy, x.block.rx) && equal(first.block.ry + vx, x.block.ry))
				value += 2; // left is blocked
			else if (equal(first.block.rx + vy, x.block.rx) && equal(first.block.ry - vx, x.block.ry))
				value += 4; // right is blocked
			else if (equal(first.block.rx + vx - vy, x.block.rx) && equal(first.block.ry + vy + vx, x.block.ry))
				value += 8; // left front is blocked
			else if (equal(first.block.rx + vx + vy, x.block.rx) && equal(first.block.ry + vy - vx, x.block.ry))
				value += 16; // right front is blocked
		}
		if (first.block.rx + vx < 0 || first.block.rx + vx > 2 || first.block.ry + vy < 0 || first.block.ry + vy > 2)
			value += 1; // front is blocked
		if (first.block.rx - vy < 0 || first.block.rx - vy > 2 || first.block.ry + vx < 0
				|| first.block.ry + vx > 2)
			value += 2; // left is blocked
		if (first.block.rx + vy < 0 || first.block.rx + vy > 2 || first.block.ry - vx < 0
				|| first.block.ry - vx > 2)
			value += 4; // right is blocked
		if (first.block.rx + vx - vy < 0 || first.block.rx + vx - vy > 2 || first.block.ry + vy + vx < 0
				|| first.block.ry + vy + vx > 2)
			value += 8; // left front is blocked
		if (first.block.rx + vx + vy < 0 || first.block.rx + vx + vy > 2 || first.block.ry + vy - vx < 0
				|| first.block.ry + vy - vx > 2)
			value += 16; // right front is blocked
		return value;
	}

/*
 * if S represents the head of the snake:
 * --------
 * |8|1|16|
 * |2|S| 4|
 * --------
 * the value represents the surrounding environment of the snake head
 * F: front L: left R: right Z: left front Y: right front
 * 1 F
 * 2 L
 * 3 FL
 * 4 R
 * 5 FR
 * 6 LR
 * 7 FLR
 * 8 Z
 * 9 FZ
 * 10 LZ
 * 11 FLZ
 * 12 RZ
 * 13 FRZ
 * 14 LRZ
 * 15 FLRZ
 * 16 Y
 * 17 FY
 * 18 LY
 * 19 FLY
 * 20 RY
 * 21 FRY
 * 22 LRY
 * 23 FLRY
 * 24 ZY
 * 25 FZY
 * 26 LZY
 * 27 FLZY
 * 28 RZY
 * 29 FRZY
 * 30 LRZY
 * 31 FLRZY
 */

	public void route(double rx, double ry, Square square) {
		int value = selfDetect();
		if (first.block.rx + vx + vx < 0 || first.block.rx + vx + vx > 2 || first.block.ry + vy + vy < 0
				|| first.block.ry + vy + vy > 2) {
			if (value == 0) {
				if (isGoingUp() && (rx - first.block.rx > 1e-6)) {
					turnRight();
					return;
				} else if (isGoingUp() && (first.block.rx - rx > 1e-6)) {
					turnLeft();
					return;
				} else if (isGoingDown() && (first.block.rx - rx > 1e-6)) {
					turnRight();
					return;
				} else if (isGoingDown() && (rx - first.block.rx > 1e-6)) {
					turnLeft();
					return;
				} else if (isGoingLeft() && (ry - first.block.ry > 1e-6)) {
					turnRight();
					return;
				} else if (isGoingLeft() && (first.block.ry - ry > 1e-6)) {
					turnLeft();
					return;
				} else if (isGoingRight() && (first.block.ry - ry > 1e-6)) {
					turnRight();
					return;
				} else if (isGoingRight() && (ry - first.block.ry > 1e-6)) {
					turnLeft();
					return;
				} else {
					randomTurn();
					return;
				}
			} else if (value == 2 || value == 10 || value == 18) {
				turnRight();
				return;
			} else if (value == 4 || value == 12 || value == 20) {
				turnLeft();
				return;
			}
		}
		if (value == 3 || value == 11 || value == 19 || value == 27) {
			// left and front are blocked
			turnRight();
			return;
		} else if (value == 5 || value == 13 || value == 21 || value == 29) {
			// right and front are blocked
			turnLeft();
			return;
		} else if (value == 7 || value == 15 || value == 23 || value == 31) {
			// dead end
			return;
		} else if (value == 6 || value == 8 || value == 14 || value == 16 || value == 22 || value == 30) {
			// better go straight ahead in this situation
			return;
		} else if (value == 9 || value == 12 || value == 17 || value == 18 || value == 24 || value == 25 || value == 26
				|| value == 28) {
			// dangerous, ask for the player to help
			askDirection(square, value);
			return;
		} else if (value == 1) {
			// play randomly
			randomTurn();
			return;
		} else {
			// if nothing around, move according to the location of the square
			if (rx - first.block.rx > 1e-6) {
				if (isGoingRight() || isGoingLeft())
					return;
				else if (isGoingUp()) {
					if (value != 4 && value != 6 && value != 12 && value != 14 && value != 20 && value != 22
							&& value != 30) {
						// right is not blocked
						turnRight();
						return;
					}
					else
						return;
				} else if (isGoingDown()) {
					if (value != 2 && value != 6 && value != 10 && value != 14 && value != 18 && value != 22
							&& value != 30) {
						// left is not blocked
						turnLeft();
						return;
					}
					else
						return;
				}
			} else if (first.block.rx - rx > 1e-6) {
				if (isGoingRight() || isGoingLeft())
					return;
				else if (isGoingDown()) {
					if (value != 4 && value != 6 && value != 12 && value != 14 && value != 20 && value != 22
							&& value != 30) {
						// right is not blocked
						turnRight();
						return;
					}
					else
						return;
				} else if (isGoingUp()) {
					if (value != 2 && value != 6 && value != 10 && value != 14 && value != 18 && value != 22
							&& value != 30) {
						// left is not blocked
						turnLeft();
						return;
					}
					else
						return;
				}
			} else {
				if (ry - first.block.ry > 1e-6) {
					if (isGoingUp() || isGoingDown())
						return;
					else if (isGoingLeft()) {
						if (value != 4 && value != 6 && value != 12 && value != 14 && value != 20 && value != 22
								&& value != 30) {
							// right is not blocked
							turnRight();
							return;
						}
						else
							return;
					} else if (isGoingRight()) {
						if (value != 2 && value != 6 && value != 10 && value != 14 && value != 18 && value != 22
								&& value != 30) {
							// left is not blocked
							turnLeft();
							return;
						}
						else
							return;
					}
				} else if (first.block.ry - ry > 1e-6) {
					if (isGoingUp() || isGoingDown())
						return;
					else if (isGoingRight()) {
						if (value != 4 && value != 6 && value != 12 && value != 14 && value != 20 && value != 22
								&& value != 30) {
							// right is not blocked
							turnRight();
							return;
						}
						else
							return;
					} else if (isGoingLeft()) {
						if (value != 2 && value != 6 && value != 10 && value != 14 && value != 18 && value != 22
								&& value != 30) {
							// left is not blocked
							turnLeft();
							return;
						}
						else
							return;
					}
				} else
					return;
			}
			return;
		}
	}

	private void askDirection(Square square, int value) {
		// this function will be called only when value is equal to 9, 17, 24,
		// 25, 26, 28
		draw();
		square.draw();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(1.0, 1.0, "Which direction? Please answer using keyboard.");
		StdDraw.show();
		while (true) {
			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.pause(100);
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
				if (isGoingUp() && (value == 9 || value == 17 || value == 25))
					// front is blocked, cannot go up
					continue;
				else if (isGoingUp() && (value == 24 || value == 26 || value == 28))
					// front is not blocked
					break;
				else if (isGoingDown())
					continue;
				else if (isGoingLeft() && value != 28) {
					// right is not blocked
					turnRight();
					break;
				}
				else if (isGoingLeft() && value == 28)
					// right is blocked, cannot turn right
					continue;
				else if (isGoingRight() && value != 26) {
					// left is not blocked
					turnLeft();
					break;
				}
				else if (isGoingRight() && value == 26)
					// left is blocked, cannot turn left
					continue;
				else
					continue;
				// the determination is similar for the rest of three arrow keys
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
				if (isGoingUp())
					continue;
				else if (isGoingDown() && (value == 9 || value == 17 || value == 25))
					continue;
				else if (isGoingDown() && (value == 24 || value == 26 || value == 28))
					break;
				else if (isGoingLeft() && value != 26) {
					turnLeft();
					break;
				}
				else if (isGoingLeft() && value == 26)
					continue;
				else if (isGoingRight() && value != 28) {
					turnRight();
					break;
				}
				else if (isGoingRight() && value == 28)
					continue;
				else
					continue;
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
				if (isGoingUp() && value != 26) {
					turnLeft();
					break;
				}
				else if (isGoingUp() && value == 26)
					continue;
				else if (isGoingDown() && value != 28) {
					turnRight();
					break;
				}
				else if (isGoingDown() && value == 28)
					continue;
				else if (isGoingLeft() && (value == 9 || value == 17 || value == 25))
					continue;
				else if (isGoingLeft() && (value == 24 || value == 26 || value == 28))
					break;
				else if (isGoingRight())
					continue;
				else
					continue;
			} else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
				if (isGoingUp() && value != 28) {
					turnRight();
					break;
				}
				else if (isGoingUp() && value == 28)
					continue;
				else if (isGoingDown() && value != 26) {
					turnLeft();
					break;
				}
				else if (isGoingDown() && value == 26)
					continue;
				else if (isGoingLeft())
					continue;
				else if (isGoingRight() && (value == 9 || value == 17 || value == 25))
					continue;
				else if (isGoingRight() && (value == 24 || value == 26 || value == 28))
					break;
				else
					continue;
			} else
				continue;
		}
		StdDraw.pause(INTERVAL);
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		first.block.draw();
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		for (Node x = first.next; x != null; x = x.next) {
			x.block.draw();
		}
	}

	public double[][] getAll() {
		double[][] position = new double[n][2];
		int i = 0;
		for (Node x = first; x != null; x = x.next) {
			position[i][0] = x.block.rx;
			position[i][1] = x.block.ry;
			i++;
		}
		return position;
	}

	private boolean equal(double a, double b) {
		if (Math.abs(a - b) <= 1e-6) {
			return true;
		}
		return false;
	}

	public boolean selfCollide() {
		for (Node x = first.next.next.next; x != last; x = x.next) {
			if (equal(first.block.rx + vx, x.block.rx) && equal(first.block.ry + vy, x.block.ry)) {
				return true;
			}
		}
		return false;
	}

	public boolean collide() {
		if (first.block.rx + vx < 0 || first.block.rx + vx > 2 || first.block.ry + vy < 0 || first.block.ry + vy > 2) {
			return true;
		}
		return false;
	}

	private class Block {

		private double rx;
		private double ry;
		private final double halfLength;

		public Block(double rx, double ry) {
			this.rx = rx;
			this.ry = ry;
			halfLength = 0.018;
		}

		private void update(double rx, double ry) {
			this.rx = rx;
			this.ry = ry;
		}

		private void draw() {
			StdDraw.filledSquare(rx, ry, halfLength);
		}

	}

	public double getRx() {
		return first.block.rx;
	}

	public double getRy() {
		return first.block.ry;
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

	public int getN() {
		return n;
	}

}
