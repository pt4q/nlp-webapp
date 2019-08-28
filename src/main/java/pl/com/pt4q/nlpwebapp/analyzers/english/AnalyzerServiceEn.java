package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

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
        return new Tokenizer(getPipelineNLP());
    }

    public List<String> tokenize(String input) {
        Tokenizer tokenizer = getTokenizer();
        return tokenizer.toStringList(tokenizer.analyze(input));
    }

    public List<String> sentences (String input){
        return null;
    }

    public List<String[]> lemmatize(String input) {
        Lemmatizer lemmatizer = new Lemmatizer().builder()
                .pipeline(getPipelineNLP())
                .tokenizer(getTokenizer())
                .build();
        return lemmatizer.analyze(input);
    }

    public List<String[]> ner(String input) {
        NERFinder nerFinder = new NERFinder().builder()
                .stanfordCoreNLP(getPipelineNLP())
                .tokenizer(getTokenizer())
                .build();
        return nerFinder.analyze(input);
    }

    public List<String[]> pos(String input) {
        POSFinder posFinder = new POSFinder().builder()
                .pipeline(getPipelineNLP())
                .tokenizer(getTokenizer())
                .build();
        return posFinder.analyze(input);
    }

    public List<String []> eachSentenceSentiment(String input){
        return null;
    }

    public List <String> fullTextSentiment (String input){
        return null;
    }

}
