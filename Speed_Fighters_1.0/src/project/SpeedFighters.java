package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.Game;
import engine.Input;

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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		nivel.render(g);
	}
}
