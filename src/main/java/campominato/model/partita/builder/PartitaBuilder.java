package campominato.model.partita.builder;

import campominato.model.Dimensioni;
import campominato.model.Giocatore;
import campominato.model.partita.Partita;
import campominato.model.partita.impl.PartitaImpl;

public class PartitaBuilder 
{
	private Giocatore giocatore;
	
	private GiocatoreBuilder giocatoreBuilder;
	private CampoBuilder campoBuilder;
	private ReportBuilder reportBuilder;
	
	/**
	 * Costruisce una partita da zero
	 * @param giocatore
	 * @param dimensioni
	 * @param numMine
	 * @return
	 */
	public static PartitaBuilder creaPartitaBuilder(Giocatore giocatore, Dimensioni dimensioni, 
			int numMine)
	{
		return creaPartitaBuilder(giocatore, 
				CampoBuilder.creaCampoBuilderConMineCasuali(dimensioni, numMine),
				ReportBuilder.creaReportBuilder());
	}
	
	/**
	 * Costruisce una partita con dati di una partita esistente
	 * @param giocatore
	 * @param builder
	 * @param reportBuilder
	 * @return
	 */
	public static PartitaBuilder creaPartitaBuilder(Giocatore giocatore, CampoBuilder campoBuilder, 
			ReportBuilder reportBuilder)
	{
		PartitaBuilder builder = new PartitaBuilder();
		builder.giocatore = giocatore;
		builder.campoBuilder = campoBuilder;
		builder.reportBuilder = reportBuilder;
		
		return builder;
	}
	
	public Partita build()
	{
		return new PartitaImpl(giocatore, this.campoBuilder.build(), this.reportBuilder.build());
	}
}
