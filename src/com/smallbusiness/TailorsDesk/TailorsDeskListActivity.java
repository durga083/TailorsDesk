package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/*
*@Author: T. Durga Kumari
*This class is used to list saved data
 */
public class TailorsDeskListActivity extends Activity {
    String[] savedFiles;

    /*
    *This method updates the view based on the list size
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tdlistmain);
        showSavedFiles();
    }

    /*
    *This method gets all the files list to display.
     */
    void showSavedFiles() {

        try {
            savedFiles = getApplicationContext().fileList();
        } catch (Exception e) {
            e.printStackTrace();
            savedFiles = new String[0];
        }

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                savedFiles);

        ListView listView = (ListView) findViewById(R.id.listVal);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
    }
}
