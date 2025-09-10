package campominato.view;

import campominato.view.subviews.SubView;

public interface ModularView extends View 
{
	public void registraSubView(int idSubView, SubView subView);
	
	public void mostraSubView(int idSubView);
}
