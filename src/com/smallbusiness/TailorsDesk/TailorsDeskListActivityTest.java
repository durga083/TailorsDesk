package com.smallbusiness.TailorsDesk;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class TailorsDeskListActivityTest extends ActivityInstrumentationTestCase2<TailorsDeskListActivity> {

    private TailorsDeskListActivity mTailorsDeskListActivity;
    private ListView listVal;

    public TailorsDeskListActivityTest() {
        super(TailorsDeskListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mTailorsDeskListActivity = getActivity();
        listVal =
                (ListView) mTailorsDeskListActivity
                        .findViewById(R.id.listVal);
    }

    public void testPreconditions() {
        assertNotNull("TailorsDeskListActivity is null", mTailorsDeskListActivity);
        assertNotNull("listVal is null", listVal);
    }

    public void testShowSavedFiles_listValCheck() {
        ListView listVal = (ListView) mTailorsDeskListActivity.findViewById(R.id.listVal);
        assertNotNull(listVal);
    }

}