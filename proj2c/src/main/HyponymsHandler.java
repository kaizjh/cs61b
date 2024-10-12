package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import graph.WordNet;
import ngrams.NGramMap;
import java.util.*;
import java.util.stream.Collectors;

import static browser.NgordnetQueryType.HYPONYMS;

public class HyponymsHandler extends NgordnetQueryHandler {

    WordNet wn;
    NGramMap ngm;

    public HyponymsHandler(String wordFile, String countFile, String synsetFile, String hyponymFile) {
        wn = new WordNet(synsetFile, hyponymFile);
        ngm = new NGramMap(wordFile, countFile);
    }

    @Override
    /**  */
    public String handle(NgordnetQuery q) {
        if (q.ngordnetQueryType() == HYPONYMS) {
            return handleHyponyms(q);
        }
        return handleAncestors(q);
    }

    public String handleHyponyms(NgordnetQuery q) {
        List<String> children = new ArrayList<>();
        // 两种情况，多个单词和一个单词分开处理。
        if (q.words().size() > 1) {
            children = wn.hyponyms(q.words());
        } else {
            children = wn.hyponyms(q.words().getFirst());
        }

        int startYear = q.startYear();
        int endYear = q.endYear();
        HashMap<String, Double> popularity = new HashMap<>();
        for (String word : children) {
            double counts = ngm.countTotalHistory(word, startYear, endYear);
            if (counts > 0) {
                popularity.put(word, counts);
            }
        }

        // Kimi大模型:
        // 使用Stream API进行排序，获取排序后的前k个键的列表
        if (q.k() > 0) {
            children = popularity.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed()) // 降序排序
                    .map(Map.Entry::getKey) // 获取键
                    .limit(q.k()) // 获取前k个元素
                    .collect(Collectors.toList()); // 收集到列表
        }

        // 按照字母顺序再排序
        Collections.sort(children);

        return children.toString();
    }

    public String handleAncestors(NgordnetQuery q) {
        List<String> parents = new ArrayList<>();
        // 两种情况，多个单词和一个单词分开处理。
        if (q.words().size() > 1) {
            parents = wn.ancestors(q.words());
        } else {
            parents = wn.ancestors(q.words().getFirst());
        }

        int startYear = q.startYear();
        int endYear = q.endYear();
        HashMap<String, Double> popularity = new HashMap<>();
        for (String word : parents) {
            double counts = ngm.countTotalHistory(word, startYear, endYear);
            if (counts > 0) {
                popularity.put(word, counts);
            }
        }

        if (q.k()> 0) {
            parents = popularity.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed()) // 降序排序
                    .map(Map.Entry::getKey) // 获取键
                    .limit(q.k()) // 获取前k个元素
                    .collect(Collectors.toList()); // 收集到列表
        }
        // 按照字母顺序再排序
        Collections.sort(parents);

        return parents.toString();
    }
}
