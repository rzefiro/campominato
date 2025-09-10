package campominato.view.subviews.setuppartita;

import campominato.view.subviews.SubView;

public interface SetupPartitaSubView extends SubView<SetupPartitaSubViewListener>
{
	public String getNumRigheValue();
	
	public String getNumColonneValue();
	
	public String getNumMineValue();
}
