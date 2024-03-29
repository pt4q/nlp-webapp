package pl.com.pt4q.nlpwebapp.analyzers.english;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

class Pipeline {

    private static Properties properties;
    private static String propertiesName= "tokenize, ssplit, pos, lemma, ner, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;

    Pipeline() {
    }

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPipeline() {
        if (stanfordCoreNLP == null)
            stanfordCoreNLP = new StanfordCoreNLP(properties);

        return stanfordCoreNLP;
    }

}
