package com.ag.lfm.api.methods;


import com.ag.lfm.LfmParameters;
import com.ag.lfm.LfmRequest;
import com.ag.lfm.util.LfmUtil;

import java.util.Locale;

@SuppressWarnings("unused")
public class LfmApiArtist extends ApiBase {

    /**
     * http://www.last.fm/api/show/artist.addTags
     *
     * @param params the artist, the tags (comma delimited list, 10 max)
     * @return a LfmRequest for adding the given tags to an artist
     */
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
    public LfmRequest getCorrection(LfmParameters params) {
        return prepareRequest("getCorrection", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getInfo
     * @param params the artist, mbid (optional), lang (optional),
     *               autocorrect (optional), username (optional)
     * @return a Lfm request for getting artist info
     */
    public LfmRequest getInfo(LfmParameters params) {
        return prepareRequest("getInfo", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getSimilar
     * @param params the artist, autocorrect (optional), mbid (optional),
     *               limit (optional)
     * @return a Lfm request for getting similar artists
     */
    public LfmRequest getSimilar(LfmParameters params) {
        return prepareRequest("getSimilar", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTags
     * @param params that artist, mbid (optional), user (optional),
     *               autocorrect (optional)
     * @return a Lfm request for getting tags for an artist
     */
    public LfmRequest getTags(LfmParameters params) {
        return prepareRequest("getTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTopAlbums
     * @param params artist, mbid (optional), autocorrect (optional),
     *               page (optional), limit (optional)
     * @return a Lfm request for getting the top albums for an artist
     */
    public LfmRequest getTopAlbums(LfmParameters params) {
        return prepareRequest("getTopAlbums", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTopTags
     * @param params artist, mbid (optional), autocorrect (optional)
     * @return a Lfm request for getting the top tags for an artist
     */
    public LfmRequest getTopTags(LfmParameters params) {
        return prepareRequest("getTopTags", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.getTopTracks
     * @param params artist, mbid (optional), autocorrect (optional),
     *               page (optional), limit (optional)
     * @return a Lfm request for getting the top tracks for an artist
     */
    public LfmRequest getTopTracks(LfmParameters params) {
        return prepareRequest("getTopTracks", params, false);
    }

    /**
     * http://www.last.fm/api/show/artist.removeTag
     * @param params artist, tag
     * @return a request to remove a tag from an artist
     */
    public LfmRequest removeTag(LfmParameters params) {
        params.put("api_sig", LfmUtil.generateSignature(String.format(Locale.US, "%s.%s", getMethodsGroup(), "removeTag"), params));
        return prepareRequest("removeTag", params, true);
    }

    /**
     * http://www.last.fm/api/show/artist.search
     * @param params artist, page (optional), limit (optional)
     * @return a Lfm request to search for a given artist
     */
    public LfmRequest search(LfmParameters params) {
        return prepareRequest("search", params, false);
    }

    @Override
    protected String getMethodsGroup() {
        return "artist";
    }
}
