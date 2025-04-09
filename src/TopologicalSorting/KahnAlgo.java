package TopologicalSorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KahnAlgo {
    public void topologicalSort(int[][] edges, int n){
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegree = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i <n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            adjList.get(edge[0]).add(edge[1]);
            //indegree[edge[1]]++;
        }

        for(int i = 0; i < n; i++){
            for(int v: adjList.get(i)){
                indegree[v]++;
            }
        }

        for(int i = 0; i < n; i++){
            if(indegree[i] == 0)
                q.offer(i);
        }
        int[] result = new int[n];
        int index = 0;
        while(!q.isEmpty()){
            int vertex = q.poll();
            result[index++] = vertex;

            for(int v: adjList.get(vertex)){
                indegree[v]--;

                if(indegree[v] == 0){
                    q.offer(v);
                }
            }
        }

        for(int i = 0; i < n; i++){
            System.out.println(result[i]);
        }
    }
}
