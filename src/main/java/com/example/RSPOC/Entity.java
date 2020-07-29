package com.example.RSPOC;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Entity {
    String type;
    String value;
    Long timestamp;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    Entity(@JsonProperty("type")String type, @JsonProperty("value") String value) {
        this.type = type;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

}
