package src.co.edu.unbosque.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class View {

    public View() {
        try {
            UIManager UI=new UIManager();
            UI.put("OptionPane.background", new Color(0,0,0));
            UI.put("Panel.background", new Color(66, 73, 73));
            UI.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UI.put("OptionPane.messageForeground", new Color(191, 204, 193  ));
            UI.put("Button.background",new Color(152, 176, 144  ));
            UI.put("Button.font",new Font("Times New Roman", Font.PLAIN, 15));
            UI.put("OptionPane.messageFont", new Font("Georgia", Font.BOLD, 17));
            UI.put("Button.select", new Color(119, 49, 49  ));
            UI.put("ComboBox.selectionBackground", new Color(77, 81, 75  ));
            UI.put("ComboBox.background", new Color(80, 98, 74   ));
            UI.put("ComboBox.foreground", new Color(234, 236, 234 ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarInformacion(String mensages,String titulo, int icon){
        JOptionPane.showMessageDialog(null, mensages, titulo, icon);
    }
    
    public int mostrarChooser() {
    	JFileChooser fileChooser = new JFileChooser();

    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	
    	return fileChooser.showOpenDialog(null);
    }
    


    public String mostrarListaDeOpciones(String[] options, String mensaje){
        Object o = JOptionPane.showInputDialog(null,mensaje, "Elegir",JOptionPane.QUESTION_MESSAGE,
                null,options, options[0]);
        return  o.toString();
    }

    public Integer preguntarTipo(String mensaje) {
        Object[] options = { "Grafo no Dirigido", "Grafo Dirigido" };
       return JOptionPane.showOptionDialog(null, "Tipo de grafo", mensaje,
        	    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
        	    options, options[0]);
        
    }
   public String ingresoDeDatos(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
   }



}
