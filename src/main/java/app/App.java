package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ui.menus.MenuPrincipal;

public class App {

	public static void main(String[] args) {
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setContentPane(new MenuPrincipal(ventana));
		ventana.setVisible(true);
	}

}