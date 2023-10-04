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
}
