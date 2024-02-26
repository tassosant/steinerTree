import org.junit.Test;
import steinerTreePackage.GFG;
import steinerTreePackage.Node;

import java.util.ArrayList;
import java.util.List;

public class GFGTest {

    @Test
    public void calculateShortestPathTest1(){

        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges by declaring List class object
        // Declaring object of type List<Node>
        List<List<Node>> adj
                = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the GFG(dpq) graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        GFG gfg = new GFG();
        gfg.calculate(adj, V, source);
    }
}
