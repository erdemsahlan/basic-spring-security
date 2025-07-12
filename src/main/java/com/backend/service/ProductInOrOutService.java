package com.backend.service;

import com.backend.config.Mapper;
import com.backend.dto.ProductInOrOutDto;
import com.backend.entity.Product;
import com.backend.entity.ProductInOrOut;
import com.backend.repository.ProductInOrOutRepository;
import com.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInOrOutService {

    private final ProductInOrOutRepository productInOrOutRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductInOrOutDto create(ProductInOrOutDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + dto.getProductId()));

        ProductInOrOut productInOrOut = ProductInOrOut.builder()
                .product(product)
                .kilograms(dto.getKilograms())
                .price(dto.getPrice())
                .alisSatis(dto.getAlisSatis())
                .active(true)
                .build();

        ProductInOrOut saved = productInOrOutRepository.save(productInOrOut);
        return convertToDto(saved);
    }

    @Transactional(readOnly = true)
    public ProductInOrOutDto getById(Long id) {
        ProductInOrOut productInOrOut = productInOrOutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kayıt bulunamadı: " + id));
        return convertToDto(productInOrOut);
    }

    @Transactional(readOnly = true)
    public List<ProductInOrOutDto> getAll() {
        return productInOrOutRepository.findByActive(true)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Transactional
    public ProductInOrOutDto update(Long id, ProductInOrOutDto dto) {
        ProductInOrOut existing = productInOrOutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kayıt bulunamadı: " + id));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + dto.getProductId()));

        existing.setProduct(product);
        existing.setKilograms(dto.getKilograms());
        existing.setPrice(dto.getPrice());
        existing.setAlisSatis(dto.getAlisSatis());

        ProductInOrOut updated = productInOrOutRepository.save(existing);
        return convertToDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        ProductInOrOut productInOrOut = productInOrOutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kayıt bulunamadı: " + id));
        productInOrOut.setActive(false);
        productInOrOutRepository.save(productInOrOut);
    }

    private ProductInOrOutDto convertToDto(ProductInOrOut entity) {
        return ProductInOrOutDto.builder()
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .productName(entity.getProduct().getProductName())
                .kilograms(entity.getKilograms())
                .price(entity.getPrice())
                .alisSatis(entity.getAlisSatis())
                .active(entity.isActive())
                .build();
    }
}