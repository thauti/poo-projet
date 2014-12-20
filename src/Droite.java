import java.awt.Graphics;
import java.io.Serializable;


public class Droite extends Forme implements Serializable {


	private static final long serialVersionUID = -6873228210001511477L;
	private double m; // Coefficient directeur y = mx + p
	private double p; 
	
	private int orgx;
	private int orgy;
	
	private double org2x;
	private double org2y;
	
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
	public void afficher(Graphics g) {
		if(org2x - orgx != 0)
		{
			m = (org2y - orgy)/(org2x - orgx);
			p = (orgy - orgx*m);
			
			int y1 = (int) (0*m + p);
			int y2 = (int) (5000*m +p);
			
			g.drawLine(0, y1, 5000, y2);
		}
		else
		{
			g.drawLine(((int)org2x), 0, ((int)org2x), 5000);
		}

	}
	
}
