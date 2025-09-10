package campominato.model.listeners;

import java.util.Map;
import java.util.Set;

import campominato.model.Posizione;

public interface CampoPartitaListener 
{
	public void caselleScoperte(Map<Posizione, Integer> caselleSenzaMinaScoperte);
	
	public void bandierinaAggiunta(Posizione pos);
	
	public void bandierinaRimossa(Posizione pos);
	
	public void vittoria(Set<Posizione> posBandierineMineRimanenti);
	
	public void sconfitta(Posizione posMinaTrovata, Set<Posizione> posMineRimanenti);
}
