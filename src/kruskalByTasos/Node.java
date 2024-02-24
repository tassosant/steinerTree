package kruskalByTasos;
import java.util.List;

public class Node {
    private List<Edge2> edges;



    //number of vertice
    private int V;

    public Node(int v) {
        V = v;
    }

    public Node(List<Edge2> edges, int v) {
        this.edges = edges;
        V = v;
    }

    public Node() {
    }

    public List<Edge2> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge2> edges) {
        this.edges = edges;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public void addEdge(Edge2 edge2){
        this.edges.add(edge2);
    }
}
