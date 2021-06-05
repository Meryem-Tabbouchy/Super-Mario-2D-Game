package main.java.com.accueil.start;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WelcomeImagePan extends JPanel{
	private static final String urlGameImage="supermario.jpg";

	private BufferedImage imageAccueil;
	
	public WelcomeImagePan() {
		
		//this.setSize(500,650);
		//this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setResizable(false);
		// TODO Auto-generated constructor stub
		// chargement de l'image
		try {
			imageAccueil= ImageIO.read( new File(urlGameImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g)
	{
	
		g.drawImage(imageAccueil, 0, 0, null);
	}

}
