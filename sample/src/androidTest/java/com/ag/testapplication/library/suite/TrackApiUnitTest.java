package com.ag.testapplication.library.suite;

import android.util.Log;

import com.ag.lfm.LfmError;
import com.ag.lfm.LfmParameters;
import com.ag.lfm.LfmRequest;
import com.ag.lfm.api.LfmApi;

import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by 4-Eyes on 05/04/2017.
 *
 */

public class TrackApiUnitTest extends AuthenticationBase {

    private static final String TAG = "TrackApiUnitTest";

    @Test
    public void updateNowPlaying() throws Exception {
        LfmParameters params = new LfmParameters();
        params.put("artist", "Evermore");
        params.put("track", "It s Too Late");
        params.put("album", "Dreams");
        params.put("trackNumber", "2");
        params.put("duration", "235");
        params.put("albumArtist", "Evermore");
        LfmRequest request = LfmApi.track().updateNowPlaying(params);
        request.executeWithListener(new LfmRequest.LfmRequestListener() {
            @Override
            public void onComplete(JSONObject response) {
                Log.i(TAG, response.toString());
            }

            @Override
            public void onError(LfmError error) {
                Log.wtf(TAG, "This should not happen");
            }
        }).get();
    }

    @Test
    public void unlove() throws Exception {

    }

    @Test
    public void search() throws Exception {

    }

    @Test
    public void scrobble() throws Exception {

    }

    @Test
    public void removeTag() throws Exception {

    }

    @Test
    public void love() throws Exception {

    }

    @Test
    public void getTopTags() throws Exception {

    }

    @Test
    public void getTags() throws Exception {

    }

    @Test
    public void addTags() throws Exception {

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
