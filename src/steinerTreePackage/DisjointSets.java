package steinerTreePackage;

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
            rank[xRoot]++;
        }
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public int[] getParent() {
        return parent;
    }

    public void setParent(int[] parent) {
        this.parent = parent;
    }

    public int[] getRank() {
        return rank;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }
}
