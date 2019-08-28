package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerInterface;

import java.util.List;

@AllArgsConstructor
public class SentimentAnalyzer implements AnalyzerInterface<CoreSentence> {

    private StanfordCoreNLP stanfordCoreNLP;

    @Override
    public List<CoreSentence> analyze(String input) {
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);

        return coreDocument.sentences();
    }

    @Override
    public String listToString(List<CoreSentence> coreSentences) {
        StringBuilder sb = new StringBuilder();

        coreSentences.stream()
                .forEach(cs -> sb
                        .append(cs.sentiment())
                        .append('\t')
                        .append(cs.toString())
                        .append('\n'));

        return sb.toString();
    }
}
