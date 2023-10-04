package dsa.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Node {

    private String name;
    private List<Node> neighbors;

    public Node(String name) {
        this.name = name;
    }


}
