package dsa.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Graph {
    private List<Node> nodes;

    // Function to detect cycle in a provided graph (graph as a adjList)
    public static Boolean detectCycle(List<List<Integer>> graph) {
        int [] visited = new int[graph.size()+1];
        int [] pathVisited = new int[graph.size()+1];
        Arrays.fill(visited,0);
        Arrays.fill(pathVisited,0);
        for(int i=1;i<graph.size();i++){
            if(visited[i] == 0) {
                if(dfs(graph, i,visited,pathVisited)) return true;
            }
        }
        return false;
    }

    // Function to traverse graph in dfs manner
    private static boolean dfs(List<List<Integer>> graph, int source, int[] visited, int[] pathVisited) {
        visited[source] = 1;
        pathVisited[source] = 1;
        // traverse neighbor nodes of the provided source node
        for(int neighbor: graph.get(source)){
            // if neighbor node is not yet visited then explore traverse it
            if(visited[neighbor] == 0) {
                if(dfs(graph,neighbor,visited,pathVisited)) return true;
            }
            // else check whether is on a same path
            else if(pathVisited[neighbor] == 1) {
                return true;
            }
        }
        pathVisited[source] = 0;
        return false;
    }


    //  Get the dummy graph to learn graph traversals
    public Graph getSimpleDummyGraph1(){
        // Create all nodes/vertices
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        // Create adj List and store nodes
        a.setNeighbors(List.of(b,c));
        b.setNeighbors(List.of(d));
        c.setNeighbors(List.of(e));
        d.setNeighbors(List.of(f));
        e.setNeighbors(List.of());
        f.setNeighbors(List.of());
        return new Graph(List.of(a,b,c,d,e,f));
    }

    //Depth first search using Node class object as node value
    public void iterativeDFS(Node source) {
        StringBuilder dfsOrder = new StringBuilder("dfs on Node class: ");
        Stack<Node> stack = new Stack<>();
        // push first source node
        stack.push(source);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            dfsOrder.append(current.getName()).append(" ");
            List<Node> neighbors = current.getNeighbors();
            // Push all neighbors of the current node to the stack
            for(Node neighbor: neighbors){
                stack.push(neighbor);
            }
        }
        System.out.println(dfsOrder);
    }


    public void recursiveDFS(Node source, StringBuilder result){
        result.append(source.getName()).append(" ");
        if(!source.getNeighbors().isEmpty()) {
            for(Node neighbor: source.getNeighbors()){
                recursiveDFS(neighbor,result);
            }
        }
    }

    public void iterativeBFS(Node source) {
        StringBuilder bfsOrder = new StringBuilder("bfs on Node class: ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            bfsOrder.append(currentNode.getName()).append(" ");
            // Add all the neighbours to the queue
            queue.addAll(currentNode.getNeighbors());
        }
        System.out.println(bfsOrder);
    }

    //Depth first search using String as node value
    public void iterativeDFS(Map<String, List<String>> adjList, String source){
        StringBuilder dfsOrder = new StringBuilder("dfs on string    : ");
        Stack<String> stack = new Stack<>();
        stack.push(source);

        while(!stack.isEmpty()){
            String currentNode = stack.pop();
            dfsOrder.append(currentNode).append(" ");
            // Push all neighbors of the current node to the stack
            for(String neighbor: adjList.get(currentNode)){
                stack.push(neighbor);
            }
        }
        System.out.println(dfsOrder);
    }

    // Breadth first search using String as a node value
    public void iterativeBFS(Map<String, List<String>> adjList, String source){
        StringBuilder bfsOrder = new StringBuilder("bfs on string    : ");
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()) {
            String currentNode = queue.poll();
            bfsOrder.append(currentNode).append(" ");

            // add all neighbors of currentNode to the queue
            /*for(String neighbor: adjList.get(currentNode)){
                queue.add(neighbor);
            }*/

            // Single line for adding all neighbors to queue
            queue.addAll(adjList.get(currentNode));
        }
        System.out.println(bfsOrder);

    }

    // Problem statement: https://structy.net/problems/has-path
    public boolean hasPathExistsDFS(Map<String, List<String>> adjList, String src, String dest) {
        if(src.equals(dest)) {
            return true;
        }
        Stack<String> stack = new Stack<>();
        stack.push(src);

        while(!stack.isEmpty()){
            String currentNode = stack.pop();
            if(currentNode.equals(dest)) return true;

            for(String neighbor:adjList.get(currentNode)){
                stack.push(neighbor);
            }
        }
        return false;
    }

    public boolean hasPathExistsRecursiveDFS(Map<String, List<String>> adjList, String src, String dest) {
        if(Objects.equals(src, dest)) return true;

        for(String neighbor: adjList.get(src)) {
            if(hasPathExistsRecursiveDFS(adjList,neighbor,dest)){
                return true;
            }
        }

        return false;
    }

    public boolean hasPathExistsBFS(Map<String, List<String>> adjList, String src, String dest) {
        if(src.equals(dest)) return true;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(src);
        while(!queue.isEmpty()){
            String currentNode = queue.poll();
            if(currentNode.equals(dest)){
                return true;
            }
            if(!visited.contains(currentNode)){
                visited.add(currentNode);
                queue.addAll(adjList.get(currentNode));
            }
        }
        return false;
    }

    // Problem statement: https://structy.net/problems/undirected-path
    public static boolean undirectedPath(List<List<String>> edges, String src, String dest) {
        // convert edges to adjList
        Map<String,List<String>> adjList = new HashMap<>();
        for(List<String> l: edges){
            if(!adjList.containsKey(l.get(0))){
                List<String> neighbors = new ArrayList<>();
                neighbors.add(l.get(1));
                adjList.put(l.get(0),neighbors);
            } else {
                adjList.get(l.get(0)).add(l.get(1));
            }
            if(!adjList.containsKey(l.get(1))){
                List<String> neighbors = new ArrayList<>();
                neighbors.add(l.get(0));
                adjList.put(l.get(1),neighbors);
            } else {
                adjList.get(l.get(1)).add(l.get(0));
            }
        }

        Set<String> visitedNodes = new HashSet<>();

//        Stack<String> stack = new Stack<>();
//        stack.push(src);
//
//        while(!stack.isEmpty()) {
//            String currentNode = stack.pop();
//            if(currentNode.equals(dest)) return true;
//            if(!visitedNodes.contains(currentNode)){
//                visitedNodes.add(currentNode);
//                for(String neighbor: adjList.get(currentNode)) {
//                    stack.push(neighbor);
//                }
//            }
//        }
//        return false;

        return hasPathRecursive(adjList,src,dest,visitedNodes);
    }

    public static boolean hasPathRecursive(Map<String,List<String>> adjList, String src, String dest, Set<String> visited){
        if(src.equals(dest)) return true;
        if(!visited.contains(src)){
            visited.add(src);
            for(String neighbor: adjList.get(src)){
                if(hasPathRecursive(adjList,neighbor,dest,visited)){
                    return true;
                }
            }
        }
        return false;
    }

    // Problem statement: https://structy.net/problems/connected-components-count
    public static int connectedComponentsCount(Map<Integer, List<Integer>> graph) {
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        Set<Map.Entry<Integer, List<Integer>>> entries = graph.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : entries) {
            if(!visited.contains(entry.getKey())){
                count++;
                dfsConnectedComponents(graph,entry.getKey(),visited);
            }
        }

        return count;
    }

    public static void dfsConnectedComponents(Map<Integer, List<Integer>> graph,Integer node, Set<Integer> visited){
        if(!visited.contains(node)){
            visited.add(node);
            for(Integer neighbor:graph.get(node)){
                dfsConnectedComponents(graph,neighbor,visited);
            }
        }
    }

    // Problem statement: https://structy.net/problems/largest-component
    public static int largestComponentCount(Map<Integer, List<Integer>> graph) {
        int maxSize = 0;
        Set<Integer> visited = new HashSet<>();
        Set<Map.Entry<Integer, List<Integer>>> entries = graph.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : entries) {
            if(!visited.contains(entry.getKey())){
                int componentSize = explore(graph,entry.getKey(),visited);
                if(componentSize > maxSize){
                    maxSize = componentSize;
                }
            }
        }
        return maxSize;
    }

    public static int explore(Map<Integer, List<Integer>> graph,Integer node, Set<Integer> visited){
        int count = 0;
        if(!visited.contains(node)){
            count++;
            visited.add(node);
            for(Integer neighbor:graph.get(node)){
                count = count + explore(graph,neighbor,visited);
            }
        }
        return count;
    }

    // Problem Statement: https://structy.net/problems/shortest-path
    public static int shortestPath(List<List<String>> edges, String nodeA, String nodeB) {
        Map<String,List<String>> adjList = new HashMap<>();
        for(List<String> l: edges){
            if(!adjList.containsKey(l.get(0))){
                List<String> neighbors = new ArrayList<>();
                neighbors.add(l.get(1));
                adjList.put(l.get(0),neighbors);
            } else {
                adjList.get(l.get(0)).add(l.get(1));
            }
            if(!adjList.containsKey(l.get(1))){
                List<String> neighbors = new ArrayList<>();
                neighbors.add(l.get(0));
                adjList.put(l.get(1),neighbors);
            } else {
                adjList.get(l.get(1)).add(l.get(0));
            }
        }

        Queue<Map.Entry<String,Integer>> queue = new LinkedList<>();
        queue.add(new AbstractMap.SimpleEntry<>(nodeA, 0));
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Map.Entry<String,Integer> currentNode = queue.poll();
            if(currentNode.getKey().equals(nodeB)) return currentNode.getValue();
            if(!visited.contains(currentNode.getKey())){
                visited.add(currentNode.getKey());
                for(String neighbor: adjList.get(currentNode.getKey())){
                    queue.add(new AbstractMap.SimpleEntry<>(neighbor,currentNode.getValue()+1));
                }
            }
        }
        return -1;
    }


    // Problem statement: https://structy.net/problems/island-count
    public static int countIslands(List<List<String>> grid){
        int count = 0;
        Set<String> visited = new HashSet<>();

        for(int r=0; r<grid.size(); r++) {
            for(int c=0; c<grid.get(0).size(); c++) {
                if(explore(grid,r,c,visited)) count++;
            }
        }
        return count;
    }

    public static boolean explore(List<List<String>> grid, int r, int c, Set<String> visited){
        boolean rowInbounds = r >= 0 && r < grid.size();
        boolean columnInbounds = c >= 0 && c < grid.get(0).size();
        if(!rowInbounds || !columnInbounds) return false;
        if(grid.get(r).get(c).equals("W")) return false;

        String pos = r+","+c;
        if(visited.contains(pos)) return false;
        visited.add(pos);

        explore(grid,r-1,c,visited); // up
        explore(grid,r+1,c,visited); // down
        explore(grid,r,c-1,visited); // left
        explore(grid,r,c+1,visited); // right
        return true;
    }

    // Problem statement: Minimum island size
    public static int getMinimumIslandSize(List<List<String>> grid){
        int minIslandsSize = -1;

        Set<String> visited = new HashSet<>();

        for(int r = 0; r<grid.size(); r++) {
            for(int c=0; c < grid.get(0).size(); c++) {
                int count = exploreMinIsland(grid,r,c,visited);
                if(minIslandsSize == -1 && count != 0){
                    minIslandsSize = count;
                }
                else if(count != 0){
                    minIslandsSize = Math.min(minIslandsSize,count);
                }
            }
        }

        if(minIslandsSize == -1) {
            return 0;
        }

        return minIslandsSize;
    }

    private static int exploreMinIsland(List<List<String>> grid, int r, int c, Set<String> visited) {
        boolean rowInbounds = r >= 0 && r < grid.size();
        boolean columnInbounds = c >= 0 && c < grid.get(0).size();
        if(!rowInbounds || !columnInbounds) return 0;
        if (grid.get(r).get(c).equals("W")) return 0;

        String pos = r+","+c;
        if(visited.contains(pos)) return 0;
        visited.add(pos);
        int countNodes = 1;
        countNodes += exploreMinIsland(grid,r-1,c,visited);
        countNodes += exploreMinIsland(grid,r+1,c,visited);
        countNodes += exploreMinIsland(grid,r,c-1,visited);
        countNodes += exploreMinIsland(grid,r,c+1,visited);
        return countNodes;
    }

    public static class Pair {
        int i,j,t;

        public Pair(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

    public static int rottenOranges(int [][] grid){
        Set<String> visitedCells = new HashSet<>();
        // add the rotten oranges to the queue;
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0;i<grid.length; i++) {
            for(int j = 0; j<grid[i].length; j++){
                if(grid[i][j] == 2) {
                    q.add(new Pair(i,j,0));
                }
            }
        }

        int maxTime = 0;
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        while(!q.isEmpty()) {
            Pair p = q.poll();
            maxTime = Math.max(maxTime, p.t);
            String pos = p.i+","+p.j;
            if (!visitedCells.contains(pos)){
                visitedCells.add(pos);
                for(int i = 0; i<4; i++) {
                    int nrow = p.i + drow[i];
                    int ncol = p.j + dcol[i];
                    boolean rowBoundary = nrow >= 0 && nrow < grid.length;
                    boolean colBoundary = ncol >= 0 && ncol < grid[0].length;
                    if(rowBoundary && colBoundary && grid[nrow][ncol] == 1) {
                        q.add(new Pair(nrow,ncol,p.t+1));
                        grid[nrow][ncol] = 2;
                    }
                }

            }
        }

        for (int i = 0; i<grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }

        }

        return maxTime;
    }
}
