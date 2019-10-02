package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.Game;
import engine.Input;

public class SpeedFighters {
	Jugador jugador1, jugador2;
	Piso piso;
	boolean gameOver;

	public SpeedFighters() {

	}

	public void init() {
		// Condiciones de Inicio
		gameOver = false;
		jugador1 = new Jugador(true);
		jugador2 = new Jugador(false);
		piso = new Piso();
	}

	public void tick() {
		// Cuando pierda, puede reinciar dando presionando en space
		if (Input.get().isKeyPressed(KeyEvent.VK_SPACE) && gameOver) {
			init();
		}
		if (gameOver)
			return;

		jugador1.tick();
		jugador2.tick();

		if (jugador1.collides(jugador2)) {
			// Game Over
			gameOver = true;
		}

	}

	public void render(Graphics2D g) {
		jugador1.render(g);
		jugador2.render(g);
		piso.render(g);
		g.setColor(Color.BLACK);

		if (gameOver) {
			Font font = new Font("Helvetica", Font.BOLD, 48);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", Game.WIDTH / 2 - 128, Game.HEIGHT / 2);
		}
	}
}
