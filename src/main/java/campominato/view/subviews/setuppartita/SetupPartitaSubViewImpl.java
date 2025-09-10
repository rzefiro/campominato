package campominato.view.subviews.setuppartita;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import campominato.util.gui.FormCreator;
import campominato.util.gui.FormCreatorImpl;
import campominato.view.subviews.SubViewBaseClass;
import campominato.view.subviews.partita.EventSender;

public class SetupPartitaSubViewImpl extends SubViewBaseClass<SetupPartitaSubViewListener>
		implements SetupPartitaSubView
{
	private JComboBox<String> comboDifficolta;
	private JTextField textNumRighe;
	private JTextField textNumColonne;
	private JTextField textNumMine;
	
	@Override
	public JPanel getPanel() 
	{
		JPanel contentPane = new JPanel();
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JLabel labelTitolo = new JLabel("Campo minato");
		labelTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(labelTitolo);
		
		container.add(Box.createVerticalStrut(20));
		
		JPanel form = creaFormNuovaPartita();
		form.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(form);
		
		contentPane.add(container);
		
		return contentPane;
	}
	
	private JPanel creaFormNuovaPartita()
	{
		FormCreator formCreator = new FormCreatorImpl();
		
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("Facile");
		lista.add("Medio");
		lista.add("Difficile");
		lista.add("Personalizzato");
		comboDifficolta = formCreator.aggiungiCampoComboBox("Difficoltà", lista);
		
		formCreator.aggiungiSpaziatura(10);
		textNumRighe = formCreator.aggiungiCampoTextField("Numero righe");
		textNumColonne = formCreator.aggiungiCampoTextField("Numero colonne");
		textNumMine = formCreator.aggiungiCampoTextField("Numero mine");
		
		textNumRighe.setColumns(3);
		textNumColonne.setColumns(3);
		textNumMine.setColumns(3);
		setCampiForm();
		
		comboDifficolta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				setCampiForm();
			}
		});
		
		JPanel form = formCreator.crea(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				inviaEvento(new EventSender<SetupPartitaSubViewListener>() {

					@Override
					public void invia(SetupPartitaSubViewListener listener) 
					{
						listener.tastoCreaPartitaCliccato();
					}

				});	
			}
		});
		
		return form;
	}
	
	private void setCampiForm()
	{
		switch(comboDifficolta.getSelectedIndex())
		{
			case 0:
				setValoriCampi(10, 10, 10);
				break;
			case 1:
				setValoriCampi(20, 20, 20);
				break;
			case 2:
				setValoriCampi(20, 30, 25);
		}
		
		if (comboDifficolta.getSelectedIndex() < 3)
			setCampiFormModificabili(false);
		else
			setCampiFormModificabili(true);
	}
	
	private void setValoriCampi(int numRighe, int numColonne, int numMine)
	{
		textNumRighe.setText(String.valueOf(numRighe));
		textNumColonne.setText(String.valueOf(numColonne));
		textNumMine.setText(String.valueOf(numMine));
	}
	
	private void setCampiFormModificabili(boolean modificabili)
	{
		textNumRighe.setEditable(modificabili);
		textNumColonne.setEditable(modificabili);
		textNumMine.setEditable(modificabili);
	}

	@Override
	public String getNumRigheValue() 
	{
		return textNumRighe.getText();
	}

	@Override
	public String getNumColonneValue() 
	{
		return textNumColonne.getText();
	}

	@Override
	public String getNumMineValue() 
	{
		return textNumMine.getText();
	}
	
}
