import java.awt.Color;
import java.awt.Graphics;


public class Intersection {

	public Forme f1;
	public Forme f2;
	
	int x;
	int y;
	
	public Intersection(Forme f1, Forme f2)
	{
		this.f1 = f1;
		this.f2 = f2;
	}
	public void calculerIntersection()
	{
		if(f1 instanceof Droite)
		{
			if(f2 instanceof Droite)
			{
				if(((Droite) f1).m != ((Droite)f2).m)
				{
					double a1 = ((Droite) f1).m;
					double b1 = ((Droite) f1).p;
					double a2 = ((Droite) f2).m;
					double b2 = ((Droite) f2).p;
					
					try{
						this.x =(int) ((b2-b1)/(a1-a2));
					}
					catch(Exception e)
					{
			
					}
					this.y = (int) (a1*x+b1);
				}
			}
			if(f2 instanceof Ligne)
			{
				
			}
		}
		if(f1 instanceof Ligne)
		{
			if(f2 instanceof Droite)
			{
				
			}
			if(f2 instanceof Ligne)
			{
				
			}
		}
	}
	public void afficher(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x-2, y-2, 4, 4);
	}
	public String toSVG()
	{
		return "<circle cx='"+(x)+"' cy='"+(y)+"' r='2' fill='green' />";
	}
	
}
