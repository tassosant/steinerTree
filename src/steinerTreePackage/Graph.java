package steinerTreePackage;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private List<Edge> graphEdges;
    private List<Integer> nodes;
    private List<Integer> steinerNodes;
    private List<List<Node>> adj;
    private List<Edge> minimumSpanningTree;

    private DisjointSets disjointSets;

    private int steinerNodesNum;

    private int verticesNum;

    public Graph(int vertices) {
        this.verticesNum = vertices;
        initProperties();
        generateGraph();
        this.minimumSpanningTree = applyKruskal(this.graphEdges,this.disjointSets);
        printEdgesWithWeights(this.minimumSpanningTree);
        System.out.println("Root:"+(this.disjointSets.getParent()[0]+1));
        Arrays.stream(this.disjointSets.getRank()).forEach((rank)->{
            System.out.print(rank+" ");
        });
        System.out.println();

    }

    public Graph() {

    }

    private void initProperties(){
        this.steinerNodesNum = 0;
        this.graphEdges = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.steinerNodes = new ArrayList<>();
        this.minimumSpanningTree = new ArrayList<>();
        this.disjointSets = new DisjointSets(this.verticesNum);
//        this.neighbourNodes = new ArrayList<>(this.verticesNum);
    }

    private void generateGraph(){
        //firstly generate nodes
        generateNodes(this.verticesNum);
        pickRandomlySteinerNodes(1,this.verticesNum);
        Collections.sort(this.steinerNodes);
        printList(this.steinerNodes,"Steiner nodes before sort");
        generateEdges(0,0);
//        generateRandomEdges(1,(this.verticesNum*(this.verticesNum-1))/2,1);
//        Collections.sort(this.steinerNodes, Comparator.comparingInt(Node::getV));
        Collections.sort(this.graphEdges);
        printEdgesWithWeights(this.graphEdges);
        //70% must be steiner nodes
        //generate edges
    }

    private void generateNodes(int nodes){

        int vertice = 1;
        do {
            //Node node = new Node(vertice++);
            int node = vertice++;
            System.out.println(String.format("Node %d created", vertice-1));
            this.nodes.add(node);
        }while (this.nodes.size()<nodes);



    }



    private int generateRandomInRangeExceptForOne(int min, int max, int exception){
        // to run unit tests comment out the next line
         Random random = new Random(1);
        // to run actual random comment out the next line
        // Random random = new Random(0);
        int randomInt;
        do {
            randomInt = random.nextInt(max-min+1)+min;
        }while (randomInt==exception);

        return randomInt;

    }

    private int generateRandomInRange(int min,int max){
        // to run unit tests comment out the next line
        Random random = new Random(1);
        // to run actual random comment out the next line
        // Random random = new Random(0);
        return random.nextInt(max-min+1)+min;
    }



    //nodes steiner
    //100   70
    //6     x
    //x=percentage*6/100

    public void pickRandomlySteinerNodes(int min,int max){
        int node = generateRandomInRange(min,max);
        if(this.steinerNodes.size()+1<=(70*this.nodes.size())/100) {
            this.steinerNodes.add(this.nodes.get(node-1));
        }
        if(node!=min){
            pickRandomlySteinerNodes(min,node-1);
        }
        if(node!=max){
            pickRandomlySteinerNodes(node + 1, max);
        }

    }


    private void generateEdges(int min, int max){
        int source = 1;
        while (source<=this.nodes.size()-1){
            int destination = source+1;
//            List<Node> neighbors = new ArrayList<>();
            while (destination<=this.nodes.size()) {
                Edge edge = generateEdge(source, destination);
                Node neighborNode = new Node(destination,edge.getWeight());
//                neighbors.add(neighborNode);
                this.graphEdges.add(edge);
                destination++;
            }
//            this.neighbourNodes.add(source-1,neighbors);
            source++;
        }
        printList(this.graphEdges);
    }

    public List<List<Node>> getAdjacency(){
        List<List<Node>> a = new ArrayList<>();
        for (Integer node: nodes){
            List<Node> list = new ArrayList<>();
            for (Edge edge : graphEdges){
                if (edge.getSource()==node){
                    Node n = new Node(edge.getDestination(), edge.getWeight());
                    list.add(n);
                }
                if (edge.getDestination()==node){
                    Node n = new Node(edge.getSource(), edge.getWeight());
                    list.add(n);
                }
            }
            a.add(node-1,list);
        }

        this.adj = a;

        return a;
    }

    private void generateRandomEdges(int min, int max, int source){
        //generate the maximum edges (n*(n-1))/2
        if(source>=this.nodes.size()){
            return;
        }
        //edges of current node
        int edges = generateRandomInRangeExceptForOne(min,this.nodes.size()-1,source);
        int edgesCreated = 0;
        List<Integer> destinations = new ArrayList<>();
        while (edgesCreated<edges) {
            Edge edge;
            int destination = generateRandomInRangeExceptForOne(min, this.nodes.size(), source);
            while (destinations.contains(destination) || destination<source){
                destination = generateRandomInRangeExceptForOne(min, this.nodes.size(), source);
                System.out.println("I am in loop");
            }
            edge = generateEdge(source, destination);
            this.graphEdges.add(edge);
            destinations.add(destination);
            edgesCreated++;
        }
        printList(this.graphEdges);
        generateRandomEdges(min,max,source+1);
        //check if source and destination have not an edge
        //check if source or destination are not exceeding the number of edges
    }

    private Edge generateEdge(int source, int destination){
        Edge edge = new Edge(source,destination);
        edge.setWeight(generateRandomInRange(0,50));
        //source.addEdge(edge);
        //destination.addEdge(edge);
        return edge;
    }

    private void printList(List<Integer> list,String text){
        System.out.println(text);
        list.forEach((localNode)->{
            System.out.print(localNode+" ");
        });
        System.out.println();
    }

    private void printList(List<Edge> list){

        list.forEach((localNode)->{
            System.out.print(String.format("(%d,%d)\t",localNode.getSource(),localNode.getDestination()));
        });
        System.out.println();
    }



    private void checkForSameEdge(Node source,Node destination){

    }

    public List<Edge> applyKruskal(List<Edge> edgesList, DisjointSets disjointSets){
        Collections.sort(edgesList);
        List<Edge> minimumSpanningTree = new ArrayList<>();

        edgesList.forEach((edge)->{

                int source = edge.getSource()-1; //index for parent array and rank array
                int destination = edge.getDestination()-1; //index
                if(disjointSets.find(source)!=disjointSets.find(destination)){
                    disjointSets.union(source,destination);
                    minimumSpanningTree.add(edge);


                }else{
//                    System.out.println("This performs a cycle");
                }
        });



        return minimumSpanningTree;

    }

    private void printEdgesWithWeights(List<Edge> edgesList){

        System.out.println(String.format("source\t|\tdestination\t|\tweight"));
        System.out.println("-----------------------------------");

        edgesList.forEach((edge -> {
            System.out.println(String.format("%d\t\t|\t\t%d\t\t|\t%d", edge.getSource(), edge.getDestination(), edge.getWeight()));
        }));
        System.out.println("-----------------------------------");
    }

    public List<Integer> getNodes() {
        return this.nodes;
    }

    public List<Integer> getSteinerNodes() {
        return steinerNodes;
    }

    public List<Edge> getMinimumSpanningTree() {
        return minimumSpanningTree;
    }

    public DisjointSets getDisjointSets() {
        return disjointSets;
    }

    public void setVerticesNum(int verticesNum) {
        this.verticesNum = verticesNum;
    }

    public void setDisjointSets(DisjointSets disjointSets) {
        this.disjointSets = disjointSets;
    }

    public void setNodes(List<Integer> nodes){
        this.nodes = nodes;
    }

    public int getSteinerNodesNum() {
        return steinerNodesNum;
    }

    public void setSteinerNodesNum(int steinerNodesNum) {
        this.steinerNodesNum = steinerNodesNum;
    }

    public void setSteinerNodes(List<Integer> steinerNodes) {
        this.steinerNodes = steinerNodes;
    }

    public List<List<Node>> getAdj() {
//        System.out.println(neighbourNodes.toString());
        return adj;
    }

    public void printAdj(){
        for (int i=0; i<adj.size(); i++){
            int node = i+1;
            System.out.println("for node "+node+" the adj are:");
//            int size = adj.get(i).size();
//            for ( int j=0; j < size; j++){
                System.out.println(adj.get(i).toString());
//            }
        }
    }
}
