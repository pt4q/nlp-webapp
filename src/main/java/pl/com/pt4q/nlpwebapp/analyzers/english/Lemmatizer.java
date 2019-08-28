package pl.com.pt4q.nlpwebapp.analyzers.english;

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
class Lemmatizer implements AnalyzerInterface<String[]> {

    private StanfordCoreNLP pipeline;
    private Tokenizer tokenizer;

    @Override
    public List<String[]> analyze(String input) {
        return tokenizer.analyze(input).stream()
                .map(t -> getLemma(t))
                .collect(Collectors.toList());
    }

    private String[] getLemma(CoreLabel cl) {
        return new String[]{cl.originalText(), cl.lemma()};
    }

    @Override
    public String listToString(List<String[]> lemmaList) {
       return AnalyzersStringFormatter.listToString(lemmaList);
    }
}
