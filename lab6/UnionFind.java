import java.util.ArrayDeque;

public class UnionFind {

    // TODO - Add instance variables?
    private int size;
    private int[] ds;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        ds = new int[n];
        for(int i: ds) {
            ds[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if(vertex < 0) {
            throw new IllegalArgumentException("vertex should larger or equal than 0");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int size = 1;
        while(ds[v1] != -1) {
            size += 1;
            v1 = ds[v1];
        }
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        if(ds[v1] == -1) {
            return -sizeOf(v1);
        } else {
            return ds[v1];
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return parent(v1) == parent(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int h1 = find(v1);
        int h2 = find(v2);
        if(connected(v1,v2)) {
            throw new IllegalArgumentException();
        } else if(sizeOf(v1) < sizeOf(v2)) {
            ds[h1] = h2;
        } else if(sizeOf(v1) < sizeOf(v2)) {
            ds[h2] = h1;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        ArrayDeque<Integer> vertexes = new ArrayDeque();
        while(ds[vertex] != -1) {
            vertexes.add(vertex);
            vertex = ds[vertex];
        }
        for(int item: vertexes) {
            ds[vertexes.removeFirst()] = vertex;
        }
        return vertex;
    }

}
