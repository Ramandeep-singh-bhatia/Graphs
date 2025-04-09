
import Common.Edge;
import MST.Kruskals;
import ShortestPath.BellmanFord;
import ShortestPath.Dijkstra;
import TopologicalSorting.KahnAlgo;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FindAllPaths fp = new FindAllPaths();
        char[][] edges = new char[][]{
                new char[]{'A','B'},
                new char[]{'A', 'C'},
                new char[]{'A', 'D'},
                new char[]{'B', 'E'},
                new char[]{'D', 'E'},
                new char[]{'C', 'E'},
                new char[]{'B', 'F'},
                new char[]{'E', 'F'}
        };

        //fp.getAllPaths(edges, 'A', 'F');

        Edge[] mstEdges = new Edge[] {
                new Edge('A', 'B', 0.5d),
                new Edge('B', 'C', 4.0d),
                new Edge('B', 'D', 3.0d),
                new Edge('B', 'E', 1.5d),
                new Edge('A', 'E', 1.0d),
                new Edge('E', 'D', 2.0d)
        };

        //Prims prims = new Prims();
        //prims.createMST(mstEdges);
        Kruskals kruskals = new Kruskals();
        //kruskals.createMST(mstEdges);

        int[][] tsedges = { { 0, 1 }, { 1, 2 }, { 2, 3 },
                { 4, 5 }, { 5, 1 }, { 5, 2 } };

        KahnAlgo ka = new KahnAlgo();
        //ka.topologicalSort(tsedges, 6);

        Edge[] spedges = {
                new Edge('A', 'B', 1),
                new Edge('A', 'D', 3),
                new Edge('A', 'C', 1),
                new Edge('C', 'D', 1),
                new Edge('B', 'D', 2),
                new Edge('D', 'E', 2),
                new Edge('B', 'E', 1)
        };

        Dijkstra dijkstra = new Dijkstra();
        //dijkstra.shortestPath(spedges, 'A');

        Edge[] spnegativeedges = {
                new Edge('D','C', 6),
                new Edge('F', 'D', 1),
                new Edge('A', 'B', 5),
                new Edge('B', 'F', -3),
                new Edge('B', 'C', -2),
                new Edge('D', 'E', -2),
                new Edge('C', 'E', 3)
        };

        BellmanFord bf = new BellmanFord();
        bf.shortestPath(spnegativeedges, 'A');
    }
}