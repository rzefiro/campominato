package campominato.controller.subcontrollers;

import campominato.controller.ModularController;
import campominato.model.Model;
import campominato.view.subviews.SubView;

public abstract class SubControllerBaseClass<T extends SubView> implements SubController<T> 
{
	private ModularController controller;
	private Model model;
	private T subView;
	
	public SubControllerBaseClass(ModularController controller, Model model, T subView)
	{
		this.controller = controller;
		this.model = model;
		this.subView = subView;
	}
	
	@Override
	public T getSubView() 
	{
		return subView;
	}
	
	@Override
	public ModularController getController() 
	{
		return controller;
	}
	
	@Override
	public Model getModel() 
	{
		return model;
	}
	
	public abstract void init();
}
