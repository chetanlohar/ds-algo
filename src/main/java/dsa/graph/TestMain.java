package dsa.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestMain {

    public static void main(String[] args) {
        Set<List<List<Integer>>> set = new HashSet<>();
        List<List<Integer>> l1 = List.of(List.of(0,0),List.of(0,1),List.of(1,0),List.of(1,1));
        List<List<Integer>> l2 = List.of(List.of(0,0),List.of(0,1),List.of(1,0),List.of(1,2));

        set.add(l1);
        System.out.println(set.size());
        set.add(l2);
        System.out.println(set.size()+" :: " + set);
    }
}
