package pl.com.pt4q.nlpwebapp.analyzers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzersStringFormatter {

    public static String listToString(List<String[]> listOfStringArr){
        return listOfStringArr.stream()
                .map(sa -> sa[0] + "\t" + sa[1] + "\n")
                .collect(Collectors.joining());
    }
}
