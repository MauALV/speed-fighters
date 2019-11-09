package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Special extends HUD {

	public Special(boolean j1) {
		super(j1);
		posX = isJugador1() ? 320 : 554;
		posY = 45;
		width = 0;
		height = 20;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect((int) posX + 1, (int) posY + 1, (int) width - 1, (int) height - 1);

	}

}
