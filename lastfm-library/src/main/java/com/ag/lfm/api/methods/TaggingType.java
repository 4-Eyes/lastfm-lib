package com.ag.lfm.api.methods;

/**
 * Created by 4-Eyes on 12/04/2017.
 */

@SuppressWarnings("unused")
public enum TaggingType {
    ARTIST("artist"),
    ALBUM("album"),
    TRACK("track");


    private final String key;

    TaggingType(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
