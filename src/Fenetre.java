
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class Fenetre extends JFrame{
	private Barre menu_bar;

	private Gestion gestion;
	
	private ZoneDessin zonedessin;
	
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
	private void initFenetre()
	{
		menu_bar = new Barre(this);
		this.setJMenuBar(menu_bar);
		this.zonedessin = new ZoneDessin(this);
		this.add(zonedessin);
	}
	public Gestion getGestion()
	{
		return this.gestion;
	}

}