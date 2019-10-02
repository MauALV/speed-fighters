package project;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Game;

public class Piso extends ObjetoDeJuego {

	public Piso() {
		posX = 0;
		posY = 556;
		width = Game.WIDTH;
		height = 20; 
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(posX, posY, width, height);

	}
}
