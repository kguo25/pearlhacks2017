package compsci290.edu.duke.ecopet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katherine on 2/11/2017.
 */

public class EcoQuiz {

    private String title = "Your Eco Score Quiz";
    private List<Question> questions;
    private int mIndex;

    public EcoQuiz(List<Question> quests) {
        questions = quests;
    }

    public Question getQuestion(int mIndex) {
        if(mIndex < questions.size()) {
            Question q = questions.get(mIndex);
            mIndex++;
            return q;
        }
        return null;
    }

    public int size() {
        return questions.size();
    }

    public String getTitle() {
        return title;
    }

}
