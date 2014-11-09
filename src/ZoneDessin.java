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
				g.ajouter(new Ligne(orgx, orgy, e.getX(), e.getY()));
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
