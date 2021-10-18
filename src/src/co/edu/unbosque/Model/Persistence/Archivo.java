package src.co.edu.unbosque.Model.Persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Archivo {


	private ArrayList<String> nombresS;
	private ArrayList<String> enlacesEntreNodos;
	private ArrayList<String> zonaSegura;
	private String zonaActual;

	/**
	 * Constructor de la clase Archivo. Inicializa la clase
	 * @param archivo: el archivo que se vaya a utilizar
	 */
	public void imprimirDatos() {
		System.out.println("Ciudades: \n");
		for (int i = 0; i < nombresS.size(); i++) {
			System.out.println(nombresS.get(i));
		}
		System.out.println("\nEnlaces: \n");
		for (int i = 0; i < enlacesEntreNodos.size(); i++) {
			System.out.println(enlacesEntreNodos.get(i));
		}
		System.out.println("\nZonas Seguras: \n");
		for (int i = 0; i < zonaSegura.size(); i++) {
			System.out.println(zonaSegura.get(i));
		}
		System.out.println("\nZona Actual: " + zonaActual);
		
	}
	
	public Archivo(File archivo) {
		enlacesEntreNodos = new ArrayList<>();
		zonaSegura = new ArrayList<>();
		if (archivo.exists()) {
		} else {
			try {
				archivo.createNewFile();

			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	


	/**
	 * metodo de lectura de archivos, en este caso binarios
	 * <b>pre</b> se ha inicializado la clase archivo<br>
	 * <b>post</b> se retorno el valor solicitado del archivo correctamente <br>
	 * @param archivo: el archivo a leer
	 * @return una lista con todos los entrenadores
	 */
	
	
	
	public void leerArchivo(File archivo) {
		String[] enlaces = null;
		String[] zonas = null;
		if (archivo.length() != 0) {
			try {
				BufferedReader fLectura = new BufferedReader(new FileReader(archivo));
				int count = 0;
				Scanner scanner;
				String line = "";
				ArrayList<String> nombres = new ArrayList<>();
				scanner = new Scanner(archivo);
				while(scanner.hasNextLine()) {
					line = scanner.nextLine();
					if(count != 0 && !line.contains("Zonas Seguras")
							&& !line.contains("X")) {
						enlaces = line.split(" ");
						for (int i = 0; i < enlaces.length; i++) {
							enlaces[i] = enlaces[i].replace(":", "");
						}
						nombres.add(enlaces[0]);
						nombres.add(enlaces[2]);
						enlacesEntreNodos.add(enlaces[0] + " " + enlaces[2] + " " + enlaces[3]);
					}else if(count != 0 && line.contains("Zonas Seguras")) {
						System.out.println("aca");
						zonas = line.split(" ");
						for (int i = 2; i < zonas.length; i++) {
							if(!zonas[i].equalsIgnoreCase("–")) {
							zonaSegura.add(zonas[i]);
							}
						}
					}else {
						zonas = line.split(" ");
						zonaActual = zonas[1];
					}
					count++;
				}
				ArrayList<String> nombresSinRepetir = new ArrayList<>();
				for (int i = 0; i < nombres.size(); i++) {
					if(!nombresSinRepetir.contains(nombres.get(i))) {
						nombresSinRepetir.add(nombres.get(i));
					}
				}
		
				nombresS = nombresSinRepetir;
				imprimirDatos();
				scanner.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}




	/**
	 * @return the nombresS
	 */
	public ArrayList<String> getNombresS() {
		return nombresS;
	}




	/**
	 * @param nombresS the nombresS to set
	 */
	public void setNombresS(ArrayList<String> nombresS) {
		this.nombresS = nombresS;
	}
	
	
}