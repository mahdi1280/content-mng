package com.crypto.exchange.contentmng.service.tag;

import com.crypto.exchange.contentmng.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Optional<Tag> findById(long id);

    void save(Tag tag);

    void savAll(List<Tag> tags);

    void deleteAll(List<Tag> tags);

}
