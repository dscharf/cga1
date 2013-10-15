import org.amcgala.TurtleMode;

public class Main extends TurtleMode {
	int x0 = 1;
	int y0 = 225;

	public static void main(String[] args) {
		new Main();
	}
    
	public Main() {
		Scene gebirge = new Scene.Builder().withHouse(500, 3, 4)
				.withHouse(400, 2, 4).build();

		gebirge.draw();
	}

	interface Drawable {
		void draw();
	}

	static class Scene implements Drawable {
		private Scene(Builder sb) {

		}

		@Override
		public void draw() {

		}

		public static class Builder {
			class House implements Drawable {
				public House() {
				}

				public void draw() {
				}
			}

			class Tree implements Drawable {
				public Tree() {

				}

				@Override
				public void draw() {
				}

			}

			public Builder() {
			}

			public Builder withHouse(int width, int levelCount, int windowCount) {

				return this;
			}

			public Scene build() {
				return new Scene(this);
			}
		}
	}

	@Override
	public void turtleCommands() {

		/*
		 * down(); move(300); turnRight(90); move(200); turnLeft(90); move(200);
		 * turnRight(90); move(200); turnLeft(90); move(200); turnLeft(90);
		 */
		drawRectangle(x0 + 50, y0 + 50, 30, 50);
		drawTriangle(x0 + 150, y0 + 150, 50, 50);
	}

	public void drawRectangle(int x, int y, int width, int height) {
		up();
		move(x);
		turnRight(90);
		move(y);
		down();

		turnLeft(90);
		move(width);
		turnRight(90);
		move(height);
		turnRight(90);
		move(width);
		turnRight(90);
		move(height);

		up();
		turnLeft(90);
		move(x);
		turnRight(90);
		move(y);
		turnRight(90);
	}

	public void drawTriangle(int x, int y, int width, int height) {
		up();
		move(x);
		turnRight(90);
		move(y);
		down();

		turnLeft(90);
		move(width);
		turnRight(90);
		move(height);
		turnRight(90);
		move(width);
		turnRight(90);
		move(height);

		up();
		turnLeft(90);
		move(x);
		turnRight(90);
		move(y);
		turnRight(90);
	}

}  
  