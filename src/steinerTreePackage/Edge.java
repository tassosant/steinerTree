package steinerTreePackage;

public class Edge implements Comparable<Edge>{
    private int source;
    private int destination;
    private int weight;

    public Edge() {
    }

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        // Check if source-destination or destination-source are equal
        return (source == edge.source && destination == edge.destination) ||
                (source== edge.destination && destination == edge.source);
    }


    @Override
    public int compareTo(Edge edge) {
        return this.weight-edge.getWeight();
    }
}
