package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        imageView.setImageBitmap(loadImage(picturePath));
        imageView.invalidate();
        imageView.setVisibility(View.VISIBLE);
    }

    private Bitmap loadImage(String imgPath) {
        BitmapFactory.Options options;
        try {
            options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
