import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JPanel;


public class ColorPicker extends JColorChooser{
	JPanel pan;
	public ColorPicker(Fenetre fen)
	{
		super();
		pan = new JPanel();
		
		this.setVisible(true);
		
		System.out.println("Hello");
	}

}
