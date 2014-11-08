import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class ZoneDessin extends JPanel implements MouseListener{

	private int cursor_x;
	private int cursor_y;
	
	private boolean enCours;
	private Forme enCreation = null;
	private Gestion g;
	private Fenetre f;
	
	public ZoneDessin(Fenetre fen)
	{
		super();
		this.setBackground(Color.WHITE);
		this.f = fen;
		this.g = f.getGestion();
		this.addMouseListener(this);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawLine(50, 50, 400, 400);
		this.g.afficher(g);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Truc");
		if(!enCours)
		{
			switch(g.mode)
			{
				case LIGNE:
					System.out.println(e.getX() +" "+ e.getY());
					enCreation = new Ligne(e.getX(), e.getY(),0, 0);
					break;
			}
			enCours = true;
		}
		else
		{
			switch(g.mode)
			{
			case LIGNE:
				System.out.println(g.mode);
				((Ligne)enCreation).setP2(e.getX(), e.getY());
				g.ajouter(enCreation);
				break;
			}
			this.repaint();
			enCours = false;
			enCreation = null;
		}
			
	}
	@Override
	public void mouseEntered(MouseEvent e) {

//		if(enCours)
//		{
//			if(enCreation instanceof Ligne)
//			{
//				((Ligne) enCreation).setP2(this.cursor_x, this.cursor_y);
//			}
//		}
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
}
