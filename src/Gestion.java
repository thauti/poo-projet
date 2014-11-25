import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Gestion {
	
	private ArrayList<Forme> figure;
	
	private Color c = Color.BLACK;
	
	private boolean afficherPoint = true;
	
	private double zoom_x = 1.0;
	private double zoom_y = 1.0;
	
	public enum Mode{
		POINT, LIGNE, CARRE, RECTANGLE
	}
	
	public Mode mode;
	public Gestion()
	{
		this.c = Color.BLACK;
		mode = Mode.POINT;
		figure = new ArrayList<Forme>();
	}
	public void setColor(Color c)
	{
		this.c = c;
	}
	public void ajouter(Forme f)
	{
		if(f != null)
			figure.add(f);
		else
			System.out.println("Erreur");
		System.out.println(figure.toString());
	}
	public boolean isclicSurPoint(int x, int y)
	{
		for(int i = 0; i<figure.size(); i++)
		{
			if(figure.get(i) instanceof Point)
			{
				Point p = (Point)(figure.get(i));
				if((x <= p.getX()+4 && x >= p.getX()-4) && (y <= p.getY()+4 && y >= p.getY()-4))
				{
					return true;
				}
			}
		}
		return false;
	}
	public Point getclicSurPoint(int x, int y)
	{
		for(int i = 0; i<figure.size(); i++)
		{
			if(figure.get(i) instanceof Point)
			{
				Point p = (Point)(figure.get(i));
				if((x <= p.getX()+4 && x >= p.getX()-4) && (y <= p.getY()+4 && y >= p.getY()-4))
				{
					return p;
				}
			}
		}
		return null;
	}
	public void afficher(Graphics g)
	{
		for(int i =0; i<figure.size();i++)
		{
			if(afficherPoint)
			{
				figure.get(i).afficherPoint(afficherPoint);;
			}
			figure.get(i).afficher(g);
		}
	}
	public Color getCouleur()
	{
		return this.c;
	}
	public double getZoomX()
	{
		return this.zoom_x;
	}
	public double getZoomY()
	{
		return this.zoom_y;
	}
	public void addZoom(double i, double j)
	{
		this.zoom_x += i;
		this.zoom_y += j;
	}
}
