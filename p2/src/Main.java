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
			this.alpha = alpha;
			this.beta = beta;
			this.depth = depth;
		}

		@Override
		public void draw() {
			

			drawLine(x, y, height, 90);
			fractal(x, y - height, height, 90, 0);
		}
		
		private void fractal(int x, int y, int length, int gamma, int level){
			if(level >= depth)
				return;
			else {
				level++;
				int a = alpha + gamma;
				int b = 360 - alpha + gamma;
				int l = (int) (length * 0.7);
				drawLine(x, y, l,  a);
				drawLine(x, y, l,  b);
				
				
				int tmp = (int) Math.round(Math.sqrt(l * l / 2));
				int tmpX = tmp;
				int tmpY = tmp;
				fractal(x + tmpX,//(int) Math.round(l * Math.atan(alpha)), 
						y - tmpY, //(int) Math.round(l * Math.acos(alpha)), 
						l, a, ++level);
				
				
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
  