package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rondas extends HUD {

	boolean jugador1;

	public Rondas(boolean j1) {
		super(j1);
		jugador1 = j1;
	}

	@Override
	public void render(Graphics2D g) {


	}

	public void renderRondas(Graphics2D g, int rondas) {
		g.setColor(Color.white);
		if (jugador1) {
			switch (rondas) {
			case 1:
				g.fillRect(306, 51, 9, 9);
				break;

			case 2:
				g.fillRect(306, 51, 9, 9);
				g.fillRect(291, 51, 9, 9);
				break;

			case 3:
				g.fillRect(306, 51, 9, 9);
				g.fillRect(291, 51, 9, 9);
				g.fillRect(276, 51, 9, 9);
				break;
			}
		}
		else {
			switch (rondas) {
			case 1:
				g.fillRect(710, 51, 9, 9);
				break;

			case 2:
				g.fillRect(710, 51, 9, 9);
				g.fillRect(725, 51, 9, 9);
				break;

			case 3:
				g.fillRect(710, 51, 9, 9);
				g.fillRect(725, 51, 9, 9);
				g.fillRect(740, 51, 9, 9);
				break;
			}
		}

	}

	@Override
	public void renderDynamic(Graphics2D g, float hp) {


	}

}
