package pl.com.pt4q.nlpwebapp.analyzers;

import java.util.List;

public interface AnalyzerInterface<R> {

    List<R> analyze(String input);

    String listToString(List<R> coreLabels);
}
