package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View.OnClickListener;

import java.io.*;
import java.util.Calendar;
import com.smallbusiness.TailorsDesk.R;

/* @Author: T. Durga Kumari
* This class is for first activity where user will be providing input and
* data will be saved, display, clear.
 */
public class TailorsDeskActivity extends Activity implements OnItemSelectedListener, OnClickListener {

        Spinner stitchStyleSpinner;
        private String[] stitchStyles = { "Salwar", "Kurta", "Gown" };
        EditText customerNameVal;
        EditText phoneNumberVal;
        EditText saveDataVal;
    TextView stitchStyleVal;
    TextView responseTextVal;
    private static int RESULT_LOAD_IMAGE = 1;

    EditText itemCountVal;
    EditText commentsVal;
    EditText lengthVal;
    EditText shoulderVal;
    EditText imageVal;

    File internalFile = null;


    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private TextView tvDisplayDeliveryDate;
    private DatePicker dpDeliveryResult;
    private Button btnChangeDeliveryDate;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;

    static final int DELIVERY_DATE_DIALOG_ID = 99;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setCurrentDateOnView();
        addListenerOnButton();

        internalFile = new File(getFilesDir(), getFileName());
        customerNameVal = (EditText) findViewById(R.id.customerNameVal);
        phoneNumberVal = (EditText) findViewById(R.id.phoneNumberVal);
        stitchStyleVal = (TextView) findViewById(R.id.stitchStyleVal);
        saveDataVal = (EditText) findViewById(R.id.saveDataVal);
        responseTextVal = (TextView) findViewById(R.id.responseTextVal);

        itemCountVal = (EditText) findViewById(R.id.itemCountVal);
        commentsVal = (EditText) findViewById(R.id.commentsVal);
        lengthVal = (EditText) findViewById(R.id.lengthVal);
        shoulderVal = (EditText) findViewById(R.id.shoulderVal);
        imageVal = (EditText) findViewById(R.id.imageVal);

        View.OnClickListener editTextValListener = new View.OnClickListener()  {
            public void onClick(View v) {
                TapEditText(v);
            }
        };

        Button clearFields = (Button) findViewById(R.id.clearFields);
        clearFields.setOnClickListener(this);

        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(this);


