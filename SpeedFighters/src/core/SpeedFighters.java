package core;

import java.awt.Graphics2D;

public class SpeedFighters {
	Nivel nivel;

	public SpeedFighters() {
	}

	public void init() {
		nivel = new Nivel();
	}

	public void tick() {
		nivel.tick();
	}

	public void render(Graphics2D g) {
		nivel.render(g);
	}
}
