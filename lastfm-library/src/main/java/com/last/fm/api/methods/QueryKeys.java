package com.last.fm.api.methods;

import com.last.fm.api.util.ISO3166;
import com.last.fm.api.util.ISO639;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    COUNTRY("country", (country) -> ((ISO3166) country).getCountryName()),
    TRACK("track"),
    TIMESTAMP("timestamp", (timestamp) -> String.valueOf(((Date) timestamp).getTime() / 1000)),
    CHOSEN_BY_USER("chosenByUser", (chosenByUser) -> (Boolean) chosenByUser ? "1" : "0"),
    TRACK_NUMBER("trackNumber"),
    ALBUM_ARTIST("albumArtist"),
    DURATION("duration"),
    START_TIMESTAMP("startTimestamp", (timestamp) -> String.valueOf(((Date) timestamp).getTime() / 1000)),
    END_TIMESTAMP("endTimestamp", (timestamp) -> String.valueOf(((Date) timestamp).getTime() / 1000)),
    RECENT_TRACKS("recenttracks", (recentTracks) -> (Boolean) recentTracks ? "1" : "0"),
    TAGGING_TYPE("taggingtype", (taggingType) -> ((TaggingType) taggingType).getKey()),
    FROM("from", (from) -> String.valueOf(((Date) from).getTime() / 1000)),
    TO("to", (to) -> String.valueOf(((Date) to).getTime() / 1000)),
    EXTENDED("extended", (extended) -> (Boolean) extended ? "1" : "0"),
    PERIOD("period", (period) -> ((Period) period).getKey());

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

    public SimpleEntry<String, Collection<String>> generateScrobbleKeyValues(Object... values) {
        if (values != null && this.conversionFunction != null && values.length > 0) {
            Collection<String> convertedValues = new ArrayList<>();
            for (Object value : values) {
                convertedValues.add(this.conversionFunction.apply(value));
            }
            return new SimpleEntry<>(this.key, convertedValues);
        } else if (values != null && values.length > 0) {
            Collection<String> stringValues = new ArrayList<>();
            for (Object value: values) {
                stringValues.add(value.toString());
            }
            return new SimpleEntry<>(this.key, stringValues);
        }
        return new SimpleEntry<>(this.key, null);
    }
}
