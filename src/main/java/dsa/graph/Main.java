package dsa.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph().getSimpleDummyGraph1();

        System.out.println("**** Iterative Traversals below ****");

        graph.iterativeDFS(graph.getNodes().get(0));
        graph.iterativeBFS(graph.getNodes().get(0));

        System.out.println("\n**** Recursive Traversals below ****");
        StringBuilder result = new StringBuilder("dfs on Node class: ");
        graph.recursiveDFS(graph.getNodes().get(0),result);
        System.out.println(result);
        System.out.println("\n------------------------------------------------\n");
        // For quick understandings lets use String as node names
        Map<String,List<String>> adjList = new HashMap<>();
        adjList.put("a",List.of("b","c"));
        adjList.put("b",List.of("d"));
        adjList.put("c",List.of("e"));
        adjList.put("d",List.of("f"));
        adjList.put("e",List.of());
        adjList.put("f",List.of());

        graph.iterativeDFS(adjList,"a");
        graph.iterativeBFS(adjList,"a");

        System.out.println("****** Has Path exists problem... below *******");


        Map<String,List<String>> adjList2 = new HashMap<>();
        adjList2.put("f",List.of("g","i"));
        adjList2.put("g",List.of("h"));
        adjList2.put("h",List.of());
        adjList2.put("i",List.of("g","k"));
        adjList2.put("j",List.of("i"));
        adjList2.put("k",List.of());

        System.out.println("iterative dfs approach: "+graph.hasPathExistsDFS(adjList2,"f","k"));
        System.out.println("recursive dfs approach: "+graph.hasPathExistsRecursiveDFS(adjList2,"f","j"));
        System.out.println("iterative bfs approach: "+graph.hasPathExistsBFS(adjList2,"f","h"));
    }
}
