import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


public class Forme implements Serializable{

	private static final long serialVersionUID = 4088339238779728407L;
	public boolean afficherPoint = false;
	int x, y;
	public Color c;
	
	public Color couleurref;

	public Forme()
	{
		
	}

	public void afficher(Graphics g) {

		
	}
	public void afficherPoint(boolean a)
	{
		
	}
	public void setColor(Color e)
	{
		this.c = e;
	}
	public String toSVG()
	{
		return "";
		
	}
}
