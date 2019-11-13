package core;

import java.awt.Color;

public class Tile {

	protected boolean solid;
	protected Color col;
	protected String type;

	public Tile(String type, boolean solid, Color color) {
		this.solid = solid;
		this.type = type;
		col = color;
	}

	public boolean isSolid(int vx, int vy) {
		if (type.contentEquals("semi"))
			return (vy > 0);
		else if (type.contentEquals("no"))
			return false;
		else
			return true;

	}

	public Color getColor() {
		return col;
	}
}
