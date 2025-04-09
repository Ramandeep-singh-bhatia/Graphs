package ShortestPath;

import Common.Edge;

import java.util.*;

public class BellmanFord {
    public void shortestPath(Edge[] edges, char source){

        Map<Character, List<Edge>> graph = new HashMap<>();
        Set<Character> vertices = new HashSet<>();

        for (Edge edge : edges) {
            graph.putIfAbsent(edge.getSource(), new ArrayList<>());
            graph.putIfAbsent(edge.getDestination(), new ArrayList<>());
            vertices.add(edge.getSource());
            vertices.add(edge.getDestination());

            // Only add the edge in one direction (not creating bidirectional edges)
            graph.get(edge.getSource()).add(edge);
        }
        Map<Character, Double> distance = new HashMap<>();

        for (char vertex : vertices) {
            distance.put(vertex, Double.MAX_VALUE);
        }
        distance.put(source, 0.0);

        for(int i = 0; i < vertices.size(); i++){
            for(Edge edge : edges){
                char u = edge.getSource();
                char v = edge.getDestination();
                double wt = edge.getWeight();
                if(distance.get(u) != Integer.MAX_VALUE && distance.get(u) + wt < distance.get(v))
                    distance.put(v, distance.get(u) + wt);
            }
        }
        for(int i = 0; i < vertices.size(); i++){
            for(Edge edge : edges){
                char u = edge.getSource();
                char v = edge.getDestination();
                double wt = edge.getWeight();
                if(distance.get(u) != Integer.MAX_VALUE && distance.get(u) + wt < distance.get(v)){
                    System.out.println("Negative cycle exist");
                }
            }
        }


        System.out.println("Shortest distances from " + source + ":");
        for (char vertex : vertices) {
            if (vertex == source) {
                System.out.println(source + " to " + vertex + ": Distance = 0, Path = [" + source + "]");
                continue;
            }

            if (distance.get(vertex) == Double.MAX_VALUE) {
                System.out.println(source + " to " + vertex + ": No path exists");
                continue;
            }

            // Print result
            System.out.println(source + " to " + vertex + ": Distance = " + distance.get(vertex));
        }
    }
}
