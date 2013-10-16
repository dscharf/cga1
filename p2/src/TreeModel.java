public class TreeModel extends Model implements Drawable {
	int x, y, height, alpha, beta, depth;
	double shrinkRate;

	public TreeModel(ModelContext con, int x, int y, int rootHeight, int alpha,
			int beta, int depth, double shrinkRate) {
		super(con);
		this.x = x;
		this.y = y;
		this.height = rootHeight;
		this.alpha = alpha;
		this.beta = beta;
		this.depth = depth;
		this.shrinkRate = shrinkRate;
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
		length *= shrinkRate;
		if (level == depth || length < 1) {
			g.glTurnRight(180);
		} else {
			g.glTurnLeft(alpha);
			g.glMove(length);

			turtleFractalRec(g, length, ++level);
			g.glMove(length);

			g.glTurnLeft(180 - alpha - beta);
			g.glMove(length);

			turtleFractalRec(g, length, level);
			g.glMove(length);
			g.glTurnLeft(beta);
		}
	}
}
