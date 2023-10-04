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

        System.out.println("****** has Path problem (Directed Graph) *******");


        Map<String,List<String>> adjList2 = Map.of(
                "f",List.of("g","i"),
                "g",List.of("h"),
                "h",List.of(),
                "i",List.of("g","k"),
                "j",List.of("i"),
                "k",List.of()
        );

        System.out.println("iterative dfs approach: "+graph.hasPathExistsDFS(adjList2,"f","k"));
        System.out.println("recursive dfs approach: "+graph.hasPathExistsRecursiveDFS(adjList2,"f","j"));
        System.out.println("iterative bfs approach: "+graph.hasPathExistsBFS(adjList2,"f","h"));


        System.out.println("****** has Path problem (Undirected Graph) *******");

        List<List<String>> edges = List.of(
                List.of("i", "j"),
                List.of("k", "i"),
                List.of("m", "k"),
                List.of("k", "l"),
                List.of("o", "n")
        );

        System.out.println("Has path between nodes: "+Graph.undirectedPath(edges,"i","o"));

        Map<Integer, List<Integer>> graph5 = Map.of(
                0, List.of(8, 1, 5),
                1, List.of(0),
                5, List.of(0, 8),
                8, List.of(0, 5),
                2, List.of(3, 4),
                3, List.of(2, 4),
                4, List.of(3, 2)
        );

        Map<Integer, List<Integer>> graph6 = Map.of(
                3, List.of(),
                4, List.of(6),
                6, List.of(4, 5, 7, 8),
                8, List.of(6),
                7, List.of(6),
                5, List.of(6),
                1, List.of(2),
                2, List.of(1)
        );
        System.out.println("****** Connected Components Problem (Undirected Graph) ****");

        System.out.println("connected components count: "+Graph.connectedComponentsCount(graph5));
        System.out.println("connected components count: "+Graph.connectedComponentsCount(graph6));

        Map<Integer, List<Integer>> graph7 = Map.of(
                1, List.of(2),
                2, List.of(1, 8),
                6, List.of(7),
                9, List.of(8),
                7, List.of(6, 8),
                8, List.of(9, 7, 2)
        );
        System.out.println("****** Largest Components Problem (Undirected Graph) ****");

        System.out.println("largest components size: "+Graph.largestComponentCount(graph5));
        System.out.println("largest components size: "+Graph.largestComponentCount(graph6));
        System.out.println("largest components size: "+Graph.largestComponentCount(graph7));
    }
}
