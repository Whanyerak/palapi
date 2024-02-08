package com.uphf.livedemo.persistance;

import com.uphf.livedemo.exception.ProductNotFoundException;
import com.uphf.livedemo.persistance.dto.ProductMongoDto;
import com.uphf.livedemo.service.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductMongoRepository {

    private final ProductMongoDao productMongoDao;

    public ProductMongoRepository(ProductMongoDao productMongoDao) {
        this.productMongoDao = productMongoDao;
    }

    public String save(Product product) {
        UUID id = UUID.nameUUIDFromBytes(product.getIdentity().getBytes());

        var productMongoDto = new ProductMongoDto(
                id,
                product.name(),
                product.price()
        );

        productMongoDao.save(productMongoDto);

        return id.toString();
    }

    public Product findById(String id) {
        return productMongoDao.findById(UUID.fromString(id))
                .map(productDto -> new Product(
                        productDto.getName(),
                        productDto.getPrice())
                )
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
