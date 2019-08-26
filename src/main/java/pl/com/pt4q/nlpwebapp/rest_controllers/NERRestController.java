package pl.com.pt4q.nlpwebapp.rest_controllers;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import org.springframework.web.bind.annotation.*;
import pl.com.pt4q.nlpwebapp.analyzers.AnalyzerService;
import pl.com.pt4q.nlpwebapp.analyzers.TypeEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class NERRestController {

    @PostMapping(value = "/ner")
    public Set<String> ner(@RequestBody final String input, @RequestParam TypeEnum type) {
        AnalyzerService analyzerService = AnalyzerService.getInstance();

        return new HashSet<String>(getWordsByType(analyzerService.ner(input), type));
    }

    private List<String> getWordsByType(List<CoreLabel> coreLabels, TypeEnum type) {
        return coreLabels.stream()
                .filter(cl -> type.getType().equalsIgnoreCase(cl.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }

}
