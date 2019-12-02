package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import engine.Input;
import estados.Estado;
import estados.Estatico;
import estados.GolpeEspecial;
import estados.Golpeando;
import estados.Moviendo;
import estados.Muriendo;
import estados.Saltando;

public class Jugador extends ObjetoDeJuego {

	private Estado estatico;
	private Estado moviendo;
	private Estado saltando;
	private Estado golpeando;
	private Estado golpe_especial;
	private Estado muriendo;
	private Estado estado;

	private boolean jugador1, mirandoDerecha;
	private boolean puedeSaltar, puedeDobleSalto, estaSaltando;
	private boolean estaGolpeando, atacandoEspecial;
	private boolean estaMuerto;
	private float gravedad;
	private int contadorGolpe;
	public Estado getEstado() {
		return estado;
	}

	private int hitWidth, hitHeight;
	private int hitX, hitY;
	private int rondasGanadas = 0;
	private int direccionMovimiento;
	private float hp, sp;
	private AtaqueEspecial ae;

	public Jugador(boolean jugador1) {
		height = 96;
		width = 32;
		hitWidth = 50;
		hitHeight = 20;
		hp = 100;
		sp = 0;
		contadorGolpe = 0;
		gravedad = 0.2f;
		puedeSaltar = true;
		estaGolpeando = false;
		puedeDobleSalto = false;
		atacandoEspecial = false;
		estaSaltando = false;
		this.jugador1 = jugador1;
		this.spawn();
		hitbox = new Rectangle((int) posX, (int) posY, (int) width, (int) height);

		estatico = new Estatico(this);
		moviendo = new Moviendo(this);
		golpeando = new Golpeando(this);
		saltando = new Saltando(this);
		muriendo = new Muriendo(this);
		golpe_especial= new GolpeEspecial(this);

		estado = estatico;

	}

	public void spawn() {
		posY = 448;
		posX = isJugador1() ? 50 : 942;
		direccionMovimiento = isJugador1() ? 1 : -1;
		mirandoDerecha = jugador1 ? true : false;
	}

	public void accion() {

	}

	public void animacion() {

	}

	public void salta() {
		posY -= gravedad;
		gravedad -= 9;
		estado = saltando;
	}

