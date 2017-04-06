package com.ag.testapplication.library.suite;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ag.lfm.Lfm;
import com.ag.lfm.LfmError;
import com.ag.lfm.Session;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by 4-Eyes on 05/04/2017.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public abstract class AuthenticationBase {

    private static final String TAG = "AuthenticationBaseTest";

    // This is a real account, but is only used for testing.
    static final String TEST_USERNAME = "AlmightyTaniwha";
    static final String TEST_PASSWORD = "4b$c6Tz1XK^JtNqRn";

    @BeforeClass
    public static void setUp() throws Exception{
        try {
            Lfm.initializeWithSecret(InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Lfm.login(TEST_USERNAME, TEST_PASSWORD, new Lfm.LfmCallback<Session>() {
            @Override
            public void onResult(Session result) {
                Log.i(TAG, "Successfully logged in for testing");
            }

            @Override
            public void onError(LfmError error) {
                Log.i(TAG, "Failed to log in for testing");
            }
        }).get();
    }

    @AfterClass
    public static void tearDown() {
        Lfm.logout();
    }
}
