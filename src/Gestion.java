import java.awt.Color;
import java.util.ArrayList;


public class Gestion {
	
	private ArrayList<Forme> figure;
	
	private Color c;
	
	public enum Mode{
		LIGNE, CARRE, RECTANGLE
	}
	
	public Mode mode;
	public Gestion()
	{
		this.c = Color.BLACK;
		mode = Mode.LIGNE;
	}
	public void setColor(Color c)
	{
		this.c = c;
	}
}
