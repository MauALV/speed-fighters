package project;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class ObjetoDeJuego {

	protected int height, posX, posY, width;
	protected Rectangle hitbox;

	public ObjetoDeJuego() {
		posX = 0;
		posY = 0;
		height = 0;
		width = 0;
	}

	public ObjetoDeJuego(int x, int y, int h, int w) {
		this.posX = x;
		this.posY = y;
		this.height = h;
		this.width = w;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	public abstract void render(Graphics2D g);

	public boolean colisiona(ObjetoDeJuego objAux) {

		return true;
	}

}