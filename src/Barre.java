import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Cree la Barre de Menu
 */
public class Barre extends JMenuBar implements ActionListener{
	
	private Fenetre fenetre;
	
	private JMenu fichier;
	
	private JMenu options;
	
	private JToggleButton point;
	
	private JToggleButton delete;
	
	private JToggleButton ligne;
	
	private JToggleButton droite;
	
	private JToggleButton selection;
	
	private JMenuItem barycentre;
	
	private JCheckBoxMenuItem masquerbarycentre;
	
	private JCheckBoxMenuItem masquerintersection;

	private JToggleButton bouger;
	
	private JButton couleur;
	
	private JMenuItem nouveau;
	
	private JMenuItem open;
	
	private JMenuItem save;
	
	private JMenuItem exit;
	
	private JMenuItem save2;
	
	/**
	 * Cree une barre
	 * @param fen
	 * 			La fenetre rattache
	 */
	public Barre(Fenetre fen)
	{
		/**
		 * Constructeur de la barre
		 * @param fen 
		 */
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
		ligne.setIcon(new ImageIcon("res/segment.png"));
		ligne.setMargin(new Insets(0,0,0,0));
		ligne.setPreferredSize(new Dimension(10,10));
		ligne.addActionListener(this);
		
		droite = new JToggleButton();
		droite.setIcon(new ImageIcon("res/ligne.png"));
		droite.setMargin(new Insets(0,0,0,0));
		droite.setPreferredSize(new Dimension(10,10));
		droite.addActionListener(this);
		
		delete = new JToggleButton();
		delete.setIcon(new ImageIcon("res/delete.png"));
		delete.setMargin(new Insets(0,0,0,0));
		delete.setPreferredSize(new Dimension(10,10));
		delete.addActionListener(this);
		
		selection = new JToggleButton();
		selection.setIcon(new ImageIcon("res/selection.png"));
		selection.setMargin(new Insets(0,0,0,0));
		selection.setPreferredSize(new Dimension(10,10));
		selection.addActionListener(this);
		
		bouger = new JToggleButton();
		bouger.setIcon(new ImageIcon("res/bouger.png"));
		bouger.setMargin(new Insets(0,0,0,0));
		bouger.setPreferredSize(new Dimension(10,10));
		bouger.addActionListener(this);
		bouger.setEnabled(false);
		
		
		couleur = new JButton();
		couleur.setBackground(fen.getGestion().getCouleur());
		couleur.setMargin(new Insets(10,10,10,10));
		couleur.addActionListener(this);
		nouveau = new JMenuItem("Nouveau");
		nouveau.addActionListener(this);
		open = new JMenuItem("Ouvrir");
		open.addActionListener(this);
		save2 = new JMenuItem("Enregistrer");
		save2.addActionListener(this);
		save = new JMenuItem("Exporter en SVG ...");
		save.addActionListener(this);
		exit = new JMenuItem("Quitter");
		exit.addActionListener(this);
		barycentre = new JMenuItem("Calculer le barycentre");
		barycentre.addActionListener(this);
		
		masquerbarycentre = new JCheckBoxMenuItem("Afficher les barycentres");
		masquerbarycentre.addActionListener(this);
		masquerbarycentre.setSelected(true);
		
		masquerintersection = new JCheckBoxMenuItem("Afficher les intersections");
		masquerintersection.addActionListener(this);
		masquerintersection.setSelected(true);
		fichier.add(nouveau);
		fichier.add(open);
		fichier.add(save2);
		fichier.add(save);
		fichier.add(exit);
		
		options.add(barycentre);
		options.add(masquerbarycentre);
		options.add(masquerintersection);
		this.add(fichier);
		this.add(options);
		this.add(point);
		this.add(ligne);
		this.add(droite);
		this.add(delete);
		this.add(selection);
		this.add(bouger);;
		this.add(couleur);
	}
	public void desactiverMouvement()
	{
		this.bouger.setSelected(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == couleur)
		{
			//Ouvrir le nuancier
			ColorPicker cp = new ColorPicker(fenetre, couleur);
		}
		if(o == point)
		{
			// On cree un point
			if(point.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.POINT;
				ligne.setSelected(false);
				droite.setSelected(false);
				selection.setSelected(false);
				bouger.setEnabled(false);
				delete.setSelected(false);
		}
		if(o == nouveau)
		{
			// On reset la fenetre
			fenetre.getGestion().getSelection().clear();
			fenetre.getGestion().getFigure().clear();
			fenetre.getGestion().getBarycentre().clear();
			fenetre.getGestion().getIntersection().clear();
			fenetre.repaint();
			
			
		}
		if(o == ligne)
		{
			// On cree une ligne
			if(ligne.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.LIGNE;
				point.setSelected(false);
				droite.setSelected(false);
				selection.setSelected(false);
				bouger.setEnabled(false);
				delete.setSelected(false);
		}
		if(o == droite)
		{
			// on cree une droite
			if(droite.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.DROITE;
				point.setSelected(false);
				ligne.setSelected(false);
				selection.setSelected(false);
				bouger.setEnabled(false);
				delete.setSelected(false);
		}
		if(o == selection)
		{
			//on selectionne
			if(selection.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.SELECTION;
				point.setSelected(false);
				ligne.setSelected(false);
				droite.setSelected(false);
				bouger.setEnabled(true);
				delete.setSelected(false);
			
		}
		if(o == delete)
		{
			// Supression
			if(delete.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.SUPPRIMER;
				ligne.setSelected(false);
				droite.setSelected(false);
				selection.setSelected(false);
				bouger.setSelected(false);
				point.setSelected(false);
		}
		if(o == bouger)
		{
			// Deplacement
			if(selection.isSelected())
				this.fenetre.getGestion().mode = this.fenetre.getGestion().mode.MOUVEMENT;
				this.fenetre.getGestion().enMouvement = true;
				this.fenetre.getGestion().Mouvementactiver = false;
		}
		if(o == save)
		{
			// Export en SVG
			JFileChooser filechooser = new JFileChooser();
			filechooser.setDialogTitle("Exporter en SVG ...");
			filechooser.setAcceptAllFileFilterUsed(false);
			filechooser.setFileFilter(new FileNameExtensionFilter("Scalable Vector Graphics (.svg)", "svg"));
			int a = filechooser.showSaveDialog(fenetre);
			if (a == JFileChooser.APPROVE_OPTION) {
			    File fichier = filechooser.getSelectedFile();
			    try {
			    	FileWriter fichiersave = null;
			    	if(fichier.getAbsolutePath().endsWith(".svg"))
			    	{
			    		fichiersave = new FileWriter(filechooser.getSelectedFile());
			    	}
			    	else
			    	{
			    		fichiersave = new FileWriter(filechooser.getSelectedFile()+".svg");
			    	}
					fichiersave.write(fenetre.getGestion().toSVG());
					fichiersave.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    System.out.println("Save as file: " +fichier.getAbsolutePath()+".svg");
			}
			
		}
		if(o == save2)
		{
			// Sauvegarder
			JFileChooser filechooser = new JFileChooser();
			filechooser.setDialogTitle("Enregistrer");
			int a = filechooser.showSaveDialog(fenetre);
			if (a == JFileChooser.APPROVE_OPTION) {
				File fichier = filechooser.getSelectedFile();
				try {
					FileOutputStream fos = new FileOutputStream(fichier.getAbsolutePath());
					ObjectOutputStream oos= new ObjectOutputStream(fos);
					oos.writeObject(this.fenetre.getGestion().getFigure());
					oos.close();
					fos.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		}
		if(o == open)
		{
			// Ouvrir
			JFileChooser filechooser = new JFileChooser();
			filechooser.setDialogTitle("Ouvrir");
			int a = filechooser.showOpenDialog(fenetre);
			if (a == JFileChooser.APPROVE_OPTION) {
				File fichier = filechooser.getSelectedFile();
				try {
					FileInputStream fis = new FileInputStream(fichier.getAbsolutePath());
					ObjectInputStream ois = new ObjectInputStream(fis);
					fenetre.getGestion().getFigure().clear();
					fenetre.getGestion().chargerArray(ois.readObject());
					fenetre.getGestion().recalculerIntersection();
					ois.close();
					fis.close();
				} catch (IOException e1) {
					System.out.println("Erreur � l'ouverture du fichier");
				} catch (ClassNotFoundException e1) {
					System.out.println("Erreur � l'ouverture du fichier");
				}
			}
		}
		if(o == barycentre)
		{
			// Creation d'un barycentre
			int etat = this.fenetre.getGestion().calculerBarycentre();
			if(etat == -1)
			{
				JOptionPane.showMessageDialog(fenetre,
					    "Vous devez s�lectionner au moins 2 points",
					    "Erruer",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		if(o == masquerbarycentre)
		{
			if(masquerbarycentre.isSelected())
			{
				this.fenetre.getGestion().afficherBarycentre = true;
			}
			else
			{
				this.fenetre.getGestion().afficherBarycentre = false;
			}
		}
		if(o == masquerintersection)
		{
			if(masquerintersection.isSelected())
			{
				this.fenetre.getGestion().afficherIntersection = true;
			}
			else
			{
				this.fenetre.getGestion().afficherIntersection = false;
			}
		}
		if(o == exit)
		{
			this.fenetre.dispose();
			System.exit(0);
		}
		
	}
}
