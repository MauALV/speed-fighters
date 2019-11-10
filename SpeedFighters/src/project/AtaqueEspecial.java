package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class AtaqueEspecial extends ObjetoDeJuego{
	
	private int direccion;

	public AtaqueEspecial(Jugador j, int d){
		posX = j.getPosX();
		posY = j.getPosY();
		width = 25;
		height = 25;
		direccion = d;
	}
	
	public void tick() {
		if(direccion == 1) { //solo a la derecha
			
			posX += 10;
			
		} else if (direccion == 0) { // hacia arriba
			
			posY -= 10;
			
		} else if (direccion == -1){ // solo a la izquierda
			
			posX -= 10;
			
		}else if (direccion == -2){ // diagonal hacia izquierda
			
			posX -= 10;
			posY -= 10;
			
		}else if (direccion == 2){ // diagonal hacia derecha
			
			posX += 10;
			posY -= 10;
			
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect((int)posX, (int)posY, (int)width,(int) height);
	}

}
