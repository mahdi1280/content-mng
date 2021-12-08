package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MetaModifyRequest implements Serializable {

    private final int id;
    private final String value;

    @JsonCreator
    public MetaModifyRequest(int id,String value) {
        this.id = id;
        this.value = value;
    }

    @Min(value = 1,message = "{meta.modify.request.id.min}")
    public int getId() {
        return id;
    }

    @NotBlank(message = "{meta.modify.request.value.empty}")
    @NotNull(message = "{meta.modify.request.value.null}")
    public String getValue() {
        return value;
    }
}
