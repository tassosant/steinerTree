package kruskalByTasos;

public class DisjointSets {
    private int vertices;

    public DisjointSets(int vertices) {
        this.vertices = vertices;
        this.parent = new int[vertices];
        this.rank = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            this.parent[i] = i;
        }
    }

    private int[] parent;
    private int[] rank;



    // Find the root of the set in which element `i` is included, with path compression
    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    // Union by rank optimization
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return;
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
