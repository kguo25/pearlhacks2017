package compsci290.edu.duke.ecopet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


/**
 * Created by Emily on 2/11/2017.
 */

public class ResultsActivity extends Activity {

    PieChart pieChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        //get the category scores
        SharedPreferences prefs = getSharedPreferences("Sections", MODE_PRIVATE);
        int portScore = prefs.getInt("Transportation", 0);
        int utilScore = prefs.getInt("Utilities", 0);
        int foodScore = prefs.getInt("Food", 0);
        int totalScore = portScore + utilScore + foodScore;

        //add values to pie chart
        pieChart = (PieChart) findViewById(R.id.pieChart);

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            entries.add(new PieEntry((float)portScore/totalScore, "Transportation"));
            entries.add(new PieEntry((float)utilScore/totalScore, "Utilities"));
            entries.add(new PieEntry((float)foodScore/totalScore, "Food"));

        PieDataSet pieSet = new PieDataSet(entries, "Pie Chart");
        PieData data = new PieData(pieSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

    }

}
