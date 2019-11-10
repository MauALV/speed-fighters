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

	private boolean jugador1, puedeSaltar, mirandoDerecha, estaGolpeando, puedeDobleSalto, estaSaltando, atacandoEspecial;
	private float gravedad = 0.2f;
	int hitWidth = 50;
	int hitHeight = 20;
	int hitX, hitY;
	float hp, sp;

	public Jugador(boolean jugador1) {
		height = 96;
		width = 32;
		hp = 100;
		sp = 0;
		posY = 448;
		this.jugador1 = jugador1;
		puedeSaltar = true;
		estaGolpeando = false;
		puedeDobleSalto = false;
		atacandoEspecial = false;
		estaSaltando = false;
		hitbox = new Rectangle((int) posX, (int) posY, (int) width, (int) height);
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
		mirandoDerecha = jugador1 ? true : false;
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
			//ddwSystem.out.println(atacandoEspecial);
			hp -= 0.1;
			if (input.isKeyPressed(KeyEvent.VK_A)) {
				mirandoDerecha = false;
				posX -= 4;
				if (!input.isKeyPressed(KeyEvent.VK_D))
					hp += 0.3;
			}
			if (input.isKeyPressed(KeyEvent.VK_D)) {
				mirandoDerecha = true;
				posX += 4;
				if (!input.isKeyPressed(KeyEvent.VK_A))
					hp += 0.3;
			}
			// Controlar el golpe
			if (input.isKeyPressed(KeyEvent.VK_G)) {
				estaGolpeando = true;
			}
			
			// Controlar el salto y el salto doble
			if (input.isKeyPressed(KeyEvent.VK_W) && puedeSaltar) {
				salta();
				puedeSaltar = false;
				estaSaltando = true;
			}
			if (input.isKeyReleased(KeyEvent.VK_W) && estaSaltando) {
				puedeDobleSalto = true;
				estaSaltando = false;
			}
		
			if (input.isKeyPressed(KeyEvent.VK_W) && !puedeSaltar && puedeDobleSalto) {
				gravedad = 0.2f;
				puedeDobleSalto = false;
				salta();
			}
			if (input.isKeyPressed(KeyEvent.VK_S) && !puedeSaltar) {
				if (!input.isKeyPressed(KeyEvent.VK_W))
					gravedad += 1;
			}
			
			if (input.isKeyPressed(KeyEvent.VK_H) && sp == 100) {
				sp = 0;
				atacandoEspecial = true;
			}

			//Controlar los limites de la vida del jugador 1
			if (hp >= 100)
				hp = 100;
			if (hp <= 0)
				hp = 0;
			
			//Controlar los limites del contador especial del jugador 1

			if (sp >= 100)
				sp = 100;
			if (sp <= 0)
				sp = 0;

			if (posY + height >= 544) {
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
			hp -= 0.1;
			if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
				mirandoDerecha = false;
				posX -= 4;
				if (!input.isKeyPressed(KeyEvent.VK_RIGHT))
					hp += 0.3;
			}
			if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
				mirandoDerecha = true;
				posX += 4;
				if (!input.isKeyPressed(KeyEvent.VK_LEFT))
					hp += 0.3;
			}
			if (input.isKeyPressed(KeyEvent.VK_NUMPAD0)) {
				estaGolpeando = true;
			}
			if (input.isKeyPressed(KeyEvent.VK_UP) && puedeSaltar) {
				salta();
				puedeSaltar = false;
				estaSaltando = true;
			}
			if (input.isKeyReleased(KeyEvent.VK_UP) && estaSaltando) {
				puedeDobleSalto = true;
				estaSaltando = false;
			}
		
			if (input.isKeyPressed(KeyEvent.VK_UP) && !puedeSaltar && puedeDobleSalto) {
				gravedad = 0.2f;
				puedeDobleSalto = false;
				salta();
			}

			if (input.isKeyPressed(KeyEvent.VK_DOWN) && !puedeSaltar) {
				if (!input.isKeyPressed(KeyEvent.VK_UP))
					gravedad += 1;
			}
			
			if (input.isKeyPressed(KeyEvent.VK_NUMPAD1) && sp == 100) {
				sp = 0;
				atacandoEspecial = true;
			}

			if (hp >= 100)
				hp = 100;
			if (hp <= 0)
				hp = 0;

			if (sp >= 100)
				sp = 100;
			if (sp <= 0)
				sp = 0;

			if (posY + height >= 544) {
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

	public void render(Graphics2D g) {
		if (isJugador1()) {
			g.setColor(Color.RED);
			g.fillRect((int) posX, (int) posY, (int) width, (int) height);

		} else {
			g.setColor(Color.BLUE);
			g.fillRect((int) posX, (int) posY, (int) width, (int) height);
		}
		if (estaGolpeando) {
			if (isMirandoDerecha()) {
				hitX = (int) getPosX() + (int) getWidth();
				hitY = (int) getPosY() + 25;
			} else {
				hitX = (int) getPosX() - (int) getWidth() - 18;
				hitY = (int) getPosY() + 25;
			}
			g.fillRect(hitX, hitY, hitWidth, hitHeight);
			estaGolpeando = false;
		}
		
	}

	public boolean conectaGolpe(Jugador otroJugador) {
		Rectangle hitbox = new Rectangle((int) hitX, (int) hitY, (int) hitWidth, (int) hitHeight);
		return hitbox.intersects(otroJugador.getHitbox());
	}

	public int getHitWidth() {
		return hitWidth;
	}

	public int getHitHeight() {
		return hitHeight;
	}

	public int getHitX() {
		return hitX;
	}

	public int getHitY() {
		return hitY;
	}

	public boolean isMirandoDerecha() {
		return mirandoDerecha;
	}

	public boolean isEstaGolpeando() {
		return estaGolpeando;
	}

	public void setEstaGolpeando(boolean estaGolpeando) {
		this.estaGolpeando = estaGolpeando;
	}

	public float getHp() {
		return hp;
	}

	public float getSp() {
		return sp;
	}

	public void setSp(float sp) {
		this.sp = sp;
	}

	public boolean isAtacandoEspecial() {
		return atacandoEspecial;
	}

	public void setAtacandoEspecial(boolean atacandoEspecial) {
		this.atacandoEspecial = atacandoEspecial;
	}
	

}
