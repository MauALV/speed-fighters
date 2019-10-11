package project;

import java.awt.Graphics2D;

public abstract class Tile {
	protected int tileX, tileY;
	
	public Tile(int x, int y) {
		tileX = x;
		tileY = y;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public abstract void render(Graphics2D g);
	
}
