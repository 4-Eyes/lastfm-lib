package com.ag.testapplication.library.suite;

import android.support.test.filters.LargeTest;
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
 * Test for the functions of the album api
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AlbumApiUnitTest extends AuthenticationBase {

    private static final String TAG = "AlbumApiUnitTest";

    @Test
    public void addTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Muse");
        params.put("album", "Drones");
        params.put("tags", "rock,alternative rock");
        LfmRequest request = LfmApi.album().addTags(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Add tags response: " + response.toString());
                Assert.assertEquals("Response should be empty for success", "{}", response.toString());
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getInfo() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        params.put("album", "Dreams");
        params.put("username", "BlueImbecile");
        LfmRequest request = LfmApi.album().getInfo(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get info response: " + response.toString());
                try {
                    JSONObject album = response.getJSONObject("album");
                    Assert.assertNotNull("The album shouldn't be null", album);
                    Assert.assertTrue("The play count for this user should be over 40", Integer.valueOf(album.getString("userplaycount")) > 40);
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

    @Test
    public void getTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Muse");
        params.put("album", "Drones");
        LfmRequest request = LfmApi.album().getTags(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get tags response: " + response.toString());
                Assert.assertNotNull("The response should contain tags", response.optJSONObject("tags"));
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
        params.put("artist", "Muse");
        params.put("album", "Drones");
        LfmRequest request = LfmApi.album().getTopTags(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top tags response: " + response.toString());
                Assert.assertNotNull("The response should contain tags", response.optJSONObject("toptags"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void removeTag() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Muse");
        params.put("album", "Drones");
        params.put("tags", "hard rock");
        LfmRequest request = LfmApi.album().addTags(params);
        request.executeWithListener(null).get();

        params = new LfmParameters();
        params.put("artist", "Muse");
        params.put("album", "Drones");
        params.put("tag", "hard rock");
        request = LfmApi.album().removeTag(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Remove tag response: " + response.toString());
                Assert.assertEquals("Response should be empty for success", "{}", response.toString());
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void search() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("limit", "20");
        params.put("page", "1");
        params.put("album", "Land of Light");
        final LfmRequest request = LfmApi.album().search(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Search response: " + response.toString());
                try {
                    JSONObject results = response.getJSONObject("results");
                    Assert.assertEquals("The number of items per page should be 20", "20", results.getString("opensearch:itemsPerPage"));
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
