package project;

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

	public Color getColor() {
		return col;
	}
}
