package steinerTreePackage;
import java.util.Comparator;
import java.util.List;

public class Node implements Comparator<Node> {
    private List<Edge> edges;

    //to make dijkstra work
    public int node;
    public int cost;


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

    //to make dijkstra work
    public Node(int node, int cost)
    {

        // This keyword refers to current instance itself
        this.node = node;
        this.cost = cost;
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

    // Method 1
    @Override
    public int compare(Node node1, Node node2)
    {

        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }
}
