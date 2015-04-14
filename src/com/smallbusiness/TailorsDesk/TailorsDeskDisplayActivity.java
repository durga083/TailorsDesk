package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.*;

import java.util.StringTokenizer;

/*
*@Author: T. Durga Kumari
*This class is used to display saved data.
* and also to send SMS to customer.
 */
public class TailorsDeskDisplayActivity extends Activity{

    private String phoneNo;
    private Button buttonSend;
    private Button builtInSmsButton;
    private String smsText;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tddisplaymain);

        Intent intent = getIntent();

        buttonSend = (Button) findViewById(R.id.sendSMSButton);
        builtInSmsButton = (Button) findViewById(R.id.sendBuildInSMSButton);

        String message = intent.getStringExtra("displayText");
        smsText = message;

        StringTokenizer stAllAttributes = new StringTokenizer(message, ",");

        LinearLayout ll = (LinearLayout) findViewById(R.id.info);
        ll.setBackgroundColor(Color.parseColor("#6A5ACD"));
        int i = 0;
        while(stAllAttributes.hasMoreTokens()){
          String eachAttribute = (String) stAllAttributes.nextElement();
            TextView tv1 = new TextView(this);
            tv1.setText(eachAttribute);
            //check for phone number and get it
            if (eachAttribute.contains("Phone Number")) {
                String[] parsedPHNo = eachAttribute.split(":");
                phoneNo = parsedPHNo[1];
            }
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

        sendSMS();


    }

    /*
    *This method is used get Bitmap for the image
    * This reduces the size of the image
     */
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

    /*
    *This method sends SMS to customer
     */
    private void sendSMS() {

        //sendSMS
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, smsText, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        //built in SMS launcher
        builtInSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.putExtra("sms_body", smsText);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(smsIntent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
