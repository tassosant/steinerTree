package steinerTreePackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class SteinerTree {
    private int root;

    private List<Integer> nodesInt;

    private List<Integer> steinerNodesInt;

    private List<Edge> MST;

    private List<Node> nodes;
    private List<Node> steinerNodes;

    public SteinerTree(int root,List<Integer> nodes,List<Integer> steinerNodes,List<Edge> MST) {
        this.root = root;
        this.nodesInt = nodes;
        this.steinerNodesInt = steinerNodes;
        this.MST = MST;
        initProperties();
        parseIntNodesToNodesList(this.nodesInt,this.nodes);
        parseIntNodesToNodesList(this.steinerNodesInt,this.steinerNodes);
        mapNodes(this.MST,this.nodes);
        makeNodesRelationship(this.nodes);
//        printTree();
    }

    private void initProperties(){
        this.steinerNodes = new ArrayList<>();
        this.nodes = new ArrayList<>();
        Collections.sort(this.nodesInt);
        Collections.sort(this.steinerNodesInt);
    }

    private void parseIntNodesToNodesList(List<Integer> nodesInt,List<Node> nodes){
        for(int node:nodesInt){
            nodes.add(new Node(node));
        }
    }

    //nodes list is sorted, so e.g. when we want the node 1(first node), list is get(0)
    private void mapNodes(List<Edge> edgeList,List<Node> nodes){
        //find root
        edgeList.forEach((edge -> {
            int source = edge.getSource()-1;
            int destination = edge.getDestination()-1;
//            if(source==root){
//                nodes.get(source).addChild(destination+1);
//                nodes.get(destination).addParent(source+1);
//                return;
//            }
//            if(destination==root){
//                nodes.get(destination).addChild(source+1);
//                nodes.get(source).addParent(destination+1);
//                return;
//            }
//            nodes.get(destination).addParent(source+1);
//            nodes.get(source).addChild(destination+1);
            nodes.get(source).addConnectionWithVerticeNumber(destination+1);
            nodes.get(destination).addConnectionWithVerticeNumber(source+1);
        }));


    }

    private void makeNodesRelationship(List<Node> nodes){
        int root = 0; //first nodeIndex, pick randomly the index at the future
        System.out.println("Selected root:"+(root+1));
        makeNodeRelationship(nodes,nodes.get(root));


    }

    private void makeNodeRelationship(List<Node> nodes,Node currentNode){
        List<Node> connectedNodes = currentNode.getConnectedNodes();
        connectedNodes.forEach(connectedNode -> {
            connectedNode.addParent(currentNode);
            currentNode.addChildNode(connectedNode);
        });
        currentNode.getChildren().forEach((child)->{
            makeNodeRelationship(nodes,child);
        });



    }


    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public void printTree(){
        this.nodes.forEach((System.out::println));
    }

    public List<Edge> getMST() {
        return MST;
    }

    public void setMST(List<Edge> MST) {
        this.MST = MST;
    }

    public List<Node> getNodes() {
        return nodes;
    }





    public void printEdgesWithWeights(List<Edge> edgesList){

        System.out.println(String.format("source\t|\tdestination\t|\tweight"));
        System.out.println("-----------------------------------");

        edgesList.forEach((edge -> {
            System.out.println(String.format("%d\t\t|\t\t%d\t\t|\t%d", edge.getSource(), edge.getDestination(), edge.getWeight()));
        }));
        System.out.println("-----------------------------------");
    }
}
