package dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetAlgo {

    List<Integer> ranks = new ArrayList<>();
    List<Integer> parents = new ArrayList<>();

    public DisjointSetAlgo(int n){
        for(int i=0;i<=n;i++){
            ranks.add(0);
            parents.add(i);
        }
    }

    public int findUltimateParent(int node){
        int parent = parents.get(node);
        if(node == parent) return node;
        int ultimateParent = findUltimateParent(parent);
        parents.set(node,ultimateParent);
        return ultimateParent;
    }

    public void unionByRank(int u, int v){
        int ulpU = findUltimateParent(u);
        int ulpV = findUltimateParent(v);
        if(ulpU == ulpV) return;
        if(ranks.get(ulpU) < ranks.get(ulpV)) {
            parents.set(ulpU,ulpV);
        }
        else if (ranks.get(ulpV) < ranks.get(ulpU)) {
            parents.set(ulpV,ulpU);
        } else {
            parents.set(ulpU,ulpV);
            ranks.set(ulpV,ranks.get(ulpV)+1);
        }
    }

    public static void main(String[] args) {
        System.out.println("--------- How to use DisjointSetAlgo set DS -----------");

        DisjointSetAlgo ds = new DisjointSetAlgo(7);
        ds.unionByRank(1,2);
        ds.unionByRank(2,3);
        ds.unionByRank(4,5);
        ds.unionByRank(6,7);
        ds.unionByRank(5,6);

        // if 3 and 7 are same or not
        if(ds.findUltimateParent(3) == ds.findUltimateParent(7)){
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        ds.unionByRank(3,7 );

        // again test if 3 and 7 are same or not
        if(ds.findUltimateParent(3) == ds.findUltimateParent(7)){
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }

}
