package ShortestPath;

import Common.Edge;

import java.util.*;

public class Dijkstra {
    public void shortestPath(Edge[] edges, char source) {
        // Build the graph
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

        // Initialize data structures
        Map<Character, Double> distance = new HashMap<>();
        Map<Character, Character> prevVertex = new HashMap<>();

        // Using Node class for priority queue
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distance));

        // Initialize distances
        for (char vertex : vertices) {
            distance.put(vertex, Double.MAX_VALUE);
            prevVertex.put(vertex, null);
        }
        distance.put(source, 0.0);

        // Add source to priority queue
        pq.add(new Node(source, 0.0));

        // Process vertices
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            char currentVertex = current.vertex;

            // Skip if we've found a better path already
            if (current.distance > distance.get(currentVertex)) {
                continue;
            }

            // Process neighbors
            if (graph.containsKey(currentVertex)) {
                for (Edge edge : graph.get(currentVertex)) {
                    char neighbor = edge.getDestination();
                    double newDistance = distance.get(currentVertex) + edge.getWeight();

                    // Update if we found a better path
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                        prevVertex.put(neighbor, currentVertex);
                        pq.add(new Node(neighbor, newDistance));
                    }
                }
            }
        }

        // Print results
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

            // Reconstruct path
            List<Character> path = new ArrayList<>();
            char current = vertex;

            while (current != source ) {
                path.add(current);
                Character prev = prevVertex.get(current);
                if (prev == null) break;
                current = prev;
            }

            path.add(source);
            Collections.reverse(path);

            // Format path string
            StringBuilder pathStr = new StringBuilder("[");
            for (int i = 0; i < path.size(); i++) {
                pathStr.append(path.get(i));
                if (i < path.size() - 1) {
                    pathStr.append(" -> ");
                }
            }
            pathStr.append("]");

            // Print result
            System.out.println(source + " to " + vertex + ": Distance = " + distance.get(vertex) +
                    ", Path = " + pathStr);
        }
    }

    // Helper class for priority queue
    private class Node {
        char vertex;
        double distance;

        public Node(char vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}

