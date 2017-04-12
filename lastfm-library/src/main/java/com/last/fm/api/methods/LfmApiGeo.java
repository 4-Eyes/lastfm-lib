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
import com.last.fm.api.util.ISO3166;

@SuppressWarnings("unused")
public class LfmApiGeo extends ApiBase {

    //region new method signatures

    public LfmRequest getTopArtists(ISO3166 country) {
        return getTopArtists(country, null, null);
    }

    public LfmRequest getTopArtists(ISO3166 country, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.COUNTRY.generateKeyValue(country),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopArtists", params, false);
    }

    public LfmRequest getTopTracks(ISO3166 country) {
        return getTopTracks(country, null, null, null);
    }

    public LfmRequest getTopTracks(ISO3166 country, String location, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.COUNTRY.generateKeyValue(country),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page)
        );
        return prepareRequest("getTopArtists", params, false);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/geo.getTopArtists
     */
    @Deprecated
    public LfmRequest getTopArtists(LfmParameters params) {
        return prepareRequest("getTopArtists", params, false);
    }

    /**
     * http://www.last.fm/api/show/geo.getTopTracks
     */
    @Deprecated
    public LfmRequest getTopTracks(LfmParameters params) {
        return prepareRequest("getTopTracks", params, false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "geo";
    }
}
