import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import steinerTreePackage.*;

import java.util.ArrayList;
import java.util.List;

public class GraphTest {

    @Test
    public void getAdjacencyTest(){
        Graph graph = new Graph(6);
        List<List<Node>> adj = graph.getAdjacency();
        graph.printAdj();

    }

    @Test
    public void calculateSortestPath(){
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
        List<Edge> actual = gfg.calculate(adj, 6, source);
        List<Edge> expected = new ArrayList<>();
        expected.add(new Edge(1,2, 4));
//        Assertions.assertEquals( expected, actual);
    }

    @Test
    public void applyKruskalTest(){
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
        List<Edge> actual =  g.applyKruskal(graphB,new DisjointSets(6));
//        List<Edge> expected = new ArrayList<>();
//        expected.add(new Edge(1,2, 4));
//        Assertions.assertEquals( expected, actual);

    }



}
