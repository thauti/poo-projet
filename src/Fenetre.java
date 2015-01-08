
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

/**
 * Fenetre Principale
 */
public class Fenetre extends JFrame{
	private Barre menu_bar;

	private Gestion gestion;
	
	private ZoneDessin zonedessin;

	public JScrollPane scrollpan;
	
	/**
	 * Cree une fenetre
	 * @param Gestion
	 */
	public Fenetre(Gestion gest)
	{
		super("Dessin Vectoriel");
		this.gestion = gest;
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initFenetre();
		this.setVisible(true);
		
	}
	/**
	 * Initialise la fenetre
	 */
	private void initFenetre()
	{
		menu_bar = new Barre(this);
		this.setJMenuBar(menu_bar);
		this.zonedessin = new ZoneDessin(this);
		this.zonedessin.setPreferredSize(new Dimension(5000,5000));
		this.scrollpan = new JScrollPane(zonedessin);
		scrollpan.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpan.add(zonedessin);
        scrollpan.setViewportView(zonedessin);

        this.add(scrollpan);
	}
	public Gestion getGestion()
	{
		return this.gestion;
	}
	public Barre getBarre()
	{
		return menu_bar;
	}

}
