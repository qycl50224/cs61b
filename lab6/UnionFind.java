public class UnionFind {

    // TODO - Add instance variables?
    private int[] ds;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        ds = new int[n];
        for(int i = 0; i < n; i++ ) {
            ds[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if(vertex < 0 || vertex > ds.length) {
            throw new IllegalArgumentException("Invalid Argument");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return ds[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if(!connected(v1, v2)) {
            if (size1 < size2) {
                ds[find(v1)] = find(v2);
                ds[find(v2)] -= sizeOf(v1);
            } else if (size1 > size2) {
                ds[find(v2)] = find(v1);
                ds[find(v1)] -= sizeOf(v2);
            } else {
                ds[find(v1)] = find(v2);
                ds[find(v2)] -= sizeOf(v1);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int root = vertex;
        while(ds[root] > -1) {
            root = ds[root];
        }
        int currentParent = ds[vertex];
        while(currentParent > -1) {
            ds[vertex] = root;
            vertex = currentParent;
            currentParent = ds[vertex];
        }
        return root;
    }
    public static void main(String[]Args) {
        UnionFind uf = new UnionFind(6);
        uf.union(0,1);
        uf.union(2, 3);
        uf.union(1, 2);
    }
}
