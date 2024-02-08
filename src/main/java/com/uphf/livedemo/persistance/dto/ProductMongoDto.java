package com.uphf.livedemo.persistance.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "products")
public class ProductMongoDto {

    @MongoId private final UUID id;
    private final String name;
    private final double price;

}
