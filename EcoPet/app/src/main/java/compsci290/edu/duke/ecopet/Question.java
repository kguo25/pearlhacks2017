package compsci290.edu.duke.ecopet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine on 2/11/2017.
 */

public class Question {
    List<String> choices = new ArrayList<>();
    String quest = "";
    String section = "";

    public Question(String q, String sec, List<String> answers) {
        quest = q;
        choices = answers;
        section = sec;
    }

    public String getText() {
        return quest;
    }

    public String getSection() {
        return section;
    }

    public String [] getAnswers() {
        return choices.toArray(new String [0]);
    }
}
