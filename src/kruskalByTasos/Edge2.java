package kruskalByTasos;

public class Edge2 implements Comparable<Edge2>{
    private int source;
    private int destination;
    private int weight;

    public Edge2() {
    }

    public Edge2(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge2(int source, int destination) {
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

        Edge2 edge2 = (Edge2) o;

        // Check if source-destination or destination-source are equal
        return (source == edge2.source && destination == edge2.destination) ||
                (source==edge2.destination && destination == edge2.source);
    }


    @Override
    public int compareTo(Edge2 edge) {
        return this.weight-edge.getWeight();
    }
}
