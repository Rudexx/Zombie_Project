package src.co.edu.unbosque.Model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String city;
    private List<Edge> edges;
    private Boolean seguridad;

    public Node(String city, Boolean security) {
        edges = new ArrayList<>();
        seguridad = security;
        this.city = city;
    }




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Boolean verifyNodes(Node origin, Node destination){

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getOrigin() == origin && edges.get(i).getDestination() == destination){
                return false;
            }
        }
        return true;
    }

    public Boolean addEdge(Edge edge) {
        if (edges == null) {
            edges = new ArrayList<>();
        }
        if(verifyNodes(edge.getOrigin(), edge.getDestination())){
            edges.add(edge);
        }else{
            return false;
        }
       return true;
    }



    @Override
    public String toString() {
        return "\n Nombre: " + city + "\n Seguridad: " + seguridad + "\n Enlaces: " + edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Boolean getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Boolean seguridad) {
        this.seguridad = seguridad;
    }
}