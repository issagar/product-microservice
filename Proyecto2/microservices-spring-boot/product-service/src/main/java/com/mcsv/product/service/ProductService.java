package com.mcsv.product.service;

import com.mcsv.product.dto.ProductRequest;
import com.mcsv.product.dto.ProductResponse;
import com.mcsv.product.model.Product;
import com.mcsv.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .nombre(productRequest.getNombre())
                .descripcion(productRequest.getDescripcion())
                .precio(productRequest.getPrecio())
                .build();
        productRepository.save(product);
        log.info("Guardado con exito", product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }
    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .precio(product.getPrecio())
                .build();
    }
}
