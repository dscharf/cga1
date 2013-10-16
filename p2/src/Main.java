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
		
		list.add(new Tree(300, 500, 50, 45, 45, 5));

		Random r = new Random();
		int x = 100;
		for(int i = 0; i < 5; i++){
			int w = 70 + r.nextInt(70);
			list.add(new House(x, 300, w, 1 + r.nextInt(5), 2 + r.nextInt(10)));
			x += w + 10;
		}
		

		for(Drawable d : list)
			d.draw();
	}

	interface Drawable {
		void draw();
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
			
			
			int doorWidth = (int) (width * 0.2);
			int doorHeight = (int) (LEVEL_HEIGHT * 0.8);
			drawRectangle(x + (width - doorWidth) / 2, y, doorWidth, doorHeight);
			
			int columnCount = windowCount / levelCount;
			if(windowCount % levelCount > 0)
				columnCount++;
			
			int rasterWidth = width / columnCount;
			int windowWidth = (int) (rasterWidth * 0.5);
			int windowHeight = (int) (LEVEL_HEIGHT * 0.5);
			
			int windowOffsetX = (int) Math.round((rasterWidth - windowWidth) / 2.0);
			int windowOffsetY = (int) Math.round((LEVEL_HEIGHT - windowHeight) / 2.0);
					System.out.println(levelCount  + " " + columnCount);
			
			for(int i = 0; i < windowCount; i++) {
				int xtmp = (i % (columnCount) ) * rasterWidth + windowOffsetX;
				int ytmp = (i / levelCount ) * LEVEL_HEIGHT + windowOffsetY;
				drawRectangle(x + xtmp, y - ytmp, windowWidth, windowHeight);
				
				if(i % columnCount == 0)
					System.out.println();
				System.out.print(i % columnCount);
				
			}
			
			drawTriangle(x, y - height, width);
		}
	}

	class Tree implements Drawable {
		int x, y, height, alpha, beta, depth;
		public Tree(int x, int y, int height, int alpha, int beta, int depth) {
			this.x = x;
			this.y = y;
			this.height = height;
			this.alpha = 45;
			this.beta = beta;
			this.depth = depth;
		}

		@Override
		public void draw() {
			int angle = 45;
			int length = height;

			drawLine(x, y, height, 90);
			//fractal(x, y - height, height, 90, 0);
			
			turtleFractalInit(x, y - height, height, 90, 0.7);
		
		}
		
		private void turtleFractalInit(int x, int y, int length, int direction, double shrinkRate){
			
			up();
			
			move(x0 + x);
			turnRight(90);
			move(y0 + y);
			down();
			

			turnLeft(90);
			turnLeft(direction);
			turtleFractalRec(length, shrinkRate, 0, false);
			turnRight(direction);
			up();
			
			turnRight(90);
			turnLeft(90);
			move(x0 + x);
			turnRight(90);
			move(y0 + y);
			turnRight(90);
		}
		
		private void turtleFractalRec(int length, double shrinkRate, int level, boolean isRight){
			if(level == depth){
				//up();
				turnRight(180);
			} else {
				turnLeft(alpha);
				move(length *= shrinkRate);
				turtleFractalRec(length, shrinkRate, ++level, false);
				move(length);
				
				turnLeft(180 - alpha * 2);
				move(length);

				turtleFractalRec(length, shrinkRate, level, true);
				move(length);
				turnLeft(alpha);
			} 
		}
		
		private void fractal(int x, int y, int length, int gamma, int level){
			if(level >= depth)
				return;
			else {

				drawRectangle(x - 1, y + 1, 2, 2);
				
				level++;
				length *= 0.7;
				x += (int) Math.floor(length * Math.cos(gamma)  );
				y -= (int) Math.floor(length * Math.sin(gamma)  * 0.75);
				
				
				int gammaA = 45 + gamma;
				int gammaB = 360 - alpha + gamma;
				drawRectangle(x - 1, y + 1, 2, 2);
				drawLine(x, y, length,  180 + gammaA);
				//drawLine(x, y, length,  180  b);
				

				
				
				fractal(x, y, length, gammaA, level);
				
				
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
  