import java.lang.reflect.Array;

public class UnionFind {
    // TODO: Instance variables
    private int[] parent;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        parent = new int[N];
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int r = find(v);
        return -parent(r);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= parent.length || v < 0) {
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
        if (parent(v) < 0) {
            return v;
        } else {
            int r = find(parent(v));
            parent[v] = r;
            return r;
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 == v2) {
            return;
        }

        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return;
        }

        // Because parent[root] must be a negative int, if (parent[r1] < parent[r2]), r1 bigger than r2.
        if (sizeOf(v1) > sizeOf(v2)) {
            parent[r1] += parent[r2];
            parent[r2] = r1;
            return;
        } else {
            parent[r2] += parent[r1];
            parent[r1] = r2;
        }

    }

}
