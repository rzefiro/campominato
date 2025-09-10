package campominato.controller.subcontrollers;

import campominato.controller.ModularController;
import campominato.model.Model;
import campominato.view.subviews.SubView;

public interface SubController<T extends SubView>
{
	public ModularController getController();
	
	public Model getModel();
	
	public T getSubView();
	
	public void init();
}
