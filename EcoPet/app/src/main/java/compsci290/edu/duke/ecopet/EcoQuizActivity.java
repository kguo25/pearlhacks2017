package compsci290.edu.duke.ecopet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Katherine on 2/11/2017.
 */

public class EcoQuizActivity extends Activity{
    private static EcoQuiz q;
    private int mIndex;
    private Button b1, b2, b3, b4, b5;
    private TextView myQuestionView;
    private TextView mySectionView;
    private HashMap<String, Integer> scores;

    private static String INDEX = "INDEX";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        scores = new HashMap<>();

        myQuestionView = (TextView) this.findViewById(R.id.question_text);
        mySectionView = (TextView) this.findViewById(R.id.section_text);
        b1 = (Button) this.findViewById(R.id.button1);
        b2 = (Button) this.findViewById(R.id.button2);
        b3 = (Button) this.findViewById(R.id.button3);
        b4 = (Button) this.findViewById(R.id.button4);
        b5 = (Button) this.findViewById(R.id.button5);
        createQuiz(getApplicationContext());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        mIndex = savedInstanceState.getInt(INDEX);
        askQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        state.putInt(INDEX,mIndex);
    }

    private void createQuiz(Context context) {
        try {
            InputStream is = context.getAssets().open("quizzes/eco_quiz.xml");
            q = (new QuizParser().parse(is));

        } catch (Exception e) {
            e.printStackTrace();
        }
        mIndex = 0;
        askQuestion();
    }

    public void askQuestion() {
        if(mIndex < q.size()) {
            Question curq = q.getQuestion(mIndex);
            System.out.println("Importance = " + curq.getImportance());
            System.out.println("Top = " + curq.topGood());
            myQuestionView.setText(curq.getText());
            mySectionView.setText("Section: " + curq.getSection());
            String[] answers = curq.getAnswers();
            b1.setText(answers[0]);
            b2.setText(answers[1]);
            b3.setText(answers[2]);
            b4.setText(answers[3]);
            b5.setText(answers[4]);
        }
    }

    public void click(View v) {
        /*
        Question curq = q.getQuestion(mIndex);
        String section = curq.getSection();
        if(!scores.containsKey(section)) {
            scores.put(section, 0);
        }
        calcScore(curq);
        */

        if(mIndex < q.size())
            mIndex++;
            askQuestion();
    }

    public void calcScore(Question curq) {
        String section = curq.getSection();

       // int score = scores.get(section) + curq.getImportance()*
                //scores.put(section,
    }

    public boolean getButton() {
        return true;
    }
}
