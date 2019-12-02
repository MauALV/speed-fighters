package HUD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Temporizador {

	private int tiempo;
	private int ticks = 0;
	private String display;

	public Temporizador() {
		tiempo = 31;
		display = "";
	}

	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRect(487, 20, 50, 50);
		if (tiempo < 10) {
			g.drawString(display, 510, 50);
		} else
			g.drawString(display, 507, 50);

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

	}

	public void reset() {
		tiempo = 31;
		display = "";
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

}