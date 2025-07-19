package com.backend.service;

import com.backend.config.Mapper;
import com.backend.dto.ProductInOrOutDto;
import com.backend.dto.ProductSaveResponse;
import com.backend.entity.Customer;
import com.backend.entity.Product;
import com.backend.entity.ProductInOrOut;
import com.backend.enums.AlisSatis;
import com.backend.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    @Transactional
    public ProductSaveResponse create(ProductInOrOutDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + dto.getProductId()));

        Customer customer = null;
        if (dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new EntityNotFoundException("Müşteri bulunamadı: " + dto.getCustomerId()));
        }

        ProductSaveResponse response = productCalculateLogic(customer, product, dto);
        if (response.isSuccess()) {
            ProductInOrOut productInOrOut = ProductInOrOut.builder()
                    .product(product)
                    .customer(customer)
                    .kilograms(dto.getKilograms())
                    .price(dto.getPrice())
                    .alisSatis(dto.getAlisSatis())
                    .paraTipi(dto.getParaTipi())
                    .odemeTip(dto.getOdemeTip())
                    .dovizKuru(dto.getDovizKuru())
                    .active(true)
                    .build();
            ProductInOrOut saved = productInOrOutRepository.save(productInOrOut);
        }
        return response;
    }

    @Transactional
    public ProductSaveResponse productCalculateLogic(Customer customer, Product product, ProductInOrOutDto dto) {
        if (dto.getAlisSatis() == AlisSatis.ALIS) {
            product.setTotalAmounth(product.getTotalAmounth() + dto.getKilograms());
            productRepository.save(product);
            return new ProductSaveResponse(true, "Alış İşlemi Başarılı");
        } else if (dto.getAlisSatis() == AlisSatis.SATIS) {
            if (customer == null) return new ProductSaveResponse(false, "Müşteri Verilerinde Hata Oluştu");
            if (product.getTotalAmounth() < dto.getKilograms()) return new ProductSaveResponse(false, "Yetersiz Ürün");

            product.setTotalAmounth(product.getTotalAmounth() - dto.getKilograms());
            productRepository.save(product);
            return new ProductSaveResponse(true, "Satış İşlemi Başarılı");
        }
        return new ProductSaveResponse(false, "Başarısız İşlem");
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

    @Transactional(readOnly = true)
    public List<ProductInOrOutDto> getByCustomerId(Long customerId) {
        return productInOrOutRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }


    public ProductInOrOutDto update(Long id, ProductInOrOutDto dto) {
        ProductInOrOut existingProductInOrOut = productInOrOutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kayıt bulunamadı: " + id));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Ürün bulunamadı: " + dto.getProductId()));

        Customer customer = null;
        if (dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new EntityNotFoundException("Müşteri bulunamadı: " + dto.getCustomerId()));
        }
        existingProductInOrOut.setProduct(product);
        existingProductInOrOut.setCustomer(customer);
        existingProductInOrOut.setKilograms(dto.getKilograms());
        existingProductInOrOut.setPrice(dto.getPrice());
        existingProductInOrOut.setAlisSatis(dto.getAlisSatis());
        existingProductInOrOut.setParaTipi(dto.getParaTipi());
        existingProductInOrOut.setDovizKuru(dto.getDovizKuru());
        existingProductInOrOut.setOdemeTip(dto.getOdemeTip());
        existingProductInOrOut.setActive(dto.isActive());

        ProductInOrOut saved = productInOrOutRepository.save(existingProductInOrOut);
        return convertToDto(saved);
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
                .paraTipi(entity.getParaTipi())
                .odemeTip(entity.getOdemeTip())
                .dovizKuru(entity.getDovizKuru())
                .alisSatis(entity.getAlisSatis())
                .active(entity.isActive())
                .build();
    }
}