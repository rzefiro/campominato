package campominato.model;

public class GiocatoreImpl implements Giocatore
{
	private String username;
	private String password;
	private String nome;
	private String cognome;
	
	public GiocatoreImpl(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}
	
	public GiocatoreImpl(String username, String password, String nome, String cognome) 
	{
		this(username, password);
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public String getUsername() 
	{
		return username;
	}

	@Override
	public String getPassword() 
	{
		return password;
	}

	@Override
	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}

	@Override
	public String getCognome() 
	{
		return cognome;
	}
	
	
}
