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
public class ChartApiUnitTest extends AuthenticationBase {

    private static final String TAG = "ChartApiUnitTest";

    @Test
    public void getTopArtists() throws Exception {
        LfmRequest request = LfmApi.charts().getTopArtists();
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top artists response: " + response.toString());
                Assert.assertNotNull("We should have got artists back", response.optJSONObject("artists"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getTopTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("page", "1");
        params.put("limit", "20");
        LfmRequest request = LfmApi.charts().getTopTags(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top tags response: " + response.toString());
                Assert.assertNotNull("There should be tags sent", response.optJSONObject("tags"));
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
        params.put("limit", "20");
        params.put("page", "1");
        LfmRequest request = LfmApi.charts().getTopTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top tracks response: " + response.toString());
                try {
                    JSONObject toptracks = response.getJSONObject("tracks");
                    JSONObject attr = toptracks.getJSONObject("@attr");
                    Assert.assertEquals("Should have retrieved 20 tracks", "20", attr.getString("perPage"));
                    Assert.assertEquals("Should be on page 1", "1", attr.getString("page"));
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                    Assert.assertTrue("Failed to parse object", false);
                }
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }
}
