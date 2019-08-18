package pl.com.pt4q.nerwebapp.rest_controllers;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.web.bind.annotation.*;
import pl.com.pt4q.nerwebapp.analyzers.Pipeline;
import pl.com.pt4q.nerwebapp.analyzers.ner.NERFinder;
import pl.com.pt4q.nerwebapp.analyzers.ner.TypeEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class NERRestController {

    @PostMapping(value = "/ner")
    public Set<String> ner(@RequestBody final String input, @RequestParam TypeEnum type) {
        StanfordCoreNLP pipeline = Pipeline.getPipeline();
        NERFinder finder = new NERFinder(pipeline);

        return new HashSet<String>(getWordsByType(finder.analyze(input), type));
    }

    private List<String> getWordsByType(List<CoreLabel> coreLabels, TypeEnum type) {
        return coreLabels.stream()
                .filter(cl -> type.getType().equalsIgnoreCase(cl.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }

}