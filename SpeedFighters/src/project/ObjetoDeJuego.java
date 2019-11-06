package project;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class ObjetoDeJuego {

	protected float height, posX, posY, width;
	protected Rectangle hitbox;
	protected boolean destroyed;
	public ObjetoDeJuego() {
		destroyed = false;
		posX = 0;
		posY = 0;
		height = 0;
		width = 0;
	}

	public ObjetoDeJuego(float x, float y, float h, float w) {
		this.posX = x;
		this.posY = y;
		this.height = h;
		this.width = w;
	}
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	public abstract void render(Graphics2D g);

	public boolean colisiona(ObjetoDeJuego objAux) {

		return true;
	}
	
	public void onCollision(ObjetoDeJuego other) {
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

}