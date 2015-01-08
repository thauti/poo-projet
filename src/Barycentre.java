import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Represente un Barycentre
 */
public class Barycentre {
	private int x;
	private int y;
	/**
	 *  La liste des points lies au barycentre
	 */
	public ArrayList<Point> listepoint;
	
	/**
	 * Constructeur
	 */
	public Barycentre()
	{
		listepoint = new ArrayList<Point>();
	}
	/**
	 * Genere la position du barycentre
	 */
	public void calculerBarycentre()
	{
		if(listepoint.size() !=0)
		{
			int sommex =0;
			int sommey =0;
			for(Point p: listepoint)
			{
				sommex += p.getX();
				sommey += p.getY();
			}
			this.x = sommex/listepoint.size();
			this.y = sommey/listepoint.size();
		}
	}
	/**
	 * Methode de rendu du barycentre
	 */
	public void afficher(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(this.x-3, this.y-3, 4, 4);
	}
	/**
	 * Export en SVG
	 */
	public String toSVG()
	{
		return "<circle cx='"+(x-3)+"' cy='"+(y-3)+"' r='4' fill='blue' />";
	}
}    