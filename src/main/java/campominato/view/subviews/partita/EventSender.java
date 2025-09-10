package campominato.view.subviews.partita;

import campominato.view.subviews.SubViewListener;

public interface EventSender<T extends SubViewListener> 
{
	public void invia(T listener);
}
