import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Gestion {
	
	private ArrayList<Forme> figure;
	
	private ArrayList<Forme> selection;
	
	private ArrayList<Intersection> intersection;
	
	private Color c = Color.BLACK;
	
	private boolean afficherPoint = true;
	public boolean enMouvement = false;
	
	private double zoom_x = 1.0;
	private double zoom_y = 1.0;
	
	public enum Mode{
		POINT, LIGNE, DROITE, SELECTION, MOUVEMENT
	}
	
	public Mode mode;
	public Gestion()
	{
		this.c = Color.BLACK;
		mode = Mode.POINT;
		figure = new ArrayList<Forme>();
		selection = new ArrayList<Forme>();
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
		System.out.println(f.toString());
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
			g.setColor(c);
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
	public ArrayList<Forme> getFigure()
	{
		return this.figure;
	}
	public String toSVG()
	{
		return ExportSVG.toSVG(figure);
	}
	public void chargerArray(Object a)
	{
		try
		{
		this.figure = (ArrayList<Forme>) a;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Erreur � l'ouverture du fichier");
		}
	}
	public void addSelection(Forme f)
	{
		this.selection.add(f);
	}
	public  ArrayList<Forme> getSelection()
	{
		return this.selection;
	}
	public void mouvement(int x, int y) {
		for(Forme a: selection) 
		{
			if(a instanceof Point)
			{
				System.out.println(a);
				int xdecallage = x - a.x;
				int ydecallage = y - a.y;
				System.out.println(xdecallage +";"+ydecallage);
				System.out.println(a.x +".;."+a.y);
				((Point) a).setX(a.x + xdecallage);
				((Point) a).setY(a.y + ydecallage);
			}
		}
		
	}

}
