package src.co.edu.unbosque.Model;

import src.co.edu.unbosque.Model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrafoDirigido {
    private List<Node> nodes;
    private Double [][] matrizAdy;
    private int n;
    private int numberOfEdges;
    private double[] distance;
    private String[] path;
    private static int[][] edges;
    private boolean[] isVisited;
    public  GrafoDirigido(int n){
        nodes = new ArrayList<>(n);
        this.n = n;
        numberOfEdges=0;
        edges  = new int[n][n];
        isVisited = new boolean[n+1];
        distance = new double[n];
        for (int i = 0; i <n ; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }

        path = new String[n];
        for (int i = 0; i <n ; i++) {
            path[i] = "";
        }
    }



    public void addNode(Node node) {
        nodes.add(node);

    }

    // Imprimir lista de adyacencia
    public static void showEdges(){
        for (int[] edse: edges
        ) {
            System.out.println(Arrays.toString(edse));
        }

    }
    // Obtener el número de vértices
    public  int GetSizeOfGraph(ArrayList<String> Vertax){
        return nodes.size();
    }

    // Obtiene el primer vértice adyacente del vértice especificado
    public int getFirstCO(int index){
        for (int i = 0; i <nodes.size() ; i++) {
            if (edges[index][i]>0) return i;
        }
        return n;
    }
    // Obtiene los vértices adyacentes secuenciales del vértice especificado
    public int getNextCO(int index,int firstCO){
        for (int i =firstCO+1 ; i <nodes.size() ; i++) {
            if (edges[index][i]>0) return i;
        }
        return n;
    }
    // Agregar borde
    public  void addEdges(int e1,int e2 , int weight){
        edges[e1][e2] = weight;
        //edges[e2][e1] = weight;
        numberOfEdges++;
    }
    // Obtener el número de aristas
    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    public ArrayList<String> dijkStra(int index, ArrayList<String> s ){

        // CO son las coordenadas necesarias para la iteración, headIndex es el vértice inicial de cada DIJKSTRA


        int CO;
        int headIndex = index;
        ArrayList<String> lista = new ArrayList<>();
        //
        // Establece la distancia desde el punto inicial al punto inicial, naturalmente 0
        distance[index]=0;


        // Luego haz lo siguiente para cada vértice
        // 1. Establece este vértice en conocido, no te preocupes por la distancia y la ruta de este punto, porque ha sido diseñado antes
        // 2. Encuentra cada vértice adyacente de este vértice. Para un vértice desconocido, compare la distancia alcanzada a lo largo de este vértice con su distancia original, si es menor que la distancia original, actualice la distancia y actualice la ruta
        // 3. Después de establecer este vértice, use la función indexGet para encontrar el vértice con la distancia más pequeña entre los vértices desconocidos actuales, y utilícelo como el siguiente vértice para realizar el paso 2

        while (!isVisited[headIndex]){

            // CO es la primera CO que no ha sido visitada
            CO = getFirstCO(headIndex);
            while(isVisited[CO]){
                CO = getNextCO(headIndex,CO);
            }

            // Si el vértice headIndex no tiene vértices adyacentes que no hayan sido visitados, la coordenada del vértice se obtiene como n, lo que indica que es el último nodo desconocido, y solo necesita establecerse como conocido
            if (CO==n) {
                isVisited[headIndex]=true;
                //System.out.println("Coordinate not found ");
            }
            // Ejecuta el paso 2 para todos los vértices adyacentes a través de un bucle
            else {
                while (!isVisited[CO]&&CO<n) {
                    isVisited[headIndex]=true;
                    double currentDis = distance[headIndex]+edges[headIndex][CO];
                    if (currentDis<distance[CO]) {
                        distance[CO] = currentDis;

                        path[CO] = path[headIndex]+" "+nodes.get(headIndex).getCity();
                    }

                    CO = getNextCO(headIndex, CO);

                }
            }

            headIndex = indexGet(distance,isVisited);


        }
        for (int i = 0; i <n ; i++) {
            path[i] = path[i]+" "+nodes.get(i).getCity();
        }
        System.out.println("Iniciar nodo:"+nodes.get(index).getCity());
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < s.size(); j++) {
                if(nodes.get(i).getCity().equalsIgnoreCase(s.get(j))){
                    //System.out.println(nodes.get(i).getCity()+"   "+distance[i]+"   "+path[i]);
                    lista.add(nodes.get(i).getCity()+" "+distance[i]+" "+path[i]);
                    j = s.size();
                }

            }

        }
        return lista;


    }
    // Devuelve el siguiente vértice requerido a través de la matriz de distancia y la matriz de acceso dadas
    public int indexGet(double[] distance, boolean[] isVisited){
        int j=0;
        double mindis=Double.POSITIVE_INFINITY;
        for (int i = 0; i < distance.length; i++) {
            if (!isVisited[i]){
                if(distance[i]<mindis){
                    mindis=distance[i];
                    j=i;
                }
            }
        }
        return j;
    }


    @Override
    public String toString() {
        return " Nodos:\n" + nodes;
    }

    public int searchNodePosition(String city){

        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getCity().equalsIgnoreCase(city)){
                return i;
            }
        }

        return -1;
    }








    public Node searchNode(String name){
        for (int i = 0; i < nodes.size() ; i++) {
            if(nodes.get(i).getCity().equalsIgnoreCase(name)){
                return nodes.get(i);
            }
        }
        return null;
    }






}