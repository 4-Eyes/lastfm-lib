package com.ag.testapplication.library.suite;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ag.lfm.LfmError;
import com.ag.lfm.LfmParameters;
import com.ag.lfm.LfmRequest;
import com.ag.lfm.api.LfmApi;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by 4-Eyes on 07/04/2017.
 *
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class LibraryApiUnitTest extends AuthenticationBase {

    private static final String TAG = "LibraryApiUnitTest";

    @Test
    public void getArtists() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.library().getArtists(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get artists response: " + response.toString());
                Assert.assertNotNull("The response should contain artists: ", response.optJSONObject("artists"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }
}
