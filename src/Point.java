import java.awt.Graphics;


public class Point extends Forme{

	private static final long serialVersionUID = 3147055707617939538L;

	private int x;
	
	private int y;
	
	public Point()
	{
		this.x = 0;
		this.y = 0;
	}
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void afficher(Graphics g)
	{
		g.setColor(this.c);
		g.drawOval(this.x-4+super.x, this.y-4+super.y, 8, 8);
	}
	public String toSVG()
	{
		int x = this.x;
		int y = this.y;
		return "<circle cx='"+x+"' cy='"+y+"' r='5' fill='black' />";
		
	}
}
