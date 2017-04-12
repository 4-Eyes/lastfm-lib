package com.last.testapplication.library.suite;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.last.fm.api.LfmError;
import com.last.fm.api.LfmParameters;
import com.last.fm.api.LfmRequest;
import com.last.fm.api.LfmApi;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by 4-Eyes on 07/04/2017.
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GeoApiUnitTest extends AuthenticationBase {

    private static final String TAG = "GeoApiUnitTest";

    @Test
    public void getTopArtists() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("country", "New Zealand");
        LfmRequest request = LfmApi.geo().getTopArtists(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top artists response: " + response.toString());
                Assert.assertNotNull("We should have got top artists", response.optJSONObject("topartists"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getTopTracks() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("country", "New Zealand");
        LfmRequest request = LfmApi.geo().getTopTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top artists response: " + response.toString());
                Assert.assertNotNull("We should have got top tracks", response.optJSONObject("tracks"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }
}
