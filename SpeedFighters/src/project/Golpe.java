package project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Golpe extends ObjetoDeJuego {

	private Rectangle punchBox;
	private int hitX, hitWidth, hitY, hitHeight;

	public Golpe(Jugador jugador) {
		hitWidth = 60;
		hitHeight = 20;
		if (jugador.isMirandoIzquierda()) {
			hitX = (int) jugador.getPosX() + (int) jugador.getWidth();
			hitY = (int) jugador.getPosY()+10;
		} else {
			hitX = (int) jugador.getPosX() - (int) jugador.getWidth()-28;
			hitY = (int) jugador.getPosY()+10;
		}
		punchBox = new Rectangle(hitX, hitY, hitWidth, hitHeight);

	}

	public void destroy() {
		hitY = 0;
		hitX = 0;
		hitWidth = 0;
		hitHeight = 0;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(punchBox.x, punchBox.y, punchBox.width, punchBox.height);
	}
}