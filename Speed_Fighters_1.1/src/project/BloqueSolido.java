package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class BloqueSolido extends Tile{
	private int width, height;
	
	public BloqueSolido(int x, int y) {
		super(x, y);
		width = 32;
		height = 32;
	}
	
	public boolean isSolid() {
		return true;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(tileX, tileY, width, height);
		
	}
	
}
