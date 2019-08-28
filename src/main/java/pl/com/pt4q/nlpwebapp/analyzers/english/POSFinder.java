package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerInterface;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzersStringFormatter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
class POSFinder implements AnalyzerInterface<String[]> {

    private StanfordCoreNLP pipeline;
    private Tokenizer tokenizer;

    @Override
    public List<String[]> analyze(String input) {
        return tokenizer.analyze(input).stream()
                .map(s -> getPartOfSpeech(s))
                .collect(Collectors.toList());
    }

    private String[] getPartOfSpeech(CoreLabel cl) {
        return new String[]{cl.originalText(), cl.get(CoreAnnotations.PartOfSpeechAnnotation.class)};
    }

    @Override
    public String listToString(List<String[]> posList) {
        return AnalyzersStringFormatter.listToString(posList);
    }
}
