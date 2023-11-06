package fr.univamu.graph.randomTree;

import java.util.*;

import fr.univamu.graph.UndirectedGraph;

public interface MinSpanningTree {

    ArrayList<Arc> getTree(UndirectedGraph graph, int start);    
}
