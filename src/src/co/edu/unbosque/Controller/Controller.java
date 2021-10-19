package src.co.edu.unbosque.Controller;



import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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

    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
    	
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects a file
			File selectedFile = fileChooser.getSelectedFile();
			file = new Archivo(selectedFile);
			file.leerArchivo(selectedFile);
			grafo = new GrafoDirigido(file.getnVertices());
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
			int node1 = grafo.searchNodePosition(valores[0]);
			int node2 = grafo.searchNodePosition(valores[1]);
			grafo.addEdges(node1, node2, Integer.parseInt(valores[2]));
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
		ArrayList<String> listaFinal = grafo.dijkStra(grafo.searchNodePosition(zonaActual), lista);
		ArrayList<Double> pesos = new ArrayList<>();
		String ruta = "";
//		for (int i = 0; i < listaFinal.size(); i++) {
//			System.out.println(listaFinal.get(i));
////			String[] s = lista.get(i).split(" ");
////			for (int j = 0; j < s.length; j++) {
////				System.out.println(s[j]);
////			}
//
//		}
		Double min = 0.0;
		for (int i = 0; i < listaFinal.size(); i++) {
			String[] s = listaFinal.get(i).split(" ");
			pesos.add(Double.parseDouble(s[1]));
		}
		if(pesos.size() > 0) {

			Collections.sort(pesos);
			min = pesos.get(0);
			for (int i = 0; i < pesos.size(); i++) {
				String[] s = listaFinal.get(i).split(" ");
				if (min == Double.parseDouble(s[1])) {

					for (int j = 2; j < s.length; j++) {
						ruta = ruta + s[j] + " ";
					}
				}
			}
		}
		if(!ruta.equals("") && min != Double.POSITIVE_INFINITY){
			System.out.println("La ruta mas corta tiene un peso de: " + min
					+ "\nLa ruta es la siguiente: " + ruta);
		}else{
			System.out.println("Estas perdido");
		}




	}

}
