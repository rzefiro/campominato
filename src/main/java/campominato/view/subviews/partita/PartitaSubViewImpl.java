package campominato.view.subviews.partita;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import campominato.model.Posizione;
import campominato.model.PosizioneImpl;
import campominato.view.subviews.SubViewBaseClass;

public class PartitaSubViewImpl extends SubViewBaseClass<PartitaSubViewListener> implements PartitaSubView
{
	private static final int CASELLA_SIZE = 20;
	
	private int numRighe;
	private int numColonne;
	private JPanel campo;
	private Casella[][] caselle;
	
	@Override
	public void setDimensioniCampo(int numRighe, int numColonne) 
	{
		this.numRighe = numRighe;
		this.numColonne = numColonne;
	}
	
	@Override
	public JPanel getPanel() {
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel dashboard = creaDashboard();
		//contentPane.add(dashboard);
		//dashboard.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.campo = creaCampo();
		contentPane.add(campo);
		campo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		return contentPane;
	}
	
	private JPanel creaDashboard()
	{
		JPanel dashboard = new JPanel();
		dashboard.setLayout(new BoxLayout(dashboard, BoxLayout.X_AXIS));
		
		JLabel labelNumBandierine = new JLabel("001");
		JLabel labelDurata = new JLabel("002");
		
		dashboard.add(labelNumBandierine);
		dashboard.add(labelDurata);
		
		return dashboard;
	}
	
	private JPanel creaCampo()
	{
		JPanel campo = new JPanel();
		campo.setLayout(new GridLayout(numRighe, numColonne));
		
		caselle = new Casella[numRighe][numColonne];
		
		for (int i = 0; i < numRighe; i++)
		{
			for (int j = 0; j < numColonne; j++)
			{
				Casella casella = new Casella(new PosizioneImpl(i, j), CASELLA_SIZE);
				casella.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mousePressed(MouseEvent e) 
					{
						if (SwingUtilities.isLeftMouseButton(e))
						{
							generaEventoClickTastoSinistroSuCasella(casella.getPosizione());
						}
						else if (SwingUtilities.isRightMouseButton(e))
						{
							generaEventoClickTastoDestroSuCasella(casella.getPosizione());
						}
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				campo.add(casella);
				caselle[i][j] = casella;
			}
		}
		
		return campo;
	}

	@Override
	public void mostraCaselleSenzaMinaScoperte(Map<Posizione, Integer> caselle) 
	{
		campo.setVisible(false);
		for (Posizione p : caselle.keySet())
		{
			Casella casella = getCasella(p);
			int num = caselle.get(p);
			if (num > 0)
				casella.scopri(num);
			else
				casella.scopri();
		}
		campo.setVisible(true);
	}

	@Override
	public void mostraMossaSconfitta(Posizione pos, Set<Posizione> posMineRimanenti) 
	{
		Casella casella = getCasella(pos);
		campo.setVisible(false);
		casella.mostraMina(true);
		for (Posizione p : posMineRimanenti)
		{
			casella = getCasella(p);
			casella.mostraMina(false);
		}
		campo.setVisible(true);
	}
	
	@Override
	public void mostraBandierina(Posizione p) 
	{
		Casella casella = getCasella(p);
		casella.mostraBandierina();
	}
	
	@Override
	public void mostraBandierine(Set<Posizione> posBandierineMineRimanenti) 
	{
		Casella casella;
		
		campo.setVisible(false);
		for (Posizione p : posBandierineMineRimanenti)
		{
			casella = getCasella(p);
			casella.mostraBandierina();
		}
		campo.setVisible(true);
	}
	
	@Override
	public void rimuoviBandierina(Posizione p) 
	{
		Casella casella = getCasella(p);
		casella.rimuoviBandierina();
	}
	
	private void generaEventoClickTastoSinistroSuCasella(Posizione p)
	{
		inviaEvento(new EventSender<PartitaSubViewListener>() {

			@Override
			public void invia(PartitaSubViewListener listener) 
			{
				listener.clickCasella(PartitaSubViewListener.TASTO_SINISTRO, p);
			}
			
		});
	}
	
	private void generaEventoClickTastoDestroSuCasella(Posizione p)
	{
		inviaEvento(new EventSender<PartitaSubViewListener>() {

			@Override
			public void invia(PartitaSubViewListener listener) 
			{
				listener.clickCasella(PartitaSubViewListener.TASTO_DESTRO, p);
			}
			
		});
	}
	
	private Casella getCasella(Posizione p)
	{
		return caselle[p.getRiga()][p.getColonna()];
	}

	@Override
	public void mostraMessaggioPartitaVinta() 
	{
		mostraMessaggio("Complimenti, hai vinto!");	
	}
}
