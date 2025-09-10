package campominato.model.partita.builder;

import campominato.model.Giocatore;
import campominato.model.GiocatoreImpl;

public class GiocatoreBuilder 
{
	private String username;
	private String password;
	private String nome;
	private String cognome;
	
	public static GiocatoreBuilder creaGiocatoreBuilder(String username, String password)
	{
		GiocatoreBuilder builder = new GiocatoreBuilder();
		
		builder.username = username;
		builder.password = password;
		
		return builder;
	}
	
	public GiocatoreBuilder nome(String nome)
	{
		this.nome = nome;
		
		return this;
	}
	
	public GiocatoreBuilder cognome(String cognome)
	{
		this.cognome = cognome;
		
		return this;
	}
	
	public Giocatore build()
	{
		Giocatore giocatore = new GiocatoreImpl(this.username, this.password);
		
		if (nome != null)
			giocatore.setNome(nome);
		
		if (cognome != null)
			giocatore.setCognome(cognome);
		
		return giocatore;
	}
}
