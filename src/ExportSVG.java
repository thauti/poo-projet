import java.util.ArrayList;


public class ExportSVG {

		public static String toSVG(ArrayList<Forme> f, ArrayList<Barycentre> b, ArrayList<Intersection> intersection)
		{
			int maxx = 250;
			int maxy = 250;
			
			for(Forme a : f)
			{
				if(a instanceof Point)
				{
					if(((Point) a).getX() > maxx)
						maxx = ((Point) a).getX()+20;
					if(((Point) a).getY() > maxy)
						maxy = ((Point) a).getY()+20;
				}
			}
			
			String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\""+maxx+"\" height=\""+maxy+"\">"
					+ "<title>Dessin Vectoriel</title>";
			for(Forme a : f)
			{
				s = s+a.toSVG()+"\n";
			}
			if(b != null)
			{
				for(Barycentre ba: b)
				{
					s = s+ ba.toSVG();
				}
			}
			if(intersection != null)
			{
				for(Intersection a: intersection)
				{
					s = s+ a.toSVG();
				}
			}
			s= s+"</svg>";
			return s;
		}
}
