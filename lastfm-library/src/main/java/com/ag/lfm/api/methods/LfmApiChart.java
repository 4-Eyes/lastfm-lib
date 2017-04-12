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

@SuppressWarnings("unused")
public class LfmApiChart extends ApiBase {

    //region new method signatures

    public LfmRequest getTopArtists() {
        return getTopArtists(null, null);
    }

    public LfmRequest getTopArtists(Integer page, Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.LIMIT.generateKeyValue(limit));
        return prepareRequest("getTopArtists", params, false);
    }

    public LfmRequest getTopTags() {
        return getTopArtists(null, null);
    }

    public LfmRequest getTopTags(Integer page, Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.LIMIT.generateKeyValue(limit));
        return prepareRequest("getTopTags", params, false);
    }

    public LfmRequest getTopTracks() {
        return getTopArtists(null, null);
    }

    public LfmRequest getTopTracks(Integer page, Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.LIMIT.generateKeyValue(limit));
        return prepareRequest("getTopTracks", params, false);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/chart.getTopArtists
     */
    @Deprecated
    public LfmRequest getTopArtists(LfmParameters params) {
        return prepareRequest("getTopArtists", params, false);
    }

    /**
     * http://www.last.fm/api/show/chart.getTopTags
     */
    @Deprecated
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/chart.getTopTracks
     */
    @Deprecated
    public LfmRequest getTopTracks(LfmParameters params){
        return prepareRequest("getTopTracks",params,false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "chart";
    }
}
