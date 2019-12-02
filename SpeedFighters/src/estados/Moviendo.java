package estados;

import java.awt.Graphics2D;

import core.AnimationSprite;
import core.Jugador;
import core.SpriteBuilder;

public class Moviendo implements Estado {
	Jugador jugador;
	SpriteBuilder builder;
	AnimationSprite sprite;
	int dir = 1;

	public Moviendo(Jugador jugador) {
		super();
		this.jugador = jugador;
		if (jugador.isJugador1()) {
			builder = new SpriteBuilder("./Sprites/player1.png", 100, 140, 0, 0);
			builder.addImage(2, 0);
			builder.addImage(3, 0);
			builder.addImage(4, 0);
			builder.addImage(5, 0);
			builder.addImage(6, 0);
			builder.addImage(7, 0);
			sprite = new AnimationSprite((int) jugador.getPosX() - 50, (int) jugador.getPosY() - 30, builder.build(),
					100, 140);
		} else {
			builder = new SpriteBuilder("./Sprites/player2.png", 100, 140, 0, 0);
			builder.addImage(2, 0);
			builder.addImage(3, 0);
			builder.addImage(4, 0);
			builder.addImage(5, 0);
			builder.addImage(6, 0);
			builder.addImage(7, 0);
			sprite = new AnimationSprite((int) jugador.getPosX() - 50, (int) jugador.getPosY() - 30, builder.build(),
					100, 140);
		}

		sprite.setAnimSpd(6);

	}

	public void animacion(Graphics2D g) {

		if (jugador.isJugador1()) {
			if (!jugador.isMirandoDerecha()) {
				dir = -1;
				sprite.setX((int) jugador.getPosX() + 50);
				sprite.setY((int) jugador.getPosY() - 30);

			} else {
				dir = 1;
				sprite.setX((int) jugador.getPosX() - 50);
				sprite.setY((int) jugador.getPosY() - 30);
			}
		} else {
			if (!jugador.isMirandoDerecha()) {
				dir = 1;
				sprite.setX((int) jugador.getPosX() - 50);
				sprite.setY((int) jugador.getPosY() - 30);

			} else {
				dir = -1;
				sprite.setX((int) jugador.getPosX() + 50);
				sprite.setY((int) jugador.getPosY() - 30);
			}
		}
		sprite.setScaleX(1f * dir);
		sprite.update();
		sprite.render(g);
	}

}
