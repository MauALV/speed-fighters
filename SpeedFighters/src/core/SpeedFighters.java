package core;

import java.awt.Graphics2D;

import engine.Game;

public class SpeedFighters {
	Nivel nivel;
	boolean paused;
	int contador;
	Game g;

	public SpeedFighters(Game g) {
		paused = false;
		this.g = g;
	}

	public void init() {
		nivel = new Nivel(g);
	}

	public void tick() {
		nivel.tick();
	}

	public void render(Graphics2D g) {
		nivel.render(g);
	}
}
