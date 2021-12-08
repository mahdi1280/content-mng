package com.gd.contentmng.repository;

import com.gd.contentmng.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin,Integer> {

}
