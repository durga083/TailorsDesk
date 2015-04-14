package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by kumarid on 4/14/2015.
 */
public class TDTailorActivity extends Activity implements
        RatingBar.OnRatingBarChangeListener {
    RatingBar getRatingBar;
    RatingBar setRatingBar;
    TextView countText;
    int count;
    float curRate;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tdtailormain);

        findViewsById();

        setRatingBar.setRating(curRate);
        getRatingBar.setOnRatingBarChangeListener(this);
    }

    private void findViewsById() {
        getRatingBar = (RatingBar) findViewById(R.id.getRating);
        setRatingBar = (RatingBar) findViewById(R.id.setRating);
        countText = (TextView) findViewById(R.id.countText);
    }

    public void onRatingChanged(RatingBar rateBar, float rating,
                                boolean fromUser) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        curRate = Float.valueOf(decimalFormat.format((curRate * count + rating)
                / ++count));
        Toast.makeText(TDTailorActivity.this,
                "New Rating: " + curRate, Toast.LENGTH_SHORT).show();
        setRatingBar.setRating(curRate);
        countText.setText(count + " Ratings");
    }

}
