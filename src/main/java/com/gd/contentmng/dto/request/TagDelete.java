package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class TagDelete implements Serializable {

    private final int id;

    @JsonCreator
    public TagDelete(int id) {
        this.id = id;
    }

    @Min(value = 1,message = "{tag.delete.id.min}")
    public int getId() {
        return id;
    }
}
