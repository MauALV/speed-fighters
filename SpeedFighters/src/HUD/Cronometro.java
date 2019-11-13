package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cronometro extends HUD {

	private int tiempo;
	private int ticks = 0;
	private String display;

	public Cronometro() {
		super();
		tiempo = 31;
		display = "";
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawRect(490, 20, 50, 50);
		g.drawString(display, 510, 50);

	}

	public void tick() {
		ticks++;
		if (ticks % 120 == 0) {
			if (tiempo > 0) {
				tiempo--;
				display = "";
				display += tiempo;
			} else
				tiempo = 0;
		}
		// System.out.println(tiempo);
	}

	@Override
	public void renderDynamic(Graphics2D g, float hp) {
		// TODO Auto-generated method stub

	}

}