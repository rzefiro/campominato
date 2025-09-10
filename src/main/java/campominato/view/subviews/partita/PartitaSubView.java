package campominato.view.subviews.partita;

import java.util.Map;
import java.util.Set;

import campominato.model.Posizione;
import campominato.view.subviews.SubView;

public interface PartitaSubView extends SubView<PartitaSubViewListener>
{
	public void setDimensioniCampo(int numRighe, int numColonne);
	
	public void mostraCaselleSenzaMinaScoperte(Map<Posizione, Integer> posizioni);
	
	public void mostraBandierina(Posizione p);
	
	public void mostraBandierine(Set<Posizione> posBandierineMineRimanenti);
	
	public void rimuoviBandierina(Posizione p);
	
	public void mostraMossaSconfitta(Posizione pos, Set<Posizione> posMineRimanenti);
	
	public void mostraMessaggioPartitaVinta();
}
