package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;


public class HistoryTextHandler extends NgordnetQueryHandler {

    NGramMap map;

    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        TimeSeries ts;
        for (String word: words) {
            response += word + ": ";
            ts = map.weightHistory(word, startYear, endYear);
            response += ts.toString() + "\n";
        }

        return response;
    }
}
