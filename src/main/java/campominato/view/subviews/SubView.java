package campominato.view.subviews;

import javax.swing.JPanel;

public interface SubView<T extends SubViewListener>
{
	public JPanel getPanel();
	
	public void aggiungiSubViewListener(T listener);
	
	public void inviaEventoOnPanelCreation();
	
	public void inviaEventoOnPanelCreated();
	
	public void mostraMessaggio(String messaggio);
}
