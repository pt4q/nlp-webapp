package pl.com.pt4q.nlpwebapp.analyzers;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class Lemmatizer implements AnalyzerInterface<CoreLabel> {

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
        return coreLabels.stream()
                .map(cl -> sb
                        .append(cl.originalText())
                        .append(" = ")
                        .append(cl.lemma())
                        .append('\n'))
                .collect(Collectors.joining());
    }
}
