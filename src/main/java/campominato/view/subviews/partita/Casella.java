package campominato.view.subviews.partita;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import campominato.model.Posizione;
import campominato.util.Resources;

public class Casella extends JButton 
{
	private Posizione posizione;
	
	public Casella(Posizione posizione, int size)
	{
		super();
		this.posizione = posizione;
		
		init(size);
	}
	
	private void init(int size)
	{
		setUI(new BasicButtonUI());
		setBackground(new Color(240, 240, 240));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setPreferredSize(new Dimension(size, size));
		
		URL resourceUrl = Resources.getResourceURL(Resources.FONT_DIGITAL7);
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, new File(resourceUrl.toURI()));
			setFont(f.deriveFont(Font.BOLD, 20f));
		} catch (FontFormatException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Posizione getPosizione()
	{
		return this.posizione;
	}
	
	public void mostraMina(boolean sconfitta)
	{
		scopri();
		if (sconfitta)
			setBackground(new Color(255, 50, 50));
		
		setImageIcon(Resources.ICON_BOMB);
	}
	
	public void mostraBandierina()
	{
		setImageIcon(Resources.ICON_FLAG);
	}
	
	public void rimuoviBandierina()
	{
		setIcon(null);
	}
	
	public void mostraNumMineAdiacenti(int num)
	{
		scopri();
		setForeground(new Color(255, 0, 0));
		setText(String.valueOf(num));
	}
	
	public void scopri(int numCaselleConMinaAdiacenti)
	{
		scopri();
		setForeground(new Color(255, 0, 0));
		setText(String.valueOf(numCaselleConMinaAdiacenti));
	}
	
	public void scopri()
	{
		setBackground(new Color(220, 220, 220));
		setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	private void setImageIcon(int icon)
	{
		URL resourceUrl = Resources.getResourceURL(icon);
		
		Image img = new ImageIcon(resourceUrl).getImage();
		int iconWidth = (int)((float)getWidth() / 100 * 80);
		int iconHeight = (int)((float)getHeight() / 100 * 80);
		Image scaledImg = img.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaledImg));
	}
}
