package com.backend.controller;

import com.backend.dto.ProductInOrOutDto;
import com.backend.service.ProductInOrOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-movements")
@RequiredArgsConstructor
public class ProductInOrOutController {

    private final ProductInOrOutService productInOrOutService;

    @PostMapping
    public ResponseEntity<ProductInOrOutDto> create(@RequestBody ProductInOrOutDto dto) {
        return new ResponseEntity<>(productInOrOutService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInOrOutDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productInOrOutService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductInOrOutDto>> getAll() {
        return ResponseEntity.ok(productInOrOutService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductInOrOutDto> update(
            @PathVariable Long id,
            @RequestBody ProductInOrOutDto dto) {
        return ResponseEntity.ok(productInOrOutService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productInOrOutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}