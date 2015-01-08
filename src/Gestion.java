import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Objet Gestion, Gere la logique du programme
 */
public class Gestion {
	
	/**
	 *  Liste des figures crees
	 */
	private ArrayList<Forme> figure;
	
	/**
	 *  Figures en cours de selection
	 */
	private ArrayList<Forme> selection;
	
	/**
	 *  Liste des intersections
	 */
	private ArrayList<Intersection> intersection;
	
	/**
	 *  Liste des barycentres
	 */
	private ArrayList<Barycentre> barycentre;
	
	/**
	 *  Couleur en cours
	 */
	private Color c = Color.BLACK;
	
	/**
	 *  Options d'affichage
	 */
	private boolean afficherPoint = true;
	public boolean enMouvement = false;
	
	/*
	 *  Zoom
	 */
	private double zoom_x = 1.0;
	private double zoom_y = 1.0;
	
	/*
	 *  Mode en cours
	 */
	public enum Mode{
		POINT, LIGNE, DROITE, SELECTION, MOUVEMENT, SUPPRIMER
	}
	
	public Mode mode;

	public boolean Mouvementactiver;

	public int mouvementorgx;

	public int mouvementorgy;

	public boolean afficherBarycentre = true;

	public boolean afficherIntersection = true;
	
	/**
	 * Constructeur
	 */
	public Gestion()
	{
		this.c = Color.BLACK;
		mode = Mode.POINT;
		figure = new ArrayList<Forme>();
		selection = new ArrayList<Forme>();
		barycentre = new ArrayList<Barycentre>();
		intersection = new ArrayList<Intersection>();
	}
	/**
	 * Changer la couleur en cours
	 * @param c
	 * 			La couleur choisie
	 */
	public void setColor(Color c)
	{
		this.c = c;
	}
	/**
	 * Ajouter une forme
	 * @param f
	 * 			La forme
	 */
	public void ajouter(Forme f)
	{
		if(f != null)
		{
			figure.add(f);
			if(this.afficherIntersection)
				this.genererintersection();
		}
		else
			System.out.println("Erreur");
		System.out.println(f.toString());
	}
	/**
	 * Calcule les intersections
	 */
	public void genererintersection() {
		if(this.figure.size() > 1)
		{
			for(int i =0; this.figure.size()>i;i++)
			{
				for(int j=i+1; this.figure.size()>j;j++)
				{
					if(this.figure.get(i) instanceof Droite)
					{
						if(this.figure.get(j) instanceof Droite)
						{
							if(this.figure.get(j) != this.figure.get(i))
							{
							Intersection ins = new Intersection(this.figure.get(i), this.figure.get(j));
							ins.calculerIntersection();
							this.intersection.add(ins);
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Clique t-on sur un point
	 * @param x Position x
	 * @param y Position y
	 * @return true si on clique sur un point
	 */
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
	/**
	 * Retourne le point sur la posistion ou l'on clique
	 * @param x Position x
	 * @param y Position y
	 */
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
	/**
	 * Méthode de rendu
	 */
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
		if(this.afficherBarycentre)
		{
			for(Barycentre b: barycentre)
			{
				b.afficher(g);
				g.setColor(c);
			}
		}
		if(this.afficherIntersection)
		{
			for(Intersection i: intersection)
			{
				i.afficher(g);
				g.setColor(c);
			}
		}
		
		
	}
	/**
	 * Renvoie la couleur en cours
	 */
	public Color getCouleur()
	{
		return this.c;
	}
	/**
	 * Renvoie le zoom en x
	 */
	public double getZoomX()
	{
		return this.zoom_x;
	}
	/**
	 * Renvoie le zoom en y
	 */
	public double getZoomY()
	{
		return this.zoom_y;
	}
	/**
	 * Ajoute du zoom
	 */
	public void addZoom(double i, double j)
	{
		
		this.zoom_x += i;
		this.zoom_y += j;
	}
	/**
	 * Renvoie la liste de figures
	 */
	public ArrayList<Forme> getFigure()
	{
		return this.figure;
	}
	/**
	 * Génération du texte du futur SVG
	 */
	public String toSVG()
	{
		if(this.afficherBarycentre && this.afficherIntersection)
		{
			return ExportSVG.toSVG(figure, barycentre, intersection);
		}else
		if(this.afficherBarycentre == false && this.afficherIntersection == true)
		{
			return ExportSVG.toSVG(figure, null, intersection);

		}else
		if(this.afficherBarycentre == false && this.afficherIntersection == false)
		{
			return ExportSVG.toSVG(figure, null, null);

			
		}else
		if(this.afficherBarycentre == true && this.afficherIntersection == false)
		{
			return ExportSVG.toSVG(figure, barycentre, null);

		}
		return "";
	}
	/**
	 * Chargement d'un fichier
	 * @param a Objet
	 */
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
	/**
	 * Ajoute une forme a la selection
	 * @param f La forme
	 */
	public void addSelection(Forme f)
	{
		this.selection.add(f);
	}
	/**
	 * Renvoie la selection en cours
	 */
	public  ArrayList<Forme> getSelection()
	{
		return this.selection;
	}
	/**
	 * Bouge les elements selectionnees
	 */
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
			if(a instanceof Droite)
			{

				int ydecallage;
				ydecallage = y - this.mouvementorgy;
				((Droite) a).orgy += ydecallage;
				((Droite) a).org2y += ydecallage;
			}
		}
		this.mouvementorgx =x;
		this.mouvementorgy =y;
		
	}
	/**
	 * Indique si on clique sur une droite
	 */
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
	/**
	 * Renvoie la droite en x,y
	 * @param Position x
	 * @param Poistion y
	 */
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
	/**
	 * Inidique si un point fait partie d'un segment
	 *@param p Le point
	 */
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
	/**
	 * Calcul de barycentre
	 */
	public int calculerBarycentre()
	{
		if(this.selection.size()  !=0)
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
				return 1;
			}
			else
			{
				return -1;
			}
		}
		return -1;
	}
	/**
	 * Recalcule les barycentres
	 */
	public void recalculerBarycentre()
	{
		if(this.afficherBarycentre)
		{
			for(Barycentre b: barycentre)
			{
				b.calculerBarycentre();
			}
		}

	}
	/**
	 * Recalcule les intersections
	 */
	public void recalculerIntersection()
	{
		if(this.afficherIntersection)
		{
			for(Intersection b: intersection)
			{
				b.calculerIntersection();
			}
		}
	}
	/**
	 * Getter Barycentre
	 */
	public ArrayList<Barycentre> getBarycentre()
	{
		return this.barycentre;
	}
	/**
	 * Getter Intersection
	 */
	public ArrayList<Intersection> getIntersection()
	{
		return this.intersection;
	}

}
