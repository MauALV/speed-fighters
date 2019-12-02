package estados;

import java.awt.Graphics2D;

import core.AnimationSprite;
import core.Jugador;
import core.SpriteBuilder;

public class Muriendo implements Estado {
	
	Jugador jugador;
	SpriteBuilder builder;
	AnimationSprite sprite;

	public Muriendo(Jugador jugador) {
		super();
		this.jugador = jugador;
		builder = new SpriteBuilder("./Sprites/muerte.png", 100, 140, 0, 0);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);
		builder.addImage(4, 0);

		sprite = new AnimationSprite((int) jugador.getPosX() - 50, (int) jugador.getPosY() - 30, builder.build(),
				100, 140);
		
		sprite.setAnimSpd(2);

	}

	@Override
	public void animacion(Graphics2D g) {

		sprite.setX((int) jugador.getPosX() - 50);
		sprite.setY((int) jugador.getPosY() - 30);
		sprite.update();
		sprite.render(g);

	}

}