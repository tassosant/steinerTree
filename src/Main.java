import steinerTreePackage.*;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph(6);
        List<List<Node>> adj = graph.getAdjacency();
        for (List<Node> list:adj){
            for (Node node:list){
                node.setV(node.getV()-1);
            }
        }
        graph.printAdj();


        List<Integer> source = graph.getSteinerNodes();
        source.forEach(System.err::println);
        GFG gfg = new GFG();
        List<Edge> graphB = gfg.calculate(adj, 6, source);
        Graph g = new Graph();
        List<Edge> graphC =  g.applyKruskal(graphB,new DisjointSets(6));


       // SteinerTree steinerTree = new SteinerTree(graph.getDisjointSets().getParent()[0],graph.getNodes(),graph.getSteinerNodes(),graph.getMinimumSpanningTree());

        SteinerTree steinerTree = new SteinerTree(3,graph.getNodes(),graph.getSteinerNodes(),graph.getMinimumSpanningTree());




//        Edge2<Integer, Integer> edge1 = new Edge2<>(1, 3, 10);
//        Edge2<Integer, Integer> edge2 = new Edge2<>(3, 1, 20);
//
//        // Comparing edge1 and edge2
//        if (edge1.equals(edge2)) {
//            System.out.println("The edges are considered equal based on their source and destination.");
//        } else {
//            System.out.println("The edges are not equal.");
//        }
    }
}