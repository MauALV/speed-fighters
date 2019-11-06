package project;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.Game;

public class Nivel {
	private String mapStr;
	private Tile map[][], solidBlock;
	private int gridW, gridH;
	private int tileSize;
	private Jugador jugador1, jugador2;

	public Nivel() {
		mapStr = "";
		tileSize = 32;
		gridW = Game.WIDTH / tileSize;
		gridH = Game.HEIGHT / tileSize;
		map = new Tile[gridH][gridW];

		solidBlock = new Tile("solid", true, Color.magenta);
		crearNivel();
	}

	public Tile getTile(int j, int i) {
		return map[i][j];
	}

	private void cargaMapa() {
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
		mapStr += "                                ";
		mapStr += "                                ";
		mapStr += "     *****           *****      ";
		mapStr += "                                ";
		mapStr += "   1                        2   ";
		mapStr += "                                ";
		mapStr += "********************************";
	}

	public void crearNivel() {
		cargaMapa();
		for (int i = 0; i < mapStr.length(); i++) {
			int x = i % gridW;
			int y = i / gridW;
			char id = mapStr.charAt(i);
			Tile tile = null;
			switch (id) {
			case '*':
				tile = solidBlock;
				break;
			case '1':
				jugador1 = new Jugador(true);
				break;
			case '2':
				jugador2 = new Jugador(false);
				break;
			}
			map[y][x] = tile;
		}
	}

	public void renderMap(Graphics2D g) {
		for (int i = 0; i < gridH; i++) {
			for (int j = 0; j < gridW; j++) {
				if (map[i][j] != null) {
					g.setColor(getTile(j, i).getColor());
					g.fillRect(j * 32, i * 32, tileSize, tileSize);
				}

			}
		}
	}

	public void render(Graphics2D g) {
		System.out.println("[Jugador 2] = (" + jugador2.getPosX() + ", " + jugador2.getPosY() + ")");
		System.out.println("[Jugador 1] = (" + jugador1.getPosX() + ", " + jugador1.getPosY() + ")");
		renderMap(g);
		jugador1.render(g);
		jugador2.render(g);
	}

	public void tick() {
		jugador1.tick();
		jugador2.tick();
	}

}
