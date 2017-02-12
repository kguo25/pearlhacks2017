package compsci290.edu.duke.ecopet;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Katherine on 2/11/2017.
 */

public class FoxHomeActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fox_home);

        SharedPreferences prefs = getSharedPreferences("Sections", MODE_PRIVATE);
        int total = prefs.getInt("Total", 0);
        RelativeLayout l = (RelativeLayout)findViewById(R.id.fox_home_id);
        TextView t = (TextView)findViewById(R.id.fox_message);
        if(total <= 35) {
            launchHappyFox(l, t);
        }
        else {
            launchSadFox(l,t);
        }
    }

    public void launchHappyFox(RelativeLayout l, TextView t) {

        l.setBackground(getResources().getDrawable(R.drawable.fox_house));
        t.setText("Thanks for being an eco-friendly citizen! The fox is very happy.");
    }

    public void launchSadFox(RelativeLayout l, TextView t) {
        l.setBackground(getResources().getDrawable(R.drawable.fox_dirtyhouse));
        t.setText("You can improve in being a more eco-friedly citizen. The fox is very sad right now.");
    }

    public void improve(View v) {

    }

    public void updateScore(View v) {

    }


}
