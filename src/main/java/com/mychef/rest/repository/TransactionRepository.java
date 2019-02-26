package com.mychef.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mychef.rest.entity.Transaction;


public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
