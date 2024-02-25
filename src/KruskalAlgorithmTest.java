import steinerTreePackage.DisjointSets;
import steinerTreePackage.Edge;
import steinerTreePackage.Graph;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;


// Ctrl+Shift+T to generate test class
public class KruskalAlgorithmTest {


//
//@Test
//public void applyKruskalTest1(){
//    Edge ab = new Edge(0, 1, 5);
//    Edge ac = new Edge(0, 2, 2);
//    Edge bd = new Edge(1, 3, 20);
//    Edge cd = new Edge(2, 3, 10);
//    List<Edge> graph1 = new ArrayList<>();
//    graph1.add(ab);
//    graph1.add(ac);
//    graph1.add(bd);
//    graph1.add(cd);
//    KruskalAlgorithm kat = new KruskalAlgorithm();
//    List<Edge> actual = kat.applyKruskal2(4, graph1);
//    List<Edge> expected =new ArrayList<>();
//    expected.add(ac);
//    expected.add(ab);
//    expected.add(cd);
//
//    Assertions.assertEquals( expected, actual);
//}
//
//    @Test
//    public void applyKruskalTest2(){
//        Edge v0v1 = new Edge(0, 1, 8);
//        Edge v0v2 = new Edge(0, 2, 5);
//        Edge v1v3 = new Edge(1, 3, 11);
//        Edge v1v2 = new Edge(1, 2, 9);
//        Edge v2v3 = new Edge(2, 3, 15);
//        Edge v2v4 = new Edge(2, 4, 10);
//        Edge v3v4 = new Edge(3, 4, 7);
//        List<Edge> graph1 = new ArrayList<>();
//        graph1.add(v0v1);
//        graph1.add(v0v2);
//        graph1.add(v1v3);
//        graph1.add(v1v2);
//        graph1.add(v2v3);
//        graph1.add(v2v4);
//        graph1.add(v3v4);
//        KruskalAlgorithm kat = new KruskalAlgorithm();
//        List<Edge> actual = kat.applyKruskal(5, graph1);
//        List<Edge> expected =new ArrayList<>();
//        expected.add(v0v2);
//        expected.add(v3v4);
//        expected.add(v0v1);
//        expected.add(v2v4);
//
//        Assertions.assertEquals( expected, actual);
//    }
//
//    @Test
//    public void formsCircleTest1(){
//        Edge ab = new Edge(0, 1, 1);
//        Edge ac = new Edge(0, 2, 2);
//        Edge bc = new Edge(1, 2, 2);
//        List<Edge> graph1 = new ArrayList<>();
//        graph1.add(ab);
//        graph1.add(ac);
//
//        KruskalAlgorithm kat = new KruskalAlgorithm();
//        boolean actual = kat.formsCircle(graph1, bc);
//        boolean expected = true;
//        Assertions.assertEquals( expected, actual);
//    }
//
//    @Test
//    public void formsCircleTest2(){
//        Edge ab = new Edge(0, 1, 5);
//        Edge ac = new Edge(0, 2, 2);
//        Edge bd = new Edge(1, 3, 20);
//        Edge cd = new Edge(2, 3, 10);
//        List<Edge> graph1 = new ArrayList<>();
//        graph1.add(ab);
//        graph1.add(ac);
////        graph1.add(bd);
//        graph1.add(cd);
//
//        KruskalAlgorithm kat = new KruskalAlgorithm();
//        boolean actual = kat.formsCircle(graph1, bd);
//        boolean expected = true;
//        Assertions.assertEquals( expected, actual);
//    }
    @Test
    public void applyKruskalTestTasos(){
        Edge v0v1 = new Edge(0, 1, 8);
        Edge v0v2 = new Edge(0, 2, 5);
        Edge v1v3 = new Edge(1, 3, 11);
        Edge v1v2 = new Edge(1, 2, 9);
        Edge v2v3 = new Edge(2, 3, 15);
        Edge v2v4 = new Edge(2, 4, 10);
        Edge v3v4 = new Edge(3, 4, 7);
        List<Edge> graph1 = new ArrayList<>();
        graph1.add(v0v1);
        graph1.add(v0v2);
        graph1.add(v1v3);
        graph1.add(v1v2);
        graph1.add(v2v3);
        graph1.add(v2v4);
        graph1.add(v3v4);
        graph1.forEach((edge -> {
            edge.setSource(edge.getSource()+1);
            edge.setDestination(edge.getDestination()+1);
        }));
        Collections.sort(graph1);
        Graph graph = new Graph();
        List<Edge> actual =  graph.applyKruskal(graph1,new DisjointSets(5));
        List<Edge> expected =new ArrayList<>();
        expected.add(v0v2);
        expected.add(v3v4);
        expected.add(v0v1);
        expected.add(v2v4);

        Assertions.assertEquals( expected, actual);
    }
}