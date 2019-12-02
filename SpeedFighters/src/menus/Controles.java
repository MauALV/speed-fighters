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

public class Controles extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnRegresar = new JButton("Regresar");

	public Controles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("imagenControles");
		lblNewLabel.setIcon(new ImageIcon(Controles.class.getResource("/Imagenes/controles.png")));
		lblNewLabel.setBounds(42, 65, 924, 350);
		contentPane.add(lblNewLabel);

		
		btnRegresar.setBounds(409, 446, 189, 54);
		btnRegresar.addActionListener(this);
		btnRegresar.addMouseListener(this);
		contentPane.add(btnRegresar);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		AudioPlayer.get().playEffectSound("buttonRollover.wav");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			new PreJuego();
			this.dispose();
		}

	}

}
