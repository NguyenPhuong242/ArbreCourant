package fr.univamu.graph.randomTree;

public class SupSommet {
    private int parent, rank;

    public SupSommet(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public int find(SupSommet[] supSommet, int i) {
        if (supSommet[i].parent != i)
            supSommet[i].parent = find(supSommet, supSommet[i].parent);
        return supSommet[i].parent;
    }

    public void union(SupSommet[] supSommet, int x, int y) {
        int xroot = find(supSommet, x);
        int yroot = find(supSommet, y);

        if (supSommet[xroot].rank < supSommet[yroot].rank)
            supSommet[xroot].parent = yroot;
        else if (supSommet[xroot].rank > supSommet[yroot].rank)
            supSommet[yroot].parent = xroot;
        else {
            supSommet[yroot].parent = xroot;
            supSommet[xroot].rank++;
        }
    }
    
}
