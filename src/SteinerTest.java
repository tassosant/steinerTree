import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import steinerTreePackage.*;

import java.util.ArrayList;
import java.util.List;

public class SteinerTest {


    @Test
    public void aToCTest(){
        int numberOfNodes = 9;
//        int[] source = new int[]{0, 1, 2, 3};

        List<Integer> source = new ArrayList<>();
        source.add(0);
        source.add(1);
        source.add(2);
        source.add(3);
        // Adjacency list representation of the
        // connected edges by declaring List class object
        // Declaring object of type List<Node>
        List<List<Node>> adj
                = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < numberOfNodes; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the GFG(dpq) graph
        adj.get(0).add(new Node(1, 10));
        adj.get(1).add(new Node(0, 10));
        adj.get(0).add(new Node(8, 1));
        adj.get(8).add(new Node(0, 1));
        adj.get(1).add(new Node(2, 8));
        adj.get(2).add(new Node(1, 8));
        adj.get(1).add(new Node(5, 1));
        adj.get(5).add(new Node(1, 1));
        adj.get(2).add(new Node(4, 2));
        adj.get(4).add(new Node(2, 2));
        adj.get(2).add(new Node(3, 9));
        adj.get(3).add(new Node(2, 9));
        adj.get(3).add(new Node(4, 2));
        adj.get(4).add(new Node(3, 2));
        adj.get(4).add(new Node(5, 1));
        adj.get(5).add(new Node(4, 1));
        adj.get(5).add(new Node(6, 1));
        adj.get(6).add(new Node(5, 1));
        adj.get(6).add(new Node(7, 1));
        adj.get(7).add(new Node(6, 1));
        adj.get(7).add(new Node(8, 0));
        adj.get(8).add(new Node(7, 0));
        adj.get(4).add(new Node(8, 1));
        adj.get(8).add(new Node(4, 1));

        GFG gfg = new GFG();
        List<Edge> graphB = gfg.calculate(adj, numberOfNodes, source);

        graphB.forEach((edge -> {
            edge.setSource(edge.getSource()+1);
            edge.setDestination(edge.getDestination()+1);
        }));

        Graph g = new Graph();
        List<Edge> actual =  g.applyKruskal(graphB,new DisjointSets(4));
        List<Edge> expected =new ArrayList<>();
        expected.add(new Edge(0,1, 4));
        expected.add(new Edge(0,2, 4));
        expected.add(new Edge(0,3, 4));
        expected.forEach((edge -> {
            edge.setSource(edge.getSource()+1);
            edge.setDestination(edge.getDestination()+1);
        }));
        Assertions.assertEquals( expected, actual);
    }


}
