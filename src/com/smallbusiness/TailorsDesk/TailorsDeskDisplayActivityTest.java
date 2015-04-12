package com.smallbusiness.TailorsDesk;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class TailorsDeskDisplayActivityTest extends ActivityInstrumentationTestCase2<TailorsDeskDisplayActivity> {

    private TailorsDeskDisplayActivity mTailorsDeskDisplayActivity;
    private EditText imageVal;

    public TailorsDeskDisplayActivityTest() {
        super(TailorsDeskDisplayActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mTailorsDeskDisplayActivity = getActivity();
        imageVal =
                (EditText) mTailorsDeskDisplayActivity
                        .findViewById(R.id.imageVal);
    }

    public void testPreconditions() {
        assertNotNull("mTailorsDeskDisplayActivity is null", mTailorsDeskDisplayActivity);
        assertNotNull("imageVal is null", imageVal);
    }

    public void testmTailorsDeskDisplayActivity_imageValCheck() {
        EditText imageVal = (EditText) mTailorsDeskDisplayActivity.findViewById(R.id.imageVal);
        assertEquals("", imageVal);
    }

}