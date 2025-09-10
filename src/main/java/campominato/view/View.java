package campominato.view;

import campominato.controller.Controller;

public interface View 
{
	public void setController(Controller controller);
	
	public Controller getController();
}
