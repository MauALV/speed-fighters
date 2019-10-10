package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.Game;
import engine.Input;

public class Nivel {
	private String mapStr;
	private Tile mapa[][];
	private int gridW, gridH;
	private int tileSize;
	private Graphics2D g;
	private Jugador jugador1, jugador2;
	private boolean gameOver;

	public Nivel() {
		gameOver = false;
		tileSize = 32;
		gridW = Game.WIDTH / tileSize;
		gridH = Game.HEIGHT / tileSize;
		mapa = new Tile[gridH][gridW];
		mapStr = "";
		

		crearNivel();
	}

	private void crearNivel() {
		jugador1 = new Jugador(true);
		jugador2 = new Jugador(false);
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "     *****           *****      ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "********************************";

		for (int i = 0; i < mapStr.length(); i++) {
			int x = i % gridW;
			int y = i / gridW;
			if (mapStr.charAt(i) == '*') {
				mapa[y][x] = new BloqueSolido(x * 32, y * 32);
			}
		}
	}

	public void render(Graphics2D g) {
		jugador1.render(g);
		jugador2.render(g);
		g.setColor(Color.WHITE);
		for (int i = 0; i < mapStr.length(); i++) {
			int x = i % gridW;
			int y = i / gridW;
			if (mapStr.charAt(i) == '*') {
				mapa[y][x].render(g);
			}
		}
		
		if(gameOver) {
			Font font = new Font("Helvetica", Font.BOLD, 48);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", Game.WIDTH/2-128, Game.HEIGHT/2);
		}
	}

	public void tick() {
		jugador1.tick();
		jugador2.tick();
		if (gameOver)
			return;
		if (jugador1.collides(jugador2))
			gameOver = true;
		if (Input.get().isKeyPressed(KeyEvent.VK_SPACE) && gameOver) { //NO JALA YET
			gameOver=false;
			tick();
		}
		System.out.println(gameOver);
		
	}

}
