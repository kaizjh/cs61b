package main;

public class MyGraph {

    private class Node {
        String s;
        int i;

        public Node() {
            this.s = "";
            this.i = 0;
        }

        public void add(String s) {
            this.s = s;
        }

        public void add(int i) {
            this.i = i;
        }
    }

    private Node[] adj;

    public MyGraph (int size) {
        adj = new Node[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new Node();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public void createNode(String s, int i) {
        adj[i].add(s);
    }

}
