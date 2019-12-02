package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Vida extends HUD {

	public Vida(boolean j1) {
		super(j1);
		posX = isJugador1() ? 20 : 554;
		posY = 25;
		width = 0;
		height = 20;
	}

	public void renderDynamic(Graphics2D g, float hp) {
	
		if(hp >= 75)
			g.setColor(Color.green);
		else if (hp >= 50)
			g.setColor(Color.YELLOW);
		else if (hp >= 25)
			g.setColor(Color.ORANGE);
		else if (hp < 25)
			g.setColor(Color.RED);
		g.fillRect((int) posX + 1, (int) posY + 1, (int) width - 1, (int) height - 1);
	}

	@Override
	public void render(Graphics2D g) {

	}

}
