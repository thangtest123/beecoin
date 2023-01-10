package com.example.beecoin.repositories;

import com.example.beecoin.models.Wallet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, ObjectId> {
    Wallet findByUsername(String username);

    List<Wallet> findBySystemId(ObjectId id);

    Wallet findWalletById(ObjectId id);
}
