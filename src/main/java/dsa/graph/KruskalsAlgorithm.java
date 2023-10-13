package dsa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsAlgorithm {

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int wt;

        public Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge that) {
            return this.wt - that.wt;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", wt=" + wt +
                    '}';
        }
    }

    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    public KruskalsAlgorithm(int n) {
        for(int i=0;i<=n;i++) {
            rank.add(0);
            parent.add(i);
        }
    }

    public int findUltimateParent(int node){
        if(node == parent.get(node)) return node;
        int p = findUltimateParent(parent.get(node));
        parent.set(node,p);
        return p;
    }

    public void unionByRank(int u, int v){
        int pU = findUltimateParent(u);
        int pV = findUltimateParent(v);
        if(pU == pV) return;
        if(rank.get(pU) < rank.get(pV)){
            parent.set(pU,pV);
        } else if (rank.get(pV) < rank.get(pU)){
            parent.set(pV,pU);
        } else {
            parent.set(pU,pV);
            int pVRank = rank.get(pV);
            rank.set(pV,pVRank+1);
        }
    }

    List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {
        KruskalsAlgorithm ksa = new KruskalsAlgorithm(6);
        ksa.edges.add(new Edge(5,4,9));
        ksa.edges.add(new Edge(5,1,4));
        ksa.edges.add(new Edge(4,1,1));
        ksa.edges.add(new Edge(4,3,5));
        ksa.edges.add(new Edge(4,2,3));
        ksa.edges.add(new Edge(1,2,2));
        ksa.edges.add(new Edge(3,2,3));
        ksa.edges.add(new Edge(3,6,8));
        ksa.edges.add(new Edge(2,6,7));

        Collections.sort(ksa.edges);

        int mst = 0;
        for(int i=0;i<ksa.edges.size();i++) {
            if(ksa.findUltimateParent(ksa.edges.get(i).u) != ksa.findUltimateParent(ksa.edges.get(i).v)) {
                ksa.unionByRank(ksa.edges.get(i).u,ksa.edges.get(i).v);
                mst = mst + ksa.edges.get(i).wt;
            }
        }

        System.out.println(ksa.parent);

        System.out.println("MST: "+mst);

    }
}
