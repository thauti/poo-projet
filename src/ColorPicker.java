import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * * un nuancier
 */
public class ColorPicker extends JDialog implements ChangeListener, ActionListener{

	
		private JSlider rouge;
	    private JSlider vert;
	    private JSlider bleu;
	    private JPanel col;
	    private JPanel espace;
	    private Fenetre fen;
	    private JButton b;
	    JButton ok;
	    JButton annuler;
	   
	   
	    /**
    	 * Constructeur du Nuancer
    	 */
	    public ColorPicker(Fenetre fen, JButton b){
	        /*Specificité de la fenetre*/
	        this.setTitle("Couleur");
	        this.setSize(500, 300);
	        this.fen = fen;
	        this.b = b;
	        this.setLocationRelativeTo(null);
	       
	        /*Creation d'un GridLayout qui contiendra les composants de la fenetre*/
	        GridLayout tab = new GridLayout(1,2);
	        this.setLayout(tab);
	       
	        /*Creation des 3 slides RVB*/
	        rouge = new JSlider();
	        rouge.setMaximum(255);
	        rouge.setMinimum(0);
	        rouge.setPaintTicks(true);
	        rouge.setPaintLabels(true);
	        rouge.setMinorTickSpacing(10);
	        rouge.setMajorTickSpacing(100);
	        rouge.setBorder(BorderFactory.createTitledBorder("Rouge"));
	        rouge.setValue(fen.getGestion().getCouleur().getRed());
	        vert = new JSlider();
	        vert.setMaximum(255);
	        vert.setMinimum(0);
	        vert.setPaintTicks(true);
	        vert.setPaintLabels(true);
	        vert.setMinorTickSpacing(10);
	        vert.setMajorTickSpacing(100);
	        vert.setBorder(BorderFactory.createTitledBorder("Vert"));
	        vert.setValue(fen.getGestion().getCouleur().getGreen());
	        bleu = new JSlider();
	        bleu.setMaximum(255);
	        bleu.setMinimum(0);
	        bleu.setPaintTicks(true);
	        bleu.setPaintLabels(true);
	        bleu.setMinorTickSpacing(10);
	        bleu.setMajorTickSpacing(100);
	        bleu.setBorder(BorderFactory.createTitledBorder("Bleu"));
	        bleu.setValue(fen.getGestion().getCouleur().getBlue());
	        /*Creation de la partie "gauche" de la palette*/
	        JPanel panneauChoix = new JPanel();
	        this.getContentPane().add(panneauChoix);
	        /*Creation d'un nouveau GridLayout qui contiendra les Slides qui seront positionné a gauche de la palette*/
	        GridLayout choixCouleur = new GridLayout(3,1);
	        panneauChoix.setLayout(choixCouleur);
	        panneauChoix.add(rouge);
	        panneauChoix.add(vert);
	        panneauChoix.add(bleu);
	        rouge.addChangeListener(this);
	        bleu.addChangeListener(this);
	        vert.addChangeListener(this);
	        /*Creation de la partie "droite"*/
	        JPanel panneauButton = new JPanel();
	       
	        /*Creation d'un second GridLayout qui contiendra les 2 boutons*/
	        GridLayout containerBoutons = new GridLayout(4,1);
	        ok = new JButton("Ok");
	        ok.addActionListener(this);
	        annuler = new JButton("Annuler");
	        annuler.addActionListener(this);
	        col = new JPanel();
	        espace = new JPanel();
	        panneauButton.setLayout(containerBoutons);
	        panneauButton.add(col);
	        
	        panneauButton.add(ok,BorderLayout.CENTER);
	        panneauButton.add(annuler);
	        this.getContentPane().add(panneauButton);
	        this.setVisible(true);
	        col.setBackground(new Color(rouge.getValue(), vert.getValue(), bleu.getValue()));
	    }


		@Override
		public void stateChanged(ChangeEvent a) {
			Object e = a.getSource();
			col.setBackground(new Color(rouge.getValue(), vert.getValue(), bleu.getValue()));
			
			
		}


		@Override
		public void actionPerformed(ActionEvent a) {
			Object e = a.getSource();
			if(e == ok)
			{
				this.fen.getGestion().setColor(new Color(rouge.getValue(), vert.getValue(), bleu.getValue()));
				this.b.setBackground(new Color(rouge.getValue(), vert.getValue(), bleu.getValue()));
				this.dispose();
			}
			if(e == annuler)
			{
				this.dispose();
			}
			
		}
}
