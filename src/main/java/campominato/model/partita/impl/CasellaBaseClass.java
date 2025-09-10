package campominato.model.partita.impl;

import campominato.model.Posizione;
import campominato.model.partita.Casella;

public abstract class CasellaBaseClass implements Casella
{
	private Posizione posizione;
	private boolean bandierina;
	private boolean scoperta;
	
	public CasellaBaseClass(Posizione p)
	{
		this.posizione = p;
		this.bandierina = false;
		this.scoperta = false;
	}
	
	@Override
	public Posizione getPosizione() 
	{
		return posizione;
	}

	@Override
	public void toggleBandierina()
	{
		bandierina = !bandierina;
	}

	@Override
	public boolean esisteBandierina() 
	{
		return bandierina;
	}

	@Override
	public void scopri() 
	{
		scoperta = true;
	}

	@Override
	public boolean scoperta() 
	{
		return scoperta;
	}
	
}
