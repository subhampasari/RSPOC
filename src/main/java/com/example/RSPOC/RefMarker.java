package com.example.RSPOC;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RefMarker {
    String refName;
    String experience;
    String product;
    String feature;
    Long timestamp;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    RefMarker(@JsonProperty("refName")String refName, @JsonProperty("experience") String experience,
              @JsonProperty("product") String product, @JsonProperty("feature") String feature) {

        this.refName = refName;
        this.experience = experience;
        this.product = product;
        this.feature = feature;
        this.timestamp = System.currentTimeMillis();
    }
}
