package campominato.model.partita;

import java.util.Set;

import campominato.model.Dimensioni;
import campominato.model.Posizione;
import campominato.model.listeners.CampoPartitaListener;

public interface Campo 
{
	public Dimensioni getDimensioni();
	
	public void riempi(Set<Posizione> posizioniCaselleConMina, Set<Posizione> posizioniCaselleScoperte,
			Set<Posizione> posizioniBandierine);
	
	public void riempi(int numMine);
	
	public void toggleBandierina(Posizione p);
	
	/**
	 * Scopre una casella
	 * @param p		posizione della casella da scoprire
	 * @return		true se a seguito della scopertura la partita termina, sia
	 * 				in caso di vittoria che di sconfitta, false altrimenti
	 */
	public boolean scopriCasella(Posizione p);
	
	public boolean completato();
	
	public void aggiungiListener(CampoPartitaListener listener);
}
