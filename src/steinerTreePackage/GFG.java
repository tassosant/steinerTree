package steinerTreePackage;

import java.util.*;

public class GFG {


    // Member variables of this class
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    // Number of vertices
    private int V;
    List<List<Node>> adj;

    // Constructor of this class
    public GFG(int V)
    {

        // This keyword refers to current object itself
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    public GFG(){}

    // Method 1
    // Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;

        while (settled.size() != V) {

            // Terminating condition check when
            // the priority queue is empty, return
            if (pq.isEmpty())
                return;

            // Removing the minimum distance node
            // from the priority queue
            int u = pq.remove().getV();

            // Adding the node whose distance is
            // finalized
            if (settled.contains(u))

                // Continue keyword skips execution for
                // following check
                continue;

            // We don't have to call e_Neighbors(u)
            // if u is already present in the settled set.
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Method 2
    // To process all the neighbours
    // of the passed node
    private void e_Neighbours(int u)
    {

        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.getV())) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.getV()])
                    dist[v.getV()] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.getV(), dist[v.getV()]));
            }
        }
    }

    // Main driver method
    public List<Edge> calculate(List<List<Node>> adj, int V, int[] source)
    {
        List<Edge> edges = new ArrayList<>();
        for (int k : source) {
            // Calculating the single source the shortest path
            GFG dpq = new GFG(V);
            dpq.dijkstra(adj, k);

            // Printing the shortest path to all the nodes
            // from the source node
            System.out.println("The shorted path from node "+k+":");

//            for (int i = 0; i < dpq.dist.length; i++) {
//                System.out.println(k + " to " + i + " is "
//                        + dpq.dist[i]);
//            }
            for (int steinerNode : source) {
                Edge edge = new Edge(k,steinerNode,dpq.dist[steinerNode]);
                System.out.println(k + " to " + steinerNode + " is "
                        + dpq.dist[steinerNode]);
                System.out.println(edge.toString());
                edges= addUniqueEdges(edges, edge);
//                edges.add(edge);

            }


        }

        return edges;
    }

    public List<Edge> addUniqueEdges(List<Edge> edges, Edge edge){
        if (edge.getDestination()==edge.getSource()){
            return edges;
        }
       for (Edge e:edges){
           if (e.getDestination()==edge.getSource() && e.getSource()==edge.getDestination()){
               return edges;
           }
       }
       edges.add(edge);
       return edges;
    }


}
