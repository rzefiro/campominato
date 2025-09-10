package campominato.model.partita.impl;

import campominato.model.Dimensioni;
import campominato.model.Posizione;
import campominato.model.listeners.CampoPartitaListener;
import campominato.model.listeners.TimerPartitaListener;
import campominato.model.partita.Campo;
import campominato.model.Giocatore;
import campominato.model.partita.Partita;
import campominato.model.partita.Report;

public class PartitaImpl implements Partita
{
	private Giocatore giocatore;
	private Campo campo;
	private Report report;
	
	private boolean terminata;
	private boolean vittoria;
	
	public PartitaImpl(Giocatore giocatore, Campo campo, Report report)
	{
		this.giocatore = giocatore;
		this.campo = campo;
		this.report = report;
		
		this.terminata = false;
		this.vittoria = false;
	}
	
	@Override
	public void avvia() 
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void interrompi() 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean terminata() 
	{
		return terminata;
	}

	@Override
	public boolean vittoria() 
	{
		return vittoria;
	}

	@Override
	public Dimensioni getDimensioniCampo() 
	{
		return campo.getDimensioni();
	}

	@Override
	public Report getReport() 
	{
		return report;
	}

	@Override
	public Giocatore getGiocatore() 
	{
		return giocatore;
	}

	@Override
	public void toggleBandierina(Posizione p) 
	{
		if (!terminata)
			campo.toggleBandierina(p);
	}

	@Override
	public void scopriCasella(Posizione p) 
	{
		if (!terminata && campo.scopriCasella(p))
		{
			terminata = true;
			if (campo.completato())
				vittoria = true;
			else
				vittoria = false;
		}
	}

	@Override
	public void aggiungiTimerPartitaListener(TimerPartitaListener listener) 
	{
		this.report.aggiungiTimerPartitaListener(listener);
	}

	@Override
	public void aggiungiCampoPartitaListener(CampoPartitaListener listener) 
	{
		campo.aggiungiListener(listener);
	}
}
