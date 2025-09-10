package campominato.model.partita;

import java.util.Date;

import campominato.model.Dimensioni;
import campominato.model.listeners.TimerPartitaListener;

public interface Report 
{
	public void setDimensioniCampo(Dimensioni dimensioniCampo);
	
	public Dimensioni getDimensioniCampo();
	
	public void setNumeroMine(int numeroMine);
	
	public int getNumeroMine();
	
	public boolean vittoria();
	
	public void setVittoria(boolean vittoria);
	
	public void setPercentualeMineTrovate(int percentualeMineTrovate);
	
	public int getPercentualeMineTrovate();
	
	public void setDataOraInizio(Date dataOraInizio);
	
	public Date getDataOraInizio();
	
	public void setDurataSecondi(long durataSecondi);
	
	public long getDurataSecondi();
	
	public void incrementaDurataSecondi();
	
	public void aggiungiTimerPartitaListener(TimerPartitaListener listener);
}
