import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



public class Barre extends JMenuBar implements ActionListener{
	
	private Fenetre fenetre;
	
	private JMenu fichier;
	
	private JMenu options;
	
	private JToggleButton point;
	
	private JToggleButton ligne;
	
	private JButton couleur;
	
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
		point.setPreferredSize(new Dimension(10,10));
		point.addActionListener(this);
		point.setSelected(true);
		
		ligne = new JToggleButton();
		ligne.setIcon(new ImageIcon("res/ligne.png"));
		ligne.setMargin(new Insets(0,0,0,0));
		ligne.setPreferredSize(new Dimension(10,10));
		ligne.addActionListener(this);
		
		couleur = new JButton();
		couleur.setBackground(fen.getGestion().getCouleur());
		couleur.setMargin(new Insets(10,10,10,10));
		couleur.addActionListener(this);
		
		open = new JMenuItem("Ouvrir");
		save = new JMenuItem("Enregistrer");
		exit = new JMenuItem("Quitter");
		
		fichier.add(open);
		fichier.add(save);
		fichier.add(exit);
		
		this.add(fichier);
		this.add(options);
		this.add(point);
		this.add(ligne);
		this.add(couleur);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == couleur)
		{
			ColorPicker cp = new ColorPicker(fenetre);
		}
		if(o == point)
		{
			if(point.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.POINT;
				ligne.setSelected(false);
			
		}
		if(o == ligne)
		{
			if(ligne.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.LIGNE;
				point.setSelected(false);
			
		}
		
	}
}
