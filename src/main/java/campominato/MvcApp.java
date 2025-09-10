package campominato;

import campominato.controller.ModularControllerImpl;
import campominato.controller.ModularController;
import campominato.model.Model;
import campominato.model.ModelImpl;
import campominato.view.ModularViewImpl;

public class MvcApp 
{
	private ModularViewImpl view;
	private ModularControllerImpl controller;
	private Model model;
	
	public MvcApp()
	{
		this.view = new ModularViewImpl();
		this.model = new ModelImpl();
		this.controller = new ModularControllerImpl(view, model);
		this.view.setController(controller);
	}
	
	public void run()
	{
		controller.mostraSubView(ModularController.SUBVIEW_SETUP_PARTITA);
	}
}
