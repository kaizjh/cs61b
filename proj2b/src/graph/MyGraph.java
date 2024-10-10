package graph;

import net.sf.saxon.expr.IntegerRangeTest;

import java.util.*;

public class MyGraph {

    private class Node<T> {

        T item;
        Node next;

        public Node(T i, Node n) {
            this.item = i;
            this.next = n;
        }
    }

    private HashMap<Integer, Node> children;
    private HashMap<Integer, Node> parents;
    private HashMap<Integer, Node> wordsAndDescriptions;

    public MyGraph () {
        children = new HashMap<>();
        parents = new HashMap<>();
        wordsAndDescriptions = new HashMap<>();
    }

    public void addEdge(int parent, int child) {
        children.put(parent, new Node(child, children.get(parent)));
        parents.put(child, new Node(parent, parents.get(child)));
    }

    /** 一个节点可能储存多个单词，但只有一个描述，所以，node的最后一个节点永远储存描述，前面储存一个或多个单词。 */
    public void addWord(int i, String[] words, String description) {
        Node node = new Node(description, null);
        for (String word : words) {
            node = new Node(word, node);
        }
        wordsAndDescriptions.put(i, node);
    }

    // 一个单词可能有多个id
    public ArrayList<Integer> getIds(String word) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (int key : wordsAndDescriptions.keySet()) {
            Node wordsNode = wordsAndDescriptions.get(key);
            // 如果wordsNode.next == null, 则wordsNode.item 是描述而非单词，说明该节点的所有单词已经完成遍历
            while (wordsNode.next != null) {
                if (wordsNode.item.equals(word)) {
                    ids.add(key);
                    break;
                }
                wordsNode = wordsNode.next;
            }
        }
        return ids;
    }

    /** 不仅要得到当前节点的children，还要进一步得到children的children，直到没有children为止。 */
    public List<String> getChildren(int i) {

        List<String> s = new ArrayList<>();

        Node wordsNode = wordsAndDescriptions.get(i);
        while (wordsNode.next != null) {
            s.add((String) wordsNode.item);
            wordsNode = wordsNode.next;
        }

        Node cnode = children.get(i);
        while (cnode != null) {
            s.addAll(getChildren((Integer) cnode.item));
            cnode = cnode.next;
        }
        return s;
    }

}
