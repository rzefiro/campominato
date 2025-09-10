package campominato.view.subviews.partita;

import campominato.model.Posizione;
import campominato.view.subviews.SubViewListener;

public interface PartitaSubViewListener extends SubViewListener 
{
	public static final int TASTO_SINISTRO = 0;
	public static final int TASTO_DESTRO = 1;
	
	public void clickCasella(int tasto, Posizione p);
}
