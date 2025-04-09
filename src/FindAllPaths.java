import java.util.*;
public class FindAllPaths {
    public void getAllPaths(char[][] edges, char source, char destination){
        Map<Character, List<Character>> graph = new HashMap<>();
        for (char[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        List<Character> currPath = new ArrayList<>();
        List<List<Character>> allPath = new ArrayList<>();
        Set<Character> visited = new HashSet<>();
        //helper(graph, source, destination, currPath, allPath, visited);
        //helperBFS(graph, source, destination, allPath);
        getShortestPath(graph, source, destination, allPath);
        printAllPaths(allPath);
    }

    private void printAllPaths(List<List<Character>> allPath) {
        for (List<Character> path : allPath) {
            for (char vertex : path) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    private void helper(Map<Character, List<Character>> graph, char source, char destination,
                        List<Character> currPath, List<List<Character>> allPath, Set<Character> visited){
        visited.add(source);
        currPath.add(source);
        if(source == destination){
            allPath.add(new ArrayList<>(currPath));
        } else {
            for(Character neighbour: graph.get(source)){
                if(!visited.contains(neighbour)){
                    helper(graph, neighbour, destination, currPath, allPath, visited);
                }
            }
        }
        currPath.remove(currPath.size() - 1);
        visited.remove(source);
    }

    private void helperBFS(Map<Character, List<Character>> graph, char source, char destination,
                           List<List<Character>> allPath){
        Queue<List<Character>> q = new LinkedList<>();
        List<Character> path =  new ArrayList<>();
        path.add(source);
        q.offer(path);

        while(!q.isEmpty()){
            path = q.poll();
            char vertex = path.get(path.size() - 1);
            if(vertex == destination){
                allPath.add(path);
            }
            for(char v: graph.get(vertex)){
                if(!isVisited(path, v)){
                    List<Character> tempPath =  new ArrayList<>(path);
                    tempPath.add(v);
                    q.add(tempPath);
                }
            }
        }
    }

    private void getShortestPath(Map<Character, List<Character>> graph, char source, char destination,
                           List<List<Character>> allPath){
        Queue<List<Character>> q = new LinkedList<>();
        List<Character> path =  new ArrayList<>();
        path.add(source);
        q.offer(path);

        while(!q.isEmpty()){
            path = q.poll();
            char vertex = path.get(path.size() - 1);
            if(vertex == destination){
                allPath.add(path);
                break;
            }
            for(char v: graph.get(vertex)){
                if(!isVisited(path, v)){
                    List<Character> tempPath =  new ArrayList<>(path);
                    tempPath.add(v);
                    q.add(tempPath);
                }
            }
        }
    }

    private boolean isVisited(List<Character> path, char v){
        for(char i: path){
            if(i == v)
                return true;
        }

        return false;
    }
}
