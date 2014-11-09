import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Gestion {
	
	private ArrayList<Forme> figure;
	
	private Color c = Color.BLACK;
	
	private boolean afficherPoint = true;
	
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
}
