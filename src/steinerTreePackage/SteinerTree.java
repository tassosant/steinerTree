package steinerTreePackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            if(source==root){
                nodes.get(source).addChild(destination);
            }
            if(destination==root){
                nodes.get(destination).addChild(source);
            }
        }));
    }


    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }
}
