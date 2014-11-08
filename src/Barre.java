import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Barre extends JMenuBar{
	
	private Fenetre fenetre;
	
	private JMenu fichier;
	
	private JMenu options;
	
	private JToggleButton point;
	
	private JMenuItem open;
	
	private JMenuItem save;
	
	private JMenuItem exit;
	
	public Barre(Fenetre fen)
	{
		this.fenetre = fen;
		
		fichier = new JMenu("Fichier");
		options = new JMenu("Options");
		
		point = new JToggleButton();
		point.setIcon(new ImageIcon("res/point.png"));
		point.setMargin(new Insets(0,0,0,0));
		
		open = new JMenuItem("Ouvrir");
		save = new JMenuItem("Enregistrer");
		exit = new JMenuItem("Quitter");
		
		fichier.add(open);
		fichier.add(save);
		fichier.add(exit);
		
		this.add(fichier);
		this.add(options);
		this.add(point);
	}
}
