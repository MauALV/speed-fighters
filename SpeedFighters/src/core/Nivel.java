package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

import HUD.Especial;
import HUD.HUD;
import HUD.Rondas;
import HUD.Temporizador;
import HUD.Vida;
import engine.Game;
//import menus.VictoriaJugador1;
import menus.VictoriaJugadorUno;
import menus.VictoriaJugadorDos;

public class Nivel {
	private String mapStr;
	private Tile map[][], solidBlock;
	private int gridW, gridH, ticks, tiempo;
	private int tileSize;
	private Jugador jugador1, jugador2;
	private HUD hp1, hp2, sp1, sp2;
	private Rondas rnds1, rnds2;
	private Temporizador temp;
	private ArrayList<AtaqueEspecial> ae;
	private boolean rondaIniciada;
	private Game g;
	private BufferedImage imagenFondo;
	
	public Nivel(Game g) {
		AudioPlayer.get().playBackMusic("MainMenuMusicaTest.wav");
		AudioPlayer.get().setMusicVolume(0.7f);
		AudioPlayer.get().setEffectsVolume(0.7f);
		ticks = 0;
		this.g = g;
		tiempo = 3;
		ae = new ArrayList<AtaqueEspecial>();
		sp1 = new Especial(true);
		sp2 = new Especial(false);
		hp1 = new Vida(true);
		hp2 = new Vida(false);
		rnds1 = new Rondas(true);
		rnds2 = new Rondas(false);
		rondaIniciada = false;
		temp = new Temporizador();
		mapStr = "";
		tileSize = 32;
		gridW = Game.WIDTH / tileSize;
		gridH = Game.HEIGHT / tileSize;
		map = new Tile[gridH][gridW];
		solidBlock = new Tile("Solid", true,Color.darkGray);
		crearNivel();
		crearFondo();
		reset();
	}
	
	public void crearFondo() {
		File archImagen = new File("./res/Imagenes/bg3.png");
		
		try {
			imagenFondo = ImageIO.read(archImagen);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
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
//					g.setColor(myblue);;
//					g.fillRect(j * 32, i * 32, tileSize, tileSize);
				}

			}
		}
	}

	public void renderHUD(Graphics2D g) {
		// LABELS
		g.setColor(Color.blue);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString("PLAYER 1", 20, 62);
		g.setColor(Color.red);
		g.drawString("PLAYER 2", 925, 62);

		// VIDA - contenedores
		g.setColor(Color.black);
		g.drawRect(20, 25, 450, 20);
		g.drawRect(554, 25, 450, 20);

		// SPECIAL - contenedores
		g.drawRect(320, 50, 150, 20);
		g.drawRect(554, 50, 150, 20);

		// RONDAS - contenedores
		g.drawRect(305, 50, 10, 10);
		g.drawRect(290, 50, 10, 10);
		g.drawRect(275, 50, 10, 10);

		g.drawRect(709, 50, 10, 10);
		g.drawRect(724, 50, 10, 10);
		g.drawRect(739, 50, 10, 10);
	}

	public void renderAtaqueEspecial(Graphics2D g) {
		ListIterator<AtaqueEspecial> itr = ae.listIterator();
		while (itr.hasNext())
			itr.next().render(g);
	}

	public void render(Graphics2D g) {
		g.drawImage(imagenFondo, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		jugador1.render(g);
		jugador2.render(g);
		hp1.renderDynamic(g, jugador1.getHp());
		hp2.renderDynamic(g, jugador2.getHp());
		sp1.render(g);
		sp2.render(g);
		renderAtaqueEspecial(g);
		temp.render(g);
		renderHUD(g);
		renderMap(g);
		rnds1.renderRondas(g, jugador1.getRondasGanadas());
		rnds2.renderRondas(g, jugador2.getRondasGanadas());

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
			itr.next().tick(jugador1, jugador2);
		}
		if (jugador1.isAtacandoEspecial()) {
			for (int i = 0; i <= 7; i++) {
				ae.add(new AtaqueEspecial(jugador1, i));
			}
			jugador1.setAtacandoEspecial(false);
		}
		if (jugador2.isAtacandoEspecial()) {
			for (int i = 0; i <= 7; i++) {
				ae.add(new AtaqueEspecial(jugador2, i));
			}
			jugador2.setAtacandoEspecial(false);
		}
	}

	public void tick() {
		if (rondaIniciada) {
			AudioPlayer.get().setEffectsVolume(0.8f);
			jugador1.tick();
			jugador2.tick();

			tickAtaqueEspecial();

			temp.tick();

			hp1.setWidth(450 * jugador1.getHp() / 100);
			hp1.setPosX(470 - (450 * jugador1.getHp() / 100));
			hp2.setWidth(450 * jugador2.getHp() / 100);

			sp1.setPosX(470 - (150 * jugador1.getSp() / 100));
			sp1.setWidth(150 * jugador1.getSp() / 100);
			sp2.setWidth(150 * jugador2.getSp() / 100);

			if (jugador1.isEstaGolpeando() && jugador1.conectaGolpe(jugador2)) {
				jugador1.setSp(jugador1.getSp() + 7.2f);
				AudioPlayer.get().playEffectSound("golpe1.wav");
				jugador2.knockback(jugador1.isMirandoDerecha());
			}
			if (jugador2.isEstaGolpeando() && jugador2.conectaGolpe(jugador1)) {
				jugador2.setSp(jugador2.getSp() + 7.2f);
				AudioPlayer.get().playEffectSound("golpe1.wav");
				jugador1.knockback(jugador2.isMirandoDerecha());
			}

			if (temp.getTiempo() <= 0) {
				if (jugador1.getHp() > jugador2.getHp())
					jugador1.aumentaRondas();
				else
					jugador2.aumentaRondas();
				reset();
			} else {
				if (jugador1.isEstaMuerto()) {
					AudioPlayer.get().playEffectSound("muerte1.wav");
					reset();
					jugador2.aumentaRondas();
				}
				if (jugador2.isEstaMuerto()) {
					AudioPlayer.get().playEffectSound("muerte1.wav");
					reset();
					jugador1.aumentaRondas();
				}
				if (jugador1.getRondasGanadas() == 3) {
					new VictoriaJugadorUno();
					g.stop();

				}
				if (jugador2.getRondasGanadas() == 3) {
					new VictoriaJugadorDos();
					g.stop();
				}
			}
		} else {
			iniciarRonda();
		}

	}

	public void reset() {
		rondaIniciada = false;

		temp.reset();
		ListIterator<AtaqueEspecial> itr = ae.listIterator();
		while (itr.hasNext()) {
			itr.next().reset();
		}
		iniciarRonda();
	}

	private void iniciarRonda() {
		ticks++;
		if (ticks % 120 == 0) {
			if (tiempo > 0) {
				tiempo--;
			} else {
				tiempo = 3;
				rondaIniciada = true;
			}
			if (tiempo == 2) {
				jugador1.reset();
				jugador2.reset();
				AudioPlayer.get().playEffectSound("321Peleen.wav");
			}
		}
	}

}
