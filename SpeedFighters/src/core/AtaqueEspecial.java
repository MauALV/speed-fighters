package core;

import java.awt.Color;
import java.awt.Graphics2D;

public class AtaqueEspecial extends ObjetoDeJuego {

	private int direccion;

	public AtaqueEspecial(Jugador j, int dir) {
		posX = j.getPosX() + 3;
		posY = j.getPosY() + j.getHeight() / 2;
		width = 25;
		height = 25;
		direccion = dir;
	}

	public void tick() {
		switch (direccion) {
		case 0: // derecha
			posX += 15;
			break;
		case 1: // diagonal arriba-derecha
			posX += 15;
			posY -= 15;
			break;
		case 2: // arriba
			posY -= 15;
			break;
		case 3: // diagonal arriba-izquierda
			posX -= 15;
			posY -= 15;
			break;
		case 4: // izquierda
			posX -= 15;
			break;
		case 5: // diagonal abajo-izquierda
			posX -= 15;
			posY += 15;
			break;
		case 6: // abajo
			posY += 15;
			break;
		case 7: // diagonal abajo-derecha
			posX += 15;
			posY += 15;
			break;

		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect((int) posX, (int) posY, (int) width, (int) height);
	}

}
