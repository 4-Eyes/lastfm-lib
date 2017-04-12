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


package com.ag.lfm.api.methods;


import com.ag.lfm.LfmParameters;
import com.ag.lfm.LfmRequest;
import com.ag.lfm.Session;

import java.util.Date;

@SuppressWarnings("unused")
public class LfmApiUser extends ApiBase {

    //region new method signatures

    public LfmRequest getArtistTracks(String user, String artist) {
        return getArtistTracks(user, artist, null, null, null);
    }

    public LfmRequest getArtistTracks(String user, String artist, Date startTimestamp,
                                      Integer page, Date endTimestamp) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.START_TIMESTAMP.generateKeyValue(startTimestamp),
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.END_TIMESTAMP.generateKeyValue(endTimestamp)
        );
        return prepareRequest("getArtistTracks", params, false);
    }

    public LfmRequest getFriends(String user) {
        return getFriends(user, null, null, null);
    }

    public LfmRequest getFriends(String user, Boolean recentTracks, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.RECENT_TRACKS.generateKeyValue(recentTracks),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getFriends", params, false);
    }

    public LfmRequest getInfo(String user) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user)
        );
        return prepareRequest("getInfo", params, false);
    }

    public LfmRequest getLovedTracks(String user) {
        return getLovedTracks(user, null, null);
    }

    public LfmRequest getLovedTracks(String user, Integer limit, Integer page) {
        LfmParameters parameters = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getLovedTracks", parameters, false);
    }

    public LfmRequest getPersonalTags(String user, String tag, TaggingType taggingType) {
        return getPersonalTags(user, tag, taggingType);
    }

    public LfmRequest getPersonalTags(String user, String tag, TaggingType taggingType,
                                      Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.TAG.generateKeyValue(tag),
                QueryKeys.TAGGING_TYPE.generateKeyValue(taggingType),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getPersonalTags", params, false);
    }

    public LfmRequest getRecentTracks(String user) {
        return getRecentTracks(user, null, null, null, null, null);
    }

    public LfmRequest getRecentTracks(String user, Integer limit, Integer page, Date from, Date to,
                                      Boolean extended) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.FROM.generateKeyValue(from),
                QueryKeys.TO.generateKeyValue(to),
                QueryKeys.EXTENDED.generateKeyValue(extended)
        );
        return prepareRequest("getRecentTracks", params, false);
    }

    public LfmRequest getTopAlbums(String user) {
        return getTopAlbums(user, null, null, null);
    }

    public LfmRequest getTopAlbums(String user, Period period, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.PERIOD.generateKeyValue(period),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopAlbums", params, false);
    }

    public LfmRequest getTopArtists(String user) {
        return getTopArtists(user, null, null, null);
    }

    public LfmRequest getTopArtists(String user, Period period, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.PERIOD.generateKeyValue(period),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopArtists", params, false);
    }

    public LfmRequest getTopTags(String user) {
        return getTopTags(user, null);
    }

    public LfmRequest getTopTags(String user, Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.LIMIT.generateKeyValue(limit)
        );
        return prepareRequest("getTopTags", params, false);
    }

    public LfmRequest getTopTracks(String user) {
        return getTopTracks(user, null, null, null);
    }

    public LfmRequest getTopTracks(String user, Period period, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.PERIOD.generateKeyValue(period),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopTracks", params, false);
    }

    public LfmRequest getWeeklyAlbumChart(String user) {
        return getWeeklyAlbumChart(user, null, null);
    }

    public LfmRequest getWeeklyAlbumChart(String user, Date from, Date to) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.FROM.generateKeyValue(from),
                QueryKeys.TO.generateKeyValue(to)
        );
        return prepareRequest("getWeeklyAlbumChart", params, false);
    }

    public LfmRequest getWeeklyArtistChart(String user) {
        return getWeeklyArtistChart(user, null, null);
    }

    public LfmRequest getWeeklyArtistChart(String user, Date from, Date to) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.FROM.generateKeyValue(from),
                QueryKeys.TO.generateKeyValue(to)
        );
        return prepareRequest("getWeeklyArtistChart", params, false);
    }

    public LfmRequest getWeeklyChartList(String user) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user)
        );
        return prepareRequest("getWeeklyChartList", params, false);
    }

    public LfmRequest getWeeklyTrackChart(String user) {
        return getWeeklyTrackChart(user, null, null);
    }

    public LfmRequest getWeeklyTrackChart(String user, Date from, Date to) {
        LfmParameters params = generateParamters(
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.FROM.generateKeyValue(from),
                QueryKeys.TO.generateKeyValue(to)
        );
        return prepareRequest("getWeeklyTrackChart", params, false);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/user.getArtistTracks
     */
    @Deprecated
    public LfmRequest getArtistTracks(LfmParameters params) {
        return prepareRequest("getArtistTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getFriends
     */
    @Deprecated
    public LfmRequest getFriends(LfmParameters params) {
        return prepareRequest("getFriends", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getInfo
     */
    @Deprecated
    public LfmRequest getInfo() {
        LfmParameters parameters = new LfmParameters();
        parameters.put("user", Session.username);
        return prepareRequest("getInfo", parameters, false);
    }

    /**
     * http://www.last.fm/api/show/user.getInfo
     */
    @Deprecated
    public LfmRequest getInfo(LfmParameters params) {
        return prepareRequest("getInfo", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getLovedTracks
     */
    @Deprecated
    public LfmRequest getLovedTracks(LfmParameters params) {
        return prepareRequest("getLovedTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getPersonalTags
     */
    @Deprecated
    public LfmRequest getPersonalTags(LfmParameters params) {
        return prepareRequest("getPersonalTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getRecentTracks
     */
    @Deprecated
    public LfmRequest getRecentTracks(LfmParameters params) {
        return prepareRequest("getRecentTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getTopAlbums
     */
    @Deprecated
    public LfmRequest getTopAlbums(LfmParameters params) {
        return prepareRequest("getTopAlbums", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getTopArtists
     */
    @Deprecated
    public LfmRequest getTopArtists(LfmParameters params) {
        return prepareRequest("getTopArtists", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getTopTags
     */
    @Deprecated
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getTopTracks
     */
    @Deprecated
    public LfmRequest getTopTracks(LfmParameters params) {
        return prepareRequest("getTopTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getWeeklyAlbumChart
     */
    @Deprecated
    public LfmRequest getWeeklyAlbumChart(LfmParameters params) {
        return prepareRequest("getWeeklyAlbumChart", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getWeeklyArtistChart
     */
    @Deprecated
    public LfmRequest getWeeklyArtistChart(LfmParameters params) {
        return prepareRequest("getWeeklyArtistChart", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getWeeklyChartList
     */
    @Deprecated
    public LfmRequest getWeeklyChartList(LfmParameters params) {
        return prepareRequest("getWeeklyChartList", params, false);
    }

    /**
     * http://www.last.fm/api/show/user.getWeeklyTrackChart
     */
    @Deprecated
    public LfmRequest getWeeklyTrackChart(LfmParameters params) {
        return prepareRequest("getWeeklyTrackChart", params, false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "user";
    }
}
