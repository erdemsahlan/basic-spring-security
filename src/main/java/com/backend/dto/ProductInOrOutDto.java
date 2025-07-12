package com.backend.dto;

import com.backend.enums.AlisSatis;
import com.backend.enums.ParaTipi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInOrOutDto {
    private Long id;
    private Long productId;
    private String productName;
    private long kilograms;
    private long price;
    private AlisSatis alisSatis;
    private ParaTipi paraTipi;
    private boolean active;
    private double dovizKuru;
}