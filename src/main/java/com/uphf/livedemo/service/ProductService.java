package com.uphf.livedemo.service;

import com.uphf.livedemo.persistance.ProductMongoRepository;
import com.uphf.livedemo.service.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductMongoRepository productMongoRepository;

    public String saveProduct(Product product) {
        return productMongoRepository.save(product);
    }

    public Product getProduct(String id) {
        return productMongoRepository.findById(id);
    }

}
