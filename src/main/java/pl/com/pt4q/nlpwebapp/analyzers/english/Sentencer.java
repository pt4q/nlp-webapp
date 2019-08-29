package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
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
class Sentencer implements AnalyzerInterface<CoreSentence> {

    private StanfordCoreNLP pipeline;

    @Override
    public List<CoreSentence> analyze(String input) {
        CoreDocument coreDocument = new CoreDocument(input);
        pipeline.annotate(coreDocument);
        return coreDocument.sentences();
    }

    @Override
    public String listToString(List<CoreSentence> coreSentences) {
        return toStringList(coreSentences).stream()
                .map(s -> s + "\n")
                .collect(Collectors.joining());
    }

    public List<String> toStringList(List<CoreSentence> coreSentences) {
        return coreSentences.stream()
                .map(CoreSentence::toString)
                .collect(Collectors.toList());
    }
}
