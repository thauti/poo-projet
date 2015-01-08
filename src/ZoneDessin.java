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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.ViewportLayout;

/**
 * Represente la partie Graphique du programme
 */
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
	
	/**
	 * Constructeur
	 * @param fen La fenetre en cours
	 */
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
		this.g.genererintersection();
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
			// Si on etait pas en cours de creation d'une figure
			enCours = true;
			switch(g.mode)
			{
			case POINT:
				enCreation = new Point(x, y);
				enCreation.setColor(this.g.getCouleur());
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
				else if(g.isclicsurDroite(x, y))
				{
					if(!this.g.getSelection().contains(g.getclicsurDroite(x, y)))
					{
						
						g.getclicsurDroite(x, y).setColor(Color.RED);
						this.g.addSelection(g.getclicsurDroite(x, y));
						this.repaint();
					}
					else
					{
						g.getclicsurDroite(x, y).setColor(this.g.getCouleur());
						this.g.getSelection().remove(g.getclicsurDroite(x, y));
						this.repaint();
						
					}
				}
				else
				{
					for(Forme a: this.g.getSelection())
					{
						if(a instanceof Point)
							a.setColor(Color.BLACK);
						if(a instanceof Droite)
							a.setColor(a.couleurref);
					}
					this.g.getSelection().clear();
					this.repaint();
				}
				break;
			case MOUVEMENT:
				enCours = false;
				if(this.g.Mouvementactiver)
				{
				if(this.g.enMouvement)
					this.g.enMouvement = false;
					this.f.getBarre().desactiverMouvement();
					this.repaint();
					this.g.mode = g.mode.SELECTION;
				}
				else
				{
					this.g.Mouvementactiver =true;
					this.g.mouvementorgx = x;
					this.g.mouvementorgy = y;
				}
				break;
			case SUPPRIMER:
				enCours = false;
				if(this.g.isclicSurPoint(x, y))
				{
					System.out.println("True");
					ArrayList<Barycentre> asupprimer = new ArrayList<Barycentre>();
					for(Barycentre b: this.g.getBarycentre())
					{
						if(b.listepoint.contains(this.g.getclicSurPoint(x, y)))
						{
							asupprimer.add(b);
						}
					}
					for(Barycentre b: asupprimer)
					{
						this.g.getBarycentre().remove(b);
					}
					if(this.g.dansSegment(this.g.getclicSurPoint(x, y)))
					{
						this.g.getFigure().remove(this.g.getPointSegment(this.g.getclicSurPoint(x, y)));
					}
					this.g.getFigure().remove(this.g.getclicSurPoint(x, y));
				}
				if(this.g.isclicsurDroite(x, y))
				{
					ArrayList<Intersection> asupprimerinter = new ArrayList<Intersection>();
					for(Intersection b: this.g.getIntersection())
					{
						if(b.f1 == this.g.getclicsurDroite(x, y) || b.f2 == this.g.getclicsurDroite(x, y))
						{
							asupprimerinter.add(b);
						}
					}
					for(Intersection b: asupprimerinter)
					{
						this.g.getIntersection().remove(b);
					}
					this.g.getFigure().remove(this.g.getclicsurDroite(x, y));
					this.g.getSelection().remove(this.g.getclicsurDroite(x, y));
				}
				this.repaint();
				break;
			}
			
		}
		else
		{
			//Sinon on l'a cree
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
					l.getP1().setColor(g.getCouleur());
					l.getP2().setColor(g.getCouleur());
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
					d.setColor2(g.getCouleur());
					g.ajouter(d);
					
				}
				else if(this.g.isclicsurDroite(orgx, orgy))
				{
					
				}
				else
				{
					Droite d = new Droite();
					d.setOrg(orgx, orgy);
					d.setOrg2(x, y);
					d.setColor(g.getCouleur());
					d.setColor2(g.getCouleur());
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
				if(g.Mouvementactiver)
				{
					g.mouvement(x, y);
					g.recalculerBarycentre();
					g.recalculerIntersection();
					this.repaint();
				}
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
