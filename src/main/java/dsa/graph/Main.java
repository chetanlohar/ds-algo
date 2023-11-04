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


        System.out.println("****** Shortest path (min no of edges between 2 nodes) (Undirected Graph) ****");

        List<List<String>> edges1 = List.of(
                List.of("w", "x"),
                List.of("x", "y"),
                List.of("z", "y"),
                List.of("z", "v"),
                List.of("w", "v")
        );

        List<List<String>> edges2 = List.of(
                List.of("a", "c"),
                List.of("a", "b"),
                List.of("c", "b"),
                List.of("c", "d"),
                List.of("b", "d"),
                List.of("e", "d"),
                List.of("g", "f")
        );

        List<List<String>> edges3 = List.of(
                List.of("m", "n"),
                List.of("n", "o"),
                List.of("o", "p"),
                List.of("p", "q"),
                List.of("t", "o"),
                List.of("r", "q"),
                List.of("r", "s")
        );

        System.out.println("shortest path: "+Graph.shortestPath(edges1,"w","z")); // expected -> 2
        System.out.println("shortest path: "+Graph.shortestPath(edges1,"y","x")); // expected -> 1
        System.out.println("shortest path: "+Graph.shortestPath(edges2,"e","c")); // expected -> 2
        System.out.println("shortest path: "+Graph.shortestPath(edges2,"b","g")); // expected -> -1
        System.out.println("shortest path: "+Graph.shortestPath(edges3,"m","s")); // expected -> 6

        System.out.println("\n****** Total Island Count (Graph Grid) ****");

        List<List<String>> grid1 = List.of(
                List.of("W", "L", "W", "W", "W"),
                List.of("W", "L", "W", "W", "W"),
                List.of("W", "W", "W", "L", "W"),
                List.of("W", "W", "L", "L", "W"),
                List.of("L", "W", "W", "L", "L"),
                List.of("L", "L", "W", "W", "W")
        );

        List<List<String>> grid2 = List.of(
                List.of("L", "W", "W", "L", "W"),
                List.of("L", "W", "W", "L", "L"),
                List.of("W", "L", "W", "L", "W"),
                List.of("W", "W", "W", "W", "W"),
                List.of("W", "W", "L", "L", "L")
        );

        List<List<String>> grid3 = List.of(
                List.of("L", "L", "L"),
                List.of("L", "L", "L"),
                List.of("L", "L", "L")
        );

        List<List<String>> grid4 = List.of(
                List.of("W", "W"),
                List.of("W", "W"),
                List.of("W", "W")
        );

        System.out.println("Island 1 counts: "+Graph.countIslands(grid1)); // expected -> 3
        System.out.println("Island 2 counts: "+Graph.countIslands(grid2)); // expected -> 4
        System.out.println("Island 3 counts: "+Graph.countIslands(grid3)); // expected -> 1
        System.out.println("Island 4 counts: "+Graph.countIslands(grid4)); // expected -> 0


        // Minimum Island size
        System.out.println("\n****** Minimum Island Size (Graph Grid) ****");

        System.out.println("Minimum island size: "+Graph.getMinimumIslandSize(grid1)); // // expected -> 2
        System.out.println("Minimum island size: "+Graph.getMinimumIslandSize(grid2)); // // expected -> 1
        System.out.println("Minimum island size: "+Graph.getMinimumIslandSize(grid3)); // // expected -> 9
        System.out.println("Minimum island size: "+Graph.getMinimumIslandSize(grid4)); // // expected -> 0

        // Rotten Oranges: https://leetcode.com/problems/rotting-oranges/description/

        int [][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println("All Oranges Rotten time: "+Graph.rottenOranges(grid));

        List<List<Integer>> graph8 = List.of(
                List.of(),
                List.of(2),
                List.of(3),
                List.of(4,7),
                List.of(5),
                List.of(6),
                List.of(),
                List.of(5),
                List.of(9),
                List.of(10),
                List.of(8)
        );

        // G-19 Detect cycle in a directed graph using DFS
        // Link: https://www.youtube.com/watch?v=9twcmtQj4DU&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=12&ab_channel=takeUforward
        Boolean isCyclePresent = Graph.detectCycle(graph8);

        System.out.println("isCyclePresent: "+ isCyclePresent);



    }
}
