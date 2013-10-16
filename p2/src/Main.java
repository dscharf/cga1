import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.amcgala.TurtleMode;

public class Main extends TurtleMode {
	final static int x0 = 1;
	final static int y0 = 23;
	
	final static int LEVEL_HEIGHT = 30;


	
	public static void main(String[] args) {
		new Main();
	}
	

	@Override
	public void turtleCommands() {
		List<Drawable> list = new LinkedList<>();
		
		Random r = new Random();
		int x = 100;
		for(int i = 0; i < 5; i++){
			int w = 70 + r.nextInt(70);
			list.add(new House(x, 400, w, 1 + r.nextInt(5), 4 + r.nextInt(10)));
			x += w + 10;
		}
		
		x = 80;
		for(int i = 0; i < 4; i++){
			int rootHeight = 50 + r.nextInt(40);
			int alpha = 40 + r.nextInt(20);
			int beta = 10 + r.nextInt(60);
			int depth = 10;
			double shrinkRate = 0.55 + r.nextDouble() * 0.2;
			
			list.add(new Tree(x, 490 + r.nextInt(20), rootHeight, alpha, beta, 8, shrinkRate));
			x += 200 + r.nextInt(20);
		}

		for(Drawable d : list)
			d.draw();
	}

	
	
	class House implements Drawable {
		int x, y, width, height, windowCount, levelCount;
		
		public House(int x, int y, int width, int levelCount, int windowCount) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.levelCount = levelCount;
			this.height = levelCount * LEVEL_HEIGHT;
			this.windowCount = windowCount;
		}

		public void draw() {
			drawRectangle(x, y, width, height);
			drawTriangle(x, y - height, width);
		
			int columnCount = windowCount / levelCount;
			if(windowCount % levelCount > 0)
				columnCount++;
			if(columnCount % 2 == 0)
				columnCount++;
			
			int rasterWidth = width / columnCount;
			int windowWidth = (int) (rasterWidth * 0.5);
			int windowHeight = (int) (LEVEL_HEIGHT * 0.5);
			
			int windowOffsetX = (int) Math.round((rasterWidth - windowWidth) / 2.0);
			int windowOffsetY = (int) Math.round((LEVEL_HEIGHT - windowHeight) / 2.0);
			//System.out.printf("count: %s, rows: %s, column: %s\n", windowCount, levelCount, columnCount);
			
			int c = windowCount;
			for(int i = 0; i < columnCount * levelCount; i++) {
				int xtmp = (i % columnCount) * rasterWidth + windowOffsetX;
				int ytmp = (i / columnCount) * LEVEL_HEIGHT + windowOffsetY;

				if(i != Math.floor(columnCount / 2.0))
				drawRectangle(x + xtmp, y - ytmp, windowWidth, windowHeight);
				
				if(c-- == 0)
					break;
			}
			if(c == 0){
				int xtmp = (int) Math.floor(columnCount / 2.0)  * rasterWidth + windowOffsetX;
				int ytmp = levelCount * LEVEL_HEIGHT + windowOffsetY;
				drawRectangle(x + xtmp, y - ytmp, windowWidth, windowHeight);
				
			}
			
			int doorWidth = (int) (rasterWidth * 0.8);
			int doorHeight = (int) (LEVEL_HEIGHT * 0.8);
			drawRectangle(x + (width - doorWidth) / 2, y, doorWidth, doorHeight);
			
		}
	}

	class Tree implements Drawable {
		int x, y, height, alpha, beta, depth;
		double shrinkRate; 
		
		public Tree(int x, int y, int rootHeight, int alpha, int beta, int depth, double shrinkRate) {
			this.x = x;
			this.y = y;
			this.height = rootHeight;
			this.alpha = alpha;
			this.beta = beta;
			this.depth = depth;
			this.shrinkRate = shrinkRate;
		}

		@Override
		public void draw() {
			drawLine(x, y, height, 90);			
			turtleFractalInit(x, y - height, 90);
		}
		
		private void turtleFractalInit(int x, int y, int direction){
			up();
			
			move(x0 + x);
			turnRight(90);
			move(y0 + y);
			down();
			
			turnLeft(90);
			turnLeft(direction);
			turtleFractalRec(height, 0);
			turnRight(direction);
			up();
			
			turnRight(90);
			turnLeft(90);
			move(x0 + x);
			turnRight(90);
			move(y0 + y);
			turnRight(90);
		}
		
		private void turtleFractalRec(int length, int level){
			length *= shrinkRate;
			if(level == depth || length < 1){
				turnRight(180);
			} else {
				turnLeft(alpha);
				move(length);
				
				turtleFractalRec(length, ++level);
				move(length);
				
				turnLeft(180 - alpha - beta);
				move(length);

				turtleFractalRec(length, level);
				move(length);
				turnLeft(beta);
			} 
		}
	}
	
	
	
	public void drawLine(int x, int y, int length, int angle){
		up();
		
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		down();
		

		turnLeft(90);
		turnLeft(angle);
		move(length);
		
		up();
		turnRight(180);
		move(length);
		turnRight(90 + angle);

		
		turnLeft(90);
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		turnRight(90);
	}

	public void drawRectangle(int x, int y, int width, int height) {
		up();
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		down();

		turnLeft(90);
		move(width);
		turnLeft(90);
		move(height);
		turnLeft(90);
		move(width);
		turnLeft(90);
		move(height);

		up();
		turnRight(90);
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		turnRight(90);
	}

	public void drawTriangle(int x, int y, int width) {
		int a = (int) Math.round(Math.sqrt(width * width * 2) / 2.0);
		
		up();
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		down();

		turnLeft(90);
		move(width);
		turnLeft(135);
		move(a);
		turnLeft(90);
		move(a);
		turnRight(45);

		up();
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		turnRight(90);
	}

}  
  
