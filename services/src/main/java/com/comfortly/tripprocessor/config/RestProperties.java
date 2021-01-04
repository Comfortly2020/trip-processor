package com.comfortly.tripprocessor.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties.config")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(value = "broken", watch = true)
    private Boolean broken;

    @ConfigValue(value = "score-multiplier", watch = true)
    private Integer scoreMultiplier;

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(Boolean broken) {
        this.broken = broken;
    }

    public Integer getScoreMultiplier() {
        return scoreMultiplier;
    }

    public void setScoreMultiplier(Integer scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }
}
