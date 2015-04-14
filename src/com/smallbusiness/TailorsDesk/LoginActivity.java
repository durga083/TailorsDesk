package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by kumarid on 4/14/2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {


    private Button tdlogin;
    private Button mtlogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginmain);

        tdlogin = (Button) findViewById(R.id.tdLogin);
        tdlogin.setOnClickListener(this);

        mtlogin = (Button) findViewById(R.id.mtLogin);
        mtlogin.setOnClickListener(this);

    }

    /*
    * All actions related to onClick on the View is handled in this method
    *
     */
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tdLogin:
                Intent intent = new Intent(getApplicationContext(), TailorsDeskActivity.class);
                startActivity(intent);
                break;

            case R.id.mtLogin:
                Intent i = new Intent(getApplicationContext(), TDTailorActivity.class);
                startActivity(i);
                break;
        }
    }
}
