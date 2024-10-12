package graph;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {

    MyGraph graph;
    In inSynsets;
    In inHyponyms;

    public WordNet(String synsetsFile, String hyponymsFile) {
        this.inSynsets = new In(synsetsFile);
        this.inHyponyms = new In(hyponymsFile);
        this.graph = new MyGraph();
        fileToGraph();
    }

    /** Read data from files, add them to Graph. */
    public void fileToGraph() {

        // System.out.println(System.getProperty("user.dir"));

        while (!inSynsets.isEmpty()) {
            String nextLine = inSynsets.readLine();
            String[] splitLine = nextLine.split(",");
            String[] splitWords = splitLine[1].split(" ");
            graph.addWord(Integer.parseInt(splitLine[0]), splitWords, splitLine[2]);
        }

        while (!inHyponyms.isEmpty()) {
            String nextLine = inHyponyms.readLine();
            String[] splitLine = nextLine.split(",");

            int parent = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                int child = Integer.parseInt(splitLine[i]);
                graph.addEdge(parent, child);
            }
        }
    }

    public List<String> hyponyms(String word) {
        ArrayList<Integer> ids = graph.getIds(word);
        List<String> returnString = new ArrayList<>();
        for (int i : ids) {
            List<String> s0 = graph.getChildren(i);
            for (String s : s0) {
                if (!returnString.contains(s)) {
                    returnString.add(s);
                }
            }
        }
        Collections.sort(returnString);
        return returnString;
    }

    public List<String> hyponyms(List<String> words) {
        List<String> returnString = new ArrayList<>();

        // 传入的words是一个immutable，所以多了这一步。
        List<String> mutableWords = new ArrayList<>(words);

        List<String> s0 = hyponyms(mutableWords.removeFirst());
        for (String word : mutableWords) {
            List<String> s1 = hyponyms(word);
            for (String s : s1) {
                if (s0.contains(s)) {
                    returnString.add(s);
                }
            }
        }
        Collections.sort(returnString);
        return returnString;
    }

    public List<String> ancestors(String word) {
        ArrayList<Integer> ids = graph.getIds(word);
        List<String> returnString = new ArrayList<>();
        for (int i : ids) {
            List<String> s0 = graph.getParents(i);
            for (String s : s0) {
                if (!returnString.contains(s)) {
                    returnString.add(s);
                }
            }
        }
        Collections.sort(returnString);
        return returnString;
    }

    public List<String> ancestors(List<String> words) {
        List<String> returnString = new ArrayList<>();

        // 传入的words是一个immutable，所以多了这一步。
        List<String> mutableWords = new ArrayList<>(words);

        List<String> s0 = ancestors(mutableWords.removeFirst());
        for (String word : mutableWords) {
            List<String> s1 = ancestors(word);
            for (String s : s1) {
                if (s0.contains(s)) {
                    returnString.add(s);
                }
            }
        }
        Collections.sort(returnString);
        return returnString;
    }
}
