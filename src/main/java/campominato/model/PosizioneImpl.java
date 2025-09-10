package campominato.model;

public class PosizioneImpl implements Posizione, Comparable<Posizione>
{
	private int riga;
	private int colonna;
	
	public PosizioneImpl(int riga, int colonna)
	{
		this.riga = riga;
		this.colonna = colonna;
	}
	
	@Override
	public int getRiga() 
	{
		return riga;
	}

	@Override
	public int getColonna() 
	{
		return colonna;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Posizione))
			return false;
		
		Posizione p = (Posizione)o;
		
		return riga == p.getRiga() && colonna == p.getColonna();
	}

	@Override
	public int compareTo(Posizione o) 
	{
		if (riga < o.getRiga())
			return -1;
		else if (riga > o.getRiga())
			return 1;
		else
		{
			if (colonna < o.getColonna())
				return -1;
			else if (colonna > o.getColonna())
				return 1;
			else
				return 0;
		}
	}
	
	
}
