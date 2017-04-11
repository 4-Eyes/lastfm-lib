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
import com.ag.lfm.util.LfmUtil;

import java.util.Locale;
import java.util.UUID;

@SuppressWarnings("unused")
public class LfmApiAlbum extends ApiBase {

    //region updated method signatures

    /**
     * Adds custom user tags to an album.
     * <p>
     * Multiple tags can be added at the same time by providing a comma delimited set of tags.
     * For example "rock,indie rock,indie pop".
     * <p>
     * See the last.fm API <a href"http://www.last.fm/api/show/album.addTags">documentation</a> for further details
     *
     * @param artist the name of the artist linked to the album (required field)
     * @param album  the album tags are being added to (required field)
     * @param tags   the tags to be added to the item as a comma delimited set (required field)
     * @return the request for executing the add tags API method.
     */
    public LfmRequest addTags(String artist, String album, String tags) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.TAGS.generateKeyValue(tags));
        return prepareRequest("addTags",
                params, true);
    }

    /**
     * Gets information about the album specified.
     * <p>
     * The artist and album must be specified in this query.
     * <p>
     * See the last.fm API <a href"http://www.last.fm/api/show/album.addTags">documentation</a> for further details
     *
     * @param artist the name of the artist linked to the album (required field)
     * @param album the name of the album which you wish to get information about (required field)
     * @return the request for getting album details.
     */
    public LfmRequest getInfo(String artist, String album) {
        return getInfo(artist, album, null, null, null, null);
    }

    /**
     * Gets information about the album specified.
     * <p>
     * The artist and album must be specified in this query.
     * <p>
     * See the last.fm API <a href"http://www.last.fm/api/show/album.addTags">documentation</a> for further details
     *
     * @param mbid the MusicBrainz ID for the album (required)
     * @return the request for getting album details.
     */
    public LfmRequest getInfo(UUID mbid) {
        return getInfo(null, null, mbid, null, null, null);
    }

    /**
     * Gets information about the album specified.
     * <p>
     * The artist and album must be specified in this query.
     * <p>
     * See the last.fm API <a href"http://www.last.fm/api/show/album.addTags">documentation</a> for further details
     *
     * @param artist the name of the artist linked to the album (required field unless mbid provided)
     * @param album the name of the album which you wish to get information about (required field unless mbid provided)
     * @param mbid the MusicBrainz ID for the album (optional)
     * @param autocorrect whether or not to autocorrect artist names and return the corrected names (optional field)
     * @param username the username of the account to append additional details about, such as play count (optional field)
     * @param lang the language to return the biography in, should be expressed as 639 alpha-2 code (optional field)
     * @return the request for getting album details.
     */
    public LfmRequest getInfo(String artist, String album, UUID mbid, Boolean autocorrect,
                              String username, String lang) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect),
                QueryKeys.USERNAME.generateKeyValue(username),
                QueryKeys.LANGUAGE.generateKeyValue(lang));
        return prepareRequest("getInfo", params, false);
    }

    public LfmRequest getTags(String artist, String album) {
        return getTags(artist, album, null, null, null);
    }

    public LfmRequest getTags(UUID mbid) {
        return getTags(null, null, mbid, null, null);
    }

    public LfmRequest getTags(String artist, String album, UUID mbid,
                              Boolean autocorrect, String user) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect),
                QueryKeys.USER.generateKeyValue(user));
        return prepareRequest("getTags", params, !params.containsKey(QueryKeys.USER.getKey()));
    }

    public LfmRequest getTopTags(String artist, String album) {
        return getTopTags(artist, album, null, null);
    }

    public LfmRequest getTopTags(UUID mbid) {
        return getTopTags(null, null, mbid, null);
    }

    public LfmRequest getTopTags(String artist, String album, UUID mbid, Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect));
        return prepareRequest("getTopTags", params, false);
    }

    public LfmRequest removeTag(String artist, String album, String tag) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.TAG.generateKeyValue(tag));
        return prepareRequest("removeTag", params, true);
    }

    public LfmRequest search(String album) {
        return search(album, null, null);
    }

    public LfmRequest search(String album, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page));
        return prepareRequest("search", params, false);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/album.addTags
     */
    @Deprecated
    public LfmRequest addTags(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "addTags"), params));
        return prepareRequest("addTags", params, true);
    }

    /**
     * http://www.last.fm/api/show/album.getInfo
     */
    @Deprecated
    public LfmRequest getInfo(LfmParameters params) {
        return prepareRequest("getInfo", params, false);
    }

    /**
     * http://www.last.fm/api/show/album.getTags
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
     * http://www.last.fm/api/show/album.getTopTags
     */
    @Deprecated
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/album.removeTag
     */
    @Deprecated
    public LfmRequest removeTag(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "removeTag"), params));
        return prepareRequest("removeTag", params, true);
    }

    /**
     * http://www.last.fm/api/show/album.search
     */
    @Deprecated
    public LfmRequest search(LfmParameters params) {
        return prepareRequest("search", params, false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "album";
    }
}
