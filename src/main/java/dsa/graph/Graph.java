package dsa.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Graph {
    Map<Node,List<Node>> nodes = new HashMap<>();

    public Graph getDummyGraph(){
        // Create all nodes/vertices
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        // Create adj List and store nodes

        nodes.put(a,List.of(b,c));
        nodes.put(b,List.of(d));
        nodes.put(c,List.of(e));
        nodes.put(d,List.of(f));
        nodes.put(e,List.of());
        nodes.put(f,List.of());
        return new Graph(nodes);
    }

    public void dfs() {



    }
}
