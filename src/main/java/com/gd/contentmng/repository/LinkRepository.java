package com.gd.contentmng.repository;

import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByCoinInfo(CoinInfo coinInfo);
}
