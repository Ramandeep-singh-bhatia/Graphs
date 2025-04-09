package MST;

import java.util.*;

import Common.Edge;
import DisjointSet.UnionFind;

public class Kruskals {
    public void createMST(Edge[] edges){
        Arrays.sort(edges, Comparator.comparingDouble(e -> e.getWeight()));
        Set<Character> vertices = new HashSet<>();
        for(Edge e: edges){
            vertices.add(e.getSource());
            vertices.add(e.getDestination());
        }

        UnionFind uf = new UnionFind(vertices.size());
        List<Edge> mst = new LinkedList<>();

        for(Edge e : edges){
            if(mst.size() == vertices.size() - 1)
                break;
            if(uf.find(e.getSource() - 'A') != uf.find(e.getDestination() - 'A')) {
                uf.union(e.getSource() - 'A', e.getDestination() - 'A');
                mst.add(e);
            }
        }

        printMST(mst);
    }

    private void printMST(List<Edge> mst){
        for(Edge edge: mst){
            System.out.println("Edge: " + edge.getSource() + " - "
                    + edge.getDestination() + " Weight: " + edge.getWeight());
        }
    }
}
