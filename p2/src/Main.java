import graphics.Drawable;
import graphics.Graphics;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.HouseModel;
import model.ModelContext;
import model.TreeModel;

import org.amcgala.TurtleMode;

public class Main extends TurtleMode implements Graphics {
	final static int x0 = 1;
	final static int y0 = 23;
	
	
	public static void main(String[] args) {
		new Main();
	}
	

	@Override
	public void turtleCommands() {
		List<Drawable> list = new LinkedList<>();
		
		ModelContext context = new ModelContext() {
			@Override
			public int getX0() {
				return x0;
			}

			@Override
			public int getY0() {
				return y0;
			}
		};
		
		Graphics graphics = this;
		
		Random r = new Random();
		int x = 100;
		for(int i = 0; i < 5; i++){
			int w = 70 + r.nextInt(70);
			list.add(new HouseModel(context, x, 400, w, 1 + r.nextInt(5), 4 + r.nextInt(10)));
			x += w + 10;
		}
		
		x = 80;
		for(int i = 0; i < 4; i++){
			int rootHeight = 50 + r.nextInt(40);
			int alpha = 40 + r.nextInt(20);
			int beta = 10 + r.nextInt(60);
			int depth = 5 + r.nextInt(10);
			double shrinkRate = 0.55 + r.nextDouble() * 0.2;
			
			list.add(new TreeModel(context, x, 490 + r.nextInt(20), rootHeight, alpha, beta, depth, shrinkRate));
			x += 200 + r.nextInt(20);
		}

		for(Drawable d : list)
			d.draw(graphics);
	}

	

	public void glUp(){
		up();
	}


	@Override
	public void glDown() {
		down();
	}


	@Override
	public void glMove(int length) {
		move(length);
	}


	@Override
	public void glTurnRight(int angle) {
		turnRight(angle);
	}


	@Override
	public void glTurnLeft(int angle) {
		turnLeft(angle);
	}

	
	public void glDrawLine(int x, int y, int length, int angle){
		glUp();
		
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		glDown();
		

		turnLeft(90);
		turnLeft(angle);
		move(length);
		
		glUp();
		turnRight(180);
		move(length);
		turnRight(90 + angle);

		
		turnLeft(90);
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		turnRight(90);
	}

	public void glDrawRectangle(int x, int y, int width, int height) {
		glUp();
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		glDown();

		turnLeft(90);
		move(width);
		turnLeft(90);
		move(height);
		turnLeft(90);
		move(width);
		turnLeft(90);
		move(height);

		glUp();
		turnRight(90);
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		turnRight(90);
	}

	public void glDrawTriangle(int x, int y, int width) {
		int a = (int) Math.round(Math.sqrt(width * width * 2) / 2.0);
		
		glUp();
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		glDown();

		turnLeft(90);
		move(width);
		turnLeft(135);
		move(a);
		turnLeft(90);
		move(a);
		turnRight(45);

		glUp();
		move(x0 + x);
		turnRight(90);
		move(y0 + y);
		turnRight(90);
	}
}  
  
