package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerInterface;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sentencer implements AnalyzerInterface<String[]> {

    private StanfordCoreNLP stanfordCoreNLP;
    private Sentencer sentencer;

    public String splitSentences(String input) {
        StringBuilder sb = new StringBuilder();

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);

        List<CoreSentence> sentences = coreDocument.sentences();

        sentences.stream()
                .map(CoreSentence::toString)
                .forEach(s -> sb.append(s).append('\n'));

        return sb.toString();
    }

    @Override
    public List<String[]> analyze(String input) {
        return null;
    }

    @Override
    public String listToString(List<String[]> coreLabels) {
        return null;
    }
}
