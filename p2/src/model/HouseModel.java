package model;
import graphics.Drawable;
import graphics.Graphics;

public class HouseModel extends Model implements Drawable {
	int x, y, width, height, windowCount, levelCount;
	

	final static int LEVEL_HEIGHT = 30;
	
	public HouseModel(ModelContext con, int x, int y, int width, int levelCount, int windowCount) {
		super(con);
		this.x = x;
		this.y = y;
		this.width = width;
		this.levelCount = levelCount;
		this.height = levelCount * LEVEL_HEIGHT;
		this.windowCount = windowCount;
	}

	public void draw(Graphics g) {
		g.glDrawRectangle(x, y, width, height);
		g.glDrawTriangle(x, y - height, width);
	
		int columnCount = windowCount / levelCount;
		if(windowCount % levelCount > 0)
			columnCount++;
		if(columnCount % 2 == 0)
			columnCount++;
		
		int rasterWidth = width / columnCount;
		int windowWidth = (int) (rasterWidth * 0.5);
		int windowHeight = (int) (LEVEL_HEIGHT * 0.5);
		
		
		if(windowWidth > width * 0.25)
			windowWidth = (int) Math.round(width * 0.25);
		
		int windowOffsetX = (int) Math.round((rasterWidth - windowWidth) / 2.0);
		int windowOffsetY = (int) Math.round((LEVEL_HEIGHT - windowHeight) / 2.0);
		//System.out.printf("count: %s, rows: %s, column: %s\n", windowCount, levelCount, columnCount);

		
		int c = windowCount;
		for(int i = 0; i < columnCount * levelCount; i++) {
			int xtmp = (i % columnCount) * rasterWidth + windowOffsetX;
			int ytmp = (i / columnCount) * LEVEL_HEIGHT + windowOffsetY;

			if(i != Math.floor(columnCount / 2.0))
				g.glDrawRectangle(x + xtmp, y - ytmp, windowWidth, windowHeight);
			
			if(c-- == 0)
				break;
		}
		if(c == 0){
			int xtmp = (int) Math.floor(columnCount / 2.0)  * rasterWidth + windowOffsetX;
			int ytmp = levelCount * LEVEL_HEIGHT + windowOffsetY;
			g.glDrawRectangle(x + xtmp, y - ytmp, windowWidth, windowHeight);
			
		}
		
		int doorWidth = (int) (rasterWidth * 0.8);
		if(rasterWidth > width * 0.3)
			doorWidth = (int) Math.round(width * 0.3);
		
		int doorHeight = (int) (LEVEL_HEIGHT * 0.8);
		g.glDrawRectangle(x + (width - doorWidth) / 2, y, doorWidth, doorHeight);
		
	}
}

