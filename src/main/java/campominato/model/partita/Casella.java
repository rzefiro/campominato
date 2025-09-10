package campominato.model.partita;

import campominato.model.Posizione;

public interface Casella 
{
	public Posizione getPosizione();
	
	public void toggleBandierina();
	
	public boolean esisteBandierina();
	
	public void scopri();
	
	public boolean scoperta();
}
