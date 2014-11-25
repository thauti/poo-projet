import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class ColorPicker extends JDialog{
	JPanel pan;
	GridLayout gl;
	GridLayout gc;
	JSlider r;
	JSlider g;
	JSlider b;
	JPanel p_color;
	JPanel p_slider;
	JButton b_ok;
	JButton b_cancel;
	
	public ColorPicker(Fenetre fen)
	{
		super();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(400,200));
		b_ok = new JButton("OK");
		b_cancel = new JButton("Annuler");
		
		p_color = new JPanel();
		p_slider = new JPanel();
		
		p_color.setBackground(Color.red);
		gc = new GridLayout(3,1);
		p_color.setLayout(gc);
		r = new JSlider();
		r.setMaximum(255);
		r.setMajorTickSpacing(100);
		r.setMinorTickSpacing(10);
		r.setPaintTicks(true);
		r.setPaintLabels(true);
		g = new JSlider();
		g.setMaximum(255);
		b = new JSlider();
		b.setMaximum(255);
		
		p_slider.add(r);
		p_slider.add(g);
		p_slider.add(b);
		
		gl = new GridLayout(3,2);

		this.setLayout(gl);
		
		this.add(p_slider);
		this.add(p_color);
		this.add(b_ok);
		this.add(b_cancel);
		this.setSize(new Dimension(400,200));
		System.out.println("Hello");
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

}
