package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerInterface;

import java.util.List;

public class AnalyzerServiceEn {

    private StanfordCoreNLP pipeline;
    private static AnalyzerServiceEn instance;

    private AnalyzerServiceEn() {
    }

    public static AnalyzerServiceEn getInstance() {
        if (instance == null)
            instance = new AnalyzerServiceEn();

        return instance;
    }

    private StanfordCoreNLP getPipelineNLP() {
        if (pipeline == null)
            this.pipeline = Pipeline.getPipeline();
        return this.pipeline;
    }

    private Tokenizer getTokenizer() {
        return new Tokenizer().builder()
                .pipeline(getPipelineNLP())
                .build();
    }

    public List<String> tokenize(String input) {
        Tokenizer tokenizer = getTokenizer();
        return tokenizer.toStringList(tokenizer.analyze(input));
    }

    private Sentencer getSentencer() {
        return new Sentencer().builder()
                .pipeline(getPipelineNLP())
                .build();
    }

    public List<String> sentences(String input) {
        Sentencer sentencer = getSentencer();
        return sentencer.toStringList(sentencer.analyze(input));
    }

    public List<String[]> lemma(String input) {
        AnalyzerInterface lemma = new Lemmatizer().builder()
                .pipeline(getPipelineNLP())
                .tokenizer(getTokenizer())
                .build();
        return lemma.analyze(input);
    }

    public List<String[]> ner(String input) {
        AnalyzerInterface nerFinder = new NERFinder().builder()
                .stanfordCoreNLP(getPipelineNLP())
                .tokenizer(getTokenizer())
                .build();
        return nerFinder.analyze(input);
    }

    public List<String[]> pos(String input) {
        AnalyzerInterface posFinder = new POSFinder().builder()
                .pipeline(getPipelineNLP())
                .tokenizer(getTokenizer())
                .build();
        return posFinder.analyze(input);
    }

    public List<String[]> eachSentenceSentiment(String input) {
        return null;
    }

    public List<String> fullTextSentiment(String input) {
        return null;
    }

}
