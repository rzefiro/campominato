package campominato.model.partita.impl;

import java.util.HashSet;
import java.util.Set;

import campominato.model.Posizione;
import campominato.model.partita.CasellaConMina;
import campominato.model.partita.CasellaSenzaMina;

public class CasellaSenzaMinaImpl extends CasellaBaseClass implements CasellaSenzaMina 
{
	private Set<CasellaConMina> caselleConMinaAdiacenti;
	private Set<CasellaSenzaMina> caselleSenzaMinaAdiacenti;
	
	public CasellaSenzaMinaImpl(Posizione p)
	{
		super(p);
		this.caselleConMinaAdiacenti = new HashSet<CasellaConMina>();
		this.caselleSenzaMinaAdiacenti = new HashSet<CasellaSenzaMina>();
	}
	
	@Override
	public void aggiungiCasellaConMinaAdiacente(CasellaConMina c) 
	{
		caselleConMinaAdiacenti.add(c);
	}

	@Override
	public void aggiungiCasellaSenzaMinaAdiacente(CasellaSenzaMina c) 
	{
		caselleSenzaMinaAdiacenti.add(c);
	}

	@Override
	public int getNumMineAdiacenti() 
	{
		return caselleConMinaAdiacenti.size();
	}

	@Override
	public Set<CasellaSenzaMina> getCaselleSenzaMinaAdiacenti() 
	{
		return caselleSenzaMinaAdiacenti;
	}

	@Override
	public Set<CasellaConMina> getCaselleConMinaAdiacenti() 
	{
		return caselleConMinaAdiacenti;
	}

}
