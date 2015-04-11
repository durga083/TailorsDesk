package com.smallbusiness.TailorsDesk;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;


public class TailorsDeskActivityTest extends ActivityInstrumentationTestCase2<TailorsDeskActivity> {
    private TailorsDeskActivity mainActivity = null;
    private Activity testActivity = null;
    private View mainLayout;
    private View rootView = null;

    public TailorsDeskActivityTest() {
        super("com.smallbusiness.TailorsDesk", TailorsDeskActivity.class);

    }

    public void testPreconditions() {
        assertNotNull("mainActivity is null", mainActivity);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        testActivity = new Activity();
        rootView = ((Activity) mainActivity.getApplicationContext()).getWindow().getDecorView().findViewById(android.R.id.content);

    }


    public void testOnClick() {
        //todo: need to write testcases
    }
}