package fr.univamu.graph.randomTree;

import fr.univamu.graph.Edge;
import fr.univamu.graph.UndirectedGraph;
import java.util.*;

//Insertion aléatoire d’arêtes
public class RandomInsert {
    // Arbres couvrant de poids minimum aléatoire Voici l’algorithme techniquement
    // le plus simple pour obtenir un arbre couvrant qui semble vraiment très
    // aléatoire. 1. Attribuer à chaque arête un poids réel choisi dans [0, 1]
    // uniformément aléatoirement, indépendamment pour chaque arête. 2. Retourner un
    // arbre couvrant de poids minimum (pour ce choix de poids). Pour la deuxième
    // étape, utiliser l’algorithme de Prim ou celui de Kruskal par exemple

    private UndirectedGraph graph;
    private ArrayList<Arc> tree;
    private List<Edge> edges;

    public RandomInsert(UndirectedGraph graph) {
        this.graph = graph;
        this.edges = new LinkedList<Edge>();
        Iterator<Edge> iterator = this.graph.edges().iterator();
        while (iterator.hasNext()) {
            Edge currentEdge = iterator.next();
            edges.add(currentEdge);
        }
        this.tree = new ArrayList<>(graph.order());
    }

    private class Part {
        int parent, rank;
    }

    private int find(Part Parts[], int i) {
        if (Parts[i].parent != i)
            Parts[i].parent = find(Parts, Parts[i].parent);

        return Parts[i].parent;
    }

    private void Union(Part Parts[], int x, int y) {
        int xroot = find(Parts, x);
        int yroot = find(Parts, y);

        if (Parts[xroot].rank < Parts[yroot].rank)
            Parts[xroot].parent = yroot;
        else if (Parts[xroot].rank > Parts[yroot].rank)
            Parts[yroot].parent = xroot;

        else {
            Parts[yroot].parent = xroot;
            Parts[xroot].rank++;
        }
    }

    public void execute() {
        int V = graph.order();
        int e = 0;
        int i = 0;

        Part Parts[] = new Part[V];
        for (i = 0; i < V; i++) {
            Parts[i] = new Part();
            Parts[i].parent = i;
            Parts[i].rank = 0;
        }

        Collections.shuffle(edges);

        i = 0;
        while (e < V - 1) {
            Edge next_edge = edges.get(i++);
            int x = find(Parts, next_edge.vertex1());
            int y = find(Parts, next_edge.vertex2());

            if (x != y) {
                e++;
                tree.add(new Arc(next_edge, false));
                Union(Parts, x, y);
            }
        }
    }

    public ArrayList<Arc> getTree() {
        RandomInsert randomInsert = new RandomInsert(graph);
        randomInsert.execute();
        return randomInsert.tree;
    }

}
