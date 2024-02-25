package kruskalByTasos;

import java.util.*;

public class Graph {

    private List<Edge2> graphEdges;
    private List<Integer> nodes;
    private List<Integer> steinerNodes;

    private List<Edge2> minimumSpanningTree;

    private DisjointSets disjointSets;

    private int steinerNodesNum;

    private int verticesNum;

    public Graph() {
        this.verticesNum = 12;
        initProperties();
        generateGraph();
        applyKruskal();
    }

    private void initProperties(){
        this.steinerNodesNum = 0;
        this.graphEdges = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.steinerNodes = new ArrayList<>();
        this.minimumSpanningTree = new ArrayList<>();
        this.disjointSets = new DisjointSets(this.verticesNum);
    }

    private void generateGraph(){
        //firstly generate nodes
        generateNodes(this.verticesNum);
        pickRandomlySteinerNodes(1,this.verticesNum,0);
        printList(this.steinerNodes,"Steiner nodes before sort");
        generateEdges(0,0);
//        generateRandomEdges(1,(this.verticesNum*(this.verticesNum-1))/2,1);
//        Collections.sort(this.steinerNodes, Comparator.comparingInt(Node::getV));
        Collections.sort(this.graphEdges);
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
        Random random = new Random();
        int randomInt;
        do {
            randomInt = random.nextInt(max-min+1)+min;
        }while (randomInt==exception);

        return randomInt;

    }

    private int generateRandomInRange(int min,int max){
        Random random = new Random();
        return random.nextInt(max-min+1)+min;
    }



    //nodes steiner
    //100   70
    //6     x
    //x=percentage*6/100

    private void pickRandomlySteinerNodes(int min,int max, int exceptNode){
        int node = generateRandomInRangeExceptForOne(min,max,exceptNode);
        if(this.steinerNodes.size()+1<=(70*this.nodes.size())/100) {
            this.steinerNodes.add(this.nodes.get(node-1));
        }
        if(node!=min){
            pickRandomlySteinerNodes(min,node-1,node);
        }
        if(node!=max){
            pickRandomlySteinerNodes(node + 1, max, node);
        }

    }


    private void generateEdges(int min, int max){
        int source = 1;
        while (source<=this.nodes.size()-1){
            int destination = source+1;
            while (destination<=this.nodes.size()) {
                Edge2 edge2 = generateEdge(source, destination);
                this.graphEdges.add(edge2);
                destination++;
            }
            source++;
        }
        printList(this.graphEdges);
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
            Edge2 edge2;
            int destination = generateRandomInRangeExceptForOne(min, this.nodes.size(), source);
            while (destinations.contains(destination) || destination<source){
                destination = generateRandomInRangeExceptForOne(min, this.nodes.size(), source);
                System.out.println("I am in loop");
            }
            edge2 = generateEdge(source, destination);
            this.graphEdges.add(edge2);
            destinations.add(destination);
            edgesCreated++;
        }
        printList(this.graphEdges);
        generateRandomEdges(min,max,source+1);
        //check if source and destination have not an edge
        //check if source or destination are not exceeding the number of edges
    }

    private Edge2 generateEdge(int source, int destination){
        Edge2 edge = new Edge2(source,destination);
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

    private void printList(List<Edge2> list){

        list.forEach((localNode)->{
            System.out.print(String.format("(%d,%d)\t",localNode.getSource(),localNode.getDestination()));
        });
        System.out.println();
    }



    private void checkForSameEdge(Node source,Node destination){

    }

    private void applyKruskal(){
        this.graphEdges.forEach((edge)->{
                int source = edge.getSource()-1;
                int destination = edge.getDestination()-1;
                if(this.disjointSets.find(source)!=this.disjointSets.find(destination)){
                    this.disjointSets.union(source,destination);
                    this.minimumSpanningTree.add(edge);
                    System.out.println("Edge added");
                }else{
                    System.out.println("This performs a cycle");
                }
        });
    }




}
