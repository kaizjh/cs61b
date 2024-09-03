package bk14;

public class QuickUnionDS implements DisjointSets {
    private int[] parent;

    public QuickUnionDS(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = -1;
        }
    }

    private int find(int p) {

        if (parent[p] < 0) {
            return p;
        }
        parent[p] = find(parent[p]);
        return parent[p];
    }

    /** From textbook:
     * public int find(int p) {
     *         int root = p;
     *         while (root != parent[root]) {
     *             root = parent[root];
     *         }
     *
     *         while (p != root) {
     *             int newp = parent[p];
     *             parent[p] = root;
     *             p = newp;
     *         }
     *
     *         return root;
     *     }
     */

    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j= find(q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}