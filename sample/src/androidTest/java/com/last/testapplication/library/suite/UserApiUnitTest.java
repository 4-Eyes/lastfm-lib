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
public class UserApiUnitTest {

    private static final String TAG = "UserApiUnitTest";

    @Test
    public void getArtistTracks() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user","BlueImbecile");
        params.put("artist","VersaEmerge");
        LfmRequest request = LfmApi.user().getArtistTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get artist tracks response: " + response.toString());
                Assert.assertNotNull("We should have got some tracks back", response.optJSONObject("artisttracks"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getFriends() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user","BlueImbecile");
        params.put("recenttracks","1");
        LfmRequest request = LfmApi.user().getFriends(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get friends response: " + response.toString());
                Assert.assertNotNull("We should have got some friends back", response.optJSONObject("friends"));
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
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getInfo(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "User get info response: " + response.toString());
                Assert.assertNotNull("We should have got a user back", response.opt("user"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getLovedTracks() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getLovedTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "User get loved tracks response: " + response.toString());
                Assert.assertNotNull("We should have got loved tracks back", response.opt("lovedtracks"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getPersonalTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        params.put("tag", "indie rock");
        params.put("taggingtype", "track");
        LfmRequest request = LfmApi.user().getPersonalTags(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "User get personal tags response: " + response.toString());
                Assert.assertNotNull("We should have got personal tags back", response.opt("taggings"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getRecentTracks() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        params.put("extended", "1");
        LfmRequest request = LfmApi.user().getRecentTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "User get recent tracks response: " + response.toString());
                Assert.assertNotNull("We should have got recent tracks back", response.opt("recenttracks"));
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
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getTopAlbums(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top albums response: " + response.toString());
                Assert.assertNotNull("There should be top albums in the response", response.optJSONObject("topalbums"));
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
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getTopArtists(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top artists response: " + response.toString());
                Assert.assertNotNull("There should be top artists in the response", response.optJSONObject("topartists"));
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
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getTopTags(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top tags response: " + response.toString());
                Assert.assertNotNull("There should be top tags in the response", response.optJSONObject("toptags"));
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
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getTopTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top tracks response: " + response.toString());
                Assert.assertNotNull("There should be top tracks in the response", response.optJSONObject("toptracks"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getWeeklyAlbumChart() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getWeeklyAlbumChart(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get weekly album chart response: " + response.toString());
                Assert.assertNotNull("There should be weekly album chart in the response", response.optJSONObject("weeklyalbumchart"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getWeeklyArtistChart() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getWeeklyArtistChart(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get weekly artist chart response: " + response.toString());
                Assert.assertNotNull("There should be weekly artist chart in the response", response.optJSONObject("weeklyartistchart"));
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
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getWeeklyChartList(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get weekly chart list response: " + response.toString());
                Assert.assertNotNull("There should be weekly chart list in the response", response.optJSONObject("weeklychartlist"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void getWeeklyTrackChart() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("user", "BlueImbecile");
        LfmRequest request = LfmApi.user().getWeeklyTrackChart(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get weekly track chart response: " + response.toString());
                Assert.assertNotNull("There should be weekly track chart in the response", response.optJSONObject("weeklytrackchart"));
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }
}
