public interface Graphics {
	void glUp();

	void glDown();

	void glMove(int length);

	void glTurnRight(int angle);

	void glTurnLeft(int angle);

	void glDrawLine(int x, int y, int length, int angle);

	void glDrawRectangle(int x, int y, int width, int height);

	void glDrawTriangle(int x, int y, int width);
}
