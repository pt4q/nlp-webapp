package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerInterface;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzersStringFormatter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class SentimentAnalyzer implements AnalyzerInterface<CoreSentence> {

    private StanfordCoreNLP stanfordCoreNLP;

    @Override
    public List<CoreSentence> analyze(String input) {
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);

        return coreDocument.sentences();
    }

    @Override
    public String listToString(List<CoreSentence> coreSentences) {
        return AnalyzersStringFormatter.listToString(toStringList(coreSentences));
    }

    public List<String[]> toStringList(List<CoreSentence> coreSentences) {
        return coreSentences.stream()
                .map(cs -> getSentenceAndSentiment(cs))
                .collect(Collectors.toList());
    }

    private String[] getSentenceAndSentiment(CoreSentence cs) {
        return new String[]{cs.sentiment(), cs.toString()};
    }
}
