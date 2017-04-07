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
 * Test for all api methods relating to artists
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ArtistApiUnitTest extends AuthenticationBase {

    private static final String TAG = "ArtistApiUnitTest";

    @Test
    public void addTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("tags", "rock,alternative rock");
        LfmRequest request = LfmApi.artist().addTags(params);
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
    public void getCorrection() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Florence and the Machine");
        LfmRequest request = LfmApi.artist().getCorrection(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get correction response: " + response.toString());
                try {
                    JSONObject correction = response.getJSONObject("corrections").getJSONObject("correction");
                    JSONObject artist = correction.getJSONObject("artist");
                    Assert.assertEquals("The artist should have been correct to Florence + the Machine", "Florence + the Machine", artist.getString("name"));
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
    public void getInfo() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        params.put("username", "BlueImbecile");
        LfmRequest request = LfmApi.artist().getInfo(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get info response: " + response.toString());
                try {
                    JSONObject artist = response.getJSONObject("artist");
                    Assert.assertNotNull("The artist shouldn't be null", artist);
                    Assert.assertTrue("The play count for this user should be over 40", Integer.valueOf(artist.getJSONObject("stats").getString("userplaycount")) > 40);
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
    public void getSimilar() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        LfmRequest request = LfmApi.artist().getSimilar(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get similar response: " + response.toString());
                Assert.assertNotNull("Similar artists should exist for this song", response.optJSONObject("similarartists"));
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
        params.put("artist", "Greywind");
        LfmRequest request = LfmApi.artist().getTags(params);
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
    public void getTopAlbums() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Youth in Revolt");
        LfmRequest request = LfmApi.artist().getTopAlbums(params);
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
    public void getTopTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        LfmRequest request = LfmApi.artist().getTopTags(params);
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
    public void getTopTracks() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        params.put("limit", "20");
        params.put("page", "1");
        LfmRequest request = LfmApi.artist().getTopTracks(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Get top tags response: " + response.toString());
                try {
                    JSONObject toptracks = response.getJSONObject("toptracks");
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
    public void removeTag() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("tags", "hard rock");
        LfmRequest request = LfmApi.artist().addTags(params);
        request.executeWithListener(null).get();

        params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("tag", "hard rock");
        request = LfmApi.artist().removeTag(params);
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
        params.put("artist", "Florence + the Machine");
        final LfmRequest request = LfmApi.artist().search(params);
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