        View.OnFocusChangeListener editTextValFocusListener = new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean gainFocus) {
            onFocusEvent(v, gainFocus);
            }
        };

        customerNameVal.setOnFocusChangeListener(editTextValFocusListener);
        phoneNumberVal.setOnFocusChangeListener(editTextValFocusListener);
        itemCountVal.setOnFocusChangeListener(editTextValFocusListener);
        commentsVal.setOnFocusChangeListener(editTextValFocusListener);
        lengthVal.setOnFocusChangeListener(editTextValFocusListener);
        shoulderVal.setOnFocusChangeListener(editTextValFocusListener);

        Button saveToInternalStorage =
                (Button) findViewById(R.id.saveInternalStorage);
        saveToInternalStorage.setOnClickListener(this);

        Button readFromInternalStorage =
                (Button) findViewById(R.id.getInternalStorage);
        readFromInternalStorage.setOnClickListener(this);


        stitchStyleSpinner = (Spinner) findViewById(R.id.stitchStyleValSpinner);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, stitchStyles);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stitchStyleSpinner.setAdapter(adapter_state);
        stitchStyleSpinner.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        stitchStyleSpinner.setSelection(position);
       String stitchType = (String) stitchStyleSpinner.getSelectedItem();
        stitchStyleVal.setText(stitchType);
    }

    private String getFileName(){
        int i = 0;
        if(fileList() != null){
             i = fileList().length;
        }
        String filename = "TD"+i;
        return filename;
    }

    public void onClick(View v) {

        String myData = "";

        EditText customerNameVal = (EditText) findViewById(R.id.customerNameVal);
        EditText phoneNumberVal = (EditText) findViewById(R.id.phoneNumberVal);
        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        TextView tvDeliveryDate = (TextView) findViewById(R.id.tvDeliveryDate);

        saveDataVal.setText("Customer Name : " + customerNameVal.getText().toString()+",Phone Number : "+phoneNumberVal.getText().toString()
                +",Stitch Style : "+stitchStyleVal.getText().toString()
                        +",Received Date : "+tvDisplayDate.getText().toString()
                       +",Item Count : "+itemCountVal.getText().toString()
                        +",Length : "+lengthVal.getText().toString()
                        +",Shoulder : "+shoulderVal.getText().toString()
                        +",Delivery Date : "+tvDeliveryDate.getText().toString()
                        +",Comments : "+commentsVal.getText().toString()


        );

        switch (v.getId()) {
            case R.id.saveInternalStorage:
                try {
                    FileOutputStream fos = new FileOutputStream(internalFile);
                    fos.write(saveDataVal.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveDataVal.setText("");

                AlertDialog ad = new AlertDialog.Builder(this).create();
                ad.setCancelable(false); // This blocks the 'BACK' button
                ad.setMessage("Saved Successfully!");
                ad.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();

                break;

            case R.id.getInternalStorage:
                try {
                    FileInputStream fis = new FileInputStream(internalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveDataVal.setText(myData);

                responseTextVal
                        .setText(myData);
                responseTextVal.setVisibility(View.GONE);

                Intent intent = new Intent(getApplicationContext(), TailorsDeskDisplayActivity.class);
                intent.putExtra("displayText", myData);
                Bundle extras = new Bundle();
                extras.putString("status", "VIEW!");
                extras.putString("imageURL", imageVal.getText().toString());
                intent.putExtras(extras);
                startActivity(intent);
                break;
            case R.id.buttonLoadPicture:
                     Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;

            case R.id.clearFields:
                customerNameVal.setText("");
                phoneNumberVal.setText("");
                itemCountVal.setText("");
                commentsVal.setText("");
                lengthVal.setText("");
                shoulderVal.setText("");
                stitchStyleSpinner.setSelection(0);
                ImageView imageView = (ImageView) findViewById(R.id.imgView);
                imageView.setImageBitmap(null);
                imageView.destroyDrawingCache();
                imageVal.setText("");
                setCurrentDateOnView();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void TapEditText(View v){
        if(v == customerNameVal) {
            customerNameVal.setText("");
        }
        if(v == phoneNumberVal) {
            phoneNumberVal.setText("");
        }
        if(v == itemCountVal) {
            itemCountVal.setText("");
        }
        if(v == commentsVal) {
            commentsVal.setText("");
        }
        if(v == lengthVal) {
            lengthVal.setText("");
        }
        if(v == shoulderVal) {
            shoulderVal.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            imageVal.setText(picturePath);

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trimCache() {
        try {
            File dir = getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir)
    {
        if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
                return false;
            }
        }
    }
        return dir.delete();
    }

    private void onFocusEvent(View v, boolean gainFocus){
        //onFocus
        if (gainFocus) {
            ((View) v.getParent()).setBackgroundColor(Color.rgb(0, 128, 128));
        }
        //onBlur
        else {
            ((View) v.getParent()).setBackgroundColor(Color.rgb(176, 176, 176));
        }
    }

    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        tvDisplayDeliveryDate = (TextView) findViewById(R.id.tvDeliveryDate);
        dpDeliveryResult = (DatePicker) findViewById(R.id.dpDeliveryResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvDisplayDate.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        dpResult.init(year, month, day, null);

        tvDisplayDeliveryDate.setText(new StringBuilder()
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        dpDeliveryResult.init(year, month, day, null);

    }

    public void addListenerOnButton() {

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);
                DatePicker dpResult = (DatePicker) findViewById(R.id.dpResult);
                dpResult.setVisibility(View.VISIBLE);

            }

        });

        btnChangeDeliveryDate = (Button) findViewById(R.id.btnChangeDeliveryDate);

        btnChangeDeliveryDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DELIVERY_DATE_DIALOG_ID);
                DatePicker dpDeliveryResult = (DatePicker) findViewById(R.id.dpDeliveryResult);
                dpDeliveryResult.setVisibility(View.VISIBLE);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case DELIVERY_DATE_DIALOG_ID:
                return new DatePickerDialog(this, deliveryDatePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

                    tvDisplayDate.setText(new StringBuilder().append(month + 1)
                            .append("-").append(day).append("-").append(year)
                            .append(" "));

                    dpResult.init(year, month, day, null);

                    dpResult.setVisibility(View.GONE);

        }
    };

    private DatePickerDialog.OnDateSetListener deliveryDatePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            tvDisplayDeliveryDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            dpDeliveryResult.init(year, month, day, null);

            dpDeliveryResult.setVisibility(View.GONE);

        }
    };
}