import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.ViewportLayout;


public class ZoneDessin extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{

	private int cursor_x;
	private int cursor_y;
	

	private boolean enCours;
	private Forme enCreation = null;
	private Gestion g;
	private Fenetre f;
	private JScrollPane scrollpan;
	
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
		this.f.scrollpan.repaint();
		this.g.afficher(g);

		if(enCreation != null)
		{
			enCreation.afficher(g);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Hello");
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
				enCreation.setColor(g.getCouleur());
				orgx = x;
				orgy = y;
				break;
			case DROITE:
				enCreation = new Droite();
				enCreation.setColor(g.getCouleur());
				orgx = x;
				orgy = y;
				((Droite) enCreation).setOrg(orgx, orgy);
				break;
			case SELECTION:
				enCours = false;
				if(g.isclicSurPoint(x, y))
				{
					if(!this.g.getSelection().contains(g.getclicSurPoint(x, y)))
					{
						
						g.getclicSurPoint(x, y).setColor(Color.RED);
						this.g.addSelection(g.getclicSurPoint(x, y));
						this.repaint();
					}
					else
					{
						g.getclicSurPoint(x, y).setColor(this.g.getCouleur());
						this.g.getSelection().remove(g.getclicSurPoint(x, y));
						this.repaint();
						
					}
				}
				else
				{
					
					for(Forme a: this.g.getSelection())
						if(a instanceof Point)
							a.setColor(Color.BLACK);
					this.g.getSelection().clear();
					this.repaint();
				}
				break;
			case MOUVEMENT:
				enCours = false;
				if(this.g.enMouvement)
					this.g.enMouvement = false;
					this.f.getBarre().desactiverMouvement();
					this.repaint();
				break;
			}
			
		}
		else
		{
			switch(g.mode)
			{
			case LIGNE:
				
				if(this.g.isclicSurPoint(orgx, orgy))
				{
					
					Point p = this.g.getclicSurPoint(orgx, orgy);
					if(p != null)
					{
						if(this.g.isclicSurPoint(x, y))
						{
							Point p2 = this.g.getclicSurPoint(x, y);
							Ligne l = new Ligne(p, p2);
							l.setColor(g.getCouleur());
							g.ajouter(l);
						}
						else
						{
							Ligne l = new Ligne(p, x, y);
							l.setColor(g.getCouleur());
							g.ajouter(l);
							g.ajouter(l.getP2());
						}

						
					}
				}
				else
				{
					Ligne l = new Ligne(orgx, orgy, x, y);
					l.setColor(g.getCouleur());
					g.ajouter(l);
					g.ajouter(l.getP1());
					g.ajouter(l.getP2());
				}
				break;
			case DROITE:
				if(this.g.isclicSurPoint(orgx, orgy))
				{
					Droite d = new Droite();
					Point p = this.g.getclicSurPoint(orgx, orgy);
					if(p != null)
					{
						d.setOrg(p.getX(), p.getY());
						if(this.g.isclicSurPoint(x, y))
						{
							Point p2 = this.g.getclicSurPoint(x, y);
							d.setOrg2(p2.getX(), p2.getY());
							
						}
					}
					d.setColor(g.getCouleur());
					g.ajouter(d);
				}
				else
				{
					Droite d = new Droite();
					d.setOrg(orgx, orgy);
					d.setOrg2(x, y);
					d.setColor(g.getCouleur());
					g.ajouter(d);
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
		double xprecis =  e.getPoint().x / g.getZoomX();
		double yprecis = e.getPoint().y / g.getZoomY();
		if(this.g.enMouvement){
			if(g.mode == g.mode.MOUVEMENT)
			{
				g.mouvement(x, y);
				this.repaint();
			}
		}
		if(enCreation != null)
		{
			switch(g.mode)
			{

			case LIGNE:
				((Ligne)enCreation).setP2(x,y);
				this.repaint();
				break;
			case DROITE:
				((Droite) enCreation).setOrg2(xprecis, yprecis);
				this.repaint();
				break;
			}
			
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if(arg0.getWheelRotation() < 0)
		{
			g.addZoom(0.05, 0.05);
			this.repaint();
		}
		if(arg0.getWheelRotation() > 0)
		{
			if(g.getZoomX() > 0.19)
				g.addZoom(-0.05, -0.05);
		}
			
		
	}

}
