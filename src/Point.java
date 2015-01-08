import java.awt.Graphics;

/**
 * Represente un Point
 */
public class Point extends Forme{

	private static final long serialVersionUID = 3147055707617939538L;

	/* Position*/
	private int x;
	private int y;
	
	/**
	 * Constructeur
	 */
	public Point()
	{
		this.x = 0;
		this.y = 0;
	}
	/**
	 * Constructeur
	 * @param x Abscisse
	 * @param y Ordonnee
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	/**
	 * Retourne l'abscisse
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * Retourne l'ordonnee
	 */
	public int getY()
	{
		return this.y;
	}
	/**
	 * Setter x
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	/**
	 * Setter y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	/**
	 * 
	 * @see Forme#afficher(java.awt.Graphics)
	 */
	public void afficher(Graphics g)
	{
		g.setColor(this.c);
		g.fillOval(this.x-4+super.x, this.y-4+super.y, 8, 8);
	}
	/**
	 * 
	 * @see Forme#toSVG()
	 */
	public String toSVG()
	{
		int x = this.x;
		int y = this.y;
		return "<circle cx='"+x+"' cy='"+y+"' r='4' fill='rgb("+this.c.getRed()+","+this.c.getGreen()+","+this.c.getBlue()+")' />";
		
	}
}
