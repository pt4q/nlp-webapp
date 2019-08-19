package pl.com.pt4q.nlpwebapp.analyzers.ner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import pl.com.pt4q.nlpwebapp.analyzers.SentenceAnalyzerInterface;

import java.util.List;

@AllArgsConstructor
public class NERFinder implements SentenceAnalyzerInterface<CoreLabel> {

    private StanfordCoreNLP stanfordCoreNLP;

    @Override
    public List<CoreLabel> analyze(String input) {
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);

        return coreDocument.tokens();
    }

    @Override
    public String listToString(List<CoreLabel> coreLabels) {
        StringBuilder sb = new StringBuilder();

        coreLabels.stream()
                .forEach(cl -> sb
                        .append(cl.originalText())
                        .append(" <== ")
                        .append(cl.get(CoreAnnotations.NamedEntityTagAnnotation.class))
                        .append('\n'));

        return sb.toString();
    }
}
