import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import steinerTreePackage.*;

import java.util.*;

public class SteinerTreeTest {
    @Test
    public void testSteinerTree() {
        Edge v0v1 = new Edge(0, 1, 8);
        Edge v0v2 = new Edge(0, 2, 5);
        Edge v1v3 = new Edge(1, 3, 11);
        Edge v1v2 = new Edge(1, 2, 9);
        Edge v2v3 = new Edge(2, 3, 15);
        Edge v2v4 = new Edge(2, 4, 10);
        Edge v3v4 = new Edge(3, 4, 7);
        List<Integer> nodes = new ArrayList<>();
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);
        nodes.add(5);

        List<Edge> graph1 = new ArrayList<>();
        graph1.add(v0v1);
        graph1.add(v0v2);
        graph1.add(v1v3);
        graph1.add(v1v2);
        graph1.add(v2v3);
        graph1.add(v2v4);
        graph1.add(v3v4);
        graph1.forEach((edge -> {
            edge.setSource(edge.getSource() + 1);
            edge.setDestination(edge.getDestination() + 1);
        }));
        Collections.sort(graph1);

        Graph graph = new Graph();
        graph.setVerticesNum(5);
        graph.setNodes(nodes);
        graph.setSteinerNodes(new ArrayList<>());
        graph.setDisjointSets(new DisjointSets(5));
        graph.pickRandomlySteinerNodes(1,5);
        List<Edge> MST = graph.applyKruskal(graph1, graph.getDisjointSets());
        SteinerTree steinerTree = new SteinerTree(graph.getDisjointSets().getParent()[0],graph.getNodes(),graph.getSteinerNodes(),MST);


        makeNodesRelationship(steinerTree.getNodes());
        steinerTree.printTree();
//
//        List<Edge> expected = new ArrayList<>();
//        expected.add(v0v2);
//        expected.add(v3v4);
//        expected.add(v0v1);
//        expected.add(v2v4);


    }

    private void makeNodesRelationship(List<Node> nodes){
        int root = 0; //first nodeIndex, pick randomly the index at the future
        System.out.println("Selected root:"+(root+1));
        makeNodeRelationship(nodes, nodes.get(root),new HashSet<>(),root);


    }

    private void makeNodeRelationship(List<Node>nodes,Node currentNode, Set<Integer> visited, int root){
        if (visited.contains(currentNode.getV())) return;
        visited.add(currentNode.getV());
        List<Node> connectedNodes = currentNode.getConnectedNodes();
//        currentNode.setChildren(new ArrayList<>());
//        currentNode.setParent(null);
        connectedNodes.forEach(connectedNode -> {
            if(connectedNode==nodes.get(root)){
                connectedNode.setParent(null);
            }
            if(connectedNode.getParent()==null) {
                connectedNode.addParent(currentNode);
            }
            if(currentNode.getParent()!=connectedNode) {
                currentNode.addChildNode(connectedNode);
            }
            makeNodeRelationship(nodes,connectedNode,visited,root);
        });
//        currentNode.getChildren().forEach((child)->{
//        });



    }



    @Test
    public void testChildren(){
        List<Edge> graph = new ArrayList<>();
        graph.add(new Edge(1,9, 1));
        graph.add(new Edge(9,8, 0));
        graph.add(new Edge(9,5, 1));
        graph.add(new Edge(8,7, 1));
        graph.add(new Edge(5,6, 1));
        graph.add(new Edge(6,2, 1));
        graph.add(new Edge(5,3, 2));
        graph.add(new Edge(5,4, 2));
        List<Integer> nodes = new ArrayList<>();
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);
        nodes.add(5);
        nodes.add(6);
        nodes.add(7);
        nodes.add(8);
        nodes.add(9);
        Graph graph1 = new Graph();
        graph1.setVerticesNum(nodes.size());
        graph1.setNodes(nodes);
        graph1.setSteinerNodes(new ArrayList<>());
        graph1.setDisjointSets(new DisjointSets(nodes.size()));
        graph1.pickRandomlySteinerNodes(1,nodes.size());
        SteinerTree steinerTree = new SteinerTree(1,nodes,graph1.getSteinerNodes(),graph);
        steinerTree.printTree();

    }


}
