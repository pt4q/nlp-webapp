package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerInterface;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
class Tokenizer implements AnalyzerInterface<CoreLabel> {

    private StanfordCoreNLP pipeline;

    @Override
    public List<CoreLabel> analyze(String input) {
        CoreDocument coreDocument = new CoreDocument(input);
        pipeline.annotate(coreDocument);

        return coreDocument.tokens();
    }

    @Override
    public String listToString(List<CoreLabel> coreLabels) {
        return toStringList(coreLabels).stream()
                .map(s -> s+'\n')
                .collect(Collectors.joining());
    }

    public List<String> toStringList (List<CoreLabel> coreLabels){
        return coreLabels.stream()
                .map(c->c.originalText())
                .collect(Collectors.toList());
    }
}
