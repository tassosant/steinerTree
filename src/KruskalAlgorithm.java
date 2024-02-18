import java.util.*;

public class KruskalAlgorithm {
//    Initialize an empty edge set T.
//    Sort all graph edges by the ascending order of their weight values.
//    foreach edge in the sorted edge list
//    Check whether it will create a cycle with the edges inside T.
//    If the edge doesn't introduce any cycles, add it into T.
//    If T has (V-1) edges, exit the loop.
//            return T

    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank)
        {
            this.parent = parent;
            this.rank = rank;
        }
    }

    public List<Edge> applyKruskal(int V, List<Edge> edges){
        int j = 0;
        int noOfEdges = 0;

        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[V];

        // Allocate memory for results
        Edge results[] = new Edge[V];

        // Create V subsets with single elements
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }

        // Number of edges to be taken is equal to V-1
        while (noOfEdges < V - 1) {

            // Pick the smallest edge. And increment
            // the index for next iteration
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.source);
            int y = findRoot(subsets, nextEdge.destination);

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }

            j++;
        }

        // Print the contents of result[] to display the
        // built MST
        System.out.println(
                "Following are the edges of the constructed MST:");
        int minCost = 0;
        for (int i = 0; i < noOfEdges; i++) {
            System.out.println(results[i].source + " -- "
                    + results[i].destination + " == "
                    + results[i].weight);
            minCost += results[i].weight;
        }
        System.out.println("Total cost of MST: " + minCost);

        return edges;
    }

    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y)
    {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        }
        else if (subsets[rootX].rank
                < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        }
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i)
    {
        if (subsets[i].parent == i)
            return subsets[i].parent;

        subsets[i].parent
                = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    public List<Edge> applyKruskal2(int V, List<Edge> edges){
//      1. Sort all graph edges by the ascending order of their weight values
        Collections.sort(edges);

//      2.foreach edge in the sorted edge list
//        Check whether it will create a cycle with the edges inside T.
//        If the edge doesn't introduce any cycles, add it into T.
        List<Edge> tree = new ArrayList<>();
//        If T has (V-1) edges, exit the loop.
        int numberOfEdges = 0;
        while (numberOfEdges < V - 1){
            for (Edge edge : edges){
                if (!formsCircle(tree, edge)){
                    System.out.println("added edge "+edge.weight);
                    tree.add(edge);
//                    edges.remove(edge);

                }

            }
            numberOfEdges++;
        }

        return tree;
    }

    public boolean formsCircle(List<Edge> tree, Edge edge){
        int count = 0;
        for (Edge value : tree) {

            int node1 = value.source;
            int node2 = value.destination;
            if (node1 == edge.destination || node1 == edge.source  || node2 == edge.source || node2 == edge.destination) {
                count++;
            }
        }
        return count > 1;
    }

}
