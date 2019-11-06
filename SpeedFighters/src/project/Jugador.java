package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import engine.Input;

public class Jugador extends ObjetoDeJuego {

	/*
	 * private Estado estatico; private Estado moviendo; private Estado saltando;
	 * private Estado bloqueando; private Estado muriendo; private Estado golpeando;
	 * private Estado golpe_especial; private Estado estado;
	 */

	private boolean jugador1, puedeSaltar, mirandoIzquierda, estaGolpeando;
	private float gravedad = 0.2f;
	private Golpe golpe;

	public Jugador(boolean jugador1) {
		height = 96;
		width = 32;
		posY = 448;
		this.jugador1 = jugador1;
		this.puedeSaltar = true;
		hitbox = new Rectangle((int) posX, (int) posY, (int) width, (int) height);
		mirandoIzquierda = jugador1 ? false : true;
		this.spawn();
	}

	public boolean isPuedeSaltar() {
		return puedeSaltar;
	}

	public void setPuedeSaltar(boolean puedeSaltar) {
		this.puedeSaltar = puedeSaltar;
	}

	public float getAceleracion() {
		return gravedad;
	}

	public void setAceleracion(float gravedad) {
		this.gravedad = gravedad;
	}

	public boolean isJugador1() {
		return jugador1;
	}

	public void setJugador1(boolean jugador1) {
		this.jugador1 = jugador1;
	}

	public void spawn() {
		posX = isJugador1() ? 50 : 942;
	}

	public void accion() {

	}

	public void animacion() {

	}

	public void salta() {
		posY -= gravedad;
		gravedad -= 9;
	}

	public void tick() {
		Input input = Input.get();

		if (isJugador1()) {
			if (input.isKeyPressed(KeyEvent.VK_A)) {
				mirandoIzquierda = false;
				posX -= 4;
			}

			if (input.isKeyPressed(KeyEvent.VK_D)) {
				mirandoIzquierda = true;
				posX += 4;
			}
			if (input.isKeyPressed(KeyEvent.VK_P)) {
				estaGolpeando = true;
				golpe = new Golpe(this);
				golpe.destroy();
			}
			if (input.isKeyPressed(KeyEvent.VK_W) && puedeSaltar) {
				salta();
				puedeSaltar = false;
			}

			if (posY >= 448) {
				posY = 448;
				puedeSaltar = true;
				gravedad = 0.2f;
			}

			posY += gravedad;
			gravedad += .26;
			if (posX > 1024)
				posX = 0;
			if (posX < 0)
				posX = 1024;
			hitbox.x = (int) posX;
			hitbox.y = (int) posY;
		}

		else {
			if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
				mirandoIzquierda = false;
				posX -= 4;
			}

			if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
				mirandoIzquierda = true;
				posX += 4;
			}
			if (input.isKeyPressed(KeyEvent.VK_L)) {
				estaGolpeando = true;
				golpe = new Golpe(this);
				golpe.destroy();
			}
			if (input.isKeyPressed(KeyEvent.VK_UP) && puedeSaltar) {
				salta();
				puedeSaltar = false;
			}

			if (posY >= 448) {
				posY = 448;
				puedeSaltar = true;
				gravedad = 0.2f;
			}
			posY += gravedad;
			gravedad += .26;

			if (posX > 1024)
				posX = 0;
			if (posX < 0)
				posX = 1024;
			hitbox.x = (int) posX;
			hitbox.y = (int) posY;
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
		if (estaGolpeando) {
			golpe.render(g);
			estaGolpeando = false;
		}
	}

	public boolean isMirandoIzquierda() {
		return mirandoIzquierda;
	}

	public void setMirandoIzquierda(boolean mirandoIzquierda) {
		this.mirandoIzquierda = mirandoIzquierda;
	}

	public boolean isEstaGolpeando() {
		return estaGolpeando;
	}

	public void setEstaGolpeando(boolean estaGolpeando) {
		this.estaGolpeando = estaGolpeando;
	}

	public Golpe getGolpe() {
		return golpe;
	}

	public void setGolpe(Golpe golpe) {
		this.golpe = golpe;
	}
}
