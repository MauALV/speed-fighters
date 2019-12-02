package menus;

import java.awt.Color;
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

public class Principal extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnIniciar = new JButton("");
	private JButton btnSalir = new JButton("");

	public Principal() {
		AudioPlayer.get().setMusicVolume(0.8f);
		AudioPlayer.get().playBackMusic("Musica2.wav");
		AudioPlayer.get().setEffectsVolume(0.9f);
		setResizable(false);
		setTitle("Speed Fighters");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnIniciar.setBackground(new Color(240, 240, 240));
		btnIniciar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/iniciarBoton.PNG")));

		btnIniciar.setBounds(382, 320, 254, 60);
		btnIniciar.addActionListener(this);
		btnIniciar.setRolloverEnabled(true);
		btnIniciar.addMouseListener(this);
		btnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/SalirBoton.PNG")));

		btnSalir.setBounds(382, 391, 254, 60);
		btnSalir.addActionListener(this);
		btnSalir.addMouseListener(this);

		contentPane.setLayout(null);
		contentPane.add(btnIniciar);
		contentPane.add(btnSalir);

		btnIniciar.setOpaque(false);
		btnIniciar.setContentAreaFilled(false);
		btnIniciar.setBorderPainted(false);
		btnIniciar.setFocusPainted(false);

		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);

		JLabel backgroundL = new JLabel("");
		backgroundL.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/victoriaDos.png")));
		backgroundL.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/victoriaUno.png")));
		backgroundL.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/MainMenu.png")));
		backgroundL.setBounds(0, 0, 1008, 536);
		contentPane.add(backgroundL);

		AudioPlayer.get().playEffectSound("buttonClicked.wav");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnIniciar) {
			AudioPlayer.get().setEffectsVolume(0.7f);
			AudioPlayer.get().playEffectSound("buttonClicked.wav");
			new PreJuego();
			this.dispose();
		}
		if (e.getSource() == btnSalir) {
			AudioPlayer.get().playEffectSound("buttonClicked.wav");
			System.exit(0);

		}

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnIniciar) {
			btnIniciar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/iniciarBotonSelected.PNG")));
		}
		if(e.getSource() == btnSalir) {
			btnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/salirBotonSelected.PNG")));
		}
		AudioPlayer.get().playEffectSound("buttonRollover.wav");
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
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnIniciar) {
			btnIniciar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/iniciarBoton.PNG")));
		}
		if(e.getSource() == btnSalir) {
			btnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/salirBoton.PNG")));
		}
	}
}
