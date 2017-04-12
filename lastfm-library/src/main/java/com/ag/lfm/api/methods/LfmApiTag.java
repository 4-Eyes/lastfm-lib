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
import com.ag.lfm.util.ISO639;

@SuppressWarnings("unused")
public class LfmApiTag extends ApiBase {

    //region new method signatures

    public LfmRequest getInfo(String tag) {
        return getInfo(tag, null);
    }

    public LfmRequest getInfo(String tag, ISO639 lang) {
        LfmParameters params = generateParamters(
                QueryKeys.TAG.generateKeyValue(tag),
                QueryKeys.LANGUAGE.generateKeyValue(lang)
        );
        return prepareRequest("getInfo", params, false);
    }

    public LfmRequest getSimilar(String tag) {
        LfmParameters params = generateParamters(
                QueryKeys.TAG.generateKeyValue(tag)
        );
        return prepareRequest("getSimilar", params, false);
    }


    public LfmRequest getTopAlbums(String tag) {
        return getTopAlbums(tag, null, null);
    }

    public LfmRequest getTopAlbums(String tag, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.TAG.generateKeyValue(tag),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopAlbums", params, false);
    }

    public LfmRequest getTopArtist(String tag) {
        return getTopArtist(tag, null, null);
    }

    public LfmRequest getTopArtist(String tag, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.TAG.generateKeyValue(tag),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopArtist", params, false);
    }

    public LfmRequest getTopTags() {
        return prepareRequest("getTopTags", new LfmParameters(), false);
    }

    public LfmRequest getTopTracks(String tag) {
        return getTopTracks(tag, null, null);
    }

    public LfmRequest getTopTracks(String tag, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.TAG.generateKeyValue(tag),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopTracks", params, false);
    }

    public LfmRequest getWeeklyChartList(String tag) {
        LfmParameters params = generateParamters(
                QueryKeys.TAG.generateKeyValue(tag)
        );
        return prepareRequest("getWeeklyChartList", params, false);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/tag.getInfo
     */
    @Deprecated
    public LfmRequest getInfo(LfmParameters params) {
        return prepareRequest("getInfo", params, false);
    }

    /**
     * http://www.last.fm/api/show/tag.getSimilar
     */
    @Deprecated
    public LfmRequest getSimilar(LfmParameters params) {
        return prepareRequest("getSimilar", params, false);
    }

    /**
     * http://www.last.fm/api/show/tag.getTopAlbums
     */
    @Deprecated
    public LfmRequest getTopAlbums(LfmParameters params) {
        return prepareRequest("getTopAlbums", params, false);
    }

    /**
     * http://www.last.fm/api/show/tag.getTopArtists
     */
    @Deprecated
    public LfmRequest getTopArtists(LfmParameters params) {
        return prepareRequest("getTopArtists", params, false);
    }

    /**
     * http://www.last.fm/api/show/tag.getTopTags
     */
    @Deprecated
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/tag.getTopTracks
     */
    @Deprecated
    public LfmRequest getTopTracks(LfmParameters params) {
        return prepareRequest("getTopTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/tag.getWeeklyChartList
     */
    @Deprecated
    public LfmRequest getWeeklyChartList(LfmParameters params) {
        return prepareRequest("getWeeklyChartList", params, false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "tag";
    }
}
