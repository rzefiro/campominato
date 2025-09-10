package campominato.model.partita.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import campominato.model.Dimensioni;
import campominato.model.Posizione;
import campominato.model.PosizioneImpl;
import campominato.model.listeners.CampoPartitaListener;
import campominato.model.partita.Campo;
import campominato.model.partita.Casella;
import campominato.model.partita.CasellaConMina;
import campominato.model.partita.CasellaSenzaMina;

public class CampoImpl implements Campo 
{
	private Dimensioni dimensioni;
	private Casella[][] caselle;
	private Set<Posizione> posizioniCaselleConMina;
	
	// dovrà raggiungere il numero totale di caselle senza mina per completarsi
	private Set<Posizione> posizioniCaselleSenzaMinaScoperte;
	
	private boolean scopriCaselleRicorsivamente;
	
	private ArrayList<CampoPartitaListener> listeners;
	
	public CampoImpl(Dimensioni dimensioni)
	{
		this(dimensioni, true);
	}
	
	public CampoImpl(Dimensioni dimensioni, boolean scopriCaselleRicorsivamente)
	{
		this.listeners = new ArrayList<CampoPartitaListener>();
		this.dimensioni = dimensioni;
		this.scopriCaselleRicorsivamente = scopriCaselleRicorsivamente;
		this.caselle = new Casella[dimensioni.getNumRighe()][dimensioni.getNumColonne()];
		this.posizioniCaselleConMina = new TreeSet<Posizione>();
		this.posizioniCaselleSenzaMinaScoperte = new TreeSet<Posizione>();
		
		for (int i = 0; i < dimensioni.getNumRighe(); i++)
		{
			for (int j = 0; j < dimensioni.getNumColonne(); j++)
			{
				caselle[i][j] = new CasellaSenzaMinaImpl(new PosizioneImpl(i, j));
			}
		}
	}
	
	@Override
	public Dimensioni getDimensioni() 
	{
		return dimensioni;
	}

	@Override
	public void riempi(Set<Posizione> posizioniCaselleConMina, Set<Posizione> posizioniCaselleScoperte,
			Set<Posizione> posizioniBandierine) 
	{
		riempi(posizioniCaselleConMina);
		
		for (Posizione p : posizioniCaselleScoperte)
		{
			Casella c = getCasella(p);
			c.scopri();
		}
		
		for (Posizione p : posizioniBandierine)
		{
			Casella c = getCasella(p);
			c.toggleBandierina();
		}
	}

	@Override
	public void riempi(int numMine) 
	{
		ArrayList<Posizione> list = new ArrayList<Posizione>();
		
		for (int i = 0; i < dimensioni.getNumRighe(); i++)
		{
			for (int j = 0; j < dimensioni.getNumColonne(); j++)
			{
				list.add(new PosizioneImpl(i, j));
			}
		}
		
		Set<Posizione> posizioniCaselleConMina = new TreeSet<Posizione>();
		
		Random rand = new Random();
		for (int i = 0; i < numMine; i++)
		{
			int indice = rand.nextInt(list.size());
			posizioniCaselleConMina.add(list.remove(indice));
		}
		
		riempi(posizioniCaselleConMina);
	}
	
	private void riempi(Set<Posizione> posizioniCaselleConMina)
	{
		for (Posizione p : posizioniCaselleConMina)
		{
			Posizione pos = new PosizioneImpl(p.getRiga(), p.getColonna());
			caselle[p.getRiga()][p.getColonna()] = new CasellaConMinaImpl(pos);
			this.posizioniCaselleConMina.add(pos);
		}
		
		creaCollegamentiCaselleAdiacenti();
	}
	
	private void creaCollegamentiCaselleAdiacenti()
	{
		for (int i = 0; i < dimensioni.getNumRighe(); i++)
		{
			for (int j = 0; j < dimensioni.getNumColonne(); j++)
			{
				if (caselle[i][j] instanceof CasellaSenzaMina)
				{
					CasellaSenzaMina casella = (CasellaSenzaMina)caselle[i][j];
					Set<Posizione> posizioniAdiacenti = getPosizioniAdiacenti(casella.getPosizione());
					for (Posizione p : posizioniAdiacenti)
					{
						Casella adiacente = getCasella(p);
						if (adiacente instanceof CasellaConMina)
							casella.aggiungiCasellaConMinaAdiacente((CasellaConMina)adiacente);
						else
							casella.aggiungiCasellaSenzaMinaAdiacente((CasellaSenzaMina)adiacente);
					}
				}
			}
		}
	}
	
	@Override
	public void toggleBandierina(Posizione p) 
	{
		Casella c = getCasella(p);
		
		if (!c.scoperta())
		{
			c.toggleBandierina();
			
			// genero evento bandierina aggiunta
			if (c.esisteBandierina())
				generaEventoBandierinaAggiunta(p);
			// genero evento bandierina rimossa
			else
				generaEventoBandierinaRimossa(p);
		}
	}

