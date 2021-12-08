package com.crypto.exchange.contentmng.service.link;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Link;
import com.crypto.exchange.contentmng.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Optional<Link> findByCoinInfo(CoinInfo coinInfo) {
        return linkRepository.findByCoinInfo(coinInfo);
    }
}
