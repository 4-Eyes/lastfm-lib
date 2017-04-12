package com.last.fm.api;

import com.last.fm.api.methods.LfmApiAlbum;
import com.last.fm.api.methods.LfmApiArtist;
import com.last.fm.api.methods.LfmApiChart;
import com.last.fm.api.methods.LfmApiGeo;
import com.last.fm.api.methods.LfmApiLibrary;
import com.last.fm.api.methods.LfmApiTag;
import com.last.fm.api.methods.LfmApiTrack;
import com.last.fm.api.methods.LfmApiUser;

/**
 * Provides access for API parts.
 */
public class LfmApi {

    /**
     * Returns object for preparing requests to track part of API
     */
    public static LfmApiTrack track() {
        return new LfmApiTrack();
    }

    /**
     * Returns object for preparing requests to chart part of API
     */
    public static LfmApiChart charts() {
        return new LfmApiChart();
    }

    /**
     * Returns object for preparing requests to geo part of API
     */
    public static LfmApiGeo geo() {
        return new LfmApiGeo();
    }

    /**
     * Returns object for preparing requests to tag part of API
     */
    public static LfmApiTag tag() {
        return new LfmApiTag();
    }

    /**
     * Returns object for preparing requests to user part of API
     */
    public static LfmApiUser user() {
        return new LfmApiUser();
    }

    /**
     * Returns object for preparing requests to library part of API
     */
    public static LfmApiLibrary library() {
        return new LfmApiLibrary();
    }

    /**
     * Returns object for preparing requests to album part of API
     */
    public static LfmApiAlbum album() {
        return new LfmApiAlbum();
    }

    /**
     * Returns object for preparing requests to the artist part of the API
     * @return artist api object
     */
    public static LfmApiArtist artist() {
        return new LfmApiArtist();
    }
}
