package Utils;

import java.awt.Rectangle;

public class Utils {
	
	public static boolean Intersect(Rectangle f, Rectangle s) {
		if ((f.x >= s.x && f.x <= s.x+s.width) || (f.x <= s.x && s.x <= f.x+f.width))
			if ((f.y >= s.y && f.y <= s.y+s.height) || (f.y <= s.y && s.y <= f.y+f.height))
				return true;
		return false;
	}
	
}
