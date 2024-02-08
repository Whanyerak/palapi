package com.uphf.livedemo.controller;

import com.uphf.livedemo.controller.dto.ProductHttpDto;
import com.uphf.livedemo.service.ProductService;
import com.uphf.livedemo.service.entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductHttpDto> getProduct(@PathVariable String id) {
        Product product = productService.getProduct(id);

        ProductHttpDto productHttpDto = new ProductHttpDto(
                product.name(),
                product.price()
        );

        return ResponseEntity.ok(productHttpDto);
    }

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductHttpDto productHttpDto) {
        var product = new Product(
                productHttpDto.name(),
                productHttpDto.price()
        );

        String productId = productService.saveProduct(product);

        return ResponseEntity.ok(productId);
    }

}
