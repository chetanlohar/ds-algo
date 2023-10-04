package dsa.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        graphOperationsOnNodeClass();
        System.out.println("\n------------------------------------------------\n");
        graphOperationsOnString();
    }

    public static void graphOperationsOnNodeClass(){
        Graph graph = new Graph().getSimpleDummyGraph1();
        System.out.println("**** Iterative Traversals below ****");
        graph.iterativeDFS(graph.getNodes().get(0));
        graph.iterativeBFS(graph.getNodes().get(0));
        System.out.println("\n**** Recursive Traversals below ****");

        StringBuilder result = new StringBuilder("dfs on Node class: ");
        graph.recursiveDFS(graph.getNodes().get(0),result);
        System.out.println(result);
    }

    public static void graphOperationsOnString(){
        // For quick understandings lets use String as node names
        Map<String,List<String>> adjList = new HashMap<>();
        adjList.put("a",List.of("b","c"));
        adjList.put("b",List.of("d"));
        adjList.put("c",List.of("e"));
        adjList.put("d",List.of("f"));
        adjList.put("e",List.of());
        adjList.put("f",List.of());

        Graph graph = new Graph();

        graph.iterativeDFS(adjList,"a");
        graph.iterativeBFS(adjList,"a");

    }

}
