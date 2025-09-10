package campominato.model.partita;

import campominato.model.Dimensioni;
import campominato.model.Giocatore;
import campominato.model.Posizione;
import campominato.model.listeners.CampoPartitaListener;
import campominato.model.listeners.TimerPartitaListener;

public interface Partita 
{
	public void avvia();
	
	public void interrompi();
	
	public boolean terminata();
	
	public boolean vittoria();
	
	public Dimensioni getDimensioniCampo();
	
	public Report getReport();
	
	public Giocatore getGiocatore();
	
	public void toggleBandierina(Posizione p);
	
	public void scopriCasella(Posizione p);
	
	public void aggiungiTimerPartitaListener(TimerPartitaListener listener);
	
	public void aggiungiCampoPartitaListener(CampoPartitaListener listener);
}
