import java.awt.event.ActionListener;

import javax.swing.*;


public class Barre extends JMenuBar{
	
	private Fenetre fenetre;
	
	private JMenu fichier;
	
	private JMenu options;
	
	private JMenuItem open;
	
	private JMenuItem save;
	
	private JMenuItem exit;
	
	public Barre(Fenetre fen)
	{
		this.fenetre = fen;
		
		fichier = new JMenu("Fichier");
		options = new JMenu("Options");
		
		open = new JMenuItem("Ouvrir");
		save = new JMenuItem("Enregistrer");
		exit = new JMenuItem("Quitter");
		
		fichier.add(open);
		fichier.add(save);
		fichier.add(exit);
		
		this.add(fichier);
		this.add(options);
	}
}
