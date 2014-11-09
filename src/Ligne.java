import java.awt.Graphics;


public class Ligne extends Forme {
	private Point point1;
	private Point point2;
	
	public boolean afficherPoint = false;
	
	public Ligne()
	{
	
	}
	public Ligne(int p1_x, int p1_y, int p2_x, int p2_y)
	{
		System.out.println("hello");
		point1 = new Point(p1_x, p1_y);
		point2 = new Point(p2_x, p2_y);
	}
	public Ligne(Point p1, Point p2)
	{
		point1 = p1;
		point2 = p2;
	}
	public void setP1(int x,int y)
	{
		point1.setX(x);
		point1.setY(y);
	}
	public void setP2(int x, int y)
	{
		point2.setX(x);
		point2.setY(y);
	}
	public void afficher(Graphics g)
	{
		if(afficherPoint)
		{
			point1.afficher(g);
			point2.afficher(g);
		}
		g.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}
	public void afficherPoint(boolean a)
	{
		this.afficherPoint = a;
	}
}
