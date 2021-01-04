package com.comfortly.tripprocessor.lib.analyzedtrip;

public enum EmotionLevel {
    HAPPY("HAPPY"),
    CALM("CALM"),
    STRESSED("STRESSED"),
    SAD("SAD"),
    ANGRY("ANGRY"),
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
