import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Barycentre {
	private int x;
	private int y;
	public ArrayList<Point> listepoint;
	
	public Barycentre()
	{
		listepoint = new ArrayList<Point>();
	}
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
	public void afficher(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(this.x-3, this.y-3, 4, 4);
	}
	public String toSVG()
	{
		return "<circle cx='"+(x-3)+"' cy='"+(y-3)+"' r='4' fill='blue' />";
	}
}    