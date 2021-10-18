package src.co.edu.unbosque.Model;

import java.util.ArrayList;
import java.util.List;

public class GrafoDirigido {
    private List<Node> nodes;
    private Double [][] matrizAdy;

    public  GrafoDirigido(){
        nodes = new ArrayList<>();
    }

    public void createAdyMatrix(){
        matrizAdy = new Double[nodes.size()][nodes.size()];
        Node n = null;
        Node n2 = null;

        for(int i=0; i< nodes.size(); i++){
            for(int j=0; j< nodes.size(); j++){
                matrizAdy[i][j] = 0.0;
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            n = nodes.get(i);
            for (int j = 0; j < nodes.size(); j++) {
                n2 = nodes.get(j);
                for (int k = 0; k < n.getEdges().size(); k++) {
                    if(n.getEdges().get(k).getOrigin() == n && n.getEdges().get(k).getDestination() == n2){
                        matrizAdy[i][j] = n.getEdges().get(k).getDistance();
                        k = n.getEdges().size();
                    }
                }
            }
        }
    }

    public  Double matrizCaminos(Node n, Node n1) throws Exception
        {
            createAdyMatrix();
            int l = nodes.size();
            Double [][] P = matrizAdy; // matriz de caminos
    // Se obtiene la matriz inicial: matriz de adyacencia

    // se obtienen, virtualmente, a partir de P0, las sucesivas
    // matrices P1, P2, P3 ,..., Pn-1, Pn que es la matriz de caminos
            for (int k = 0; k < l; k++) {
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        P[i][j] = Math.min(P[i][j] + P[i][k] * P[k][j], 1);
                        System.out.println(P[i][j] + " ");
                    }
                    System.out.println();
                }
            }
            for (int i = 0; i < nodes.size(); i++) {
                Node m = nodes.get(i);
                for (int j = 0; j < nodes.size(); j++) {
                    Node m2 = nodes.get(j);
                    if(P[i][j] > 0 && m == n && m2 == n1){
                        return P[i][j];
                    }
                }
            }
            return 0.0;
    }

    public void addNode(Node node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return " Nodos:\n" + nodes;
    }

    public String returnNodeNames(int i){
        return nodes.get(i).getCity();
    }

    public Boolean deleteNode(String nombre){
        Node eliminar = searchNode(nombre);
        if(eliminar != null){
            deleteEdges(eliminar);
            nodes.remove(eliminar);
            return true;
        }
        return false;
    }

    public void deleteEdges(Node n){
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getEdges().size(); j++) {
                if(nodes.get(i).getEdges() != null) {
                    if (nodes.get(i).getEdges().get(j).getOrigin() == n ||
                            nodes.get(i).getEdges().get(j).getDestination() == n) {
                        nodes.get(i).getEdges().remove(nodes.get(i).getEdges().get(j));
                    }
                }
            }
        }
    }

    public void deleteOneEdge(String name){
        String nodo1 = name.split(" ")[1];
        String nodo2 = name.split(" ")[3];


        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getCity().equalsIgnoreCase(nodo1)){
                for (int j = 0; j < nodes.get(i).getEdges().size(); j++) {
                    if(nodes.get(i).getEdges().get(j).getOrigin().getCity().equalsIgnoreCase(nodo1)
                    && nodes.get(i).getEdges().get(j).getDestination().getCity().equalsIgnoreCase(nodo2));
                    nodes.get(i).getEdges().remove(nodes.get(i).getEdges().get(j));
                }
            }
        }

    }

    public String getEdges(int i, int j){
        String resultado = "";
        if(nodes.get(i).getEdges()!= null){
            resultado = "Origen: "+nodes.get(i).getEdges().get(j).getOrigin().getCity() + " Destinacion: " +
                nodes.get(i).getEdges().get(j).getDestination().getCity();
        }

        return resultado;
    }
    public int countEdges(){
        int contador = 0;
        for (int i = 0; i < nodes.size() ; i++) {
            if(nodes.get(i).getEdges() != null){
                for (int j = 0; j < nodes.get(i).getEdges().size(); j++) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public Node searchNode(String name){
        for (int i = 0; i < nodes.size() ; i++) {
            if(nodes.get(i).getCity().equalsIgnoreCase(name)){
                return nodes.get(i);
            }
        }
        return null;
    }

    public String showPaths(){

        createAdyMatrix();
        String resultado = "";

        for (int i = 0; i <matrizAdy.length ; i++) {
            for (int j = 0; j < matrizAdy.length ; j++) {
                resultado = resultado + matrizAdy[i][j] + " ";
            }
            resultado = resultado + "\n";
        }

        return resultado;
    }

    public String searchShorterPath(){
        createAdyMatrix();
        int vertices = matrizAdy.length;
        Double matrizAdyacencia [][] = matrizAdy;
        String caminos [][] = new String[vertices][vertices];
        String caminosAuxiliares[][] = new String[vertices][vertices];
        String caminoRecorrido = "", cadena = "", caminitos="";
        int i,j,k;
        Double temporal1, temporal2, temporal3, temporal4, minimo;
        for (i = 0; i <vertices ; i++) {
            for (j = 0; j <vertices ; j++) {
                caminos[i][j] = "";
                caminosAuxiliares[i][j] = "";
            }
            for (k = 0; k <vertices ; k++) {
                for (i = 0; i <vertices ; i++) {
                    temporal1 = matrizAdyacencia[i][j];
                    temporal2 = matrizAdyacencia[i][k];
                    temporal3 = matrizAdyacencia[k][j];
                    temporal4 = temporal2 + temporal3;

                    minimo = Math.min(temporal1, temporal4);
                    if(temporal1 != temporal4){
                        if(minimo == temporal4){
                            caminoRecorrido = "";
                            caminosAuxiliares[i][j] = k + "";
                            caminos[i][j] = caminosR(i,k,caminosAuxiliares, caminoRecorrido)
                            + (k+1);
                        }
                    }
                    matrizAdyacencia[i][j]= (Double) minimo;

                }
            }
        }
        //camino minimo a cadena
        for (i = 0; i <vertices ; i++) {
            for (j = 0; j < vertices; j++) {
               cadena = cadena+"[" + matrizAdyacencia[i][j]+ "]";
            }
            cadena = cadena + "\n";
        }
        for (i = 0; i <vertices ; i++) {
            for (j = 0; j < vertices; j++) {
               if(matrizAdyacencia[i][j] != 1000000000){
                   if(i!=j){
                       if(caminos[i][j].equalsIgnoreCase("")){
                           caminitos += "De (" + (i+1) + "--->" + (j+1) +
                                   ") Irse Por...(" + (i+1)+ ", "+ (j+1)+")\n";
                       }else{
                           caminitos += "De (" + (i+1) + "--->" + (j+1) +
                                   ") Irse Por...("+ (i+1) + ", " + (j+1) + ")\n";
                       }
                   }
               }
            }
        }
        return "La matriz de Caminos" +
                " Mas Cortos Entre Los diferentes Vertices eS:\n" + cadena +
                "\nLos diferentes Caminos Mas Cortos Entre Vertices son:\n" + caminitos;
    }
    public String caminosR(int i, int k,
                           String[][] caminosAuxiliares, String caminoRecorrido){
        if(caminosAuxiliares[i][k].equals("")){
            return "";
        }else{
            ///Recursividad al Millon
            caminoRecorrido +=caminosR(i, Integer.parseInt(caminosAuxiliares[i][k].toString()),
                    caminosAuxiliares, caminoRecorrido) +
                    (Integer.parseInt(caminosAuxiliares[i][k].toString())+1) + ", ";
            return caminoRecorrido;
        }
    }




}
