package com.ag.lfm.api.methods;

import com.ag.lfm.util.ISO3166;
import com.ag.lfm.util.ISO639;

import java.util.AbstractMap.SimpleEntry;
import java.util.function.Function;

/**
 * Created by 4-Eyes on 10/04/2017.
 */

public enum QueryKeys {
    ARTIST("artist"),
    ALBUM("album"),
    TAGS("tags"),
    MUSICBRAINZ_ID("mbid"),
    AUTOCORRECT("autocorrect", (autocorrect) -> (Boolean) autocorrect ? "1" : "0"),
    USERNAME("username"),
    LANGUAGE("lang", (language) -> ((ISO639) language).getCode()),
    USER("user"),
    TAG("tag"),
    LIMIT("limit"),
    PAGE("page"),
    COUNTRY("country", (country) -> ((ISO3166) country).getCountryName());

    private final String key;
    private final Function<Object, String> conversionFunction;

    QueryKeys(String key) {
        this(key, null);
    }

    QueryKeys(String key, Function<Object, String> conversionFunction) {
        this.key = key;
        this.conversionFunction = conversionFunction;
    }

    public String getKey() {
        return key;
    }

    public SimpleEntry<String, String> generateKeyValue(Object value) {
        if (value != null && this.conversionFunction != null) {
            return new SimpleEntry<>(this.key, conversionFunction.apply(value));
        } else if (value != null) {
            return new SimpleEntry<>(this.key, value.toString());
        }
        return new SimpleEntry<>(this.key, null);
    }
}