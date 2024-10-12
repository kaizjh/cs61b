package graph;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class MyGraph {

    private class Node<T> {
        T item;
        Node<T> next; // 此处修改: 为了类型安全，给 Node 加上了泛型<T>

        public Node(T i, Node<T> n) {
            this.item = i;
            this.next = n;
        }
    }

    private HashMap<Integer, Node<Integer>> children; // 此处修改: 为了类型安全，明确声明 Node 内部存储的是 Integer 类型
    private HashMap<Integer, Node<Integer>> parents;  // 此处修改: 同上
    private HashMap<Integer, Node<String>> wordsAndDescriptions; // 此处修改: 同上, 这里存储的 Node 包含 String

    public MyGraph() {
        children = new HashMap<>();
        parents = new HashMap<>();
        wordsAndDescriptions = new HashMap<>();
    }

    public void addEdge(int parent, int child) {
        // 创建新的 child 和 parent 节点链，并保持现有链结构
        children.put(parent, new Node<>(child, children.get(parent)));  // 此处修改: 添加泛型类型 <>
        parents.put(child, new Node<>(parent, parents.get(child)));    // 此处修改: 添加泛型类型 <>
    }

    /** 一个节点可能储存多个单词，但只有一个描述，所以，node的最后一个节点永远储存描述，前面储存一个或多个单词。 */
    public void addWord(int i, String[] words, String description) {
        // 描述为链表的最后一个元素，单词放在前面
        Node<String> node = new Node<>(description, null); // 此处修改: 添加泛型类型 <String>
        for (int j = words.length - 1; j >= 0; j--) { // 此处修改: 为避免反转链表，倒序插入单词
            node = new Node<>(words[j], node); // 此处修改: 添加泛型类型 <String>
        }
        wordsAndDescriptions.put(i, node);
    }

    // 一个单词可能有多个id
    public ArrayList<Integer> getIds(String word) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Map.Entry<Integer, Node<String>> entry : wordsAndDescriptions.entrySet()) { // 此处修改: 使用 Map.Entry 来提升可读性
            Node<String> wordsNode = entry.getValue();
            // 遍历单词链表，如果找到了对应单词，则将 id 加入结果
            while (wordsNode.next != null) { // 当 next 为 null 时，表示到达描述
                if (wordsNode.item.equals(word)) {
                    ids.add(entry.getKey());
                    break;
                }
                wordsNode = wordsNode.next;
            }
        }
        return ids;
    }

    public List<String> getChildren(int i) {
        return getRelations(i, children);
    }

    public List<String> getParents(int i) {
        return getRelations(i, parents);
    }

    public List<String> getRelations(int i, HashMap<Integer, Node<Integer>> relationMap) {
        List<String> result = new ArrayList<>();

        // 获取节点的词汇内容
        Node<String> wordsNode = wordsAndDescriptions.get(i);
        while (wordsNode != null & wordsNode.next != null) {
            result.add((String) wordsNode.item);
            wordsNode = wordsNode.next;
        }

        // 递归获取关系节点（可以是 children 或 parents）
        Node<Integer> relationNode = relationMap.get(i);
        while (relationNode != null) {
            result.addAll(getRelations((Integer) relationNode.item, relationMap));
            relationNode = relationNode.next;
        }

        return result;
    }

}
