package campominato.util.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormCreatorImpl implements FormCreator 
{
	private class CampoForm
	{
		public String nome;
		public JComponent component;
	}
	
	private ArrayList<CampoForm> campi;
	private Map<Integer, Integer> spaziature;
	private int riga;
	
	public FormCreatorImpl()
	{
		this.campi = new ArrayList<FormCreatorImpl.CampoForm>();
		this.spaziature = new HashMap<Integer, Integer>();
		this.riga = 0;
	}
	
	@Override
	public JTextField aggiungiCampoTextField(String nome) 
	{
		return (JTextField)aggiungiCampo(nome, new JTextField());
	}

	@Override
	public JComboBox<String> aggiungiCampoComboBox(String nome, ArrayList<String> lista) 
	{
		JComboBox<String> comboBox = new JComboBox<String>(new Vector<String>(lista));
		return (JComboBox<String>)aggiungiCampo(nome, comboBox);
	}
	
	private JComponent aggiungiCampo(String nome, JComponent component)
	{
		CampoForm campo = new CampoForm();
		campo.nome = nome;
		campo.component = component;
		this.campi.add(campo);
		
		this.riga++;
		
		return campo.component;
	}

	@Override
	public void aggiungiSpaziatura(int spaziatura) 
	{
		this.spaziature.put(this.riga, spaziatura);
	}

	@Override
	public JPanel crea(ActionListener listener) 
	{
		JPanel form = new JPanel();
		form.setLayout(new GridBagLayout());
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.anchor = GridBagConstraints.LINE_START;
		
		for (int i = 0; i < this.campi.size(); i++)
		{
			CampoForm campo = campi.get(i);
			
			cons.gridy = i;
			
			int spaziatura = 5;
			if (spaziature.containsKey(i))
				spaziatura = spaziature.get(i);
			
			cons.insets = new Insets(spaziatura, 0, 0, 30);
			
			cons.gridx = 0;
			form.add(new JLabel(campo.nome), cons);
			
			cons.gridx = 1;
			form.add(campo.component, cons);
		}
		
		cons.gridy = this.campi.size();
		cons.gridx = 0;
		cons.gridwidth = 2;
		cons.anchor = GridBagConstraints.CENTER;
		cons.insets = new Insets(20, 0, 0, 0);
		JButton buttonConferma = new JButton("Conferma");
		buttonConferma.addActionListener(listener);
		
		form.add(buttonConferma, cons);
		
		return form;
	}

}
