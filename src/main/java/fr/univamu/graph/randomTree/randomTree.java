package fr.univamu.graph.randomTree;

import java.util.*;

import fr.univamu.graph.Edge;
import fr.univamu.graph.UndirectedGraph;

public class randomTree implements MinSpanningTree {
        // Arbres couvrant de poids minimum aléatoire Voici l’algorithme techniquement
        // le plus simple pour obtenir un arbre couvrant qui semble vraiment très
        // aléatoire. 1. Attribuer à chaque arête un poids réel choisi dans [0, 1]
        // uniformément aléatoirement, indépendamment pour chaque arête. 2. Retourner un
        // arbre couvrant de poids minimum (pour ce choix de poids). Pour la deuxième
        // étape, utiliser l’algorithme de Prim ou celui de Kruskal par exemple

        private final UndirectedGraph graph;
        private final ArrayList<Arc> tree;
        private final List<Edge> edges;

        public randomTree(UndirectedGraph graph) {
                this.graph = graph;
                this.edges = new LinkedList<Edge>();
            for (Edge currentEdge : this.graph.edges()) {
                edges.add(currentEdge);
            }
                this.tree = new ArrayList<>(graph.order());
        }

        private static class Part {
                int parent, rank;
        }

        private int find(Part[] Parts, int i) {
                if (Parts[i].parent != i)
                        Parts[i].parent = find(Parts, Parts[i].parent);

                return Parts[i].parent;
        }

        private void Union(Part[] Parts, int x, int y) {
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
                Edge[] result = new Edge[V];
                for (i = 0; i < V; ++i)
                        result[i] = new Edge(0, 0);

                Arrays.sort(edges.toArray());

                Part[] Parts = new Part[V];
                for (i = 0; i < V; ++i)
                        Parts[i] = new Part();

                for (int v = 0; v < V; ++v) {
                        Parts[v].parent = v;
                        Parts[v].rank = 0;
                }

                i = 0;

                while (e < V - 1) {
                        Edge next_edge = edges.get(i++);

                        int x = find(Parts, next_edge.vertex1());
                        int y = find(Parts, next_edge.vertex2());

                        if (x != y) {
                                result[e++] = next_edge;
                                Union(Parts, x, y);
                        }
                }

                for (i = 0; i < e; ++i) {
                        tree.add(new Arc(result[i].vertex1(), result[i].vertex2()));
                }
        }

        public ArrayList<Arc> getTree(UndirectedGraph graph, int start) {
                randomTree algorithm = new randomTree(graph);
                algorithm.execute();
                return algorithm.tree;
        }

}
