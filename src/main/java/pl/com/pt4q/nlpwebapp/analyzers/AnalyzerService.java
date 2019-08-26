package pl.com.pt4q.nlpwebapp.analyzers;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class AnalyzerService {

    private StanfordCoreNLP pipeline;
    private static AnalyzerService instance;

    private AnalyzerService() {
    }

    public static AnalyzerService getInstance() {
        if (instance == null)
            instance = new AnalyzerService();

        return instance;
    }

    private StanfordCoreNLP getPipeline() {
        if (pipeline == null)
            this.pipeline = Pipeline.getPipeline();
        return this.pipeline;
    }

    public List<CoreLabel> lemmatize(String input) {
        Lemmatizer lemmatizer = new Lemmatizer(getPipeline());
        return lemmatizer.analyze(input);
    }

    public List<CoreLabel> ner(String input) {
        NERFinder nerFinder = new NERFinder(getPipeline());
        return nerFinder.analyze(input);
    }

    public List<CoreLabel> pos(String input){
        POSFinder posFinder = new POSFinder(getPipeline());
        return posFinder.analyze(input);
    }
}
