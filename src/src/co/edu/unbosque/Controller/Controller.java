package src.co.edu.unbosque.Controller;



import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import src.co.edu.unbosque.Model.Edge;
import src.co.edu.unbosque.Model.GrafoDirigido;
import src.co.edu.unbosque.Model.Node;
import src.co.edu.unbosque.Model.Persistence.Archivo;
import src.co.edu.unbosque.View.View;



public class Controller {

    private View view;
    private GrafoDirigido grafo;
    private Archivo file;


    public  Controller() {
		view = new View();
		grafo = new GrafoDirigido();
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
    	
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects a file
			File selectedFile = fileChooser.getSelectedFile();
			file = new Archivo(selectedFile);
			file.leerArchivo(selectedFile);
			run();
		}else {
			System.exit(0);
		}

    }

    public void run() {
    	ArrayList<String> lista = file.getNombresS();

		for (int i = 0; i < lista.size(); i++) {
			grafo.addNode(new Node(lista.get(i), false));
		}
		lista = file.getEnlacesEntreNodos();

		for (int i = 0; i < lista.size(); i++) {
			String[] valores = lista.get(i).split(" ");
			Node n1 = grafo.searchNode(valores[0]);
			Node n2 = grafo.searchNode(valores[1]);
			n1.addEdge(new Edge(n1,n2, Integer.parseInt(valores[2])));
		}
		lista = file.getZonaSegura();
		for (int i = 0; i < lista.size(); i++) {
			Node n1 = grafo.searchNode(lista.get(i));
			if(n1 != null) {
				n1.setSeguridad(true);
			}
		}
		String zonaActual = file.getZonaActual();
		String info = grafo.toString();
		view.mostrarInformacion(info, "Información del grafo", 1);
	}

}
