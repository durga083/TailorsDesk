package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;

import java.util.StringTokenizer;

/*
*@Author: T. Durga Kumari
*This class is used to display saved data
 */
public class TailorsDeskDisplayActivity extends Activity{

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaymain);

        Intent intent = getIntent();

        String message = intent.getStringExtra("displayText");

        System.out.println("MESSAGE 2::::"+message);
        StringTokenizer stAllAttributes = new StringTokenizer(message, ",");

        LinearLayout ll = (LinearLayout) findViewById(R.id.info);
        ll.setBackgroundColor(Color.parseColor("#6A5ACD"));
        int i = 0;
        while(stAllAttributes.hasMoreTokens()){
          String eachAttribute = (String) stAllAttributes.nextElement();
            TextView tv1 = new TextView(this);
            tv1.setText(eachAttribute);
            tv1.setId(i);
            tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ((LinearLayout)ll).addView(tv1);
            i++;
    }
        Bundle bundle = intent.getExtras();

        String status = bundle.getString("status");

        Toast toast = Toast.makeText(this, status, Toast.LENGTH_LONG);
        toast.show();

        String picturePath = bundle.getString("imageURL");
        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }

}
