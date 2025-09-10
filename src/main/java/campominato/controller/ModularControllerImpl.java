package campominato.controller;

import campominato.controller.subcontrollers.PartitaSubController;
import campominato.controller.subcontrollers.PartitaSubControllerImpl;
import campominato.controller.subcontrollers.SetupPartitaSubController;
import campominato.controller.subcontrollers.SetupPartitaSubControllerImpl;
import campominato.model.Model;
import campominato.view.View;
import campominato.view.ModularViewImpl;
import campominato.view.subviews.partita.PartitaSubView;
import campominato.view.subviews.partita.PartitaSubViewImpl;
import campominato.view.subviews.setuppartita.SetupPartitaSubView;
import campominato.view.subviews.setuppartita.SetupPartitaSubViewImpl;

public class ModularControllerImpl implements ModularController
{
	private ModularViewImpl view;
	private Model model;
	
	public ModularControllerImpl(ModularViewImpl view, Model model)
	{
		this.view = view;
		this.model = model;
		
		PartitaSubView partitaSubView = new PartitaSubViewImpl();
		PartitaSubController partitaSubController = 
				new PartitaSubControllerImpl(this, model, partitaSubView);
		partitaSubController.init();
		view.registraSubView(SUBVIEW_PARTITA, partitaSubView);
		
		SetupPartitaSubView setupPartitaSubView = new SetupPartitaSubViewImpl();
		SetupPartitaSubController setupPartitaSubController = 
				new SetupPartitaSubControllerImpl(this, model, setupPartitaSubView);
		setupPartitaSubController.init();
		view.registraSubView(SUBVIEW_SETUP_PARTITA, setupPartitaSubView);
	}
	
	@Override
	public void mostraSubView(int subViewId)
	{
		view.mostraSubView(subViewId);
	}
	
	@Override
	public View getView() 
	{
		return view;
	}

	@Override
	public void setView(View view) 
	{
		this.view = (ModularViewImpl)view;
	}

	@Override
	public Model getModel() 
	{
		return model;
	}

	@Override
	public void setModel(Model model) 
	{
		this.model = model;
	}
}
