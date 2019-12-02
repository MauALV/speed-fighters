package core;


import java.awt.Graphics2D;
import java.awt.Rectangle;

import estados.Muriendo;

public class AtaqueEspecial extends ObjetoDeJuego {
	
	private int direccion;
	private boolean esDeJugador1;
	SpriteBuilder builder;
	AnimationSprite sprite;
	private boolean reiniciada;
	Muriendo muriendo1, muriendo2;
	
	
	
	public AtaqueEspecial(Jugador j, int dir) {
		posX = j.getPosX() + 3;
		posY = j.getPosY() + j.getHeight() / 2;
		if(j.isJugador1()) {
			esDeJugador1 = true;
			
		} else {
			esDeJugador1 = false;
		}
		reiniciada = false;
		width = 32;
		height = 32;
		direccion = dir;
		
		builder = new SpriteBuilder("./Sprites/poderEspecial.png", 64, 64, 0, 0);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);
		builder.addImage(4, 0);
		builder.addImage(5, 0);
		
		sprite = new AnimationSprite((int)posX,(int)posY, builder.build(),64, 64);
		sprite.setAnimSpd(3);
	}

	public void tick(Jugador j1, Jugador j2) {
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
		
		if(esDeJugador1) {
			if(conectaEspecial(j2)) {
				if(!j2.isEstaMuerto())
					j2.setEstado(new Muriendo(j2));
				j2.setEstaMuerto(true);
			}
		} else {
			if(conectaEspecial(j1)) {
				if(!j1.isEstaMuerto())
					j1.setEstado(new Muriendo(j1));
				j1.setEstaMuerto(true);	
				}
		}
	}

	@Override
	public void render(Graphics2D g) {

		if(!reiniciada) {
		sprite.setX((int)posX);
		sprite.setY((int)posY);
		sprite.update();
		sprite.render(g);
		}
	}
	
	public boolean conectaEspecial(Jugador otroJugador) {
		Rectangle hitbox = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
		return hitbox.intersects(otroJugador.getHitbox());
	}
	
	public boolean isEsDeJugador1() {
		return esDeJugador1;
	}

	public void setEsDeJugador1(boolean esDeJugador1) {
		this.esDeJugador1 = esDeJugador1;
	}

	public void reset() {
		posX = 0;
		posY = 0;
		width = 0;
		height = 0;
		reiniciada = true;
	}

}
