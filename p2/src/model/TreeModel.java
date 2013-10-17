package model;
import graphics.Drawable;
import graphics.Graphics;

public class TreeModel extends Model implements Drawable {
	int x, y, height, alpha, beta, depth;
	double alphaRate, betaRate;

	public TreeModel(ModelContext con, int x, int y, int rootHeight, int depth, int alpha,
			int beta, double alphaRate, double betaRate) {
		super(con);
		this.x = x;
		this.y = y;
		this.height = rootHeight;
		this.alpha = alpha;
		this.beta = beta;
		this.depth = depth;
		this.alphaRate = alphaRate;
		this.betaRate = betaRate;
	}

	@Override
	public void draw(Graphics g) {
		g.glDrawLine(x, y, height, 90);
		turtleFractalInit(g, x, y - height, 90);
	}

	private void turtleFractalInit(Graphics g, int x, int y, int direction) {
		g.glUp();

		g.glMove(context.getX0() + x);
		g.glTurnRight(90);
		g.glMove(context.getY0() + y);
		g.glDown();

		g.glTurnLeft(90);
		g.glTurnLeft(direction);
		turtleFractalRec(g, height, 0);
		g.glTurnRight(direction);
		g.glUp();

		g.glTurnRight(90);
		g.glTurnLeft(90);
		g.glMove(context.getX0() + x);
		g.glTurnRight(90);
		g.glMove(context.getY0() + y);
		g.glTurnRight(90);
	}

	private void turtleFractalRec(Graphics g, int length, int level) {
		int alphaLength = (int) (length * alphaRate);
		int betaLength = (int) (length * betaRate);
		if (level == depth || alphaLength < 1 || betaLength < 1) {
			g.glTurnRight(180);
		} else {
			g.glTurnLeft(alpha);
			g.glMove(alphaLength);

			turtleFractalRec(g, alphaLength, ++level);
			g.glMove(alphaLength);

			g.glTurnLeft(180 - alpha - beta);
			g.glMove(betaLength);

			turtleFractalRec(g, betaLength, level);
			g.glMove(betaLength);
			g.glTurnLeft(beta);
		}
	}
}
