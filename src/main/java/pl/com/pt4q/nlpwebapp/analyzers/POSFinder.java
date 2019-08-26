package pl.com.pt4q.nlpwebapp.analyzers;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class POSFinder implements AnalyzerInterface<CoreLabel> {

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
                .forEach(s -> sb.append(s.originalText())
                        .append(" <== ")
                        .append(s.get(CoreAnnotations.PartOfSpeechAnnotation.class))
                        .append('\n'));

        return sb.toString();
    }
}
