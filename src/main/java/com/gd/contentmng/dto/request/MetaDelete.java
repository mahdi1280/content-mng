package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class MetaDelete implements Serializable {

    private final int id;

    @JsonCreator
    public MetaDelete(int id) {
        this.id = id;
    }

    @Min(value = 1,message = "{meta.delete.id.min}")
    public int getId() {
        return id;
    }
}
