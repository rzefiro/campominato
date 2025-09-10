package campominato.model;

public class DimensioniImpl implements Dimensioni
{
	private int numRighe;
	private int numColonne;
	
	public DimensioniImpl(int numRighe, int numColonne)
	{
		this.numRighe = numRighe;
		this.numColonne = numColonne;
	}
	
	@Override
	public int getNumRighe() 
	{
		return numRighe;
	}

	@Override
	public int getNumColonne() 
	{
		return numColonne;
	}

}
