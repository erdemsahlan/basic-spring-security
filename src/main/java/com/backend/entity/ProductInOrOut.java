package com.backend.entity;

import com.backend.enums.AlisSatis;
import com.backend.enums.ParaTipi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductInOrOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    private long kilograms;
    private long price;
    @Enumerated(EnumType.ORDINAL)
    private AlisSatis alisSatis;
    @Enumerated(EnumType.ORDINAL)
    private ParaTipi paraTipi;
    private double dovizKuru;
    private boolean active=true;
}
