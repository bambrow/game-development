import edu.princeton.cs.introcs.StdDraw;

/**
 * The Snake Game.
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
