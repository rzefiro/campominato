package campominato.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import campominato.controller.Controller;
import campominato.controller.ModularControllerImpl;
import campominato.view.subviews.SubView;

public class ModularViewImpl extends JFrame implements ModularView
{
	private ModularControllerImpl controller;
	private Map<Integer, SubView> subViews;
	
	public ModularViewImpl()
	{
		this.subViews = new HashMap<Integer, SubView>();
	}
	
	@Override
	public void registraSubView(int idSubView, SubView subView)
	{
		this.subViews.put(idSubView, subView);
	}
	
	@Override
	public void mostraSubView(int idSubView) 
	{
		SubView subView = this.subViews.get(idSubView);
		
		subView.inviaEventoOnPanelCreation();
		
		JPanel contentPane = subView.getPanel();
		setContentPane(contentPane);
		
		subView.inviaEventoOnPanelCreated();
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void setController(Controller controller) 
	{
		this.controller = (ModularControllerImpl)controller;
	}

	@Override
	public Controller getController() 
	{
		return this.controller;
	}
}
