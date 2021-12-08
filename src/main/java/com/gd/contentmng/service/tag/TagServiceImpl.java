package com.gd.contentmng.service.tag;

import com.gd.contentmng.model.Tag;
import com.gd.contentmng.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Optional<Tag> findById(int id) {
        return tagRepository.findById(id);
    }

    @Override
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void savAll(List<Tag> tags) {
        tagRepository.saveAll(tags);
    }

    @Override
    @Transactional
    public void deleteAll(List<Tag> tags) {
        for(Tag tag:tags){
            tag.setDeleted(true);
            tagRepository.save(tag);
        }
    }
}
