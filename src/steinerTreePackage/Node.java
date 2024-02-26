package steinerTreePackage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Node implements Comparator<Node>,Comparable<Node> {
    private List<Edge> edges;

    private List<Node> children;

    private Node parent;


    //number of vertice
    private int V;

    public int cost;

    public Node(int v) {
        this.V = v;
        initProperties();
    }

    //to make dijkstra work
    public Node(int V, int cost){
        // This keyword refers to current instance itself
        this.V = V;
        this.cost = cost;
    }

    public Node(List<Edge> edges, int v) {
        this.edges = edges;
        this.V = v;
        initProperties();
    }

    public Node() {
        initProperties();
    }

    private void initProperties(){
        this.children = new ArrayList<>();
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setParent(int V){
        this.parent = new Node(V);
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(int V){
        this.children.add(new Node(V));
    }

    public void addChildNode(Node node){
        this.children.add(node);
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

    public String toString() {
        return "Node " + this.V + " -> Children: " + this.children.stream().map(n -> Integer.toString(n.getV())).collect(Collectors.joining(", "));
    }

    @Override
    public int compareTo(Node node) {
        return this.V-node.getV();
    }
}
