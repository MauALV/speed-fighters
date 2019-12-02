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

public class VictoriaJugadorDos extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnSalir = new JButton("Salir");

	public VictoriaJugadorDos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
		setLocationRelativeTo(null);
		setVisible(true);
		

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		
		btnSalir.setBounds(377, 433, 254, 60);
		contentPane.add(btnSalir);
		
		
		
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setIcon(new ImageIcon(VictoriaJugadorDos.class.getResource("/Imagenes/SalirBoton.PNG")));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VictoriaJugadorDos.class.getResource("/Imagenes/victoriaDos.png")));
		label.setBounds(0, 0, 1008, 537);
		contentPane.add(label);
		
		btnSalir.addActionListener(this);
		btnSalir.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSalir) {
			AudioPlayer.get().playEffectSound("buttonClicked.wav");
			System.exit(0);
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
		if(e.getSource() == btnSalir) {
			btnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/SalirBotonSelected.PNG")));
		}
		AudioPlayer.get().playEffectSound("buttonRollover.wav");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnSalir) {
			btnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/SalirBoton.PNG")));
		}		
	}
}