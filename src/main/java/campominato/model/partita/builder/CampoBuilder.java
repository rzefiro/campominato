package campominato.model.partita.builder;

import java.util.Set;

import campominato.model.Dimensioni;
import campominato.model.Posizione;
import campominato.model.partita.Campo;
import campominato.model.partita.impl.CampoImpl;

public class CampoBuilder 
{
	private boolean modalitaCreazioneBuilderNumMine;
	
	private Dimensioni dimensioni;
	private boolean scopriCaselleRicorsivamente;
	private int numMine;
	private Set<Posizione> posizioniCaselleConMina;
	private Set<Posizione> posizioniCaselleSenzaMinaScoperte;
	private Set<Posizione> posizioniBandierine;
	
	private static CampoBuilder creaCampoBuilder(Dimensioni dimensioni)
	{
		CampoBuilder builder = new CampoBuilder();
		builder.dimensioni = dimensioni;
		builder.scopriCaselleRicorsivamente = true;
		
		return builder;
	}
	
	public static CampoBuilder creaCampoBuilderConMineCasuali(Dimensioni dimensioni, int numMine)
	{
		CampoBuilder builder = creaCampoBuilder(dimensioni);
		
		builder.modalitaCreazioneBuilderNumMine = true;
		builder.numMine = numMine;
		
		return builder;
	}
	
	public static CampoBuilder creaCampoBuilder(Dimensioni dimensioni,
			Set<Posizione> posizioniCaselleConMina, Set<Posizione> posizioniCaselleSenzaMinaScoperte,
			Set<Posizione> posizioniBandierine)
	{
		CampoBuilder builder = creaCampoBuilder(dimensioni);
		
		builder.modalitaCreazioneBuilderNumMine = false;
		builder.posizioniCaselleConMina = posizioniCaselleConMina;
		builder.posizioniCaselleSenzaMinaScoperte = posizioniCaselleSenzaMinaScoperte;
		builder.posizioniBandierine = posizioniBandierine;
		
		return builder;
	}
	
	public CampoBuilder scopriCaselleRicorsivamente(boolean scopriCaselleRicorsivamente)
	{
		this.scopriCaselleRicorsivamente = scopriCaselleRicorsivamente;
		
		return this;
	}
	
	public Campo build()
	{
		Campo campo = new CampoImpl(this.dimensioni, this.scopriCaselleRicorsivamente);
		if (this.modalitaCreazioneBuilderNumMine)
		{
			campo.riempi(numMine);
		}
		else 
		{
			campo.riempi(posizioniCaselleConMina, posizioniCaselleSenzaMinaScoperte, posizioniBandierine);
		}
		
		return campo;
	}
}
