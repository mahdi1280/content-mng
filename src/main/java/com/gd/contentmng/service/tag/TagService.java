package com.gd.contentmng.service.tag;

import com.gd.contentmng.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Optional<Tag> findById(int id);

    void save(Tag tag);

    void savAll(List<Tag> tags);

    void deleteAll(List<Tag> tags);

}
