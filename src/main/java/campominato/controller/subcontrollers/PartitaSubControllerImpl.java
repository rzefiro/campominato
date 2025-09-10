package campominato.controller.subcontrollers;

import java.util.Map;
import java.util.Set;

import campominato.controller.ModularController;
import campominato.model.Dimensioni;
import campominato.model.Model;
import campominato.model.Posizione;
import campominato.model.listeners.CampoPartitaListener;
import campominato.view.subviews.partita.PartitaSubView;
import campominato.view.subviews.partita.PartitaSubViewListener;

public class PartitaSubControllerImpl extends SubControllerBaseClass<PartitaSubView>
		implements PartitaSubController
{

	public PartitaSubControllerImpl(ModularController controller, Model model, PartitaSubView subView) 
	{
		super(controller, model, subView);
	}
	
	@Override
	public void init() 
	{
		PartitaSubView partitaSubView = getSubView();
		Model model = getModel();
		
		partitaSubView.aggiungiSubViewListener(new PartitaSubViewListener() {
			
			@Override
			public void onPanelCreation() 
			{
				Dimensioni dimensioni = model.getDimensioniCampoPartita();
				partitaSubView.setDimensioniCampo(dimensioni.getNumRighe(), dimensioni.getNumColonne());
			}
			
			@Override
			public void onPanelCreated() 
			{
				model.avviaPartita();
			}
			
			@Override
			public void clickCasella(int tasto, Posizione p) 
			{
				switch (tasto)
				{
					case PartitaSubViewListener.TASTO_SINISTRO:
						model.scopriCasella(p);
						break;
					case PartitaSubViewListener.TASTO_DESTRO:
						model.toggleBandierina(p);
						break;
				}
			}
		});
		
		model.aggiungiCampoPartitaListener(new CampoPartitaListener() {
			
			@Override
			public void vittoria(Set<Posizione> posBandierineMineRimanenti) 
			{
				partitaSubView.mostraBandierine(posBandierineMineRimanenti);
				partitaSubView.mostraMessaggioPartitaVinta();
			}
			
			@Override
			public void sconfitta(Posizione posMinaTrovata, Set<Posizione> posMineRimanenti) 
			{
				partitaSubView.mostraMossaSconfitta(posMinaTrovata, posMineRimanenti);
			}
			
			@Override
			public void caselleScoperte(Map<Posizione, Integer> caselleSenzaMinaScoperte) 
			{
				partitaSubView.mostraCaselleSenzaMinaScoperte(caselleSenzaMinaScoperte);
			}
			
			@Override
			public void bandierinaRimossa(Posizione pos) 
			{
				partitaSubView.rimuoviBandierina(pos);
			}
			
			@Override
			public void bandierinaAggiunta(Posizione pos) 
			{
				partitaSubView.mostraBandierina(pos);
			}
		});
	}
}
