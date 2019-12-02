package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.AudioPlayer;
import engine.Game;

public class PreJuego extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnComenzarPartida = new JButton("Comenzar Partida");
	JButton btnControles = new JButton("Controles");
	JButton btnRegresar = new JButton("Regresar");
	private final JLabel label = new JLabel("");

	public PreJuego() {
		AudioPlayer.get().setEffectsVolume(0.9f);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnComenzarPartida.setBounds(377, 291, 254, 60);
		btnComenzarPartida.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/botonComenzarPartida.PNG")));
		contentPane.add(btnComenzarPartida);

		btnRegresar.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/regresarBoton.PNG")));

		btnRegresar.setBounds(377, 433, 254, 60);
		contentPane.add(btnRegresar);

		btnControles.setBounds(377, 362, 254, 60);
		btnControles.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/botonControles.PNG")));
		contentPane.add(btnControles);
		label.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/MainMenu.png")));
		label.setBounds(0, 0, 1008, 537);

		contentPane.add(label);

		btnComenzarPartida.setOpaque(false);
		btnComenzarPartida.setContentAreaFilled(false);
		btnComenzarPartida.setBorderPainted(false);
		btnComenzarPartida.setFocusPainted(false);

		btnControles.setOpaque(false);
		btnControles.setContentAreaFilled(false);
		btnControles.setBorderPainted(false);
		btnControles.setFocusPainted(false);

		btnRegresar.setOpaque(false);
		btnRegresar.setContentAreaFilled(false);
		btnRegresar.setBorderPainted(false);
		btnRegresar.setFocusPainted(false);

		btnComenzarPartida.addActionListener(this);
		btnComenzarPartida.addMouseListener(this);
		btnControles.addActionListener(this);
		btnControles.addMouseListener(this);
		btnRegresar.addActionListener(this);
		btnRegresar.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnComenzarPartida) {
			AudioPlayer.get().playEffectSound("buttonClicked.wav");
			new Game("SPEED FIGHTERS", 1024, 576).start();
			this.dispose();
		}
		if (e.getSource() == btnRegresar) {
			AudioPlayer.get().playEffectSound("buttonClicked.wav");
			new Principal();
			this.dispose();
		}
		if (e.getSource() == btnControles) {
			new Controles();
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnComenzarPartida) {
			btnComenzarPartida.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/botonComenzarPartidaSelected.PNG")));
		}
		if (e.getSource() == btnControles) {
			btnControles.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/botonControlesSelected.PNG")));
		}
		if (e.getSource() == btnRegresar) {
			btnRegresar.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/regresarBotonSelected.PNG")));
		}
		AudioPlayer.get().playEffectSound("buttonRollover.wav");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnComenzarPartida) {
			btnComenzarPartida.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/botonComenzarPartida.PNG")));
		}
		if (e.getSource() == btnControles) {
			btnControles.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/botonControles.PNG")));
		}
		if (e.getSource() == btnRegresar) {
			btnRegresar.setIcon(new ImageIcon(PreJuego.class.getResource("/Imagenes/regresarBoton.PNG")));
		}
	}
}
