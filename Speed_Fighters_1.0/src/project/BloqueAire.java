package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class BloqueAire extends Tile {
	public BloqueAire(int x, int y) {
		super(x, y);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(tileX, tileX, tileX, tileX);
	}
}
