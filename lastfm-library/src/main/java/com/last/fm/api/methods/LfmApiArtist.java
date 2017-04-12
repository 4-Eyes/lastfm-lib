package com.last.fm.api.methods;


import com.last.fm.api.LfmParameters;
import com.last.fm.api.LfmRequest;
import com.last.fm.api.util.ISO639;
import com.last.fm.api.util.LfmUtil;

import java.util.Locale;
import java.util.UUID;

@SuppressWarnings("unused")
public class LfmApiArtist extends ApiBase {

    //region new method signatures

    public LfmRequest addTags(String artist, String tags) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.TAGS.generateKeyValue(tags));
        return prepareRequest("addTags", params, true);
    }

    public LfmRequest getCorrection(String artist) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist)
        );
        return prepareRequest("getCorrection", params, false);
    }

    public LfmRequest getInfo(String artist) {
        return getInfo(artist, null, null, null, null);
    }

    public LfmRequest getInfo(UUID mbid) {
        return getInfo(null, mbid, null, null, null);
    }

    public LfmRequest getInfo(String artist, UUID mbid, ISO639 lang, Boolean autocorrect,
                              String username) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.LANGUAGE.generateKeyValue(lang),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect),
                QueryKeys.USERNAME.generateKeyValue(username));
        return prepareRequest("getInfo", params, false);
    }

    public LfmRequest getSimilar(String artist) {
        return getSimilar(artist, null, null, null);
    }

    public LfmRequest getSimilar(UUID mbid) {
        return getSimilar(null, mbid, null, null);
    }

    public LfmRequest getSimilar(String artist, UUID mbid, Boolean autocorrect, Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect),
                QueryKeys.LIMIT.generateKeyValue(limit));
        return prepareRequest("getSimilar", params, false);
    }

    public LfmRequest getTags(String artist) {
        return getTags(artist, null, null, null);
    }

    public LfmRequest getTags(UUID mbid) {
        return getTags(null, mbid, null, null);
    }

    public LfmRequest getTags(String artist, UUID mbid, String user, Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.USER.generateKeyValue(user),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect)
        );
        return prepareRequest("getTags", params, !params.containsKey(QueryKeys.USER.getKey()));
    }

    public LfmRequest getTopAlbums(String artist) {
        return getTopAlbums(artist, null, null, null, null);
    }

    public LfmRequest getTopAlbums(UUID mbid) {
        return getTopAlbums(null, mbid, null, null, null);
    }

    public LfmRequest getTopAlbums(String artist, UUID mbid, Boolean autocorrect, Integer page,
                                   Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect),
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.LIMIT.generateKeyValue(limit)
        );
        return prepareRequest("getTopAlbums", params, false);
    }

    public LfmRequest getTopTags(String artist) {
        return getTopTags(artist, null, null);
    }

    public LfmRequest getTopTags(UUID mbid) {
        return getTopTags(null, mbid, null);
    }

    public LfmRequest getTopTags(String artist, UUID mbid, Boolean autocorrect) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect)
        );
        return prepareRequest("getTopTags", params, false);
    }

    public LfmRequest getTopTracks(String artist) {
        return getTopTracks(artist, null, null, null, null);
    }

    public LfmRequest getTopTracks(UUID mbid) {
        return getTopTracks(null, mbid, null, null, null);
    }

    public LfmRequest getTopTracks(String artist, UUID mbid, Boolean autocorrect, Integer page,
                                   Integer limit) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.MUSICBRAINZ_ID.generateKeyValue(mbid),
                QueryKeys.AUTOCORRECT.generateKeyValue(autocorrect),
                QueryKeys.PAGE.generateKeyValue(page),
                QueryKeys.LIMIT.generateKeyValue(limit)
        );
        return prepareRequest("getTopAlbums", params, false);
    }

    public LfmRequest removeTag(String artist, String album, String tag) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.ALBUM.generateKeyValue(album),
                QueryKeys.TAG.generateKeyValue(tag));
        return prepareRequest("removeTag", params, true);
    }

    public LfmRequest search(String artist) {
        return search(artist, null, null);
    }

    public LfmRequest search(String artist, Integer limit, Integer page) {
        LfmParameters params = generateParamters(
                QueryKeys.ARTIST.generateKeyValue(artist),
                QueryKeys.LIMIT.generateKeyValue(limit),
                QueryKeys.PAGE.generateKeyValue(page));
        return prepareRequest("search", params, false);
    }

    //endregion

    //region old methods

    /**
     * http://www.last.fm/api/show/artist.addTags
     *
     * @param params the artist, the tags (comma delimited list, 10 max)
     * @return a LfmRequest for adding the given tags to an artist
     */
    @Deprecated
    public LfmRequest addTags(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "addTags"), params));
        return prepareRequest("addTags", params, true);
    }

    /**
     * http://www.last.fm/api/show/artist.getCorrection
     *
     * @param params the artist to be corrected
     * @return a Lfm request for getting artist corrections
     */
    @Deprecated
    public LfmRequest getCorrection(LfmParameters params) {
        return prepareRequest("getCorrection", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getInfo
     * @param params the artist, mbid (optional), lang (optional),
     *               autocorrect (optional), username (optional)
     * @return a Lfm request for getting artist info
     */
    @Deprecated
    public LfmRequest getInfo(LfmParameters params) {
        return prepareRequest("getInfo", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getSimilar
     * @param params the artist, autocorrect (optional), mbid (optional),
     *               limit (optional)
     * @return a Lfm request for getting similar artists
     */
    @Deprecated
    public LfmRequest getSimilar(LfmParameters params) {
        return prepareRequest("getSimilar", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTags
     * @param params that artist, mbid (optional), user (optional),
     *               autocorrect (optional)
     * @return a Lfm request for getting tags for an artist
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
     * http://www.last.fm/api/show/artist.getTopAlbums
     * @param params artist, mbid (optional), autocorrect (optional),
     *               page (optional), limit (optional)
     * @return a Lfm request for getting the top albums for an artist
     */
    @Deprecated
    public LfmRequest getTopAlbums(LfmParameters params) {
        return prepareRequest("getTopAlbums", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTopTags
     * @param params artist, mbid (optional), autocorrect (optional)
     * @return a Lfm request for getting the top tags for an artist
     */
    @Deprecated
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTopTracks
     * @param params artist, mbid (optional), autocorrect (optional),
     *               page (optional), limit (optional)
     * @return a Lfm request for getting the top tracks for an artist
     */
    @Deprecated
    public LfmRequest getTopTracks(LfmParameters params) {
        return prepareRequest("getTopTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.removeTag
     * @param params artist, tag
     * @return a request to remove a tag from an artist
     */
    @Deprecated
    public LfmRequest removeTag(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "removeTag"), params));
        return prepareRequest("removeTag", params, true);
    }

    /**
     * http://www.last.fm/api/show/artist.search
     * @param params artist, page (optional), limit (optional)
     * @return a Lfm request to search for a given artist
     */
    @Deprecated
    public LfmRequest search(LfmParameters params) {
        return prepareRequest("search", params, false);
    }

    //endregion

    @Override
    protected String getMethodsGroup() {
        return "artist";
    }
}
