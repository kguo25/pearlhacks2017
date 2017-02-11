package compsci290.edu.duke.ecopet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine on 2/11/2017.
 */

public class Question {
    private List<String> choices = new ArrayList<>();
    private String quest = "";
    private String section = "";
    private boolean topgood;
    private int importance;

    public Question(String q, String sec, String good, String imp, List<String> answers) {
        quest = q;
        choices = answers;
        section = sec;
        if(good.equals("top")) topgood = true;
        importance = Integer.parseInt(imp);
    }

    public String getText() {
        return quest;
    }

    public String getSection() {
        return section;
    }

    public int getImportance() {
        return importance;
    }

    public boolean topGood() {
        return topgood;
    }

    public String [] getAnswers() {
        return choices.toArray(new String [0]);
    }
}
