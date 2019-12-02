package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Especial extends HUD {

	public Especial(boolean j1) {
		super(j1);
		posX = isJugador1() ? 320 : 554;
		posY = 50;
		width = 0;
		height = 20;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect((int) posX + 1, (int) posY + 1, (int) width - 1, (int) height - 1);

	}
	
	@Override
	public void renderDynamic(Graphics2D g, float sp) {
		g.setColor(Color.cyan);
		g.fillRect((int) posX + 1, (int) posY + 1, (int) width - 1, (int) height - 1);
		
	}

}
