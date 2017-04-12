package com.ag.lfm.api.methods;

/**
 * Created by 4-Eyes on 12/04/2017.
 */

@SuppressWarnings("unused")
public enum Period {
    OVERALL("overall"),
    SEVEN_DAYS("7day"),
    ONE_MONTH("1month"),
    THREE_MONTH("3month"),
    SIX_MONTH("6month"),
    TWELVE_MONTH("12month");


    private final String key;

    Period(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
