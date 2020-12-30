package com.comfortly.tripprocessor.lib.analyzedtrip;

public enum EmotionLevel {
    HAPPY("HAPPY"),
    CALM("CALM"),
    STRESSED("STRESSED"),
    ANGRY("ANGRY"),
    SAD("SAD"),
    FRIGHTENED("FRIGHTENED"),
    UNKNOWN("UNKNOWN");

    private final String text;

    EmotionLevel(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
