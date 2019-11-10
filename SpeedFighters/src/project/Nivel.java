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
	private AtaqueEspecial a1, a2,a3,a4,a5, a6,a7,a8,a9,a10;

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
		hp1.renderDynamic(g, jugador1.getHp());
		hp2.renderDynamic(g, jugador2.getHp());
		sp1.render(g);
		sp2.render(g);
		if(a1 != null)
			a1.render(g);
		if(a2 != null)
			a2.render(g);
		if(a3 != null)
			a3.render(g);
		if(a4 != null)
			a4.render(g);
		if(a5 != null)
			a5.render(g);
		if(a6 != null)
			a6.render(g);
		if(a7 != null)
			a7.render(g);
		if(a8 != null)
			a8.render(g);
		if(a9 != null)
			a9.render(g);
		if(a10 != null)
			a10.render(g);
		g.dispose();
	}

	public void tick() {
		//System.out.println(hp1.getPosX());
		jugador1.tick();
		jugador2.tick();
		if(a1 != null)
			a1.tick();
		if(a2 != null)
			a2.tick();
		if(a3 != null)
			a3.tick();
		if(a4 != null)
			a4.tick();
		if(a5 != null)
			a5.tick();
		if(a6 != null)
			a6.tick();
		if(a7 != null)
			a7.tick();
		if(a8 != null)
			a8.tick();
		if(a9 != null)
			a9.tick();
		if(a10 != null)
			a10.tick();
		
		
		hp1.setWidth(450 * jugador1.getHp() / 100);
		hp1.setPosX(470-(450 * jugador1.getHp() / 100));
		hp2.setWidth(450 * jugador2.getHp() / 100);
		
		sp1.setPosX(470-(150 * jugador1.getSp() / 100));
		sp1.setWidth(150 * jugador1.getSp() / 100);
		//System.out.println(hp1.getWidth());
		sp2.setWidth(150 * jugador2.getSp() / 100);
		if (jugador1.isEstaGolpeando() && jugador1.conectaGolpe(jugador2)) {
			jugador1.setSp(jugador1.getSp() + 0.3f);
		}
		if (jugador2.isEstaGolpeando() && jugador2.conectaGolpe(jugador1)) {
			jugador2.setSp(jugador2.getSp() + 0.3f);
		}
		if(jugador1.isAtacandoEspecial()) {
			System.out.println("asdasd");
			 a1 = new AtaqueEspecial(jugador1, 0);
			 a2 = new AtaqueEspecial(jugador1, 1);
			 a3 = new AtaqueEspecial(jugador1, 2);
			 a4 = new AtaqueEspecial(jugador1, -1);
			 a5 = new AtaqueEspecial(jugador1, -2);
			jugador1.setAtacandoEspecial(false);
		}
		if(jugador2.isAtacandoEspecial()) {
			 a6 = new AtaqueEspecial(jugador2, 0);
			 a7 = new AtaqueEspecial(jugador2, 1);
			 a8 = new AtaqueEspecial(jugador2, 2);
			 a9 = new AtaqueEspecial(jugador2, -1);
			 a10 = new AtaqueEspecial(jugador2, -2);
			jugador2.setAtacandoEspecial(false);
		}
	}

}
