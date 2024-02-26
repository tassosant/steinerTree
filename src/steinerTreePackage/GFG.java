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

//    public List<Integer> prev = new ArrayList<>();
int[] prev;

    List<Edge> calculatedEdges = null;

    Map<Edge, List<Integer>> shortestPaths = new HashMap<>();
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

    public Map<Edge, List<Integer>> getShortestPaths() {
        return shortestPaths;
    }

    // Main driver method
//    public List<Edge> calculate(Graph graph, int V, List<Integer> source)
    public List<Edge> calculate(List<List<Node>> adj, int V, List<Integer> source)
    {

        List<Edge> edges = new ArrayList<>();
        for (int k : source) {
            // Calculating the single source the shortest path
            GFG dpq = new GFG(V);

//            dpq.dijkstra(adj, k);
//            GFG dpq2 = new GFG(V);


            // Printing the shortest path to all the nodes
            // from the source node
            System.out.println("The shorted path from node "+k+":");

            for (int steinerNode : source) {
                List<Integer> path = dpq.dijkstra2(adj, k, steinerNode);
                Edge edge = new Edge(k,steinerNode,dpq.dist[steinerNode]);
                System.out.println(k + " to " + steinerNode + " is "
                        + dpq.dist[steinerNode]);

                System.out.println(edge.toString());
                edges= addUniqueEdges(edges, edge);
//                List<Integer> path = dpq2.dijkstra2(adj, k, steinerNode);
                System.out.println(path.toString());
                shortestPaths.put(edge, path);
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


    public List<Integer> dijkstra2(List<List<Node> > adj, int src, int target) {
        this.adj = adj;
        dist = new int[V];
        prev = new int[V];
        settled.clear();

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1); // Initialize prev array with -1

        PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(node -> node.cost));
        pq.add(new Node(src, 0));

        dist[src] = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().getV();

            if (u == target) {
                return constructPath(src, target);
            }

            settled.add(u);

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.getV();
                int edgeCost = neighbor.cost;

                if (!settled.contains(v) && dist[u] != Integer.MAX_VALUE && dist[u] + edgeCost < dist[v]) {
                    dist[v] = dist[u] + edgeCost;
                    prev[v] = u; // Update previous node for vertex v
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return new ArrayList<>(); // If target is not reachable, return an empty path

    }

    // Helper method to construct the shortest path
    private List<Integer> constructPath(int src, int target) {
        List<Integer> path = new ArrayList<>();
        for (int at = target; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }



}
