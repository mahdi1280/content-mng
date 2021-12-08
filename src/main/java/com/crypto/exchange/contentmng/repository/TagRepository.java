package com.crypto.exchange.contentmng.repository;

import com.crypto.exchange.contentmng.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
