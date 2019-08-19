package pl.com.pt4q.nlpwebapp.analyzers.ner;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeEnum {

    PERSON("Person"),
    CITY("City"),
    STATE_OR_PROVINCE("State_Or_Province"),
    COUNTRY("Country"),
    EMAIL("Email"),
    TITLE("Title");

    String type;
}
