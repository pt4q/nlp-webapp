package pl.com.pt4q.nerwebapp.analyzers;

import java.util.List;

public interface SentenceAnalyzerInterface<R> {

    List<R> analyze(String input);

    String listToString(List<R> coreLabels);
}
