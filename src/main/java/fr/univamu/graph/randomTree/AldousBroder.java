package fr.univamu.graph.randomTree;
import java.util.*;

import fr.univamu.graph.UndirectedGraph;


//Algorithme d’Aldous-Broder
public class AldousBroder {

    UndirectedGraph graph;
    ArrayList<Arc> tree;
    int sommetActuel;
    HashMap<Integer, Integer> sommetsVisites;

    public AldousBroder(UndirectedGraph graph) {
        this.graph = graph;
        this.tree = new ArrayList<>(graph.order());
        this.sommetsVisites = new HashMap<>();
        for(int i = 0; i < graph.order(); i++){
            sommetsVisites.put(i, 0);
        }
    }

    private void randomWalk(){
        int sommetActuel = (int)(Math.random() * graph.order());
        sommetsVisites.put(sommetActuel, 1);
        while(!allVisited()){
            int sommetSuivant = (int)(Math.random() * graph.order());
            if(sommetsVisites.get(sommetSuivant) == 0){
                sommetsVisites.put(sommetSuivant, 1);
                tree.add(new Arc(sommetActuel, sommetSuivant));
            }
            sommetActuel = sommetSuivant;
        }
    }

    private boolean allVisited(){
        for(int i = 0; i < graph.order(); i++){
            if(sommetsVisites.get(i) == 0){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Arc> getTree(UndirectedGraph graph, int start) {
        AldousBroder algorithm = new AldousBroder(graph);
        algorithm.randomWalk();
        return algorithm.tree;
    }
    
}
