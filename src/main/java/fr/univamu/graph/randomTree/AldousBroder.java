package fr.univamu.graph.randomTree;
import java.util.*;

import fr.univamu.graph.UndirectedGraph;


//Algorithme d’Aldous-Broder
public class AldousBroder implements MinSpanningTree{

    UndirectedGraph graph;
    ArrayList<Arc> tree;

    HashMap<Integer, Integer> sommetVisits;

    public AldousBroder(UndirectedGraph graph) {
        this.graph = graph;
        this.tree = new ArrayList<>(graph.order());
        this.sommetVisits = new HashMap<>();
        for(int i = 0; i < graph.order(); i++){
            sommetVisits.put(i, 0);
        }
    }

    private void randomWalk(){
        int sommetActuel = (int)(Math.random() * graph.order());
        sommetVisits.put(sommetActuel, 1);
        while(!allVisited()){
            int sommetSuivant = (int)(Math.random() * graph.order());
            if(sommetVisits.get(sommetSuivant) == 0){
                sommetVisits.put(sommetSuivant, 1);
                tree.add(new Arc(sommetActuel, sommetSuivant));
            }
            sommetActuel = sommetSuivant;
        }
    }

    private boolean allVisited(){
        for(int i = 0; i < graph.order(); i++){
            if(sommetVisits.get(i) == 0){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Arc> getTree(UndirectedGraph graph, int start) {
        AldousBroder algorithm = new AldousBroder(graph);
        algorithm.randomWalk();
        return algorithm.tree;
    }
    
}
