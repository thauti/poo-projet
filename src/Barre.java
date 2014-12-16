import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



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
		save = new JMenuItem("Exporter en SVG ...");
		save.addActionListener(this);
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
			ColorPicker cp = new ColorPicker(fenetre, couleur);
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
		if(o == save)
		{

			System.out.println(ExportSVG.toSVG(this.fenetre.getGestion().getFigure()));
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
		
	}
}
