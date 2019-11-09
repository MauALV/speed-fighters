package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class HP extends HUD {

	public HP(boolean j1) {
		super(j1);
		posX = isJugador1() ? 20 : 554;
		posY = 20;
		width = 0;
		height = 20;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.fillRect((int) posX + 1, (int) posY + 1, (int) width - 1, (int) height - 1);
	}

}
