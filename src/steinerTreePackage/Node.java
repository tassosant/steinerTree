package steinerTreePackage;
import java.util.List;

public class Node {
    private List<Edge> edges;



    //number of vertice
    private int V;

    public Node(int v) {
        V = v;
    }

    public Node(List<Edge> edges, int v) {
        this.edges = edges;
        V = v;
    }

    public Node() {
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }
}
