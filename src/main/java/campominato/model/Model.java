package campominato.model;

import campominato.model.listeners.CampoPartitaListener;
import campominato.model.listeners.TimerPartitaListener;

public interface Model 
{
	public void creaNuovaPartita(Dimensioni dimensioni, int numMine);
	
	public Dimensioni getDimensioniCampoPartita();
	
	public void avviaPartita();
	
	public void interrompiPartita();
	
	public void rimuoviPartita();
	
	public void scopriCasella(Posizione p);
	
	public void toggleBandierina(Posizione p);
	
	public void aggiungiTimerPartitaListener(TimerPartitaListener listener);
	
	public void aggiungiCampoPartitaListener(CampoPartitaListener listener);
}
