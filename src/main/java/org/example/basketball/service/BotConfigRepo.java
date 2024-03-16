package org.example.basketball.service;

import org.example.basketball.config.BotConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface BotConfigRepo extends MongoRepository<BotConfig, BigInteger> {
}
