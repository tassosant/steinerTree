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
        makeNodesRelationship(this.nodes);

//        makeNodesRelationship(this.nodes);
        printTree();
        //cutLeaves(this.root);

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
        System.out.println("Selected root:"+(root+1));
        makeNodeRelationship(nodes, nodes.get(root),new HashSet<>(),root);


    }

    private void makeNodeRelationship(List<Node>nodes, Node parentNode, Set<Integer> visited, int root){
        if (visited.contains(parentNode.getV())) return;
        visited.add(parentNode.getV());

////        parentNode.setChildren(new ArrayList<>());
////        parentNode.setParent(null);
//        if(parentNode==nodes.get(root)){
//            parentNode.setParent(null);
//            parentNode.setChildren(parentNode.getConnectedNodes());
//            //makeNodeRelationship(nodes,parentNode.getChildren().get(0),visited,root);
//        }
//        List<Node> childrenNodes = parentNode.getChildren();
//            childrenNodes.forEach(childNode -> {
//                Node node = new Node();
//                node.setV(childNode.getV());
//
//                if (childNode == nodes.get(root)) {
////                    childNode.setParent(null);
//                    node.setParent(null);
//                }
//                if (childNode.getParent() == null && childNode != nodes.get(root)) {
////                    childNode.addParent(parentNode);
////                    parentNode.addChildNode(childNode);
//                    node.addParent(parentNode);
//                    node.addChildNode(childNode);
//
//
//                }
//                if (parentNode.getParent() != null) {
//                    if (parentNode.getParent().getV() != childNode.getV()) {
////                        parentNode.addChildNode(childNode);
//                        node.addChildNode(childNode);
//                    }
//                }
//                node.setConnectedNodes(childNode.getConnectedNodes());
//                makeNodeRelationship(nodes, node, visited, root);
//                childNode = node;
//            });
//
//
////        parentNode.getChildren().forEach((child)->{
////        });
        List<Node> childrenNodes;
        if(parentNode.getV()==nodes.get(root).getV()) {
            childrenNodes = new ArrayList<>(parentNode.getConnectedNodes()); // Copy to a temporary list
            parentNode.setParent(null);
            parentNode.setRoot(true);
            //parentNode.setChildren(childrenNodes);
        }else {
            if(parentNode.getChildren().size()>0) {
                childrenNodes = new ArrayList<>(parentNode.getChildren()); // Copy to a temporary list
            }else {
                childrenNodes = new ArrayList<>(parentNode.getConnectedNodes());
            }
        }
        childrenNodes.forEach((childNode)->{
            if(childNode!=null){

            }
                //check if is root
                if(parentNode.isRoot()){
                    parentNode.setParent(null);
                    childNode.setParent(parentNode);
                    parentNode.addChildNode(childNode);

                }else {
                    //if not root
                    //if parent not null
                    if (parentNode.getParent() != null) {

                        //if parent of current node is not the same as this child
                        if (parentNode.getV() != childNode.getV() && parentNode.getParent().getV()!=childNode.getV())

                            //check if parent of child is not this child
                            if(childNode.getParent()!=childNode ) {
                                childNode.setParent(parentNode);
                                parentNode.addChildNode(childNode);
                            }
                    //if parent null
                    } else {

                            childNode.setParent(parentNode);
                            parentNode.addChildNode(childNode);

                    }
                }

                makeNodeRelationship(nodes,childNode,visited,root);
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
        System.out.println("--------------------");
        this.nodes.forEach((System.out::println));
        System.out.println("#######################");
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

    public void cutLeaves(int root){
        cutLeave(this.nodes.get(root));
    }

    private void cutLeave(Node node){
        if(node==null){
            return;
        }
        ArrayList<Integer> steinerNodes= new ArrayList<>(9);
        steinerNodes.add(1);
        steinerNodes.add(2);
        steinerNodes.add(3);
        steinerNodes.add(4);
        steinerNodes.add(0);
        steinerNodes.add(0);
        steinerNodes.add(0);
        steinerNodes.add(0);
        steinerNodes.add(0);

        if(node.getChildren()==null || node.getChildren().size()==0){
            if(node.getV()!=steinerNodes.get(node.getV()-1)){
//                List<Node> nodeSParentChildren = node.getParent().getChildren();
//                nodeSParentChildren.removeIf(tempNode->tempNode.getV()==node.getV());
//                node.getParent().setChildren(nodeSParentChildren);
                Node parent = node.getParent();
                node.setParent(null);
                if(!node.isRoot()) {
                    cutLeave(parent);
                }
            }
        }else{
            List<Node> children = node.getChildren();
            List<Node> childrenTemp = new ArrayList<>(children);
            childrenTemp.forEach((child)->{
                if(child.getParent()==null){
                    children.removeIf((temp)->temp.getV() == child.getV());
                }else{
                    cutLeave(child);
                }
            });
        }

    }
}
