package campominato.controller.subcontrollers;

import campominato.controller.ModularController;
import campominato.model.DimensioniImpl;
import campominato.model.Model;
import campominato.view.subviews.setuppartita.SetupPartitaSubView;
import campominato.view.subviews.setuppartita.SetupPartitaSubViewListener;

public class SetupPartitaSubControllerImpl extends SubControllerBaseClass<SetupPartitaSubView> implements SetupPartitaSubController
{

	public SetupPartitaSubControllerImpl(ModularController controller, Model model, 
			SetupPartitaSubView subView) 
	{
		super(controller, model, subView);
	}

	@Override
	public void init() 
	{
		ModularController controller = getController();
		Model model = getModel();
		SetupPartitaSubView setupPartitaSubView = getSubView();
		
		setupPartitaSubView.aggiungiSubViewListener(new SetupPartitaSubViewListener() {
			
			@Override
			public void onPanelCreation() {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPanelCreated() {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void tastoCreaPartitaCliccato() 
			{
				String numRigheValue = setupPartitaSubView.getNumRigheValue();
				String numColonneValue = setupPartitaSubView.getNumColonneValue();
				String numMineValue = setupPartitaSubView.getNumMineValue();
				
				try 
				{
					if (!controllaValore(numRigheValue))
						throw new NumberFormatException("Numero righe non valido");
					
					if (!controllaValore(numColonneValue))
						throw new NumberFormatException("Numero colonne non valido");
					
					if (!controllaValore(numMineValue))
						throw new NumberFormatException("Numero mine non valido");
					
					int numRighe = Integer.parseInt(numRigheValue);
					int numColonne = Integer.parseInt(numColonneValue);
					int numMine = Integer.parseInt(numMineValue);
					
					if (numMine >= numRighe * numColonne)
						throw new NumberFormatException("Il numero di mine non deve superare " +
								"le dimensioni del campo");
					
					model.creaNuovaPartita(new DimensioniImpl(numRighe, numColonne), numMine);
					controller.mostraSubView(ModularController.SUBVIEW_PARTITA);
				}
				catch (NumberFormatException e)
				{
					setupPartitaSubView.mostraMessaggio(e.getMessage());
				}
			}
		});
	}
	
	private boolean controllaValore(String value)
	{
		try
		{
			int numero = Integer.parseInt(value);
			if (numero <= 0)
				new NumberFormatException();
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		
		return true;
	}

}
