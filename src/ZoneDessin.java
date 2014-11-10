import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;


public class ZoneDessin extends JPanel implements MouseListener, MouseMotionListener{

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
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.g.afficher(g);
		if(enCreation != null)
		{
			enCreation.afficher(g);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!enCours)
		{
			enCours = true;
			switch(g.mode)
			{
			case POINT:
				enCreation = new Point(e.getX(), e.getY());
				g.ajouter(enCreation);
				enCours = false;
				enCreation = null;
				break;
			case LIGNE:
				enCreation = new Ligne(e.getX(), e.getY(),e.getX(),e.getY());
				orgx = e.getX();
				orgy = e.getY();
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
						if(this.g.isclicSurPoint(e.getX(), e.getY()))
						{
							Point p2 = this.g.getclicSurPoint(e.getX(), e.getY());
							Ligne l = new Ligne(p, p2);
							g.ajouter(l);
						}
						else
						{
							Ligne l = new Ligne(p, e.getX(), e.getY());
							g.ajouter(l);
							g.ajouter(l.getP2());
						}

						
					}
				}
				else
				{
					Ligne l = new Ligne(orgx, orgy, e.getX(), e.getY());
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
		if(enCreation != null)
		{
			switch(g.mode)
			{

			case LIGNE:
				((Ligne)enCreation).setP2(e.getX(),e.getY());
				break;
			}
			this.repaint();
			
		}
	}
}
