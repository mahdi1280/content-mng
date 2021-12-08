package com.crypto.exchange.contentmng.service.meta;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Meta;
import com.crypto.exchange.contentmng.repository.MetaRepository;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class MetaServiceImpl implements MetaService {

    private final MetaRepository metaRepository;
    private final MessageSourceAccessor messageSourceAccessor;

    public MetaServiceImpl(MetaRepository metaRepository, MessageSourceAccessor messageSourceAccessor) {
        this.metaRepository = metaRepository;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @Override
    @Transactional
    public CoinInfo saveAll(Collection<Meta> metas) {
        try {
            return metaRepository.saveAll(metas).get(0).getCoinInfo();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("coin.info.save.exist"),
                    "coin.info.save.exist"));
        }
    }

    @Override
    public void save(Meta meta) {
        metaRepository.save(meta);
    }

    @Override
    public Optional<Meta> findById(long id) {
        return metaRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteMeta(Meta meta) {
        meta.setDeleted(true);
        metaRepository.save(meta);
    }


}
