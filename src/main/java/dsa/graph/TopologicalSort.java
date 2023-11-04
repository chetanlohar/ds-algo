package dsa.graph;

import java.util.*;

public class TopologicalSort {

    /**
     * Topological sort is a kind of sort that parent node will always come first
     * that means parent nodes should be at initial indexes of the array and child nodes should be at last
     * This sorting is very useful in following scenarios:
     * 1. Dependency resolution
     * 2. Task scheduling
     * 3. Build systems (link Ant, Make)
     * 4. Event Processing
     * 5. Instruction scheduling in compilers
     * 6. Data Flow analysis
     *
     * This sort is possible only in thge DAG (Directed Acyclic graph)
     * */

    public static void main(String[] args) {
//        char a = 'd';
        char value = 'a'+19;

        System.out.println(value);

        List<List<Integer>> adjList = List.of(
                List.of(),
                List.of(),
                List.of(3),
                List.of(1),
                List.of(0,1),
                List.of(0,2)
        );

        // t->f
        // w->e

        List<Integer> integers = topologicalSor(adjList);
        System.out.println("Topological Order: "+integers);

        List<Integer> integersUsingKahn = topologicalSortUsingKahnsAlgo(adjList);
        System.out.println("Topological Sort order using kahn: "+integersUsingKahn);

        boolean canAllTaskCompleted = prerequisiteTasks(adjList);
        System.out.println("Is all tasks completed: "+canAllTaskCompleted);

    }

    public static List<Integer> topologicalSor(List<List<Integer>> adjList){
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<adjList.size();i++){
            if(!visited.contains(i)){
                dfs(adjList,i,stack,visited);
            }
        }

        List<Integer> topologicalSort = new ArrayList<>();

        while(!stack.isEmpty()) {
            topologicalSort.add(stack.pop());
        }
        return topologicalSort;
    }

    private static void dfs(List<List<Integer>> adjList, int source, Stack<Integer> stack, Set<Integer> visited) {
        visited.add(source);
        for(int neighbor:adjList.get(source)){
            if(!visited.contains(neighbor)){
                dfs(adjList,neighbor,stack,visited);
            }
        }
        stack.push(source);
    }

    /**
     * Provide implementation using Kahn's algorithm using BFS
     * */
    public static List<Integer> topologicalSortUsingKahnsAlgo(List<List<Integer>> adjList){
        int [] indegrees = new int[adjList.size()];
        Arrays.fill(indegrees,0);

        // Prepare the indegree arrays by iterating the adjList
        for(int i=0;i<adjList.size();i++){
            for(int neighbour: adjList.get(i)){
                indegrees[neighbour]++;
            }
        }

        // Prepare Queue add all 0 degree nodes to the queue;
        Queue<Integer> queue = new LinkedList<>();
        for(int node=0;node<adjList.size();node++) {
            if(indegrees[node] == 0) {
                queue.add(node);
            }
        }
        List<Integer> topologicalOrderedList = new ArrayList<>();

        while(!queue.isEmpty()) {
            int currentNOde = queue.poll();
            topologicalOrderedList.add(currentNOde);
            for(int neighbour: adjList.get(currentNOde)){
                indegrees[neighbour]--;
                if(indegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        return topologicalOrderedList;
    }

    public static boolean prerequisiteTasks(List<List<Integer>> adjList){

        // calculate indegrees of every node
        int[] indegrees = new int[adjList.size()];
        System.out.println(Arrays.toString(indegrees));
        for(int node=0;node<adjList.size();node++) {
            for(int neighbour: adjList.get(node)) {
                indegrees[neighbour]++;
            }
        }

        // prepare queue and add all zero degree nodes to the queue and toposort list
        List<Integer> topoSort = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int node=0;node<adjList.size();node++) {
            if(indegrees[node] == 0) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            int currentTask = queue.poll();
            topoSort.add(currentTask);
            for(int neighbour: adjList.get(currentTask)){
                indegrees[neighbour]--;
                if(indegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        return topoSort.size() == adjList.size();
    }

}
