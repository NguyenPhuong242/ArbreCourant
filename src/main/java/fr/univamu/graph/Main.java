package fr.univamu.graph;

import fr.univamu.graph.generators.Lollipop;
import fr.univamu.graph.randomTree.Wilson;
import fr.univamu.graph.rootedtrees.RootedTree;
import fr.univamu.graph.rootedtrees.RootedTreeAnalyser;
import fr.univamu.graph.rootedtrees.TreeStats;


public class Main {

	public static void main(String[] argv) {
		TreeStats stats = TreeStats.empty();
		for (int i = 0; i < 100; i++) {
			UndirectedGraph graph = new Lollipop(10).graph();
					//new ErdosRenyi(20,0.2).graph();
			/*Search search = Search.breadthFirstSearch(graph);
			//search.searchFrom(0);
			//if (search.edges().size() != 19) {
			//	continue;
			//}*/
			RootedTree tree = RootedTree.of(new Wilson(graph).getTree(graph,0).stream().map(arc->new Edge(arc.getSource(),arc.getDest())).toList());
					//RootedTree.of(//0,new Wilson.getTree(graph,0).stream().map(arc->new Edge(arc.getSource(),arc.getDest())).toList());
			stats = stats.add(RootedTreeAnalyser.statsOf(tree));
		}
		System.out.println(stats);
	}

}
