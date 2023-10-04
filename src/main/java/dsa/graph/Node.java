package dsa.graph;

import lombok.Data;

import java.util.List;

@Data
public class Node {

    private String name;
    private List<Node> neighbors;

    public Node(String name) {
        this.name = name;
    }
}