	@Override
	public boolean scopriCasella(Posizione p) 
	{
		Casella casella = getCasella(p);
		
		if (!casella.scoperta() && !casella.esisteBandierina())
		{
			if (casella instanceof CasellaConMina)
			{
				casella.scopri();
				
				Set<Posizione> posizioniCaselleConMinaRimanenti = new TreeSet<Posizione>();
				for (Posizione pos : this.posizioniCaselleConMina)
				{
					Casella c = getCasella(pos);
					if (!c.scoperta() && !c.esisteBandierina())
					{
						c.scopri();
						posizioniCaselleConMinaRimanenti.add(pos);
					}
				}
				
				// genero evento di sconfitta
				generaEventoSconfitta(p, posizioniCaselleConMinaRimanenti);
				
				return true;
			}
			else 
			{
				Map<Posizione, Integer> caselleScoperte = new HashMap<Posizione, Integer>();
				if (this.scopriCaselleRicorsivamente)
				{
					scopriCaselleRicorsivamente((CasellaSenzaMina)casella, caselleScoperte);
				}
				else 
				{
					casella.scopri();
					caselleScoperte.put(p, ((CasellaSenzaMina)casella).getNumMineAdiacenti());
				}
				
				this.posizioniCaselleSenzaMinaScoperte.addAll(caselleScoperte.keySet());
				
				// genero evento di almeno una casella scoperta
				generaEventoCaselleSenzaMinaScoperte(caselleScoperte);
				
				if (completato())
				{	
					// metto bandierina in tutte le eventuali csaelle con mina non ancora scoperte
					Set<Posizione> posizioniBandierineMineRimanenti = new TreeSet<Posizione>();
					for (Posizione pos : this.posizioniCaselleConMina)
					{
						Casella casellaConMina = getCasella(pos);
						if (!casellaConMina.esisteBandierina())
						{
							casellaConMina.toggleBandierina();
							posizioniBandierineMineRimanenti.add(pos);
						}
					}
					
					// genero evento vittoria
					generaEventoVittoria(posizioniBandierineMineRimanenti);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void scopriCaselleRicorsivamente(CasellaSenzaMina casella, Map<Posizione, Integer> caselleScoperte)
	{
		if (!casella.scoperta() && !casella.esisteBandierina())
		{
			casella.scopri();
			caselleScoperte.put(casella.getPosizione(), casella.getNumMineAdiacenti());
			if (casella.getNumMineAdiacenti() == 0)
			{
				Set<CasellaSenzaMina> caselleSenzaMinaAdiacenti = casella.getCaselleSenzaMinaAdiacenti();
				for (CasellaSenzaMina adiacente : caselleSenzaMinaAdiacenti)
				{
					scopriCaselleRicorsivamente(adiacente, caselleScoperte);
				}
			}
		}
	}
	
	private Set<Posizione> getPosizioniAdiacenti(Posizione p)
	{
		Set<Posizione> posizioni = new TreeSet<Posizione>();
		
		int riga = p.getRiga();
		int colonna = p.getColonna();
		
		for (int i = riga - 1; i <= riga + 1; i++)
		{
			for (int j = colonna - 1; j <= colonna + 1; j++)
			{
				if ((i != riga || j != colonna)
					&& i >= 0 && i < dimensioni.getNumRighe()
					&& j >= 0 && j < dimensioni.getNumColonne())
				{
					posizioni.add(new PosizioneImpl(i, j));
				}
			}
		}
		
		return posizioni;
	}
	
	private Casella getCasella(Posizione p)
	{
		return caselle[p.getRiga()][p.getColonna()];
	}
	
	@Override
	public boolean completato() 
	{
		return posizioniCaselleSenzaMinaScoperte.size() ==
				dimensioni.getNumRighe() * dimensioni.getNumColonne() - posizioniCaselleConMina.size();
	}
	
	@Override
	public void aggiungiListener(CampoPartitaListener listener) 
	{
		listeners.add(listener);
	}
	
	private void generaEventoCaselleSenzaMinaScoperte(Map<Posizione, Integer> caselle)
	{
		for (CampoPartitaListener listener : this.listeners)
		{
			listener.caselleScoperte(caselle);
		}
	}
	
	private void generaEventoBandierinaAggiunta(Posizione pos)
	{
		for (CampoPartitaListener listener : this.listeners)
		{
			listener.bandierinaAggiunta(pos);
		}
	}
	
	private void generaEventoBandierinaRimossa(Posizione pos)
	{
		for (CampoPartitaListener listener : this.listeners)
		{
			listener.bandierinaRimossa(pos);
		}
	}
	
	private void generaEventoVittoria(Set<Posizione> posBandierineMineRimanenti)
	{
		for (CampoPartitaListener listener : this.listeners)
		{
			listener.vittoria(posBandierineMineRimanenti);
		}
	}
	
	private void generaEventoSconfitta(Posizione posMinaTrovata, Set<Posizione> posMineRimanenti)
	{
		for (CampoPartitaListener listener : this.listeners)
		{
			listener.sconfitta(posMinaTrovata, posMineRimanenti);
		}
	}

	public void stampa()
	{
		for (int i = 0; i < dimensioni.getNumRighe(); i++)
		{
			System.out.print("|");
			for (int j = 0; j < dimensioni.getNumColonne(); j++)
			{
				Casella casella = caselle[i][j];
				String valore;
				
				if (casella.scoperta())
				{
					CasellaSenzaMina csm = (CasellaSenzaMina)casella;
					if (csm.getNumMineAdiacenti() > 0)
						valore = String.valueOf(csm.getNumMineAdiacenti());
					else
						valore = " ";
				}
				else if (casella.esisteBandierina())
					valore = "B";
				else if (casella instanceof CasellaConMina)
					valore = "M";
				else
				{
					valore = "C";
				}
				
				System.out.printf(" %s |", valore);
			}
			System.out.println();
		}
	}

}
