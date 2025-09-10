package campominato.util.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public interface FormCreator 
{
	public JTextField aggiungiCampoTextField(String nome);
	
	public JComboBox<String> aggiungiCampoComboBox(String nome, ArrayList<String> lista);
	
	public void aggiungiSpaziatura(int spaziatura);
	
	public JPanel crea(ActionListener listener);
}
