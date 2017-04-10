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
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TagApiUnitTest extends AuthenticationBase {

    private static final String TAG = "LibraryApiUnitTest";

    @Test
    public void getInfo() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("tag", "alternative");
        LfmRequest request = LfmApi.tag().getInfo(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Tag get info response: " + response.toString());
                Assert.assertNotNull("We should have got a tag back", response.opt("tag"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getSimilar() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("tag", "alternative");
        LfmRequest request = LfmApi.tag().getSimilar(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get similar response: " + response.toString());
                Assert.assertNotNull("Similar tags should exist for this tag", response.optJSONObject("similartags"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getTopAlbums() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("tag", "alternative");
        LfmRequest request = LfmApi.tag().getTopAlbums(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top albums response: " + response.toString());
                Assert.assertNotNull("There should be top albums in the response", response.optJSONObject("albums"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getTopArtists() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("tag", "alternative");
        LfmRequest request = LfmApi.tag().getTopArtists(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top artists response: " + response.toString());
                Assert.assertNotNull("We should have got artists back", response.optJSONObject("topartists"));
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
        LfmRequest request = LfmApi.tag().getTopTags(null);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top artists response: " + response.toString());
                Assert.assertNotNull("Should have got some tags", response.optJSONObject("toptags"));
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
        params.put("tag", "alternative rock");
        params.put("limit", "20");
        params.put("page", "1");
        LfmRequest request = LfmApi.tag().getTopTracks(params);
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

    @Test
    public void getWeeklyChartList() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("tag", "alternative rock");
        LfmRequest request = LfmApi.tag().getWeeklyChartList(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get weekly chart list response: " + response.toString());
                Assert.assertNotNull("Should have got some charts", response.optJSONObject("weeklychartlist"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }
}
