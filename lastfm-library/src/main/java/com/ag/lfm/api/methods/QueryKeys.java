package com.ag.lfm.api.methods;

import java.util.AbstractMap.SimpleEntry;

/**
 * Created by 4-Eyes on 10/04/2017.
 */

public enum QueryKeys {
    ARTIST("artist"),
    ALBUM("album"),
    TAGS("tags"),
    MUSICBRAINZ_ID("mbid"),
    AUTOCORRECT("autocorrect"),
    USERNAME("username"),
    LANGUAGE("language"),
    USER("user"),
    TAG("tag"),
    LIMIT("limit"),
    PAGE("page");

    private final String key;

    QueryKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public SimpleEntry<String, Object> generateKeyValue(Object value) {
        return new SimpleEntry<>(this.key, value);
    }
}
