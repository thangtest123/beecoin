package com.example.beecoin.repositories;

import com.example.beecoin.models.ColdWallet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColdWalletRepository extends MongoRepository<ColdWallet, ObjectId> {
    ColdWallet findColdWalletByWalletId(ObjectId walletId);

    List<ColdWallet> findBySystemId(ObjectId systemId);
}
