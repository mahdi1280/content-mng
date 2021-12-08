package com.gd.contentmng.service.link;

import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.model.Link;
import com.gd.contentmng.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Optional<Link> findByCoinInfo(CoinInfo coinInfo) {
       return linkRepository.findByCoinInfo(coinInfo);
    }
}
