package engine;

import java.awt.EventQueue;

import javax.swing.UIManager;

import menus.Principal;

public class Launcher {
	public static void main(String[] args) {
		 try { 
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		    } catch(Exception ignored){}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
