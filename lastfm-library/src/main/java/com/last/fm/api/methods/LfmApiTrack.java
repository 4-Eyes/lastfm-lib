//Copyright 2016 Arthur Ghazaryan

//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.


package com.last.fm.api.methods;


import com.last.fm.api.LfmParameters;
import com.last.fm.api.LfmRequest;
import com.last.fm.api.ScrobbleParameters;
import com.last.fm.api.util.LfmUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@SuppressWarnings("unused")
public class LfmApiTrack extends ApiBase {

    //region new method signatures

    public LfmRequest addTags(String artist, String track, String tags) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.TAGS.generateKeyValue(tags)
        );
        return prepareRequest("addTags", params, true);
    }

    public LfmRequest getCorrection(String artist, String track) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track)
        );
        return prepareRequest("getCorrection", params, false);
    }

    public LfmRequest getInfo(String artist, String track) {
        return getInfo(artist, track, null, null, null);
    }

    public LfmRequest getInfo(UUID mbid) {
        return getInfo(null, null, mbid, null, null);
    }

    public LfmRequest getInfo(String artist, String track, UUID mbid, String username,
                              Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.USERNAME.generateKeyValue(username),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect)
        );
        return prepareRequest("getInfo", params, false);
    }

    public LfmRequest getSimilar(String artist, String track) {
        return getSimilar(artist, track, null, null, null);
    }

    public LfmRequest getSimilar(UUID mbid) {
        return getSimilar(null, null, mbid, null, null);
    }

    public LfmRequest getSimilar(String artist, String track, UUID mbid, Integer limit,
                                 Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect)
        );
        return prepareRequest("getSimilar", params, false);
    }

    public LfmRequest getTags(String artist, String track) {
        return getTags(artist, track, null, null, null);
    }

    public LfmRequest getTags(UUID mbid) {
        return getTags(null, null, mbid, null, null);
    }

    public LfmRequest getTags(String artist, String track, UUID mbid, String user,
                              Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect)
        );
        return prepareRequest("getTags", params, !params.containsKey(QueryKeys.USER.getKey()));
    }

    public LfmRequest getTopTags(String artist, String track) {
        return getTopTags(artist, track, null, null);
    }

    public LfmRequest getTopTags(UUID mbid) {
        return getTopTags(null, null, mbid, null);
    }

    public LfmRequest getTopTags(String artist, String track, UUID mbid, Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect)
        );
        return prepareRequest("getTopTags", params, false);
    }

    public LfmRequest love(String artist, String track) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track)
        );
        return prepareRequest("love", params, true);
    }

    public LfmRequest removeTag(String artist, String track, String tag) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.TAG.generateKeyValue(tag)
        );
        return prepareRequest("removeTag", params, true);
    }

    public LfmRequest scrobble(String artist, String track, Date timestamp) {
        return scrobble(Collections.singletonList(artist), Collections.singletonList(track),
                Collections.singletonList(timestamp));
    }

    public LfmRequest scrobble(Collection<String> artists, Collection<String> tracks,
                               Collection<Date> timestamps) {
        return scrobble(artists, tracks, timestamps, null, null, null, null, null, null);
    }

    public LfmRequest scrobble(Collection<String> artists, Collection<String> tracks,
                               Collection<Date> timestamps, Collection<String> albums,
                               Collection<Boolean> chosenByUser, Collection<Integer> trackNumber,
                               Collection<UUID> mbids, Collection<String> albumArtists,
                               Collection<Integer> durations) {
        ScrobbleParameters params = generateScrobbleParameters(
                QueryKeys.ARTIST.generateScrobbleKeyValues(arrayOrNull(artists)),
                QueryKeys.TRACK.generateScrobbleKeyValues(arrayOrNull(tracks)),
                QueryKeys.TIMESTAMP.generateScrobbleKeyValues(arrayOrNull(timestamps)),
                QueryKeys.ALBUM.generateScrobbleKeyValues(arrayOrNull(albums)),
                QueryKeys.CHOSEN_BY_USER.generateScrobbleKeyValues(arrayOrNull(chosenByUser)),
                QueryKeys.TRACK_NUMBER.generateScrobbleKeyValues(arrayOrNull(trackNumber)),
                QueryKeys.MUSICBRAINZ_ID.generateScrobbleKeyValues(arrayOrNull(mbids)),
                QueryKeys.ALBUM_ARTIST.generateScrobbleKeyValues(arrayOrNull(albumArtists)),
                QueryKeys.DURATION.generateScrobbleKeyValues(arrayOrNull(durations))
        );
        return prepareRequest(params);
    }

    public LfmRequest search(String track) {
        return search(track, null, null, null);
    }

    public LfmRequest search(String track, String artist, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("search", params, false);
    }

    public LfmRequest unlove(String artist, String track) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track)
        );
        return prepareRequest("unlove", params, true);
    }

    public LfmRequest updateNowPlaying(String artist, String track) {
        return updateNowPlaying(artist, track, null, null, null, null, null);
    }

    public LfmRequest updateNowPlaying(String artist, String track, String album, Integer trackNumber,
                                       UUID mbid, Integer duration, String albumArtist) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TRACK.generateKeyValue(track),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.TRACK_NUMBER.generateKeyValue(trackNumber),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.DURATION.generateKeyValue(duration),
                QueryKeys.ALBUM_ARTIST.generateKeyValue(albumArtist)
        );
        return prepareRequest("updateNowPlaying", params, true);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/track.updateNowPlaying
     */
    @Deprecated
    public LfmRequest updateNowPlaying(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "updateNowPlaying"), params));
        return prepareRequest("updateNowPlaying", params, true);
    }

    /**
     * http://www.last.fm/api/show/track.unlove
     */
    @Deprecated
    public LfmRequest unlove(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "unlove"), params));
        return prepareRequest("unlove", params, true);
    }

    /**
     * http://www.last.fm/api/show/track.search
     */
    @Deprecated
    public LfmRequest search(LfmParameters params) {
        return prepareRequest("search", params, false);
    }

    /**
     * http://www.last.fm/api/show/track.scrobble
     */
    @Deprecated
    public LfmRequest scrobble(ScrobbleParameters params) {
        return prepareRequest(params);
    }

    /**
     * http://www.last.fm/api/show/track.removeTag
     */
    @Deprecated
    public LfmRequest removeTag(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "removeTag"), params));
        return prepareRequest("removeTag", params, true);
    }

    /**
     * http://www.last.fm/api/show/track.love
     */
    @Deprecated
    public LfmRequest love(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "love"), params));
        return prepareRequest("love", params, true);
    }

    /**
     * http://www.last.fm/api/show/track.getTopTags
     */
    @Deprecated
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/track.getTags
     */
    @Deprecated
    public LfmRequest getTags(LfmParameters params) {
        if (params.get("user") == null) {
            return prepareRequest("getTags", params, true);
        } else {
            return prepareRequest("getTags", params, false);
        }
    }

    /**
     * http://www.last.fm/api/show/track.addTags
     */
    @Deprecated
    public LfmRequest addTags(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "addTags"), params));
        return prepareRequest("addTags", params, true);
    }

    /**
     * http://www.last.fm/api/show/track.getSimilar
     */
    @Deprecated
    public LfmRequest getSimilar(LfmParameters params) {
        return prepareRequest("getSimilar", params, false);
    }

    /**
     * http://www.last.fm/api/show/track.getCorrection
     */
    @Deprecated
    public LfmRequest getCorrection(LfmParameters params) {
        return prepareRequest("getCorrection", params, false);
    }

    /**
     * http://www.last.fm/api/show/track.getInfo
     */
    @Deprecated
    public LfmRequest getInfo(LfmParameters params) {
        return prepareRequest("getInfo", params, false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "track";
    }
}
