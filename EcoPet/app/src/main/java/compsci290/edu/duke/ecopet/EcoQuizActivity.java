package compsci290.edu.duke.ecopet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
        mIndex = savedInstanceState.getInt(INDEX);/*
        scores.put("Transportation", savedInstanceState.getInt("Transportation"));
        scores.put("Food", savedInstanceState.getInt("Food"));
        scores.put("Utilities", savedInstanceState.getInt("Utilities"));*/
        askQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        state.putInt(INDEX,mIndex);/*
        for(String key : scores.keySet()) {
            state.putInt(key, scores.get(key));
        }*/
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
            ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
            int progress = (int) ((double)mIndex/q.size() * 100);
            pb.setProgress(progress);
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
        System.out.println("mindex" + mIndex);
        calcScore(q.getQuestion(mIndex), v);
        mIndex++;
        if(mIndex < q.size()) {
            askQuestion();
        }
        else {
            savePreferences();
            Intent i = new Intent(EcoQuizActivity.this, ResultsActivity.class);
            startActivity(i);
            this.finish();
        }
    }

    public void savePreferences() {
        SharedPreferences prefs = getSharedPreferences("Sections", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        for(String key : scores.keySet()) {
            editor.putInt(key, scores.get(key));
        }
        editor.commit();
    }

    public void calcScore(Question curq, View v) {
        boolean top = curq.topGood();
        int mult = 1;
        if (!top) mult = -1;
        String section = curq.getSection();
        int importance = curq.getImportance();
        System.out.println("mult is " + mult);
        switch(v.getId()) {
            case R.id.button1:
                if (!scores.keySet().contains(section)) {
                    scores.put(section, 0);
                }
                scores.put(section, importance*(((6+mult*1) % 6)-1) + scores.get(section));
                break;
            case R.id.button2:
                if (!scores.keySet().contains(section)) {
                    scores.put(section, 0);
                }
                scores.put(section, importance*(((6+mult*2) % 6)-1) + scores.get(section));
                break;
            case R.id.button3:
                if (!scores.keySet().contains(section)) {
                    scores.put(section, 0);
                }
                scores.put(section, importance*(((6+mult*3) % 6)-1) + scores.get(section));
                break;
            case R.id.button4:
                if (!scores.keySet().contains(section)) {
                    scores.put(section, 0);
                }
                scores.put(section, importance*(((6+mult*4) % 6)-1) + scores.get(section));
                break;
            case R.id.button5:
                if (!scores.keySet().contains(section)) {
                    scores.put(section, 0);
                }
                scores.put(section, importance*(((6+mult*5) % 6)-1) + scores.get(section));
                break;
            default:
                break;

        }
        System.out.println(scores.get(section) + "");
    }
}
