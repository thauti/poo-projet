import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Represente une droite
 */
public class Droite extends Forme implements Serializable {


	private static final long serialVersionUID = -6873228210001511477L;
	/**
	 * Coefficient directeur
	 */
	public double m; // Coefficient directeur y = mx + p
	public double p; 
	
	// Org et org2 representent 2 points permettant de calculer le coefficient directeur
	public int orgx;
	public int orgy;
	
	public double org2x;
	public double org2y;
	private int y1;
	private int y2;
	
	public Droite()
	{
		
	}
	public void setCoefDirecteur(int x)
	{
		this.m =x;
	}
	public void setOrg(int x, int y)
	{
		this.orgx = x;
		this.orgy = y;
	}
	public void setOrg2(double xprecis, double yprecis)
	{
		this.org2x = xprecis;
		this.org2y = yprecis;
	}
	/**
	 * 
	 * @see Forme#afficher(java.awt.Graphics)
	 */
	public void afficher(Graphics g) {
		g.setColor(c);
		if(org2x - orgx != 0)
		{
			m = (org2y - orgy)/(org2x - orgx);
			p = (orgy - orgx*m);
			
			int y1 = (int) (0*m + p);
			int y2 = (int) (5000*m +p);
			this.y1 = y1;
			this.y2 = y2;
			g.drawLine(0, y1, 5000, y2);

		}
		else
		{
			g.drawLine(((int)org2x), 0, ((int)org2x), 5000);
		}

	}
	/**
	 * Getter coefficient directeur
	 */
	public double getm()
	{
		return this.m;
	}
	/**
	 * Getter
	 */
	public double getp()
	{
		return this.p;
	}
	public void setColor2(Color couleur) {
		this.couleurref = couleur;
	}
	/**
	 * 
	 * @see Forme#toSVG()
	 */
	public String toSVG()
	{
		return "<line x1='"+0+"' y1='"+this.y1+"'"
				+ 				" x2 ='"+5000+"' y2='"+this.y2+"'"
									+ " stroke='rgb("+this.c.getRed()+","+this.c.getGreen()+","+this.c.getBlue()+")' />";
	}
}
