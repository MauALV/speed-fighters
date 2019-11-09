package project;

import java.awt.Color;
import java.awt.Graphics2D;

import HUD.HP;
import HUD.HUD;
import HUD.Special;
import engine.Game;

public class Nivel {
	private String mapStr;
	private Tile map[][], solidBlock;
	private int gridW, gridH;
	private int tileSize;
	private Jugador jugador1, jugador2;
	private HUD hp1, hp2, sp1, sp2;

	public Nivel() {
		sp1 = new Special(true);
		sp2 = new Special(false);
		hp1 = new HP(true);
		hp2 = new HP(false);
		mapStr = "";
		tileSize = 32;
		gridW = Game.WIDTH / tileSize;
		gridH = Game.HEIGHT / tileSize;
		map = new Tile[gridH][gridW];

		solidBlock = new Tile("solid", true, Color.LIGHT_GRAY);
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
		mapStr += "                                ";
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
	
	public void renderHUD(Graphics2D g) {
		//LABELS
		g.setColor(Color.red);
		g.drawString("PLAYER 1", 20, 55);
		g.setColor(Color.blue);
		g.drawString("PLAYER 2", 950, 55);
		
		// VIDA - contenedores
		g.setColor(Color.white);
		g.drawRect(20, 20, 450, 20);
		g.drawRect(554, 20, 450, 20);

		// SPECIAL - contenedores
		g.drawRect(320, 45, 150, 20);
		g.drawRect(554, 45, 150, 20);
	}

	public void render(Graphics2D g) {
		renderMap(g);
		renderHUD(g);
		jugador1.render(g);
		jugador2.render(g);
		hp1.render(g);
		hp2.render(g);
		sp1.render(g);
		sp2.render(g);
		g.dispose();
	}

	public void tick() {
		//System.out.println(hp1.getPosX());
		jugador1.tick();
		jugador2.tick();
		
		hp1.setWidth(450 * jugador1.getHp() / 100);
		hp1.setPosX(470-(450 * jugador1.getHp() / 100));
		hp2.setWidth(450 * jugador2.getHp() / 100);
		
		sp1.setPosX(470-(150 * jugador1.getSp() / 100));
		sp1.setWidth(150 * jugador1.getSp() / 100);
		System.out.println(hp1.getWidth());
		sp2.setWidth(150 * jugador2.getSp() / 100);
		if (jugador1.isEstaGolpeando() && jugador1.conectaGolpe(jugador2)) {
			jugador1.setSp(jugador1.getSp() + 0.3f);
		}
		if (jugador2.isEstaGolpeando() && jugador2.conectaGolpe(jugador1)) {
			jugador2.setSp(jugador2.getSp() + 0.3f);
		}
	}

}
