package com.ag.testapplication.library.suite;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ag.lfm.Lfm;
import com.ag.lfm.LfmError;
import com.ag.lfm.Session;
import com.ag.testapplication.MainActivity;
import com.ag.testapplication.TestApplication;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.Assert;

import java.util.concurrent.ExecutionException;

/**
 * Created by 4-Eyes on 05/04/2017.
 *
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class AuthenticationUnitTest {

    private static final String TAG = "AuthenticationUnitTest";

    @BeforeClass
    public static void setUp() {
        try {
            Lfm.initializeWithSecret(InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTestFail() {
        try {
            Lfm.login(AuthenticationBase.TEST_USERNAME, "", new Lfm.LfmCallback<Session>() {
                @Override
                public void onResult(Session result) {
                    Log.wtf(TAG, "This should not happen");
                }

                @Override
                public void onError(LfmError error) {
                    Log.i(TAG, "Error Message: " + error.errorMessage);
                    Log.i(TAG, "Error Reason: " + error.errorReason);
                    Log.i(TAG, "Error Code: " + error.errorCode);
                    Log.i(TAG, "Is Http Client Error: " + error.httpClientError);
                    Assert.assertEquals("Error code should be 6", 6, error.errorCode);
                    Assert.assertEquals("Error message should be 'Your request is missing a required parameter'", "Your request is missing a required parameter", error.errorMessage);
                    Assert.assertEquals("Error reason should be 'Invalid parameters'", "Invalid parameters", error.errorReason);
                    Assert.assertFalse("This should not be a http client error", error.httpClientError);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTestFail2() {
        try {
            Lfm.login(AuthenticationBase.TEST_USERNAME, "lol", new Lfm.LfmCallback<Session>() {
                @Override
                public void onResult(Session result) {
                    Log.wtf(TAG, "This should not happen");
                }

                @Override
                public void onError(LfmError error) {
                    Log.i(TAG, "Error Message: " + error.errorMessage);
                    Log.i(TAG, "Error Reason: " + error.errorReason);
                    Log.i(TAG, "Error Code: " + error.errorCode);
                    Log.i(TAG, "Is Http Client Error: " + error.httpClientError);
                    Assert.assertEquals("Error code should be 4", 4, error.errorCode);
                    Assert.assertEquals("Error message should be 'You do not have permissions to access the service'", "You do not have permissions to access the service", error.errorMessage);
                    Assert.assertEquals("Error reason should be 'Authentication Failed'", "Authentication Failed", error.errorReason);
                    Assert.assertFalse("This should not be a http client error", error.httpClientError);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTestSucceed() {
        try {
            Lfm.login(AuthenticationBase.TEST_USERNAME, AuthenticationBase.TEST_PASSWORD, new Lfm.LfmCallback<Session>() {
                @Override
                public void onResult(Session result) {
                    Assert.assertNotNull(Session.sessionkey);
                    Assert.assertNotNull(Session.subscriber);
                    Assert.assertNotNull(Session.username);
                }

                @Override
                public void onError(LfmError error) {
                    Log.wtf(TAG, "This should not happen");
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        Lfm.logout();
    }
}
