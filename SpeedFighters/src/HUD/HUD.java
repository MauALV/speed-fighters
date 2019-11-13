package HUD;

import java.awt.Graphics2D;

import core.ObjetoDeJuego;

public abstract class HUD extends ObjetoDeJuego {
	protected boolean jugador1;

	public HUD() {
	}

	public HUD(boolean j1) {
		jugador1 = j1;
	}

	public boolean isJugador1() {
		return jugador1;
	}

	public void setJugador1(boolean jugador1) {
		this.jugador1 = jugador1;
	}

	public abstract void render(Graphics2D g);

	public abstract void renderDynamic(Graphics2D g, float hp);

}
