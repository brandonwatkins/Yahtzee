import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;


public class Constants {
	
	
	public static void center(Window window){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		int windowWidth		= window.getWidth();
		int windowHeight	= window.getHeight();
		
		int x = (screen.width - windowWidth) / 2;
		int y = (screen.height - windowHeight) / 2;
		
		window.setLocation(x, y);
	}

}