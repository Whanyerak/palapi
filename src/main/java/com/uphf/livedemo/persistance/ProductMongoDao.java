package com.uphf.livedemo.persistance;

import com.uphf.livedemo.persistance.dto.ProductMongoDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductMongoDao extends MongoRepository<ProductMongoDto, UUID> {
}
