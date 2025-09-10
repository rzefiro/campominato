package campominato.util;

import java.io.File;
import java.net.URL;

public class Resources 
{
	public static final int ICON_BOMB = 0;
	public static final int ICON_FLAG = 1;
	public static final int FONT_DIGITAL7 = 2;
	
	private static final String PATH_ICONS = "icons";
	private static final String PATH_FONTS = "fonts";
	
	private static final String ICON_FILENAME_BOMB = "bomb.png";
	private static final String ICON_FILENAME_FLAG = "flag.png";
	private static final String FONT_FILENAME_DIGITAL7 = "digital-7.ttf";
	
	public static URL getResourceURL(int resource)
	{
		String path = getResourcePath(resource);
		
		return Resources.class.getClassLoader().getResource(path);
	}
	
	private static String getResourcePath(int resource)
	{
		switch (resource)
		{
			case ICON_BOMB:
				return PATH_ICONS + File.separator + ICON_FILENAME_BOMB;
			case ICON_FLAG:
				return PATH_ICONS + File.separator + ICON_FILENAME_FLAG;
			case FONT_DIGITAL7:
				return PATH_FONTS + File.separator + FONT_FILENAME_DIGITAL7;
			default:
				return "";
		}
	}
}
