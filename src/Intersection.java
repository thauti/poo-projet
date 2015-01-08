import java.awt.Color;
import java.awt.Graphics;

/*
 * Represente une intersection
 */
public class Intersection {

	// Les deux formes qui se coupent
	public Forme f1;
	public Forme f2;
	
	/**
	 * La position de l'intersection
	 */
	int x;
	int y;
	
	/**
	 * Constructeur
	 * @param f1 Une figure
	 * @param f2 Une figure 
	 */
	public Intersection(Forme f1, Forme f2)
	{
		this.f1 = f1;
		this.f2 = f2;
	}
	/**
	 * Calcule la position de l'intersection
	 */
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

		}
	}
	/**
	 * Methode d'affichage
	 */
	public void afficher(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x-2, y-2, 4, 4);
	}
	/**
	 * Representation en SVG
	 */
	public String toSVG()
	{
		return "<circle cx='"+(x)+"' cy='"+(y)+"' r='2' fill='green' />";
	}
	
}
