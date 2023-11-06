package fr.univamu.graph.randomTree;

import fr.univamu.graph.Edge;

public class Arc {
	Edge support;
	boolean reversed;
	
	public Arc(Edge e, boolean reversed) {
		this.support = e;
		this.reversed = reversed;
	}

    public Arc(int vertex1, int vertex2) {
        this(new Edge(vertex1, vertex2), false);
    }
	
	public int getSource() {
		return (reversed ? support.vertex2() : support.vertex1());
	}
	
	public int getDest() {
		return (reversed ? support.vertex1() : support.vertex2());
	}
	
}