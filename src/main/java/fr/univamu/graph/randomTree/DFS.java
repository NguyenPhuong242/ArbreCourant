package fr.univamu.graph.randomTree;

import fr.univamu.graph.Edge;
import fr.univamu.graph.UndirectedGraph;
import fr.univamu.graph.search.Path;

//cette classe est utilisée pour parcourir un graphe en profondeur
public class DFS {
    private final UndirectedGraph graph;
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int start;

    public DFS(UndirectedGraph graph, int start) {
        this.graph = graph;
        this.start = start;
        marked = new boolean[graph.order()];
        edgeTo = new int[graph.order()];
        dfs(start);
    }

    private void dfs(int v) {
        marked[v] = true;
        for (int w : graph.neighboursOf(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //renvoie le chemin entre le sommet de départ et le sommet v

    public Iterable pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Path path = Path.start(v);
        for (int x = v; x != start; x = edgeTo[x]) {
            path = path.add(new Edge(x, edgeTo[x]));
        }
        return path;
    }

    
}
