package campominato.controller;

import campominato.model.Model;
import campominato.view.View;

public interface Controller 
{
	public View getView();
	
	void setView(View view);
	
	public Model getModel();
	
	public void setModel(Model model);
}
