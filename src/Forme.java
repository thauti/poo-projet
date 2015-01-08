import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Classe representant une Forme/Figure
 */
public class Forme implements Serializable{

	private static final long serialVersionUID = 4088339238779728407L;
	public boolean afficherPoint = false;
	int x, y;
	/**
	 *  Couleur "en cours"
	 */
	public Color c;
	
	/**
	 *  Couleur "sauvegarde" en cas de changements
	 */
	public Color couleurref;

	public Forme()
	{
		
	}

	/**
	 * Methode d'affichage
	 */
	public void afficher(Graphics g) {

		
	}
	/**
	 * Methode non utilisee
	 */
	public void afficherPoint(boolean a)
	{
		
	}
	/**
	 * Permet de changer la couleur
	 */
	public void setColor(Color e)
	{
		this.c = e;
	}
	/**
	 * Representation en syntaxe SVG
	 */
	public String toSVG()
	{
		return "";
		
	}
}
