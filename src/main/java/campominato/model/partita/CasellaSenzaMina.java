package campominato.model.partita;

import java.util.Set;

public interface CasellaSenzaMina extends Casella
{
	public void aggiungiCasellaConMinaAdiacente(CasellaConMina c);
	
	public void aggiungiCasellaSenzaMinaAdiacente(CasellaSenzaMina c);
	
	public int getNumMineAdiacenti();
	
	public Set<CasellaSenzaMina> getCaselleSenzaMinaAdiacenti();
	
	public Set<CasellaConMina> getCaselleConMinaAdiacenti();
}