	@Override
	public void tick() {
		Input input = Input.get();
		if (isJugador1()) { // JUGADOR 1
			hp -= 0.1;
			if(!estaSaltando && !estaGolpeando)
				estado = estatico;
			if (input.isKeyPressed(KeyEvent.VK_A)) {
				mirandoDerecha = false;
				posX -= 4;
				estado = moviendo;
				if (!input.isKeyPressed(KeyEvent.VK_D))
					hp += 0.3;
			}

			if (input.isKeyPressed(KeyEvent.VK_D)) {
				mirandoDerecha = true;
				posX += 4;
				estado = moviendo;
				if (!input.isKeyPressed(KeyEvent.VK_A))
					hp += 0.3;
			}
			if (input.isKeyPressed(KeyEvent.VK_W) && puedeSaltar) {
				if (!input.isKeyPressed(KeyEvent.VK_S)) {
					salta();
					AudioPlayer.get().playEffectSound("saltoSencillo.wav");
					puedeSaltar = false;
					estaSaltando = true;
					puedeDobleSalto = false;
				}
			}
			if (input.isKeyReleased(KeyEvent.VK_W) && estaSaltando) {
				puedeDobleSalto = true;
				estaSaltando = false;
			}
			if (input.isKeyPressed(KeyEvent.VK_W) && !puedeSaltar && puedeDobleSalto) {
				gravedad = 0.2f;
				puedeDobleSalto = false;
				puedeSaltar = false;
				AudioPlayer.get().playEffectSound("saltoDoble.wav");
				salta();
			}
			if (input.isKeyPressed(KeyEvent.VK_S) && !puedeSaltar) {
				if (!input.isKeyPressed(KeyEvent.VK_W))
					gravedad += 1;
			}
			if (input.isKeyPressed(KeyEvent.VK_C) && contadorGolpe <= 2) {
				estaGolpeando = true;
				estado = golpeando;

			}
			if (input.isKeyReleased(KeyEvent.VK_C))
				contadorGolpe = 0;
			if (input.isKeyPressed(KeyEvent.VK_V) && sp == 100) {
				sp = 0;
				AudioPlayer.get().playEffectSound("poderEspecial.wav");
				atacandoEspecial = true;
				estado=golpe_especial;
			}
		}

		else { // JUGADOR 2
			hp -= 0.1;
			if(!estaSaltando && !estaGolpeando)
				estado = estatico;
			if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
				mirandoDerecha = false;
				posX -= 4;
				estado=moviendo;
				if (!input.isKeyPressed(KeyEvent.VK_RIGHT))
					hp += 0.3;
			}
			if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
				mirandoDerecha = true;
				posX += 4;
				estado = moviendo;
				if (!input.isKeyPressed(KeyEvent.VK_LEFT))
					hp += 0.3;
			}
			if (input.isKeyPressed(KeyEvent.VK_UP) && puedeSaltar) {
				if (!input.isKeyPressed(KeyEvent.VK_DOWN)) {
					salta();
					AudioPlayer.get().playEffectSound("saltoSencillo.wav");
					puedeSaltar = false;
					estaSaltando = true;
					puedeDobleSalto = false;
				}
			}
			if (input.isKeyReleased(KeyEvent.VK_UP) && estaSaltando) {
				puedeDobleSalto = true;
				estaSaltando = false;
			}
			if (input.isKeyPressed(KeyEvent.VK_UP) && !puedeSaltar && puedeDobleSalto) {
				gravedad = 0.2f;
				puedeDobleSalto = false;
				puedeSaltar = false;
				AudioPlayer.get().playEffectSound("saltoDoble.wav");
				salta();
			}
			if (input.isKeyPressed(KeyEvent.VK_DOWN) && !puedeSaltar) {
				if (!input.isKeyPressed(KeyEvent.VK_UP))
					gravedad += 1;
			}
			if (input.isKeyPressed(KeyEvent.VK_NUMPAD0) && contadorGolpe <= 2) {
				estaGolpeando = true;
				estado = golpeando;
			}
			if (input.isKeyReleased(KeyEvent.VK_NUMPAD0))
				contadorGolpe = 0;
			if (input.isKeyPressed(KeyEvent.VK_NUMPAD1) && sp == 100) {
				sp = 0;
				AudioPlayer.get().playEffectSound("poderEspecial.wav");
				atacandoEspecial = true;
				estado=golpe_especial;
			}

		}

		condicionesGameplay();
		posY += gravedad;
		gravedad += .26;
		hitbox.x = (int) posX;
		hitbox.y = (int) posY;

	}

	public void condicionesGameplay() {
		// BARRERAS
		if (posX + width > 1024)
			posX = 1024 - width;
		if (posX < 0)
			posX = 0;

		// HP
		if (hp <= 0)
			estaMuerto = true;
		if (hp >= 100)
			hp = 100;
		if (hp <= 0)
			hp = 0;

		// SP
		if (sp >= 100)
			sp = 100;
		if (sp <= 0)
			sp = 0;

		// SALTO
		if (posY + height >= 544) {
			posY = 448;
			puedeSaltar = true;
			gravedad = 0.2f;
		}

	}

	@Override
	public void render(Graphics2D g) {
	
		estado.animacion(g);
		
		if (estaGolpeando) {
			g.setColor(Color.yellow);
			contadorGolpe++;
			if (isMirandoDerecha()) {
				hitX = (int) getPosX() + (int) getWidth();
				hitY = (int) getPosY() + 25;
			} else {
				hitX = (int) getPosX() - (int) getWidth() - 18;
				hitY = (int) getPosY() + 25;
			}

			estaGolpeando = false;
		}
		if (atacandoEspecial) {
			ae.render(g);
			ae.tick();
		}
	}

	public boolean conectaGolpe(Jugador otroJugador) {
		Rectangle hitbox = new Rectangle(hitX, hitY, hitWidth, hitHeight);
		return hitbox.intersects(otroJugador.getHitbox());
	}

	public boolean isJugador1() {
		return jugador1;
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

	public boolean isEstaMuerto() {
		return estaMuerto;
	}

	public void setEstaMuerto(boolean estaMuerto) {
		this.estaMuerto = estaMuerto;
		if(estado == muriendo) {
			
		}
	}

	public int getRondasGanadas() {
		return rondasGanadas;
	}

	public void setRondasGanadas(int rondasGanadas) {
		this.rondasGanadas = rondasGanadas;
	}

	public void reset() {
		estado = estatico;
		System.out.println(rondasGanadas);
		estaMuerto = false;
		hp = 100;
		sp = 0;
		spawn();
	}

	public void aumentaRondas() {
		rondasGanadas++;

	}

	public void knockback(boolean dirOtro) {
		posY -= 25;
		if (dirOtro) {
			posX += 35;
		} else {
			posX -= 35;
		}

	}

	public void setEstado(Estado e) {
		estado = e;
		
	}

	public int getDireccionMovimiento() {
		return direccionMovimiento;
	}

	public void setDireccionMovimiento(int direccionMovimiento) {
		this.direccionMovimiento = direccionMovimiento;
	}
	
}
