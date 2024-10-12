package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;

public class HistoryHandler extends NgordnetQueryHandler {

    NGramMap map;

    public HistoryHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        System.out.println("Got query that looks like:");
        System.out.println("Words: " + q.words());
        System.out.println("Start Year: " + q.startYear());
        System.out.println("End Year: " + q.endYear());

        System.out.println("But I'm totally ignoring that and just plotting a parabola\n" +
                "and a sine wave, because your job will be to figure out how to\n" +
                "actually use the query data.");

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (String word: q.words()) {
            lts.add(map.weightHistory(word));
            labels.add(word);
        }


//        TimeSeries parabola = new TimeSeries();
//        for (int i = 1400; i < 1500; i += 1) {
//            parabola.put(i, (i - 50.0) * (i - 50.0) + 3);
//        }
//
//        TimeSeries sinWave = new TimeSeries();
//        for (int i = 1400; i < 1500; i += 1) {
//            sinWave.put(i, 1000 + 500 * Math.sin(i/100.0*2*Math.PI));
//        }
//
//        ArrayList<TimeSeries> lts = new ArrayList<>();
//        ArrayList<String> labels = new ArrayList<>();
//
//        labels.add("parabola");
//        labels.add("sine wave");
//
//        lts.add(parabola);
//        lts.add(sinWave);

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
