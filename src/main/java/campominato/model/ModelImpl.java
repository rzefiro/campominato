package campominato.model;

import java.util.ArrayList;

import campominato.model.listeners.CampoPartitaListener;
import campominato.model.listeners.TimerPartitaListener;
import campominato.model.partita.Partita;
import campominato.model.partita.builder.GiocatoreBuilder;
import campominato.model.partita.builder.PartitaBuilder;

public class ModelImpl implements Model
{
	private Giocatore giocatoreLoggato;
	private Partita partitaCorrente;
	
	private ArrayList<CampoPartitaListener> campoPartitaListeners;
	private ArrayList<TimerPartitaListener> timerPartitaListeners;
	
	public ModelImpl()
	{
		this.campoPartitaListeners = new ArrayList<CampoPartitaListener>();
		this.timerPartitaListeners = new ArrayList<TimerPartitaListener>();
		this.giocatoreLoggato = GiocatoreBuilder.creaGiocatoreBuilder("prova", "prova").build();
	}
	
	@Override
	public void creaNuovaPartita(Dimensioni dimensioni, int numMine)
	{
		this.partitaCorrente = 
				PartitaBuilder.creaPartitaBuilder(giocatoreLoggato, dimensioni, numMine).build();
		for (CampoPartitaListener listener : campoPartitaListeners)
		{
			partitaCorrente.aggiungiCampoPartitaListener(listener);
		}
		
		for (TimerPartitaListener listener : timerPartitaListeners)
		{
			partitaCorrente.aggiungiTimerPartitaListener(listener);
		}
	}
	
	@Override
	public void avviaPartita() 
	{
		this.partitaCorrente.avvia();
	}
	
	@Override
	public void interrompiPartita() 
	{
		this.partitaCorrente.interrompi();
	}
	
	@Override
	public void rimuoviPartita() 
	{
		this.partitaCorrente = null;
	}

	@Override
	public Dimensioni getDimensioniCampoPartita() 
	{
		return this.partitaCorrente.getDimensioniCampo();
	}

	@Override
	public void scopriCasella(Posizione p) 
	{
		this.partitaCorrente.scopriCasella(p);
	}

	@Override
	public void toggleBandierina(Posizione p) 
	{
		this.partitaCorrente.toggleBandierina(p);
	}

	@Override
	public void aggiungiTimerPartitaListener(TimerPartitaListener listener) 
	{
		this.timerPartitaListeners.add(listener);
	}

	@Override
	public void aggiungiCampoPartitaListener(CampoPartitaListener listener) 
	{
		this.campoPartitaListeners.add(listener);
	}
}
