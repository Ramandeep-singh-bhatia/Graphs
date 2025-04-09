package MST;

import Common.Edge;

import java.util.*;

public class Prims {
    public void createMST(Edge[] edges){
        Map<Character, List<Edge>> graph = new HashMap<>();
        for(Edge e: edges){
            graph.putIfAbsent(e.getSource(), new ArrayList<>());
            graph.putIfAbsent(e.getDestination(), new ArrayList<>());

            graph.get(e.getSource()).add(e);
            graph.get(e.getDestination()).add(new Edge(e.getDestination(), e.getSource(), e.getWeight()));

        }

        boolean[] visited = new boolean[26];

        Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.getWeight()));

        List<Edge> mst = new ArrayList<>();

        char startVertex = edges[0].getSource();

        visited[startVertex - 'A'] = true;

        for(Edge edge: graph.get(startVertex)){
            pq.add(edge);
        }

        while(!pq.isEmpty()){
            Edge currentEdge = pq.poll();

            if(!visited[currentEdge.getDestination() - 'A']){
                mst.add(currentEdge);

                visited[currentEdge.getDestination() - 'A'] = true;

                for(Edge edge : graph.get(currentEdge.getDestination())){
                    if(!visited[edge.getDestination() - 'A']){
                        pq.add(edge);
                    }
                }
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
