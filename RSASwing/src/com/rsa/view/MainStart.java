package com.rsa.view;

import javax.swing.JFrame;

public class MainStart {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		MainView panel = new MainView();
		panel.init();
		panel.start();
		jf.add(panel);
		jf.pack();
		jf.setVisible(true);
	}
}
