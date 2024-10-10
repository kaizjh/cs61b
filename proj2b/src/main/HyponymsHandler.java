package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import graph.WordNet;

public class HyponymsHandler extends NgordnetQueryHandler {

    WordNet wn;

    public HyponymsHandler(String synsetFile, String hyponymFile) {
        wn = new WordNet(synsetFile, hyponymFile);
    }

    @Override
    /** 两种情况，多个单词和一个单词分开处理。 */
    public String handle(NgordnetQuery q) {
        if (q.words().size() > 1) {
            return wn.hyponyms(q.words()).toString();
        }
        return wn.hyponyms(q.words().getFirst()).toString();
    }
}
