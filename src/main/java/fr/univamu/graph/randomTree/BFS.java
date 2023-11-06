package fr.univamu.graph.randomTree;

import java.util.*;

import fr.univamu.graph.UndirectedGraph;
import fr.univamu.graph.search.FifoFrontier;
import fr.univamu.graph.search.Path;

//parcours en largeur
public class BFS {

    private UndirectedGraph graph;
    private LinkedList<Integer> sommets;

    public BFS(UndirectedGraph graph, int start) {
        this.graph = graph;
        sommets = new LinkedList<Integer>();
        sommets.add(start);
        bfs(start);
    }

    public void bfs(int start){
        boolean[] marked = new boolean[graph.order()];
        LinkedList<Integer> file = new LinkedList<Integer>();
        marked[start] = true;
        file.add(start);
        while(!file.isEmpty()){
            int v = file.poll();
            for(int w : graph.neighboursOf(v)){
                if(!marked[w]){
                    marked[w] = true;
                    file.add(w);
                    sommets.add(w);
                }
            }
        }
    }

}
