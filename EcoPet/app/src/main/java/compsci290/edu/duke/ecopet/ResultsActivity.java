package compsci290.edu.duke.ecopet;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;


/**
 * Created by Emily on 2/11/2017.
 */

public class ResultsActivity extends Activity {

    PieChart pieChart;

    public static final int[] PIE_COLORS = {
            Color.rgb(64,99,37), Color.rgb(141,185,54), Color.rgb(170,116,85)
    };

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

        if (totalScore != 0) {
            //add values to pie chart
            pieChart = (PieChart) findViewById(R.id.pieChart);

            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            pieChart.setHoleRadius(40f);
            pieChart.setTransparentCircleRadius(0);
            pieChart.setDrawCenterText(true);
            pieChart.setCenterText("Score Distribution");
            pieChart.setCenterTextSize(20);

            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            entries.add(new PieEntry((float) portScore / totalScore, "Transportation"));
            entries.add(new PieEntry((float) utilScore / totalScore, "Utilities"));
            entries.add(new PieEntry((float) foodScore / totalScore, "Food"));

            PieDataSet pieSet = new PieDataSet(entries, "Pie Chart");
            pieSet.setColors(PIE_COLORS);

            PieData data = new PieData(pieSet);

            //formatting
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(12f);
            data.setValueTextColor(Color.WHITE);
            pieChart.getLegend().setEnabled(false);
            pieChart.setData(data);
            pieChart.invalidate(); // refresh
        }else{

        }


    }

}
