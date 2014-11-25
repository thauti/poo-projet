import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ZoneDessin extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{

	private int cursor_x;
	private int cursor_y;
	
	private boolean enCours;
	private Forme enCreation = null;
	private Gestion g;
	private Fenetre f;
	
	private int orgx;
	private int orgy;
	
	public ZoneDessin(Fenetre fen)
	{
		super();
		this.setBackground(Color.WHITE);
		this.f = fen;
		this.g = f.getGestion();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = ((Graphics2D) g);
		g2.scale(this.g.getZoomX(), this.g.getZoomY());
		super.paintComponent(g);
		this.g.afficher(g);
		if(enCreation != null)
		{
			enCreation.afficher(g);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {

		int x = (int) (e.getPoint().x / g.getZoomX());
		int y = (int) (e.getPoint().y / g.getZoomY());
		
		if(!enCours)
		{
			enCours = true;
			switch(g.mode)
			{
			case POINT:
				enCreation = new Point(x, y);
				g.ajouter(enCreation);
				enCours = false;
				enCreation = null;
				break;
			case LIGNE:
				enCreation = new Ligne(x, y,x,y);
				orgx = x;
				orgy = y;
				break;
			}
			
		}
		else
		{
			switch(g.mode)
			{
			case LIGNE:
				System.out.println(g.mode);
				if(this.g.isclicSurPoint(orgx, orgy))
				{
					System.out.println("Oui");
					Point p = this.g.getclicSurPoint(orgx, orgy);
					if(p != null)
					{
						if(this.g.isclicSurPoint(x, y))
						{
							Point p2 = this.g.getclicSurPoint(x, y);
							Ligne l = new Ligne(p, p2);
							g.ajouter(l);
						}
						else
						{
							Ligne l = new Ligne(p, x, y);
							g.ajouter(l);
							g.ajouter(l.getP2());
						}

						
					}
				}
				else
				{
					Ligne l = new Ligne(orgx, orgy, x, y);
					g.ajouter(l);
					g.ajouter(l.getP1());
					g.ajouter(l.getP2());
				}
				break;
			}

			enCours = false;
			enCreation = null;
		}
			this.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = (int) (e.getPoint().x / g.getZoomX());
		int y = (int) (e.getPoint().y / g.getZoomY());
		if(enCreation != null)
		{
			switch(g.mode)
			{

			case LIGNE:
				((Ligne)enCreation).setP2(x,y);
				break;
			}
			this.repaint();
			
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if(arg0.getWheelRotation() < 0)
		{
			g.addZoom(0.1, 0.1);
			this.repaint();
		}
		if(arg0.getWheelRotation() > 0)
		{
			g.addZoom(-0.1, -0.1);
			this.repaint();
		}
			
		
	}
}
