import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Gestion {
	
	private ArrayList<Forme> figure;
	
	private ArrayList<Forme> selection;
	
	private ArrayList<Intersection> intersection;
	
	private ArrayList<Barycentre> barycentre;
	
	private Color c = Color.BLACK;
	
	private boolean afficherPoint = true;
	public boolean enMouvement = false;
	
	private double zoom_x = 1.0;
	private double zoom_y = 1.0;
	
	public enum Mode{
		POINT, LIGNE, DROITE, SELECTION, MOUVEMENT, SUPPRIMER
	}
	
	public Mode mode;

	public boolean Mouvementactiver;

	public int mouvementorgx;

	public int mouvementorgy;
	public Gestion()
	{
		this.c = Color.BLACK;
		mode = Mode.POINT;
		figure = new ArrayList<Forme>();
		selection = new ArrayList<Forme>();
		barycentre = new ArrayList<Barycentre>();
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
		for(Barycentre b: barycentre)
		{
			b.afficher(g);
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
		return ExportSVG.toSVG(figure, barycentre);
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
			System.out.println("Erreur à l'ouverture du fichier");
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
				int xdecallage;
				int ydecallage;
				
				xdecallage = x -this.mouvementorgx;
				ydecallage = y - this.mouvementorgy;
				((Point) a).setX(((Point) a).getX()+xdecallage);
				((Point) a).setY(((Point) a).getY()+ydecallage);
			}
		}
		this.mouvementorgx =x;
		this.mouvementorgy =y;
		
	}
	public boolean isclicsurDroite(int x, int y) {
		for(Forme f: this.figure)
		{
			if(f instanceof Droite)
			{
				int dy = (int) (x*((Droite) f).getm() + ((Droite) f).getp());
				if(dy < y+5 && dy > y-5)
				{
					return true;
				}
				
			}
		}
		return false;
	}
	public Forme getclicsurDroite(int x, int y) {
		for(Forme f: this.figure)
		{
			if(f instanceof Droite)
			{
				int dy = (int) (x*((Droite) f).getm() + ((Droite) f).getp());
				if(dy < y+5 && dy > y-5)
				{
					return f;
				}
				
			}
		}
		return null;
	}
	public boolean dansSegment(Point p)
	{
		for(Forme f: this.figure)
		{
			if(f instanceof Ligne)
			{
				if(((Ligne) f).getP1() == p)
				{
					return true;
				}
				if(((Ligne) f).getP2() == p)
				{
					return true;
				}
			}
		}
		return false;
	}
	public Forme getPointSegment(Point p)
	{
		for(Forme f: this.figure)
		{
			if(f instanceof Ligne)
			{
				if(((Ligne) f).getP1() == p)
				{
					return f;
				}
				if(((Ligne) f).getP2() == p)
				{
					return f;
				}
			}
		}
		return null;
	}
	public int calculerBarycentre()
	{
		if(this.selection.size()  != 0)
		{
			int npoint = 0;
			for(Forme f: this.selection)
			{
				if(f instanceof Point)
				{
					npoint += 1;
				}
			}
			if(npoint >= 2)
			{
				Barycentre b = new Barycentre();
				for(Forme f: this.selection)
				{
					if(f instanceof Point)
					{
						b.listepoint.add((Point) f);
					}
				}
				b.calculerBarycentre();
				this.barycentre.add(b);
				System.out.println("tello");
				return 1;
			}
		}
		return 0;
	}
	public ArrayList<Barycentre> getBarycentre()
	{
		return this.barycentre;
	}

}
