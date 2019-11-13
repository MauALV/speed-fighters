package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;

import HUD.*;
import engine.Game;

public class Nivel {
	private String mapStr;
	private Tile map[][], solidBlock;
	private int gridW, gridH;
	private int tileSize;
	private Jugador jugador1, jugador2;
	private HUD hp1, hp2, sp1, sp2, cr;
	private ArrayList<AtaqueEspecial> ae;

	public Nivel() {
		ae = new ArrayList<AtaqueEspecial>();
		sp1 = new Especial(true);
		sp2 = new Especial(false);
		hp1 = new Vida(true);
		hp2 = new Vida(false);
		cr = new Cronometro();
		mapStr = "";
		tileSize = 32;
		gridW = Game.WIDTH / tileSize;
		gridH = Game.HEIGHT / tileSize;
		map = new Tile[gridH][gridW];

		solidBlock = new Tile("solid", true, Color.LIGHT_GRAY);
		crearNivel();
	}

	public Tile getTile(int j, int i) {
		if (i < 0 || i >= gridH || j < 0 || j >= gridW)
			return solidBlock;
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
		// LABELS
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

	public void renderAtaqueEspecial(Graphics2D g) {
		ListIterator<AtaqueEspecial> itr = ae.listIterator();
		if (jugador1.isAtacandoEspecial()) {
			for (int i = 0; i <= 7; i++) {
				AtaqueEspecial ae1 = new AtaqueEspecial(jugador1, i);
				ae1.render(g);
			}
		}
		while (itr.hasNext())
			itr.next().render(g);
	}

	public void render(Graphics2D g) {
		jugador1.render(g);
		jugador2.render(g);
		hp1.renderDynamic(g, jugador1.getHp());
		hp2.renderDynamic(g, jugador2.getHp());
		sp1.render(g);
		sp2.render(g);
		renderAtaqueEspecial(g);
		cr.render(g);
		renderHUD(g);
		renderMap(g);

		if (sp1.getWidth() == 150) {
			sp1.renderDynamic(g, jugador1.getSp());
		}
		if (sp2.getWidth() == 150) {
			sp2.renderDynamic(g, jugador2.getSp());
		}
		g.dispose();
	}

	public void tickAtaqueEspecial() {
		ListIterator<AtaqueEspecial> itr = ae.listIterator();
		while (itr.hasNext()) {
			itr.next().tick();
		}

		if (jugador1.isAtacandoEspecial()) {
			for (int i = 0; i <= 7; i++) {
				AtaqueEspecial aux = new AtaqueEspecial(jugador1, i);
				ae.add(aux);
			}
			jugador1.setAtacandoEspecial(false);
		}
		if (jugador2.isAtacandoEspecial()) {
			for (int i = 0; i <= 7; i++)
				ae.add(new AtaqueEspecial(jugador2, i));
			jugador2.setAtacandoEspecial(false);
		}
	}

	public void tick() {
		jugador1.tick();
		jugador2.tick();
		tickAtaqueEspecial();
		cr.tick();

		hp1.setWidth(450 * jugador1.getHp() / 100);
		hp1.setPosX(470 - (450 * jugador1.getHp() / 100));
		hp2.setWidth(450 * jugador2.getHp() / 100);

		sp1.setPosX(470 - (150 * jugador1.getSp() / 100));
		sp1.setWidth(150 * jugador1.getSp() / 100);
		sp2.setWidth(150 * jugador2.getSp() / 100);

		if (jugador1.isEstaGolpeando() && jugador1.conectaGolpe(jugador2)) {
			jugador1.setSp(jugador1.getSp() + 7.2f);
		}
		if (jugador2.isEstaGolpeando() && jugador2.conectaGolpe(jugador1)) {
			jugador2.setSp(jugador2.getSp() + 7.2f);
		}
	}

}
