package com.ag.testapplication.library.suite;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ag.lfm.LfmError;
import com.ag.lfm.LfmParameters;
import com.ag.lfm.LfmRequest;
import com.ag.lfm.ScrobbleParameters;
import com.ag.lfm.api.LfmApi;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by 4-Eyes on 05/04/2017.
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TrackApiUnitTest extends AuthenticationBase {

    private static final String TAG = "TrackApiUnitTest";

    @Test
    public void updateNowPlaying() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        params.put("track", "It's Too Late");
        params.put("album", "Dreams");
        params.put("trackNumber", "2");
        params.put("duration", "235");
        params.put("albumArtist", "Evermore");
        LfmRequest request = LfmApi.track().updateNowPlaying(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                try {
                    JSONObject nowPlaying = response.getJSONObject("nowplaying");
                    JSONObject artist = nowPlaying.getJSONObject("artist");
                    JSONObject ignoredMessage = nowPlaying.getJSONObject("ignoredMessage");
                    JSONObject album = nowPlaying.getJSONObject("album");
                    JSONObject albumArtist = nowPlaying.getJSONObject("albumArtist");
                    JSONObject track = nowPlaying.getJSONObject("track");
                    Assert.assertEquals("Artist should not have been corrected", "0", artist.getString("corrected"));
                    Assert.assertEquals("The artist should be Evermore", "Evermore", artist.getString("#text"));

                    Assert.assertEquals("Album should not have been corrected", "0", album.getString("corrected"));
                    Assert.assertEquals("The album should be Dreams", "Dreams", album.getString("#text"));

                    Assert.assertEquals("Album artist should not have been corrected", "0", albumArtist.getString("corrected"));
                    Assert.assertEquals("The artist should be Evermore", "Evermore", albumArtist.getString("#text"));

                    Assert.assertEquals("Track should not have been corrected", "0", track.getString("corrected"));
                    Assert.assertEquals("The track should be It's Too Late", "It's Too Late", track.getString("#text"));

                    Assert.assertEquals("The message should not have been ignored", "0", ignoredMessage.getString("code"));
                    Assert.assertEquals("There should be no reason to ignore the message", "", ignoredMessage.getString("#text"));

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
                Log.i(TAG, response.toString());
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void love() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("track", "It's Too Late");
        params.put("artist", "Evermore");
        final LfmRequest request = LfmApi.track().love(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, response.toString());
                Assert.assertEquals("The response should be empty for a success", "{}", response.toString());
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
                Assert.assertTrue(error.toString(), false);
            }
        }).get();
    }

    @Test
    public void unlove() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        params.put("track", "It's Too Late");
        LfmRequest request = LfmApi.track().unlove(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, "Unlove response: " + response.toString());
                Assert.assertEquals("The response for a success should be empty", "{}", response.toString());
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
        params.put("track", "Cosmic Love");
        final LfmRequest request = LfmApi.track().search(params);
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

    @Test
    public void scrobbleOne() throws Exception {
        ScrobbleParameters params = new ScrobbleParameters();
        params.put("track", "Running to the Sea - Seven Lions Remix");
        params.put("artist", "Röyksopp");
        params.put("album", "Running to the Sea (Remixes)");
        long currentTimeStamp = System.currentTimeMillis() / 1000;
        params.put("timestamp", String.valueOf(currentTimeStamp - 4* 60));
        final LfmRequest request = LfmApi.track().scrobble(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, response.toString());
                try {
                    Assert.assertEquals("Should have accepted 1 scrobble", 1, response.getJSONObject("scrobbles").getJSONObject("@attr").getInt("accepted"));
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
    public void scrobbleMultiple() throws Exception {
        ScrobbleParameters params = new ScrobbleParameters();
        params.put("track", "Farewell To The Fairground (Single Mix)", "Starlight", "The Cave", "Afterlife (feat. Echos)", "Running to the Sea - Seven Lions Remix");
        params.put("artist", "White Lies", "Muse", "Mumford & Sons", "Illenium", "Röyksopp");
        params.put("album", "Farewell To The Fairground", "Black Holes And Revelations", "Sigh No More", "Ashes", "Running to the Sea (Remixes)");
        long currentTimeStamp = System.currentTimeMillis() / 1000;
        params.put("timestamp", String.valueOf(currentTimeStamp - 4 * 60), String.valueOf(currentTimeStamp - 2 * 4 * 60),
                String.valueOf(currentTimeStamp - 3 * 4 * 60), String.valueOf(currentTimeStamp - 4 * 4 * 60),
                String.valueOf(currentTimeStamp - 5 * 4 * 60));
        final LfmRequest request = LfmApi.track().scrobble(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, response.toString());
                try {
                    Assert.assertEquals("Should have accepted 5 scrobble", 5, response.getJSONObject("scrobbles").getJSONObject("@attr").getInt("accepted"));
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
    public void addTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("track", "Car Spin");
        params.put("tags", "rock,alternative rock");
        LfmRequest request = LfmApi.track().addTags(params);
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
    public void getTags() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("track", "Car Spin");
        LfmRequest request = LfmApi.track().getTags(params);
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
    public void removeTag() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("track", "Car Spin");
        params.put("tags", "hard rock");
        LfmRequest request = LfmApi.track().addTags(params);
        request.executeWithListener(null).get();

        params = new LfmParameters();
        params.put("artist", "Greywind");
        params.put("track", "Car Spin");
        params.put("tag", "hard rock");
        request = LfmApi.track().removeTag(params);
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
    public void getTopTags() throws Exception {

    }

    @Test
    public void getSimilar() throws Exception {

    }

    @Test
    public void getCorrection() throws Exception {

    }

    @Test
    public void getInfo() throws Exception {

    }

    @Test
    public void getMethodsGroup() throws Exception {

    }
}
