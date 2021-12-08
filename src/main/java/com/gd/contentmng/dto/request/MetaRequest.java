package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class MetaRequest implements Serializable {

    private final String value;

    @JsonCreator
    public MetaRequest(String value) {
        this.value = value;
    }

    @NotNull(message = "{meta.request.value.null}")
    @NotEmpty(message = "{meta.request.value.empty}")
    public String getValue() {
        return value;
    }

}
