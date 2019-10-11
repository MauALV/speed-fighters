package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import engine.Input;
import project.Estados.Estado;
import engine.Game;

public class Jugador extends ObjetoDeJuego {

	private class Vec2 {
		public float x, y;

		public Vec2(float _x, float _y) {
			x = _x;
			y = _y;
		}
	}

	private ArrayList<Vec2> pos;

	private Estado estatico;
	private Estado moviendo;
	private Estado saltando;
	private Estado bloqueando;
	private Estado muriendo;
	private Estado golpeando;
	private Estado golpe_especial;
	private Estado estado;

	private boolean jugador1, puedeSaltar;
	private float aceleracion = 0.2f;

	public Jugador() {
		height = 100;
		width = 30;
		jugador1 = true;
		puedeSaltar = true;
		pos = new ArrayList<>();
		hitbox = new Rectangle((int) posX, (int) posY, (int) width, (int) height);
		pos.add(new Vec2(posX, posY));

	}

	public Jugador(boolean jugador1) {
		height = 100;
		width = 30;
		this.jugador1 = jugador1;
		this.puedeSaltar = true;
		pos = new ArrayList<>();
		hitbox = new Rectangle((int) posX, (int) posY, (int) width, (int) height);
		pos.add(new Vec2(posX, posY));
		this.spawn();
	}

	public boolean isJugador1() {
		return jugador1;
	}

	public void setJugador1(boolean jugador1) {
		this.jugador1 = jugador1;
	}

	public void spawn() {
		posY = 444; // tentativo
		if (this.isJugador1())
			posX = 50; // tentativo
		else
			posX = 900; // tentativo
	}

	public void accion() {

	}

	public void animacion() {

	}

	public void salta() {
		posY -= aceleracion;
		aceleracion -= 9;
	}

	public void tick() {
		Input input = Input.get();

		if (isJugador1()) {
			System.out.println("[J1] = (" + posX + ", " + posY + ")");
			if (input.isKeyPressed(KeyEvent.VK_A)) {
				posX -= 4;
			}
			if (input.isKeyPressed(KeyEvent.VK_D))
				posX += 4;
			if (input.isKeyPressed(KeyEvent.VK_W) && puedeSaltar) {
				salta();
				puedeSaltar = false;
			}

			if (posY >= 444) { //cambiar por colision con un bloque solido
				posY = 444;	   //ahorita esta para cuando toque el piso	
				puedeSaltar = true;
				aceleracion = 0.2f;
			}
			
			posY += aceleracion;
			aceleracion += .26;		
			
			hitbox.x = (int) posX;
			hitbox.y = (int) posY;
			pos.add(new Vec2(posX, posY));
		}

		else {
			System.out.println("[J2] = (" + posX + ", " + posY + ")");
			if (input.isKeyPressed(KeyEvent.VK_LEFT))
				posX -= 4;
			if (input.isKeyPressed(KeyEvent.VK_RIGHT))
				posX += 4;
			if (input.isKeyPressed(KeyEvent.VK_UP) && puedeSaltar) {
				salta();
				puedeSaltar = false;
			}

			posY += aceleracion;
			aceleracion += .26;

			if (posY >= 444) {
				posY = 444;
				puedeSaltar = true;
				aceleracion = 0.2f;
			}

			hitbox.x = (int) posX;
			hitbox.y = (int) posY;
			pos.add(new Vec2(posX, posY));
		}

	}

	public boolean collides(ObjetoDeJuego obj) {
		return hitbox.intersects(obj.getHitbox());

	}

	@Override
	public void render(Graphics2D g) {
		if (isJugador1()) {
			g.setColor(Color.RED);
			g.fillRect((int) posX, (int) posY, (int) width, (int) height);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect((int) posX, (int) posY, (int) width, (int) height);
		}

	}

}
