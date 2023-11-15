package fr.univamu.graph.randomTree;

import java.util.*;

import fr.univamu.graph.UndirectedGraph;

public class Wilson implements MinSpanningTree{
    private final UndirectedGraph graph;
    private final ArrayList<Arc> tree;
    private final int size;
    private final HashSet<Integer> sommets;
    private final HashSet<Integer> sommetsParcours;

    Random random = new Random();

    public Wilson(UndirectedGraph graph) {
        this.graph = graph;
        tree = new ArrayList<Arc>();
        size = graph.order();
        sommets = new HashSet<Integer>();
        sommetsParcours = new HashSet<Integer>();
        for (int i = 0; i < size; i++) {
            sommets.add(i);
        }
    }

    private void explore(int start) {
        sommetsParcours.add(start);
        while (!sommetsParcours.containsAll(sommets)) {
            int v = random.nextInt(size);
            if (sommetsParcours.contains(v))
                continue;
            int w = v;
            ArrayList<Integer> chemin = new ArrayList<Integer>();
            chemin.add(v);
            while (!sommetsParcours.contains(w)) {
                w = random.nextInt(size);
                if (!chemin.contains(w)) {
                    chemin.add(w);
                }
            }
            for (int i = 0; i < chemin.size() - 1; i++) {
                tree.add(new Arc(chemin.get(i), chemin.get(i + 1)));
            }
            sommetsParcours.addAll(chemin);
        }
    }

    public void execute() {
        int root = 0;
        int maxDegree = graph.degree(root);
        for (int i = 1; i < size; i++) {
            if (graph.degree(i) > maxDegree) {
                root = i;
                maxDegree = graph.degree(i);
            }
        }

        sommets.remove(root);
        sommetsParcours.add(root);

        while (!sommets.isEmpty()) {
            int[] sommetsRester = sommets.stream().mapToInt(Number::intValue).toArray();
            int v = sommetsRester[random.nextInt(sommetsRester.length)];

            while (sommetsParcours.contains(v)) {
                v = sommetsRester[random.nextInt(sommetsRester.length)];
            }

            Arc[] chemin = new Arc[size];

            explore(v);
            for (int i = 0; i < size; i++) {
                if (chemin[i] != null) {
                    tree.add(chemin[i]);
                    int src = chemin[i].getSource();
                    int dest = chemin[i].getDest();
                    if (!sommetsParcours.contains(src)) {
                        sommetsParcours.add(src);
                        sommets.remove(src);
                    }
                    if (!sommetsParcours.contains(dest)) {
                        sommetsParcours.add(dest);
                        sommets.remove(dest);
                    }
                }
            }

        }

    }

    public   ArrayList<Arc> getTree(UndirectedGraph graph, int start) {
        Wilson wilson = new Wilson(graph);
        wilson.explore(start);
        return wilson.tree;
    }

}
