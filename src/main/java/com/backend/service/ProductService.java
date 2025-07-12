package com.backend.service;

import com.backend.config.Mapper;
import com.backend.dto.ProductDto;
import com.backend.entity.Product;
import com.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        productDto.setActive(true);
        Product product = Mapper.map(productDto, Product.class);
        product.setId(null);
        Product savedProduct = productRepository.save(product);
        return Mapper.map(savedProduct, ProductDto.class);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + id));
        return Mapper.map(product, ProductDto.class);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findByActive(true);
        return Mapper.mapAll(products, ProductDto.class);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByStatus(boolean active) {
        List<Product> products = productRepository.findByActive(active);
        return Mapper.mapAll(products, ProductDto.class);
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + id));

        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setProductDescription(productDto.getProductDescription());
        Product updatedProduct = productRepository.save(existingProduct);
        return Mapper.map(updatedProduct, ProductDto.class);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + id));
        product.setActive(false);
        productRepository.save(product);
    }
}