package campominato.view.subviews;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import campominato.view.subviews.partita.EventSender;

public abstract class SubViewBaseClass<T extends SubViewListener> implements SubView<T>
{
	private ArrayList<T> listeners;
	
	public SubViewBaseClass()
	{
		this.listeners = new ArrayList<T>();
	}
	
	@Override
	public void aggiungiSubViewListener(T listener) 
	{
		listeners.add(listener);
	}
	
	@Override
	public void inviaEventoOnPanelCreation() 
	{
		for (T listener : this.listeners)
		{
			listener.onPanelCreation();
		}
	}
	
	@Override
	public void inviaEventoOnPanelCreated() 
	{
		for (T listener : this.listeners)
		{
			listener.onPanelCreated();
		}
	}
	
	@Override
	public void mostraMessaggio(String messaggio)
	{
		JOptionPane.showMessageDialog(null, messaggio);
	}
	
	protected void inviaEvento(EventSender<T> sender)
	{
		for (T listener : this.listeners)
		{
			sender.invia(listener);
		}
	}
}
