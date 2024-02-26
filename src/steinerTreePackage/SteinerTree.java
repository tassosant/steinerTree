package steinerTreePackage;

import java.util.*;
import java.util.stream.IntStream;

public class SteinerTree {
    private int root;

    private List<Integer> nodesInt;

    private List<Integer> steinerNodesInt;

    private List<Edge> MST;

    private List<Node> nodes;
    private List<Node> steinerNodes;

    public SteinerTree(
            int root,
            List<Integer> nodes,
            List<Integer> steinerNodes,
            List<Edge> MST
    ) {
        this.root = root-1;
        this.nodesInt = nodes;
        this.steinerNodesInt = steinerNodes;
        this.MST = MST;
        initProperties();
        parseIntNodesToNodesList(this.nodesInt,this.nodes);
        parseIntNodesToNodesList(this.steinerNodesInt,this.steinerNodes);
        mapNodes(this.MST,this.nodes);
//        makeNodesRelationship(this.nodes);
//        makeNodesRelationship(this.nodes);
//        printTree();
    }

    public SteinerTree() {

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
            nodes.get(source).addConnection(nodes.get(destination));
            nodes.get(destination).addConnection(nodes.get(source));
        }));


    }

    private void makeNodesRelationship(List<Node> nodes){
        int root = this.root; //first nodeIndex, pick randomly the index at the future
        System.out.println("Selected root:"+(root));
        makeNodeRelationship(nodes, nodes.get(root),new HashSet<>(),root);


    }

    private void makeNodeRelationship(List<Node>nodes, Node currentNode, Set<Integer> visited, int root){
        if (visited.contains(currentNode.getV())) return;
        visited.add(currentNode.getV());

////        currentNode.setChildren(new ArrayList<>());
////        currentNode.setParent(null);
//        if(currentNode==nodes.get(root)){
//            currentNode.setParent(null);
//            currentNode.setChildren(currentNode.getConnectedNodes());
//            //makeNodeRelationship(nodes,currentNode.getChildren().get(0),visited,root);
//        }
//        List<Node> childrenNodes = currentNode.getChildren();
//            childrenNodes.forEach(childNode -> {
//                Node node = new Node();
//                node.setV(childNode.getV());
//
//                if (childNode == nodes.get(root)) {
////                    childNode.setParent(null);
//                    node.setParent(null);
//                }
//                if (childNode.getParent() == null && childNode != nodes.get(root)) {
////                    childNode.addParent(currentNode);
////                    currentNode.addChildNode(childNode);
//                    node.addParent(currentNode);
//                    node.addChildNode(childNode);
//
//
//                }
//                if (currentNode.getParent() != null) {
//                    if (currentNode.getParent().getV() != childNode.getV()) {
////                        currentNode.addChildNode(childNode);
//                        node.addChildNode(childNode);
//                    }
//                }
//                node.setConnectedNodes(childNode.getConnectedNodes());
//                makeNodeRelationship(nodes, node, visited, root);
//                childNode = node;
//            });
//
//
////        currentNode.getChildren().forEach((child)->{
////        });
        List<Node> childrenNodes;
        if(currentNode.getV()==nodes.get(root).getV()) {
            childrenNodes = new ArrayList<>(currentNode.getConnectedNodes()); // Copy to a temporary list
        }else {
            currentNode.setChildren(currentNode.getConnectedNodes());
            childrenNodes = new ArrayList<>(currentNode.getChildren()); // Copy to a temporary list
        }
        childrenNodes.forEach((childNode)->{
            if (!visited.contains(childNode.getV())) { // Check if the child node has not been visited
                if (childNode.getV() != nodes.get(root).getV()) {
                    if(childNode.getParent()!=null) {
                        if (childNode.getParent().getV() != childNode.getV()) {
                            childNode.setParent(currentNode); // Set parent only if it's not the root
                            currentNode.addChildNode(childNode); // Safely add child nodes
                        }
                    }else{
                        childNode.setParent(currentNode); // Set parent only if it's not the root
                        currentNode.addChildNode(childNode); // Safely add child nodes
                    }
                }
                makeNodeRelationship(nodes, childNode, visited, root); // Recurse for child node
            }
            }
        );




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
